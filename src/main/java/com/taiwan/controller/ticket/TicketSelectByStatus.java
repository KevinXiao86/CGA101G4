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

import org.springframework.beans.factory.annotation.Autowired;

import com.taiwan.beans.CouponVO;
import com.taiwan.beans.TicketVO;
import com.taiwan.service.TicketService;

@WebServlet("/ticket/selectByStatus")
public class TicketSelectByStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private TicketService ticketService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs=new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			String status = request.getParameter("status");
//			System.out.println(status);
			List<TicketVO> ls = new ArrayList<TicketVO>();
			ls = ticketService.findByStatus(status);
//			System.out.println(ls);
			//判斷有沒有搜尋到資料
			if(ls.isEmpty() || ls.size()==0) {
				errorMsgs.put("list is null", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/ticket/tkt_index.jsp");
				rd.forward(request, response);
				return;// 程式中斷
			}
			request.setAttribute("list", ls);
//		System.out.println(ls);
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/ticket/ticket_status.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			errorMsgs.put("發生異常錯誤", e.getMessage());
			RequestDispatcher rd=request.getRequestDispatcher("/back-end/ticket/ticket_index.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
