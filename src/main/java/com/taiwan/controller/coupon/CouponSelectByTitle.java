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

@WebServlet("/coupon/selectByTitle")
public class CouponSelectByTitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CouponService couponService = ControllerUtil.getBean(CouponService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 獲得請求參數的值
			String copName = request.getParameter("copName");
			// 如果獲得的是空字串或是空值
			if (copName == null || copName.trim().equals("")) {
				errorMsgs.put("copName", "輸入欄裡面不要留空");
			}
//			遍歷一下MAP裡面的值
//			for (Map.Entry<String, String> entry : errorMsgs.entrySet()) {
//				System.out.println(entry.getKey() + ":" + entry.getValue());
//			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/coupon/cop_index.jsp");
				rd.forward(request, response);
				return;// 程式中斷
			}
			// 開始做查詢
			List<CouponVO> ls = new ArrayList<CouponVO>();
			ls = couponService.selectByTitle(copName);
			// 判斷一下list裡面有沒有值
			System.out.println(ls);
			if (ls.isEmpty() || ls.size() == 0) {
				errorMsgs.put("List is null", "查無資料");
			}
//			for (Map.Entry<String, String> entry : errorMsgs.entrySet()) {
//				System.out.println(entry.getKey() + ":" + entry.getValue());
//			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/coupon/cop_index.jsp");
				rd.forward(request, response);
				return;// 程式中斷
			}
			// 把list送到request域中
			request.setAttribute("list", ls);
			// 請求轉發到搜尋標頭的jsp中
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/coupon/cop_name.jsp");
			rd.forward(request, response);
			// 判斷是否有其他錯誤
		} catch (Exception e) {
			errorMsgs.put("其他錯誤", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/coupon/cop_index.jsp");
			rd.forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
