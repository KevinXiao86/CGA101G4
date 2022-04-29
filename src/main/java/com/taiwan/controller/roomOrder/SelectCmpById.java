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

import com.taiwan.beans.Company;
import com.taiwan.service.company.CompanyService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/roomOrder/selectCmpById")
public class SelectCmpById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CompanyService companyService = ControllerUtil.getBean(CompanyService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 獲取請求參數
			String cmpIdString = request.getParameter("cmpId");
			// 轉成Integer
			Integer cmpId = Integer.valueOf(cmpIdString);
			// 查詢廠商資料
			Company company = companyService.getCompanyByCmpId(cmpId);
			System.out.println(company);
			request.setAttribute("company", company);
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/roomOrder/cmpData.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			errorMsgs.put("Exception", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/roomOrder/findAll");
			rd.forward(request, response);

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
