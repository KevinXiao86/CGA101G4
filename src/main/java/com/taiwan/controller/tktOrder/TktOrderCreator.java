package com.taiwan.controller.tktOrder;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tktOrder.creator")
public class TktOrderCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String str = req.getParameter("custId");
			if(str == null || str.trim().length() == 0) {
				errorMsgs.put("custId", "請輸入會員編號");
			}
			
			Integer custId = null;
			try {
				custId = Integer.valueOf(str);
			} catch (Exception e) {
					errorMsgs.put("custId", "會員編號格式不正確");
			}
			
			Integer originalPrice = null;
			try {
				originalPrice = Integer.valueOf(req.getParameter("originalPrice").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("originalPrice", "原始金額請填數字");
			}
			
			Timestamp orderdate = null;
			try {
				orderdate = Timestamp.valueOf(req.getParameter("orderdate").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("orderdate", "請輸入日期");
			}
			
			Integer ttlPrice = null;
			try {
				ttlPrice = Integer.valueOf(req.getParameter("ttlPrice").trim());
			} catch (Exception e) {
				errorMsgs.put("ttlPrice", "總金額請填數字");
			}
		}
	}

}
