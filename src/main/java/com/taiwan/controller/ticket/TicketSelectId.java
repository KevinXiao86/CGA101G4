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
				RequestDispatcher failed = req.getRequestDispatcher("/front-end/ticket/ticketList.jsp");
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
				RequestDispatcher failed = req.getRequestDispatcher("/front-end/ticket/ticketList.jsp"); 
				failed.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢參數 *****************************/
			TicketVO tktVO = ticketService.findById(tktId);
			if (tktVO == null) {
				errorMsgs.put("tktId", "查無資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failed = req.getRequestDispatcher("/front-end/ticket/ticketList.jsp"); 
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
			
			//根據會員編號，取得會員集合物件
			CustomerService custSvc = new CustomerServiceImpl();
			List<CustomerVO> customerList = new ArrayList<CustomerVO>();
			for(Integer custId : customerId) {
				CustomerVO customerVO = custSvc.getAll(custId);
				customerList.add(customerVO);
			}
			
			/******************************* 圖片相關 ********************************/
			// 取TktImg所有圖片
			TktImgService imgSvc = new TktImgService();
			//商品圖
			List<TktImgVO> imgList = imgSvc.getByTktId(tktId);
			
			//首圖不能這樣寫，如果尚未新增圖片會跳出IndexOutOfBound Exception~
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
