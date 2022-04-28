package com.taiwan.controller.RoomOrder;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.RoomItemVO;
import com.taiwan.service.roomItem.impl.RoomItemServiceImpl;

/**
 * Servlet implementation class seeItemByOrder
 */
public class seeItemByOrder extends HttpServlet {
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
		System.out.println("訪問成功");
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		System.out.println("訪問成功");
		
		/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		String str = req.getParameter("order");
		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.put("order","請輸入訂單編號");
		}
		System.out.println("訪問成功");

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/addOrder/selectOrder.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		}
		System.out.println("訪問成功");

		Integer order = null;
		try {
			order = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.put("order","訂單編號格式不正確");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/addOrder/selectOrder.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		}
		System.out.println("訪問成功");
	
	/***************************2.開始查詢資料*****************************************/
	RoomItemServiceImpl roomItemServiceImpl=new RoomItemServiceImpl();
	
	System.out.println("訪問成功");

	List<RoomItemVO> list=roomItemServiceImpl.searchRoomItem(order);
	System.out.println("訪問成功");

	if (list == null) {
		errorMsgs.put("order","查無資料");
	}
	System.out.println("訪問成功");

	if (list.isEmpty() ) {
		errorMsgs.put("order","查無明細");
	}
	System.out.println("訪問成功");

	// Send the use back to the form, if there were errors
	if (!errorMsgs.isEmpty()) {
		RequestDispatcher failureView = req
				.getRequestDispatcher("/front-end/addOrder/selectOrder.jsp");
		failureView.forward(req, res);
		return;//程式中斷
	}
	System.out.println("訪問成功");
	/***************************3.查詢完成,準備轉交(Send the Success view)*************/
	req.setAttribute("list", list); // 資料庫取出的list物件,存入req
	String url = "/front-end/addOrder/seeItem.jsp";
	RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 addorder.jsp
	successView.forward(req, res);
	System.out.println("訪問成功");
	
	}
}
