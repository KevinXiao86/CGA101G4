package com.taiwan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.Company;
import com.taiwan.service.CompanyService;
import com.taiwan.service.impl.CompanyServiceImpl;

@WebServlet("/CompanyServlet")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private CompanyService companyService = new CompanyServiceImpl();
	
    public CompanyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 獲取請求參數
		String account = request.getParameter("cmpAccount");
		String password = request.getParameter("cmpPassword");
		//2. 調用 service 的業務方法
		Company company = companyService.getCompany(account, password);
		//3. 
		request.setAttribute("company", company);
		request.getRequestDispatcher("/test2.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
