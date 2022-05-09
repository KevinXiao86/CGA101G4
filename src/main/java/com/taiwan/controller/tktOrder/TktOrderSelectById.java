package com.taiwan.controller.tktOrder;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.CustomerVO;
import com.taiwan.beans.TktOrder;
import com.taiwan.service.TktOrderService;
import com.taiwan.service.customer.CustomerService;
import com.taiwan.service.customer.impl.CustomerServiceImpl;

@WebServlet("/tktOrder/selectById")
public class TktOrderSelectById extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) {   
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*******************1.接收請求參數，輸入格式的錯誤處理*******************/
			String str = req.getParameter("tktOrderId");
			if(str == null || (str.trim()).length() == 0) {
				errorMsgs.put("tktOrderId", "請輸入訂單編號");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tktOrder/tktOrderIndex.jsp");
				failureView.forward(req, res);
				return;
			}
			
			Integer tktOrderId = null;
			try {
				tktOrderId = Integer.valueOf(str);
			}catch (Exception e) {
				errorMsgs.put("tktOrderId", "訂單編號格式不正確");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tktOrder/tktOrderIndex.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/**************************2.開始查詢資料**************************/
			TktOrderService tktOrderService = new TktOrderService();
			TktOrder tktOrder = tktOrderService.getTktOrderByTktOrderId(tktOrderId);
//			System.out.println(tktOrderId);
//			System.out.println(tktOrder);
			if (tktOrder == null) {
				errorMsgs.put("tktOrderId", "查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tktOrder/tktOrderIndex.jsp");
				failureView.forward(req, res);
				return;
			}
			
	
			//加入會員名字
			Integer custId = tktOrder.getCustId();
			CustomerService custSvc = new CustomerServiceImpl();
			CustomerVO customerVO = custSvc.getAll(custId);
			
			
//			System.out.println(custId);
//			System.out.println(customerVO);
			
			/********************3.查詢完成，設定參數，送出成功頁面********************/
			req.setAttribute("tktOrder", tktOrder);
			req.setAttribute("customerVO", customerVO);
			RequestDispatcher success = req.getRequestDispatcher("/front-end/tktOrder/listOneTktOrder.jsp");
			success.forward(req, res);
			
		}
	}

}
