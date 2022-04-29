package com.taiwan.controller.roomOrder;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.CustomerVO;
import com.taiwan.service.customer.CustomerService;
import com.taiwan.service.customer.impl.CustomerServiceImpl;

@WebServlet("/roomOrder/selectCustById")
public class SelectCustById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CustomerService customerService = new CustomerServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 獲取請求參數
			String custIdString = request.getParameter("custId");
			// 轉成Integer
			Integer custId = Integer.valueOf(custIdString);
			// 查詢會員資料
			CustomerVO customerVO = customerService.getAll(custId);
			System.out.println(customerVO);
			// 把取得的資料放進request域中
			request.setAttribute("customer", customerVO);
			// 請求轉向到會員資料頁面
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/roomOrder/custInfo.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			errorMsgs.put("Exception", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/roomOrder/findAll");
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
