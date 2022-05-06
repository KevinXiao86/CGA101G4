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
import com.taiwan.test.news.newsTest;

@WebServlet("/cust/DeleteRepCmp")
public class DeleteRepCmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteRepCmp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 抓到要刪除的檢舉流水號(repCmpId)
		Integer repCmpId = Integer.valueOf(request.getParameter("repCmpId"));
		// 到資料庫刪除這筆資料
		RepCmpServiceImpl repCmpServiceImpl = new RepCmpServiceImpl();
		repCmpServiceImpl.deleteRepCmp(repCmpId);
		// 抓到要顯示的會員編號(custId)
		Integer custId = Integer.valueOf(request.getParameter("custId"));
		// 到資料庫抓這個會員所有的檢舉廠商資料
		List<RepCmpVO> list = repCmpServiceImpl.getRepCmpByCustId(custId);
		request.setAttribute("list", list);
		// 重新轉送回頁面
		RequestDispatcher successView = request.getRequestDispatcher("/front-end/cust/showRepCmp.jsp");
		successView.forward(request, response);
	}

}
