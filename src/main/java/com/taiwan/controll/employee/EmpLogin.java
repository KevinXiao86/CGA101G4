package com.taiwan.controll.employee;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.taiwan.beans.EmployeeVO;
import com.taiwan.service.employee.EmployeeService;

@WebServlet("/emplogin")

public class EmpLogin extends HttpServlet {

	public EmpLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("1");
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");

		try {
			if ("justLogin".equals(action)) {
				Integer empId = Integer.valueOf(req.getParameter("empId"));
				String empPassword = req.getParameter("empPassword");
				EmployeeService employeeService = new EmployeeService();
				EmployeeVO employeeVO = employeeService.login(empId, empPassword);
				req.setAttribute("emp", employeeVO);


				if (!employeeVO.isSuccessful()) {
					employeeVO=new EmployeeVO();
					employeeVO.setUrl("/back-end/emp/login/empLogin.jsp");
					RequestDispatcher failureView = req.getRequestDispatcher(employeeVO.getUrl());
					failureView.forward(req, res);

				} else {
					
					HttpSession session = req.getSession();
					session.setAttribute("emp", employeeVO);
					String param = "empName=" + employeeVO.getEmpName() ;
					String url = "/back-end/emp/login/login-back-end-index.jsp" + param;
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				
				}
			}

			if ("forgetPassword".equals(action)) {
				System.out.println("foget");
			}
		} catch (Exception e) {
			EmployeeVO employeeVO = new EmployeeVO();
			employeeVO.setMessage("系統錯誤");
			req.setAttribute("emp", employeeVO);
			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/login/empLogin.jsp");
			failureView.forward(req, res);
		}
	}
}

	


	
	
	
	
	
	
	
	
	
	

//	========================================================================
//	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
//	}
//
//	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		
//		req.setCharacterEncoding("UTF-8");
//		System.out.println(23);
//		
//		Integer empId = Integer.valueOf(req.getParameter("empId"));
//		String empPassword = req.getParameter("empPassword");
//
////		List<User> list = Db.getAll();
//		EmployeeService employeeService = new EmployeeService();
//		EmployeeVO employeeVO = employeeService.login(empId, empPassword);
////		List<EmployeeVO> emp=employeeService.getAll();
//
//		if (employeeVO.getEmpId() == (empId) && employeeVO.getEmpPassword().equals(empPassword)) {
//			req.getSession().setAttribute("employeeVO", employeeVO);
//			res.sendRedirect("/back-end/emp/back-end-index.jsp");
//			return;
//
//		} else {
//			res.sendRedirect("/back-end/emp/login/empLogin.jsp");
//		}
//	}

	

