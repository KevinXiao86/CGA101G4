package com.taiwan.controller.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taiwan.beans.CustomerVO;
import com.taiwan.service.customer.CustomerService;
import com.taiwan.service.customer.impl.CustomerServiceImpl;

@WebServlet("/cust/CustomerInformation")
public class CustomerInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("customerInformation");
		Integer custId = Integer.valueOf(request.getParameter("customer"));
		CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
		CustomerVO customerVO = customerServiceImpl.getAll(custId);
		request.setAttribute("customer", customerVO);
		RequestDispatcher successView = request.getRequestDispatcher("/front-end/cust/CustomerInformation.jsp");
		successView.forward(request, response);
	}

}
