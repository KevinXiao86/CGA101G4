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
import com.taiwan.service.CouponService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/coupon/selectById")
public class CouponSelectById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CouponService couponService = ControllerUtil.getBean(CouponService.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs=new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			//接受請求的參數
			String copIdString=request.getParameter("copId");
			Integer copId=null;
			//確認用戶有沒有輸入東西
			if(copIdString==null || copIdString.trim().equals("")) {
				errorMsgs.put("copId", "查詢框裡不能空白");
			}
			//確認用戶是不是輸入數字
			try {
				//字串轉成Integer
				copId=Integer.valueOf(copIdString);
			} catch (Exception e) {
				errorMsgs.put("copIdChange","請輸入數字");
			}
//			遍歷一下MAP裡面的值
//			for (Map.Entry<String, String> entry : errorMsgs.entrySet()) {
//				System.out.println(entry.getKey() + ":" + entry.getValue());
//			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd=request.getRequestDispatcher("/coupon/cop_index.jsp");
				rd.forward(request, response);
				return;//程式中斷
			}
			//開始查詢
			CouponVO couponVO=couponService.findById(copId);
			if(couponVO == null) {
				errorMsgs.put("couponVO", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd=request.getRequestDispatcher("/coupon/cop_index.jsp");
				rd.forward(request, response);
				return;//程式中斷
			}
			//把查到的coupon放到request域中
			request.setAttribute("couponVO", couponVO);
			//準備請求轉向跳轉頁面
			RequestDispatcher rd=request.getRequestDispatcher("/coupon/cop_id.jsp");
			rd.forward(request, response);
			
		}catch (Exception e) {
			errorMsgs.put("其他錯誤發生", e.getMessage());
			RequestDispatcher rd=request.getRequestDispatcher("/coupon/cop_index.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
