package com.taiwan.customer.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.CustomerVO;
import com.taiwan.service.customer.impl.CustomerServiceImpl;

@WebServlet("/cust/SubmitCustomerInformation")
public class SubmitCustomerInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SubmitCustomerInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		String name = request.getParameter("name").trim();
		if (name == "") {
			errorMsgs.put("name", "此為必填欄位");
		}
		String sex = request.getParameter("sex");
		String tel = request.getParameter("tel").trim();
		if (!tel.matches("^[\\d-()]+$")) {
			errorMsgs.put("tel", "電話號碼格式有誤");
		}
		String email = request.getParameter("email").trim();
		if (!email.matches("^.+@.+$")) {
			errorMsgs.put("email", "電子郵件格式有誤");
		}
		String address = request.getParameter("address").trim();
		String idCard = request.getParameter("idCard").trim().toUpperCase();
		if (!idCard.matches("^[A-Z]\\d{9}$")) {
			errorMsgs.put("idCard", "身份證字號格式有誤");
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = request.getRequestDispatcher("/cust/UpdateCustomerInformation.jsp");
			failureView.forward(request, response);
			return;
		}
		Date birth = Date.valueOf(request.getParameter("birth"));
		String account = request.getParameter("account").trim();
		String password = request.getParameter("password").trim();
		String img = request.getParameter("img");
		String card = request.getParameter("card").trim();
		Integer custId = Integer.valueOf(request.getParameter("custId"));

		CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
		CustomerVO customerVO = customerServiceImpl.setUpdate(name, sex, tel, email, address, idCard, birth, account,
				password, img, card, custId);
		request.setAttribute("customer", customerVO);
		RequestDispatcher successView = request.getRequestDispatcher("/cust/CustomerInformation.jsp");
		successView.forward(request, response);
	}

}
