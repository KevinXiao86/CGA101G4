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
@WebServlet("/addRepCmp/selectRoomId")
public class selectRoomId extends HttpServlet {
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
		String str = req.getParameter("roomtypeId");
		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.put("repCmp", "請輸入房型編號");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/addOrder/seeItem.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}
		Integer roomtypeId = null;
		try {
			roomtypeId = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.put("repCmp", "房型編號格式不正確");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/addOrder/seeItem.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}
		System.out.println(roomtypeId);
		/*************************** 2.開始查詢資料 *****************************************/
//暫留區塊，目前不用查詢
		/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
		req.setAttribute("roomtypeId", roomtypeId); // 資料庫取出的list物件,存入req
		String url = "/front-end/addRepCmp/addRepCmp.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 addorder.jsp
		successView.forward(req, res);
	}

}
