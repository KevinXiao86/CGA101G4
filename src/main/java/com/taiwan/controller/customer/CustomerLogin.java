package com.taiwan.controller.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.CustomerVO;
import com.taiwan.service.customer.CustomerService;
import com.taiwan.service.customer.impl.CustomerServiceImpl;

@WebServlet("/cust/CustomerLogin")
public class CustomerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("1");
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");

		try {
			if ("justLogin".equals(action)) {
				String account = request.getParameter("account");
				String password = request.getParameter("password");
				CustomerService customerService = new CustomerServiceImpl();
				CustomerVO customerVO = customerService.getLogin(account, password);
				request.setAttribute("customer", customerVO);

				if (!customerVO.isSuccessful()) {
					RequestDispatcher failureView = request.getRequestDispatcher(customerVO.getUrl());
					failureView.forward(request, response);
					
				} else {
					RequestDispatcher successView = request.getRequestDispatcher("/front-end/cust/CustomerIndex.jsp");
					successView.forward(request, response);
				}
			}

			if ("forgetPassword".equals(action)) {
				System.out.println("foget");
			}
		} catch (Exception e) {
			CustomerVO customerVO = new CustomerVO();
			customerVO.setMessage("系統錯誤");
			request.setAttribute("customer", customerVO);
			RequestDispatcher failureView = request.getRequestDispatcher("/front-end/cust/CustomerLogin.jsp");
			failureView.forward(request, response);
		}
	}

}
