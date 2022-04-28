package com.taiwan.controller.tktOrder;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.TktOrder;
import com.taiwan.service.TktOrderService;

@WebServlet("/tktOrder/getAll")
public class TktOrderGetAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}	

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		/*************************** 1.查詢參數 *****************************/
		TktOrderService tktOrerSvc = new TktOrderService();
		List<TktOrder> list = tktOrerSvc.getAll();
		
		/********************* 2.查詢完成，設定參數，送出成功頁面 **********************/
		req.setAttribute("list", list);
		RequestDispatcher success = req.getRequestDispatcher("/front-end/tktOrder/listAllTktOrder.jsp");
		success.forward(req, res);
	}

}
