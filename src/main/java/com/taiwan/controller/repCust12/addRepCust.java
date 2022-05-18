package com.taiwan.controller.repCust12;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.service.customer.impl.CustomerServiceImpl;
import com.taiwan.service.impl.RepCustServiceImpl;

@WebServlet("/addRepCmp/addRepCust")
public class addRepCust extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getContextPath());
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		
		int cmpId=Integer.valueOf(req.getParameter("cmpId"));
		String str = req.getParameter("reason");
		String custIdString=req.getParameter("custId");

System.out.println(str);
		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.put("reason", "請輸入檢舉原因");
		}
		if (custIdString == null || (custIdString.trim()).length() == 0) {
			errorMsgs.put("custId", "請輸入會員編號");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/repCust12/addRepCust.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}
	
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/repCust12/addRepCust.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}
		String reason=str;
		int custId=Integer.valueOf(custIdString);
		CustomerServiceImpl customerServiceImpl=new CustomerServiceImpl();
		if (customerServiceImpl.getAll(custId)==null) {
			errorMsgs.put("custId", "會員編號不存在");
		} 
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/repCust12/addRepCust.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}
		/*************************** 2.開始新增資料 *****************************************/
	RepCustServiceImpl repCustServiceImpl=new RepCustServiceImpl();
	repCustServiceImpl.addRepCust(custId, cmpId, reason);
		
		/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
		String url = "/front-end/repCust12/seeRepCust.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 
		successView.forward(req, res);
	}

}
