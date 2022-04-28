package com.taiwan.controller.tktOrder;

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

import com.taiwan.beans.TktOrder;
import com.taiwan.service.TktOrderService;

@WebServlet("/tktOrder/getCustId")
public class TktOrderGetCustId extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("get_order_cust".equals(action)) {
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數***************************/
			String str = req.getParameter("custId");
			if(str == null || str.trim().length() == 0) {
				errorMsgs.put("custId", "請輸入會員編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failed = req.getRequestDispatcher("/front-end/tktOrder/tktOrderIndex.jsp"); 
				failed.forward(req, res);
				return;
			}
			
			Integer custId = null;
			try {
				custId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("custId", "會員編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failed = req.getRequestDispatcher("/front-end/tktOrder/tktOrderIndex.jsp"); // 連結到訂單瀏覽頁面
				failed.forward(req, res);
				return;
			}
			
			/*************************** 2.開始查詢參數 *****************************/
			TktOrderService tktOrderService = new TktOrderService();
			List<TktOrder> list = tktOrderService.getTktOrderByCustId(custId);
			if(list == null || list.size() == 0) {
				errorMsgs.put("custId", "查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failed = req.getRequestDispatcher("/front-end/tktOrder/tktOrderIndex.jsp"); // 連結到訂單瀏覽頁面
				failed.forward(req, res);
				return;
			}
			
			/********************** 3.查詢完成，設定參數，送出成功頁面 **********************/
			req.setAttribute("list", list);
			RequestDispatcher success = req.getRequestDispatcher("/front-end/tktOrder/listAllTktOrder.jsp");
			success.forward(req, res);
		}
	}

}
