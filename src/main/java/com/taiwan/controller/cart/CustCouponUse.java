package com.taiwan.controller.cart;

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
import com.taiwan.beans.CustCoupon;
import com.taiwan.service.coupon.CouponService;
import com.taiwan.service.coupon.impl.CouponServiceImpl;
import com.taiwan.service.customer.CustCouponService;

@WebServlet("/custCoupon/use")
public class CustCouponUse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		//根據會員id取得所擁有的會員優惠券物件
		CustCouponService custCouponService = new CustCouponService();
		List<CustCoupon> custCouponList = custCouponService.queryCustCouponById(10000); //custId 記得改
		//取得所擁有的優惠券編號
		List<Integer> copIds = new ArrayList<Integer>();
		for(CustCoupon custCoupon : custCouponList) {
			copIds.add(custCoupon.getCopId());
		}
		System.out.println(copIds);
		
		//根據優惠券編號取得優惠券名稱
		CouponService couponService = new CouponServiceImpl();
		List<CouponVO> couponList = new ArrayList<CouponVO>();
		for(Integer copId : copIds) {
			CouponVO couponVO = couponService.findById(copId);
			couponList.add(couponVO);
		}
		System.out.println(couponList);
		
		req.setAttribute("couponList", couponList);
		RequestDispatcher rd = req.getRequestDispatcher("/front-end/cart/cartList.jsp");
		rd.forward(req, res);
		
	}

}
