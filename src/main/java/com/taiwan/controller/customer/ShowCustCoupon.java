package com.taiwan.controller.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taiwan.beans.CouponVO;
import com.taiwan.beans.CustCoupon;
import com.taiwan.beans.CustomerVO;
import com.taiwan.dao.coupon.impl.CouponJDBCDAO;
import com.taiwan.service.custCoupon.impl.CustCouponServiceImpl14;

@WebServlet("/cust/ShowCustCoupon")
public class ShowCustCoupon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowCustCoupon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO) session.getAttribute("customer");
		System.out.println("我有到custCoupon的servlet裡呦");
		Integer custId = customerVO.getCustId();
		System.out.println("custId=" + custId);
		// 去資料庫拿這個會員的所有優惠券
		CustCouponServiceImpl14 custCouponServiceImpl14 = new CustCouponServiceImpl14();
		System.out.println("custCouponServiceImpl14=" + custCouponServiceImpl14);
		List<CustCoupon> custCouponList = custCouponServiceImpl14.queryCustCouponById(custId);
		// 去把所有優惠券抓出來
		CouponJDBCDAO couponJDBCDAO = new CouponJDBCDAO();
		List<CouponVO> couponList = couponJDBCDAO.queryAll();
		// 把這個會員所有的優惠券、網站全部優惠券存到request的attribute裡面，並且轉送到前端網頁
		request.setAttribute("custCouponList", custCouponList);
		request.setAttribute("couponList", couponList);
		System.out.println(custCouponList);
		RequestDispatcher successView = request.getRequestDispatcher("/front-end/cust/showCustCoupon.jsp");
		successView.forward(request, response);
	}

}
