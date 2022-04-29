package com.taiwan.controller.roomOrder;

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

import com.taiwan.beans.RoomOrder;
import com.taiwan.service.roomOrder.RoomOrderMyService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/roomOrder/selectById")
public class RoomOrderSelectById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RoomOrderMyService roomOrderMyService = ControllerUtil.getBean(RoomOrderMyService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 接受請求的參數
			String roomOrderIdString = request.getParameter("roomOrderId");
			Integer roomOrderId = null;
			// 確認用戶有沒有輸入東西
			if (roomOrderIdString == null || roomOrderIdString.trim().equals("")) {
				errorMsgs.put("roomOrderId", "查詢框裡不能空白");
			} else {
				// 確認用戶是不是輸入數字
				try {
					// 字串轉成Integer
					roomOrderId = Integer.valueOf(roomOrderIdString);
				} catch (Exception e) {
					errorMsgs.put("roomOrderIdChange", "請輸入數字");
				}
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/roomOrder/roomOrder_index.jsp");
				rd.forward(request, response);
				return;
			}

			// 開始查詢
			RoomOrder roomOrder = roomOrderMyService.findById(roomOrderId);
//			System.out.println(roomOrders);

			if (roomOrder == null) {
				errorMsgs.put("roomOrder", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/roomOrder/roomOrder_index.jsp");
				rd.forward(request, response);
				return;
			}
			// 把查到的coupon放到request域中
			request.setAttribute("roomOrder", roomOrder);
			// 準備請求轉向跳轉頁面
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/roomOrder/roomOrder_selectOne.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			errorMsgs.put("其他錯誤發生", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/roomOrder/roomOrder_index.jsp");
			rd.forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
