package com.taiwan.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taiwan.beans.EmployeeVO;



/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(
  dispatcherTypes = {
    DispatcherType.REQUEST, 
    DispatcherType.FORWARD, 
    DispatcherType.INCLUDE, 
    DispatcherType.ERROR
  }
     , 
  urlPatterns = { 
   "/emp/*",
   
  })
public class EmpFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public EmpFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

 /**
  * @see Filter#destroy()
  */
 public void destroy() {
  // TODO Auto-generated method stub
 }

 /**
  * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
  */
 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
  // TODO Auto-generated method stub
  // place your code here

  HttpServletRequest req = (HttpServletRequest) request;
  HttpServletResponse res = (HttpServletResponse) response;
  // 【取得 session】
  HttpSession session = req.getSession();
  // 【從 session 判斷此user是否登入過】
  EmployeeVO employeeVO =(EmployeeVO) session.getAttribute("employeeVO");
  System.out.println(employeeVO);
  if (employeeVO == null) {
//   session.setAttribute("location", req.getRequestURI());
   session.setAttribute("location", req.getRequestURI());
   res.sendRedirect(req.getContextPath() + "/login/login.jsp");
  
   return;
  } else {
   chain.doFilter(request, response);
  }
  
  
  
  
  // pass the request along the filter chain
 
 }

 /**
  * @see Filter#init(FilterConfig)
  */
 public void init(FilterConfig fConfig) throws ServletException {
  // TODO Auto-generated method stub
 }

}
