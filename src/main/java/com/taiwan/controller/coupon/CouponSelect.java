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

import org.springframework.beans.factory.annotation.Autowired;

import com.taiwan.beans.CouponVO;
import com.taiwan.service.coupon.CouponService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/couponController/*")
public class CouponSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CouponService couponService = ControllerUtil.getBean(CouponService.class);

//	@Autowired
//	private CouponService couponService;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getPathInfo();
		System.out.println(path);

		if ("/selectById".contains(path)) {
			selectById(request, response);
		}
		if("/selectByStatus".contains(path)) {
			selectByStatus(request, response);
		}
			
		if("/selectByTitle".contains(path)) {
			selectByTitle(request, response);
		}
			
	}
	
	private void selectByTitle(HttpServletRequest request, HttpServletResponse response)
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
				gotoFailedPage(request, response);
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
			gotoFailedPage(request, response);
		}
	}
	
	private void selectByStatus(HttpServletRequest request, HttpServletResponse response)
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
			}
			if (!errorMsgs.isEmpty()) {
				gotoFailedPage(request, response);
				return;// 程式中斷
			}
			request.setAttribute("list", ls);
//			System.out.println(ls);
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/coupon/cop_status.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			errorMsgs.put("發生異常錯誤", e.getMessage());
			gotoFailedPage(request, response);
		}
	}

	private void selectById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 接受請求的參數
			String copIdString = request.getParameter("copId");
			Integer copId = null;
			// 確認用戶有沒有輸入東西
			if (copIdString == null || copIdString.trim().equals("")) {
				errorMsgs.put("copId", "查詢框裡不能空白");
			} else {
				// 確認用戶是不是輸入數字
				try {
					// 字串轉成Integer
					copId = Integer.valueOf(copIdString);
				} catch (Exception e) {
					errorMsgs.put("copIdChange", "請輸入數字");
				}
			}

			if (!errorMsgs.isEmpty()) {
				gotoFailedPage(request, response);
				return;
			}

			// 開始查詢
			CouponVO couponVO = couponService.findById(copId);
			if (couponVO == null) {
				errorMsgs.put("couponVO", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				gotoFailedPage(request, response);
				return;
			}
			// 把查到的coupon放到request域中
			request.setAttribute("couponVO", couponVO);
			// 準備請求轉向跳轉頁面
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/coupon/cop_id.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			errorMsgs.put("其他錯誤發生", e.getMessage());
			gotoFailedPage(request, response);
		}

	}

	private void gotoFailedPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/back-end/coupon/cop_index.jsp");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
