package com.taiwan.controller.ticket;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.CustomerVO;
import com.taiwan.beans.TicketVO;
import com.taiwan.beans.TktItem;
import com.taiwan.service.TicketService;
import com.taiwan.service.TktItemService;
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
			if (str == null | str.trim().length() == 0) {
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

			// 在TktItem中，取得評倫
			TktItemService tktItemSvc = new TktItemService();
			List<TktItem> tktItemList = tktItemSvc.getAllTktItemConTent(tktId);
			// 取得總人數
			Integer ttlPeople = tktItemSvc.getTktItemTtlPeople(tktId);
			tktVO.setTtlPeople(ttlPeople);
			// 取得平均分數
			Integer ttlScore = (tktItemSvc.getTktItemTllScore(tktId)) / ttlPeople;
			tktVO.setTtlScore(ttlScore);
			// 取會員姓名、頭貼
//			Integer custId = 
//			CustomerService custSvc = new CustomerServiceImpl();
//			CustomerVO customerVO = custSvc.getAll(custId);
			// 取TktImg所有圖片

			/********************** 3.查詢完成，設定參數，送出成功頁面 **********************/
			req.setAttribute("tktVO", tktVO);
			req.setAttribute("tktItemList", tktItemList);
			RequestDispatcher success = req.getRequestDispatcher("/front-end/ticket11/ticketpost.jsp");
			success.forward(req, res);
		}

	}

}
