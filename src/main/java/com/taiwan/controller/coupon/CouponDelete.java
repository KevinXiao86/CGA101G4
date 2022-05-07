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


@WebServlet("/coupon/copDelete")
public class CouponDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		CouponService couponService=ControllerUtil.getBean(CouponService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> errorMsgs=new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			//接收要刪除的copId
			Integer custId=Integer.valueOf(request.getParameter("copId"));
			String whichPage=request.getParameter("whichPage");
			//開始刪除資料
			couponService.delete(custId);
			//刪除成功，準備請求轉向
			RequestDispatcher rd=request.getRequestDispatcher("/coupon/findAll?whichPage="+whichPage);
			rd.forward(request, response);
			//其他錯誤處理
		}catch(Exception e) {
			errorMsgs.put("刪除資料失敗", e.getMessage());
			RequestDispatcher rd=request.getRequestDispatcher("/coupon/findAll");
			rd.forward(request, response);
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
