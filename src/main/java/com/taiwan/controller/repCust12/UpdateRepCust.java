package com.taiwan.controller.repCust12;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.service.impl.RepCustServiceImpl;

/**
 * Servlet implementation class UpdateRepCust
 */
@WebServlet("/repCust12/UpdateRepCust")
public class UpdateRepCust extends HttpServlet {
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
		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		String repCustIdST = req.getParameter("repCustId");
		System.out.println(repCustIdST);
		Integer repCustId=Integer.valueOf(repCustIdST);
		String empIdST = req.getParameter("empId");
		System.out.println(empIdST);
		Integer empId=Integer.valueOf(empIdST);
		String repCustStatus=req.getParameter("repCustStatus");
		String repCustResult=req.getParameter("repCustResult");
		/***************************2.開始變更資料*****************************************/
		RepCustServiceImpl repCustServiceImpl=new RepCustServiceImpl();
		repCustServiceImpl.doRepCust(repCustId, empId, repCustStatus, repCustResult);
		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
		String url = "/back-end/doRepCust12/doRepCust.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
		successView.forward(req, res);
	}

}
