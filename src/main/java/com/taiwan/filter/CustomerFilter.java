package com.taiwan.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taiwan.beans.CustomerVO;

public class CustomerFilter implements Filter {

	public CustomerFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 先把request跟response轉型成HttpServletRequest
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 取出session看裡面有沒有customer這個屬性
		HttpSession session = req.getSession();
		CustomerVO customerVO = (CustomerVO) session.getAttribute("customer");
		if (customerVO == null) {
			req.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/front-end/custLogin/CustomerLogin.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

}
