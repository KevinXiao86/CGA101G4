package com.taiwan.controller.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.RepCmpVO;
import com.taiwan.service.repCmp.impl.RepCmpServiceImpl;

@WebServlet("/cust/showRepCmp")
public class showRepCmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public showRepCmp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Integer custId = Integer.valueOf(request.getParameter("customer"));System.out.println("custId="+custId);
		// 去資料庫抓這個會員的所有檢舉廠商資料
		RepCmpServiceImpl repCmpServiceImpl = new RepCmpServiceImpl();
		List<RepCmpVO> list = repCmpServiceImpl.getRepCmpByCustId(custId);System.out.println(list);
		request.setAttribute("list", list);
		RequestDispatcher successView = request.getRequestDispatcher("/front-end/cust/showRepCmp.jsp");
		successView.forward(request, response);
	}

}
