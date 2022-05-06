package com.taiwan.controller.tktOrder;

import java.io.IOException;
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

@WebServlet("/tktOrder/findByCustId")
public class TktOrderFindByCustId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 獲取請求參數
			String custIdString = request.getParameter("custId");
			Integer custId = null;
			if (custIdString == null || custIdString.trim().length() == 0) {
				errorMsgs.put("tktOrderId", "查詢框裡面不能留空");
			} else {
				// 確認用戶是不是輸入數字
				try {
					custId = Integer.valueOf(custIdString);
				} catch (Exception e) {
					errorMsgs.put("tktOrderIdChange", "請輸入數字");
				}
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/tktOrder/tktOrder_index.jsp");
				failureView.forward(request, response);
				return;
			}
			// 開始查詢資料
			TktOrderService tktOrderService = new TktOrderService();
			List<TktOrder> tktOrders=tktOrderService.getTktOrderByCustId(custId);
			if (tktOrders.isEmpty() || tktOrders.size() == 0) {
				errorMsgs.put("tktOrders", "查無資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/tktOrder/tktOrder_index.jsp");
				failureView.forward(request, response);
				return;
			}
			// 完成查詢
			request.setAttribute("tktOrders", tktOrders);
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/tktOrder/tktOrder_findByOther.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			errorMsgs.put("Exception", e.getMessage());
			RequestDispatcher failureView = request.getRequestDispatcher("/back-end/tktOrder/tktOrder_index.jsp");
			failureView.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
