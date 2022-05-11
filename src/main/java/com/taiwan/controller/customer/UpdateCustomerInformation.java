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
import com.taiwan.service.customer.impl.CustomerServiceImpl;
import com.taiwan.test.news.newsTest;

@WebServlet("/cust/UpdateCustomerInformation")
public class UpdateCustomerInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateCustomerInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 從session中取得customerVO
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO) session.getAttribute("customer");
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
		// 把請求參數串成字串，接到要轉送的網址後面，再轉送前端
		String param = "?name=" + customerVO.getName() + "&sex=" + customerVO.getSex() + "&tel=" + customerVO.getTel()
				+ "&email=" + customerVO.getEmail() + "&address=" + address + "&idCard=" + customerVO.getIdCard()
				+ "&birth=" + customerVO.getBirth() + "&account=" + customerVO.getAccount() + "&password="
				+ customerVO.getPassword() + "&imgOrigin=" + img + "&card=" + card + "&custId="
				+ customerVO.getCustId();
		String url = "/front-end/cust/UpdateCustomerInformation.jsp" + param;
		request.setAttribute("customer", customerVO);
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);
	}

}
