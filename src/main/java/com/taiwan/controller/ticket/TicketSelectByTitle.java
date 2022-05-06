package com.taiwan.controller.ticket;

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

import com.taiwan.beans.TicketVO;
import com.taiwan.service.TicketService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/ticket/selectByTitle")
public class TicketSelectByTitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TicketService ticketService=ControllerUtil.getBean(TicketService.class);
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 獲得請求參數的值
			String tktName = request.getParameter("tktName");
			// 如果獲得的是空字串或是空值
			if (tktName == null || tktName.trim().equals("")) {
				errorMsgs.put("copName", "輸入欄裡面不要留空");
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
			// 開始做查詢
			List<TicketVO> ls = new ArrayList<TicketVO>();
			ls = ticketService.findByName(tktName);
			// 判斷一下list裡面有沒有值
			System.out.println(ls);
			if (ls.isEmpty() || ls.size() == 0) {
				errorMsgs.put("List is null", "查無資料");
			}
//			for (Map.Entry<String, String> entry : errorMsgs.entrySet()) {
//				System.out.println(entry.getKey() + ":" + entry.getValue());
//			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/ticket/ticket_index.jsp");
				rd.forward(request, response);
				return;// 程式中斷
			}
			// 把list送到request域中
			request.setAttribute("list", ls);
			// 請求轉發到搜尋標頭的jsp中
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/ticket/ticket_status.jsp");
			rd.forward(request, response);
			// 判斷是否有其他錯誤
		} catch (Exception e) {
			errorMsgs.put("其他錯誤", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/ticket/ticket_index.jsp");
			rd.forward(request, response);
		}
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
