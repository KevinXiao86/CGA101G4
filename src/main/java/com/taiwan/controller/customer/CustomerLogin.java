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
import com.taiwan.test.news.newsTest;
import com.taiwan.utils.MailQrCode11;

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
				String password = request.getParameter("password").trim();
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
					if (session.getAttribute("location") != null) {
						String location = (String) session.getAttribute("location");
						session.removeAttribute("location");
						response.sendRedirect(location);
						return;
					}
					RequestDispatcher successView = request.getRequestDispatcher("/front-end/homepage/index.jsp");
					successView.forward(request, response);
				}
			}

			if ("forgetPassword".equals(action)) {
				System.out.println("foget");
				// 取得要寄信的帳號
				String account = (request.getParameter("account"));
				// 到資料庫拿密碼
				CustomerServiceImpl dao = new CustomerServiceImpl();
				CustomerVO customerVO = dao.getPassword(account);
				String password = customerVO.getPassword();
				// 把回傳的會員物件存入request
				request.setAttribute("customer", customerVO);
				// 密碼等於空值代表帳號不存在，轉送回忘記密碼頁面
				if (password == null) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/custLogin/forgetPassword.jsp");
					failureView.forward(request, response);
					return;
				}
				// 到資料庫取得email
				String email = dao.getEmail(account);
				// 開始寄信
				MailQrCode11 mail = new MailQrCode11();
				mail.SendMail(email, "台玩 | 請確認您的密碼", "您的密碼是\r\n" + password,
						getServletContext().getRealPath("/static/img/ticket-img/logo.jpg"));
				// 回到會員登入頁面
				RequestDispatcher successView = request.getRequestDispatcher("/front-end/custLogin/CustomerLogin.jsp");
				successView.forward(request, response);
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
