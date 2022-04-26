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

import com.taiwan.service.coupon.CouponService;
import com.taiwan.utils.ControllerUtil;


@WebServlet("/coupon/copStatusChange")
public class CouponStatusChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CouponService couponService=ControllerUtil.getBean(CouponService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> errorMsgs=new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			//取得copId
			Integer copId=Integer.valueOf(request.getParameter("copId"));
			//取得優惠券的狀態
			String status=request.getParameter("status");
			//判斷 一開始是下架的話，按下按鈕變改成上架
			//並重新導回搜尋頁面
			if(status.equals("下架")) {
				couponService.updateStatus(copId, "上架");
				RequestDispatcher rd=request.getRequestDispatcher("/back-end/coupon/findAll");
				rd.forward(request, response);
//				response.sendRedirect(request.getHeader("Referer"));
				
			}else {
				couponService.updateStatus(copId, "下架");
				RequestDispatcher rd=request.getRequestDispatcher("/back-end/coupon/findAll");
				rd.forward(request, response);
//				response.sendRedirect(request.getHeader("Referer"));
			}
			
		} catch (Exception e) {
			errorMsgs.put("其他錯誤", e.getMessage());
			RequestDispatcher rd=request.getRequestDispatcher("/back-end/coupon/cop_findAll.jsp");
			rd.forward(request, response);
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
