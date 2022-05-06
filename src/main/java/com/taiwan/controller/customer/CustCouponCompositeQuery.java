package com.taiwan.controller.customer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.CouponVO;
import com.taiwan.beans.CustCoupon;
import com.taiwan.dao.coupon.impl.CouponJDBCDAO;
import com.taiwan.service.customer.impl.CustCouponCompositeQueryServiceImpl;

@WebServlet("/cust/CustCouponCompositeQuery")
public class CustCouponCompositeQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustCouponCompositeQuery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取得要做萬用查詢的parameterMap
		Map<String, String[]> map = request.getParameterMap();
		// 透過萬用查詢的service來取得這個會員的custCoupon，並存入request的attribute裡
		CustCouponCompositeQueryServiceImpl dao = new CustCouponCompositeQueryServiceImpl();
		List<CustCoupon> custCouponList = dao.getAll(map);
		request.setAttribute("custCouponList", custCouponList);
		// 到資料庫取得整個網站所有的優惠券，並存入request的attribute裡
		CouponJDBCDAO couponJDBCDAO = new CouponJDBCDAO();
		List<CouponVO> couponList = couponJDBCDAO.queryAll();
		request.setAttribute("couponList", couponList);
		// 轉送到前端網頁
		RequestDispatcher successView = request.getRequestDispatcher("/front-end/cust/showCustCoupon.jsp");
		successView.forward(request, response);
	}

}
