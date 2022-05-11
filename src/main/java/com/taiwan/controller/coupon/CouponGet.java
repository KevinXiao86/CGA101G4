package com.taiwan.controller.coupon;

import java.io.IOException;
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
import com.taiwan.beans.Customer;
import com.taiwan.service.coupon.CouponService;
import com.taiwan.service.customer.CustomerService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/coupon/get")
public class CouponGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CouponService couponService=ControllerUtil.getBean(CouponService.class);
	CustomerService customerService=ControllerUtil.getBean(CustomerService.class);
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs=new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			Integer copId=Integer.valueOf(request.getParameter("copId")); 
			Integer discount=null;
			CouponVO couponVO=couponService.findById(copId);
			discount=couponVO.getDiscount();
			List<Customer> customers=customerService.findAll();
			for(Customer customer:customers) {
				Integer custId=customer.getCustId();
				couponService.addCustCoupon(copId, custId, discount);
			}
			Integer whichPage=Integer.valueOf(request.getParameter("whichPage"));
			RequestDispatcher rd=request.getRequestDispatcher("/coupon/findAll?whichPage="+whichPage);
			rd.forward(request, response);
			
			
		} catch (Exception e) {
			errorMsgs.put("Exception", e.getMessage());
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
