package com.taiwan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.taiwan.beans.EmployeeVO;

public class EmpFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		System.out.println("訪問成功");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		HttpSession session = httpServletRequest.getSession();
		
		EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("employeeVO");
		
		if (employeeVO == null) {
			System.out.println("沒有登入");
			request.getRequestDispatcher("/back-login/login/empLogin.jsp").forward(request, response);
		} else {
			filterChain.doFilter(request, response);
		}
	}
}
