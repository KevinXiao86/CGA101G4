package com.taiwan.controll.employee;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taiwan.service.coupon.CouponService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/empLogout")
public class EmpLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CouponService couponService = ControllerUtil.getBean(CouponService.class);

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);

		System.out.println(req.getContextPath());
		System.out.println("訪問成功logout");

		// 清除資料
		session.invalidate();
		System.out.println(req.getContextPath());
		res.sendRedirect(req.getContextPath() + "/back-login/login/empLogin.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
