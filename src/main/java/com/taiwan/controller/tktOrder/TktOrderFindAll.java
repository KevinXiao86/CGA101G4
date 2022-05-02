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

@WebServlet("/tktOrder/findAll")
public class TktOrderFindAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//開始查詢
		TktOrderService tktOrerSvc = new TktOrderService();
		List<TktOrder> list = tktOrerSvc.getAll();
		
		/********************* 2.查詢完成，設定參數，送出成功頁面 **********************/
		request.setAttribute("list", list);
		RequestDispatcher success = request.getRequestDispatcher("/back-end/tktOrder/tktOrder_findAll.jsp");
		success.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
