package com.taiwan.controller.customerManage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.RepCmpVO;
import com.taiwan.service.impl.RepCmpService12Impl;

@WebServlet("/custManage/ShowRepCmpManage")
public class ShowRepCmpManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowRepCmpManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("我有進來ShowRepCmpManage");
		// 取得所有檢舉廠商的資料
		RepCmpService12Impl dao = new RepCmpService12Impl();
		List<RepCmpVO> list = dao.getAllRepCmp();
		request.setAttribute("list", list);
		// 轉送前端
		RequestDispatcher successView = request.getRequestDispatcher("/back-end/customer/repCmpManage.jsp");
		successView.forward(request, response);
	}

}
