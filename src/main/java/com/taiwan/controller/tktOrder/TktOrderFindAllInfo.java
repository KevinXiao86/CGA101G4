package com.taiwan.controller.tktOrder;

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

import com.taiwan.beans.TktItem;
import com.taiwan.service.TktItemService;
import com.taiwan.service.tktItem.TktItemMyService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/tktOrder/findAllInfo")
public class TktOrderFindAllInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	TktItemMyService tktItemMyService=ControllerUtil.getBean(TktItemMyService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 獲得請求參數
			String tktOrderString = request.getParameter("tktOrderId");
			// 將參數轉成Integer
			Integer tktOrderId = Integer.valueOf(tktOrderString);
			// 查詢資料
//			TktItemService tktItemService = new TktItemService();
			List<TktItem> tktItems = new ArrayList<TktItem>();
			tktItems = tktItemMyService.selectById(tktOrderId);
			System.out.println(tktItems);
			// 把查詢的資料放進request域中
			request.setAttribute("tktItems", tktItems);
			// 進行請求轉向
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/tktOrder/listAllInfo.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			errorMsgs.put("Exception", "發生額外錯誤");
			RequestDispatcher rd = request.getRequestDispatcher("/tktOrder/findAll");
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
