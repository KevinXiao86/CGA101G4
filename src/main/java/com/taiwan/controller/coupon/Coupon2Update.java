package com.taiwan.controller.coupon;

import java.io.IOException;
import java.util.LinkedHashMap;
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


@WebServlet("/coupon/cop2Update")
public class Coupon2Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CouponService couponService=ControllerUtil.getBean(CouponService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> errorMsgs=new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			//接收請求參數
			Integer copId=Integer.valueOf(request.getParameter("copId"));
			//獲取查詢的結果
			CouponVO couponVO=couponService.findById(copId);
			System.out.println(couponVO);
			//對request域塞資料
			request.setAttribute("couponVO", couponVO);
			//請求轉發到/coupon/cop_update.jsp
			RequestDispatcher rd=request.getRequestDispatcher("/back-end/coupon/cop_update.jsp");
			rd.forward(request, response);
			//其他的錯誤處理
		}catch (Exception e) {
			errorMsgs.put("無法取得需要的資料", e.getMessage());
			RequestDispatcher rd=request.getRequestDispatcher("/back-end/coupon/cop_findAll.jsp");
			rd.forward(request, response);
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
