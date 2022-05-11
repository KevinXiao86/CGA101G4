package com.taiwan.controller.getCoupon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.CouponVO;
import com.taiwan.beans.EmployeeVO;
import com.taiwan.service.coupon.CouponService;
import com.taiwan.service.employee.EmployeeService;
import com.taiwan.service.getCoupon.GetCouponService;
import com.taiwan.utils.ControllerUtil;
@WebServlet("/getCouponFindAll")
public class GetCouponFindAll extends HttpServlet {



	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("成功進來findAll票券");
		
		GetCouponService getCouponService = new GetCouponService();
		List<CouponVO> ls = getCouponService.getAll();
		
		
		
		request.setAttribute("list", ls);
		System.out.println("35行");
		System.out.println(ls);
		RequestDispatcher rd=request.getRequestDispatcher("/front-end/getCoupon/allCoupon.jsp");
		rd.forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}