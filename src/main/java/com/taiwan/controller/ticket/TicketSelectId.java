package com.taiwan.controller.ticket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.CouponVO;
import com.taiwan.beans.CustCoupon;
import com.taiwan.beans.CustomerVO;
import com.taiwan.beans.TicketVO;
import com.taiwan.beans.TktImgVO;
import com.taiwan.beans.TktItem;
import com.taiwan.beans.TktOrder;
import com.taiwan.service.TicketService;
import com.taiwan.service.TktImgService;
import com.taiwan.service.TktItemService;
import com.taiwan.service.TktOrderService;
import com.taiwan.service.coupon.CouponService;
import com.taiwan.service.coupon.impl.CouponServiceImpl;
import com.taiwan.service.customer.CustCouponService;
import com.taiwan.service.customer.CustomerService;
import com.taiwan.service.customer.impl.CustomerServiceImpl;
import com.taiwan.service.impl.TicketServiceImpl;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/ticket/selectId")
public class TicketSelectId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TicketService ticketService = ControllerUtil.getBean(TicketService.class);

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			/**************************** 設置錯誤顯示訊息 ****************************/
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/********************* 1.接收請求參數 - 輸入格式的錯誤處理 ********************/
			String str = req.getParameter("tktId");
			if (str == null || str.trim().length() == 0) {
				errorMsgs.put("tktId", "請輸入票券編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failed = req.getRequestDispatcher("/front-end/ticket11/ticketIndex.jsp"); // 連結到票券瀏覽頁面
				failed.forward(req, res);
				return;
			}

			Integer tktId = null;
			try {
				tktId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("tktId", "票券編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failed = req.getRequestDispatcher("/front-end/ticket11/ticketIndex.jsp"); // 連結到票券瀏覽頁面
				failed.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢參數 *****************************/
			TicketVO tktVO = ticketService.findById(tktId);
			if (tktVO == null) {
				errorMsgs.put("tktId", "查無資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failed = req.getRequestDispatcher("/front-end/ticket11/ticketIndex.jsp"); // 連結到票券瀏覽頁面
				failed.forward(req, res);
				return;
			}
			
			/******************************* 評論相關 *****************************/
			// 在TktItem中，取得評倫
			TktItemService tktItemSvc = new TktItemService();
			List<TktItem> tktItemList = tktItemSvc.getAllTktItemConTent(tktId);
			// 取得有評分的總人數
			Integer ttlPeople = tktItemSvc.getTktItemTtlPeople(tktId);
			tktVO.setTtlPeople(ttlPeople);
			// 取得平均分數
			if(ttlPeople != 0) {
				Integer ttlScore = (tktItemSvc.getTktItemTllScore(tktId)) / ttlPeople;
				tktVO.setTtlScore(ttlScore);				
			}
			
			// 先取得訂單編號，拿到會員編號後再取得姓名及頭貼
			List<TktItem> tktItemGetOrder = tktItemSvc.getTktItemByTktId(tktId);
			//取得訂單編號
			List<Integer> orderIds = new ArrayList<Integer>();
			for(int i = 0; i < tktItemGetOrder.size(); i++) {
				Integer tktOrderId = tktItemGetOrder.get(i).getTktOrderId();
				orderIds.add(tktOrderId);
			}
			//根據訂單編號，取得集合物件
			TktOrderService tktOrderService = new TktOrderService();
			//取得訂單日期
			List<TktOrder> tktOrderList = new ArrayList<TktOrder>();
			//取得會員編號
			List<Integer> customerId = new ArrayList<Integer>();
			for(Integer orderId : orderIds) {
				TktOrder tktOrder = tktOrderService.getTktOrderByTktOrderId(orderId);
				tktOrderList.add(tktOrder);
				customerId.add(tktOrder.getCustId());
			}
			
//tktIteGetOrder = [TktItem [tktId=4, tktOrderId=6, amount=2, used=2, score=5, content=很好玩], TktItem [tktId=4, tktOrderId=8, amount=3, used=3, score=4, content=帶小朋友出門的好選擇!適合放鬆心情，紓壓，很喜歡], TktItem [tktId=4, tktOrderId=15, amount=13, used=0, score=0, content=null], TktItem [tktId=4, tktOrderId=16, amount=1, used=0, score=0, content=null], TktItem [tktId=4, tktOrderId=17, amount=2, used=0, score=0, content=null]]
//orderIds = [6, 8, 15, 16, 17]
//tktOrderList = [TktOrder [tktOrderId=6, custId=10016, originalPrice=500, orderdate=2022-04-09 10:54:45.0, ttlPrice=500, custCopId=0, qrcode=tibame.com.tw, orderName=kevin, orderEmail=kkkoo@gmail.com, orderMobile=0987462871], TktOrder [tktOrderId=8, custId=10016, originalPrice=1140, orderdate=2022-04-11 13:32:07.0, ttlPrice=1140, custCopId=0, qrcode=google.com.tw, orderName=amy, orderEmail=kkkoo@gmail.com, orderMobile=0987462871], TktOrder [tktOrderId=15, custId=10000, originalPrice=4940, orderdate=2022-05-06 20:45:45.0, ttlPrice=4940, custCopId=0, qrcode=, orderName=jack, orderEmail=e@d, orderMobile=0924024933], TktOrder [tktOrderId=16, custId=10000, originalPrice=630, orderdate=2022-05-07 14:05:02.0, ttlPrice=630, custCopId=0, qrcode=, orderName=jack, orderEmail=e@d, orderMobile=0924024933], TktOrder [tktOrderId=17, custId=10000, originalPrice=3220, orderdate=2022-05-09 10:39:05.0, ttlPrice=3220, custCopId=0, qrcode=, orderName=jack, orderEmail=e@d, orderMobile=0924024933]]
//customerId = [10016, 10016, 10000, 10000, 10000]
			
			//根據會員編號，取得會員集合物件
			CustomerService custSvc = new CustomerServiceImpl();
			List<CustomerVO> customerList = new ArrayList<CustomerVO>();
			for(Integer custId : customerId) {
				CustomerVO customerVO = custSvc.getAll(custId);
				customerList.add(customerVO);
			}
//customerList = [CustomerVO [custId=10016, name=kevin, sex=f, tel=09123456, email=kevin@gmail, address=新竹, idCard=J123455, birth=2022-04-07, account=kevinAccount, password=kevinpwd, img=xxx/xxx, custUse=未啟動, card=11111, custRight=正常], CustomerVO [custId=10016, name=kevin, sex=f, tel=09123456, email=kevin@gmail, address=新竹, idCard=J123455, birth=2022-04-07, account=kevinAccount, password=kevinpwd, img=xxx/xxx, custUse=未啟動, card=11111, custRight=正常], CustomerVO [custId=10000, name=jack, sex=f, tel=09240249, email=e@d, address=桃園市中壢區復興里中大路二段194巷20弄10號, idCard=Ksodijf, birth=2002-02-04, account=soid, password=sjf, img=idsjf, custUse=啟動, card=000000, custRight=正常], CustomerVO [custId=10000, name=jack, sex=f, tel=09240249, email=e@d, address=桃園市中壢區復興里中大路二段194巷20弄10號, idCard=Ksodijf, birth=2002-02-04, account=soid, password=sjf, img=idsjf, custUse=啟動, card=000000, custRight=正常], CustomerVO [custId=10000, name=jack, sex=f, tel=09240249, email=e@d, address=桃園市中壢區復興里中大路二段194巷20弄10號, idCard=Ksodijf, birth=2002-02-04, account=soid, password=sjf, img=idsjf, custUse=啟動, card=000000, custRight=正常]]
			
			
			/******************************* 圖片相關 ********************************/
			// 取TktImg所有圖片
			TktImgService imgSvc = new TktImgService();
			//商品圖
			List<TktImgVO> imgList = imgSvc.getByTktId(tktId);
			
			//不能這樣寫，如果尚未新增圖片會跳出IndexOutOfBound Exception~
			//首圖
//			TktImgVO tktImgVO = imgList.get(0);
			
			
			/********************** 3.查詢完成，設定參數，送出成功頁面 **********************/
			req.setAttribute("tktVO", tktVO);
			req.setAttribute("tktItemList", tktItemList);
			req.setAttribute("imgList", imgList);
			req.setAttribute("tktOrderList", tktOrderList);
			req.setAttribute("customerList", customerList);
			RequestDispatcher success = req.getRequestDispatcher("/front-end/ticket11/ticketpost.jsp");
			success.forward(req, res);
		}

	}

}
