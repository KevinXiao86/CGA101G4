package com.taiwan.controller.customerManage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taiwan.beans.EmployeeVO;
import com.taiwan.service.custPlatMail.impl.CustPlatMailServiceImpl;

@WebServlet("/custManage/SendCustPlatMail")
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
		// 取得想要寄送custPlatMail的會員Id
		Integer custId = Integer.valueOf(request.getParameter("custId").trim());
		// 取得管理員Id
		HttpSession session = request.getSession();
		EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("emp");
		Integer empId = employeeVO.getEmpId();
		// 取得訊息內容
		String msg = request.getParameter("msg");
		// 把資料送到資料庫
		CustPlatMailServiceImpl dao = new CustPlatMailServiceImpl();
		dao.setCust_Plat_Mail(custId, empId, msg, empId);
		// 轉送前端
		RequestDispatcher successView = request.getRequestDispatcher("/back-end/customer/custPlatMailManage.jsp");
		successView.forward(request, response);
	}

}
