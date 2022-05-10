package com.taiwan.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taiwan.beans.Company;

public class CompanyInterceptor implements HandlerInterceptor{

	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Company loginCompany = (Company) request.getSession().getAttribute("loginCompany");
		
		if (loginCompany == null) {
			System.out.println("沒有登入");
			//說明未登入
			request.getRequestDispatcher("/cmp_login/login.jsp").forward(request, response);
			return false;
		}else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
