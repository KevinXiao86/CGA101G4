package com.taiwan.controller.ticket;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.TicketVO;
import com.taiwan.service.TicketService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/ticket/selectById")
public class TicketSelectById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TicketService ticketService = ControllerUtil.getBean(TicketService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 接受請求的參數
			String tktIdString = request.getParameter("tktId");
			Integer tktId = null;
			// 確認用戶有沒有輸入東西
			if (tktIdString == null || tktIdString.trim().equals("")) {
				errorMsgs.put("tktId", "查詢框裡不能空白");
			}
			// 確認用戶是不是輸入數字
			try {
				// 字串轉成Integer
				tktId = Integer.valueOf(tktIdString);
			} catch (Exception e) {
				errorMsgs.put("tktIdChange", "請輸入數字");
			}
//			遍歷一下MAP裡面的值
//			for (Map.Entry<String, String> entry : errorMsgs.entrySet()) {
//				System.out.println(entry.getKey() + ":" + entry.getValue());
//			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/ticket/ticket_index.jsp");
				rd.forward(request, response);
				return;// 程式中斷
			}
			// 開始查詢
			TicketVO ticketVO=ticketService.findById(tktId);
			System.out.println(ticketVO);
			if (ticketVO == null) {
				errorMsgs.put("ticketVO", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/ticket/ticket_index.jsp");
				rd.forward(request, response);
				return;// 程式中斷
			}
			// 把查到的coupon放到request域中
			request.setAttribute("ticketVO", ticketVO);
			// 準備請求轉向跳轉頁面
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/ticket/ticket_id.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			errorMsgs.put("其他錯誤發生", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/ticket/ticket_index.jsp");
			rd.forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
