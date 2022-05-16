package com.taiwan.controller.addRepCmp;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.service.impl.RepCmpService12Impl;

/**
 * Servlet implementation class addRepCmp
 */
@WebServlet("/addRepCmp/addRepCmp")
public class addRepCmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getContextPath());
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		
		int custId=Integer.valueOf(req.getParameter("custId"));
		int roomId=Integer.valueOf(req.getParameter("roomtypeId"));
		System.out.println(custId);
		System.out.println(roomId);
		String str = req.getParameter("reason");
System.out.println(str);
		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.put("reason", "請輸入檢舉原因");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/addOrder/seeItem.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/addOrder/seeItem.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}
		String reason=str;
		/*************************** 2.開始新增資料 *****************************************/
		RepCmpService12Impl repCmpService12Impl=new RepCmpService12Impl();
		repCmpService12Impl.addRepCmp(custId, roomId, reason);
		
		/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
		String url = "/front-end/addOrder/seeItem.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 
		successView.forward(req, res);
	}

}
