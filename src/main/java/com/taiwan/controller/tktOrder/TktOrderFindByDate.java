package com.taiwan.controller.tktOrder;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.TktOrder;
import com.taiwan.service.TktOrderService;

@WebServlet("/tktOrder/findByDate")
public class TktOrderFindByDate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			/******************* 1.接收請求參數，輸入格式的錯誤處理 *******************/
			// 以字串取得日期區間，原本型態為Date
			String start = request.getParameter("startdate");
			String end = request.getParameter("enddate");
			// 因要使用的service方法為TimeStamp類型，所以加上時間
			Timestamp startdate = Timestamp.valueOf(start + " 00:00:00");
			Timestamp enddate = Timestamp.valueOf(end + " 23:59:59");

			/************************** 2.開始查詢資料 **************************/
			TktOrderService tktOrderService = new TktOrderService();
			List<TktOrder> tktOrders = tktOrderService.getTktOrderByDateToDate(startdate, enddate);
			if (tktOrders == null || tktOrders.size() == 0) {
				errorMsgs.put("date", "查無資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/tktOrder/tktOrder_index.jsp");
				failureView.forward(request, response);
				return;
			}

			/******************** 3.查詢完成，設定參數，送出成功頁面 ********************/
			request.setAttribute("tktOrders", tktOrders);
			RequestDispatcher success = request.getRequestDispatcher("/back-end/tktOrder/tktOrder_findByOther.jsp");
			success.forward(request, response);

		} catch (Exception e) {
			errorMsgs.put("Exception", "發生其他錯誤");
			RequestDispatcher failureView = request.getRequestDispatcher("/back-end/tktOrder/tktOrder_index.jsp");
			failureView.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
