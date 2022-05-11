package com.taiwan.controller.repCust12;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.RepCustVO;
import com.taiwan.service.impl.RepCustServiceImpl;

/**
 * Servlet implementation class doRepCust
 */
@WebServlet("/repCust12/DoRepCust")

public class DoRepCust extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getContextPath());
		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		String repCustIdST = req.getParameter("repCustId");
		System.out.println(repCustIdST);
		Integer repCustId=Integer.valueOf(repCustIdST);
		/***************************2.開始查詢資料*****************************************/
		RepCustServiceImpl repCustServiceImpl=new RepCustServiceImpl();
		RepCustVO repCustVO=repCustServiceImpl.searchRepCustById(repCustId);
		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
		req.setAttribute("repCustVO", repCustVO);
		String url = "/back-end/doRepCust12/updateRepCust.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
		successView.forward(req, res);
	}

}
