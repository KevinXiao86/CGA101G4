package com.taiwan.controller.coupon;

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
import com.taiwan.utils.ControllerUtil;

@WebServlet("/coupon/selectByStatus")
public class CouponSelectByStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CouponService couponService = ControllerUtil.getBean(CouponService.class);


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);

		
		
		
		try {
			String status = request.getParameter("status");
		
//			System.out.println(status);
			List<CouponVO> ls = new ArrayList<CouponVO>();
			ls = couponService.selectByStatus(status);
		
//			System.out.println(ls);
			//判斷有沒有搜尋到資料
			if(ls.isEmpty() || ls.size()==0) {
				errorMsgs.put("list is null", "查無資料");
				
				System.out.println("第一個if-46");
			}
			if (!errorMsgs.isEmpty()) {
				gotoFailedPage(request, response);
				System.out.println("第二個if-50");
				return;// 程式中斷
			}
			System.out.println("53行");
			request.setAttribute("list", ls);
			System.out.println(ls);
			RequestDispatcher rd = request.getRequestDispatcher("/front-end/getCoupon/cop_status.jsp");
			rd.forward(request, response);
			System.out.println("第二個if-58");
		} catch (ServletException e) {
			errorMsgs.put("發生異常錯誤", e.getMessage());
			gotoFailedPage(request, response);
		}
	}
	
	
	
	private void gotoFailedPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/front-end/getCoupon/getCouponSelect_page.jsp");
		rd.forward(request, response);                        
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}




























	
