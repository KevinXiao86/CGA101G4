package com.taiwan.controller.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taiwan.beans.CustomerVO;
import com.taiwan.beans.FollowVO;
import com.taiwan.service.impl.FollowServiceImpl;

@WebServlet("/cust/ShowFollow")
public class ShowFollow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowFollow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//取得會員Id
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO) session.getAttribute("customer");
		Integer custId = customerVO.getCustId();
		System.out.println("custId="+custId);
		//到資料庫抓這個會員關注廠商的資料
		FollowServiceImpl followServiceImpl = new FollowServiceImpl();
		List<FollowVO> list = followServiceImpl.searchAllFollow(custId);
		System.out.println(list);
		//把關注廠商的資料存入request中，轉送前端
		request.setAttribute("list", list);
		RequestDispatcher successView = request.getRequestDispatcher("/front-end/cust/showFollow.jsp");
		successView.forward(request, response);
	}

}
