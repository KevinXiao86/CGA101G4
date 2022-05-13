package com.taiwan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.taiwan.beans.Company;

public class CompanyFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		System.out.println("訪問成功");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		HttpSession session = httpServletRequest.getSession();
		
		Company loginCompany = (Company) session.getAttribute("loginCompany");
		
		if (loginCompany == null) {
			System.out.println("沒有登入");
			request.getRequestDispatcher("/cmp_login/login.jsp").forward(request, response);
		} else {
			filterChain.doFilter(request, response);
		}
	}
}
