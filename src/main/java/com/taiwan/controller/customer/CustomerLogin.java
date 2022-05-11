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
			if ("empLogin".equals(action)) {
				String account = request.getParameter("account");
				String password = request.getParameter("password");
				CustomerService customerService = new CustomerServiceImpl();
				CustomerVO customerVO = customerService.getLogin(account, password);
				request.setAttribute("customer", customerVO);

				if (!customerVO.isSuccessful()) {
					RequestDispatcher failureView = request.getRequestDispatcher(customerVO.getUrl());
					failureView.forward(request, response);

				} else {
					// 把customerVO存入session
					HttpSession session = request.getSession();
					session.setAttribute("customer", customerVO);

					// 把可能是null的欄位處理一下，避免在修改資料的表單input裡直接顯示null
					String address = "";
					if (customerVO.getAddress() != null) {
						address = customerVO.getAddress();
					}
					String img = "";
					if (customerVO.getImg() != null) {
						img = customerVO.getImg();
					}
					String card = "";
					if (customerVO.getCard() != null) {
						card = customerVO.getCard();
					}
					// 把請求參數接在網址後面，轉送到前端，修改基本資料表單要用
					String param = "?name=" + customerVO.getName() + "&sex=" + customerVO.getSex() + "&tel="
							+ customerVO.getTel() + "&email=" + customerVO.getEmail() + "&address=" + address
							+ "&idCard=" + customerVO.getIdCard() + "&birth=" + customerVO.getBirth() + "&account="
							+ customerVO.getAccount() + "&password=" + customerVO.getPassword() + "&imgOrigin=" + img
							+ "&card=" + card + "&custId=" + customerVO.getCustId();
					RequestDispatcher successView = request
							.getRequestDispatcher("/front-end/cust/CustomerInformation.jsp" + param);
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
