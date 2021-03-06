package com.taiwan.controller.coupon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.taiwan.beans.CouponVO;
import com.taiwan.service.coupon.CouponService;
import com.taiwan.utils.ControllerUtil;


@WebServlet("/coupon/findAll")
public class CouponFindAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	 CouponService couponService=ControllerUtil.getBean(CouponService.class);


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<CouponVO> ls=new ArrayList<CouponVO>();
		ls=couponService.findAll();
		request.setAttribute("list", ls);
		System.out.println(ls);
		RequestDispatcher rd=request.getRequestDispatcher("/back-end/coupon/cop_findAll.jsp");
		rd.forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
