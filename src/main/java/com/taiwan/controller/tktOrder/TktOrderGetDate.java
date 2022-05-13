package com.taiwan.controller.tktOrder;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taiwan.beans.CustomerVO;
import com.taiwan.beans.TktOrder;
import com.taiwan.service.TktOrderService;
import com.taiwan.service.customer.impl.CustomerServiceImpl;


@WebServlet("/tktOrder/getDate")
public class TktOrderGetDate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		HttpSession session = req.getSession();
		CustomerVO customerVO = (CustomerVO) session.getAttribute("customer");
//		CustomerVO customerVO = new CustomerVO();
		String custId = customerVO.getCustId().toString();
//		String custId = "10000";
		
		if ("get_date".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/******************* 1.接收請求參數，輸入格式的錯誤處理 *******************/
			//以字串取得日期區間，原本型態為Date
			String start = req.getParameter("startdate");
			String end = req.getParameter("enddate");	
			//因要使用的service方法為TimeStamp類型，所以加上時間
			Timestamp startdate = Timestamp.valueOf(start+" 00:00:00");
			Timestamp enddate = Timestamp.valueOf(end+" 23:59:59");
			
			/************************** 2.開始查詢資料 **************************/
			TktOrderService tktOrderService = new TktOrderService();
			List<TktOrder> orderList = tktOrderService.getTktOrderByDateCustID(startdate, enddate, Integer.valueOf(custId));
			if (orderList == null || orderList.size() == 0) {
				errorMsgs.put("date", "查無資料");
			} 
			//查詢訂單有幾筆  bug
//			int orderListSize = orderList.size();

			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tktOrder/listAllTktOrder.jsp");
				failureView.forward(req, res);
				return;
			}
			
			//取得會員姓名
			CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
			customerVO = customerServiceImpl.getAll(Integer.valueOf(custId));  
 
			/******************** 3.查詢完成，設定參數，送出成功頁面 ********************/
			req.setAttribute("orderList", orderList);
			req.setAttribute("customerVO", customerVO);
//			req.setAttribute("orderListSize", orderListSize);
			RequestDispatcher success = req.getRequestDispatcher("/front-end/tktOrder/listDateTktOrder.jsp");
			success.forward(req, res);

		}
	}

}
