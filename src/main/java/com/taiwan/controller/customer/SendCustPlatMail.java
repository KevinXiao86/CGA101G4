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

import com.taiwan.beans.CustPlatMailVO;
import com.taiwan.beans.CustomerVO;
import com.taiwan.service.custPlatMail.impl.CustPlatMailServiceImpl;

@WebServlet("/cust/SendCustPlatMail")
public class SendCustPlatMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SendCustPlatMail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取得前端會員寫的信件訊息
		String msg = request.getParameter("msg");
		// 拿到會員id
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO) session.getAttribute("customer");
		Integer custId = customerVO.getCustId();
		// 送到資料庫儲存
		CustPlatMailServiceImpl dao = new CustPlatMailServiceImpl();
		dao.setCust_Plat_Mail(custId, null, msg, custId);
		// 再次取出資料，存入request的Attribute裡，轉送到前端
		List<CustPlatMailVO> list = dao.getAllByCustId(custId);
		request.setAttribute("list", list);
		RequestDispatcher successView = request.getRequestDispatcher("/front-end/cust/showEmail.jsp");
		successView.forward(request, response);
	}

}
