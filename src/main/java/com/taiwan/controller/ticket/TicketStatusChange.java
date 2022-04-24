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

import com.taiwan.service.TicketService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/ticket/tktStatusChange")
public class TicketStatusChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TicketService ticketService=ControllerUtil.getBean(TicketService.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs=new LinkedHashMap<String, String>();
		try {
			Integer tktId=Integer.valueOf(request.getParameter("tktId"));
			String status=request.getParameter("status");
			if(status.equals("下架")) {
				ticketService.updateStatus(tktId, "上架");
				RequestDispatcher rd=request.getRequestDispatcher("/ticket/findAll");
				rd.forward(request, response);
			}else {
				ticketService.updateStatus(tktId, "下架");
				RequestDispatcher rd=request.getRequestDispatcher("/ticket/findAll");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			errorMsgs.put("其他錯誤", e.getMessage());
			RequestDispatcher rd=request.getRequestDispatcher("/ticket/tkt_findAll.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
