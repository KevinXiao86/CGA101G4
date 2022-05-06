package com.taiwan.controller.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.CouponVO;
import com.taiwan.service.coupon.CouponService;
import com.taiwan.service.coupon.impl.CouponServiceImpl;

@WebServlet("/cust/CustomerGetCouponAll")
public class CustomerGetCouponAll extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(req.getContextPath());
		System.out.println("訪問成功");

		if ("getAllCoupon".equals(action)) { // 來自select_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數  **********************/
			CouponVO couponVO=new CouponVO();
			CouponServiceImpl copSvcImpl = new CouponServiceImpl();
			 List<CouponVO> list = copSvcImpl.findAll();
			 
//			 CouponVO
			 
//		    pageContext.setAttribute("list",list);
		   
//		    List<CouponVO> ls=new ArrayList<CouponVO>();
//			ls=couponService.findAll();
//			request.setAttribute("list", ls);
//			System.out.println(ls);
//			RequestDispatcher rd=request.getRequestDispatcher("/back-end/coupon/cop_findAll.jsp");
//			rd.forward(request, response);
			
		}
			
			
			String str = req.getParameter("empId");
			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.put("empId", "請輸入員工編號");
			}
			
			
		}
	}
//}
