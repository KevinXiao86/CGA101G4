package com.taiwan.controller.ticket;

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

import com.taiwan.beans.TicketVO;
import com.taiwan.service.TicketService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/ticket/SelectByThree")
public class TicketSelectByThree extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TicketService ticketService = ControllerUtil.getBean(TicketService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			String status = "上架";
			String location = null;
			String tktName = null;
			location=request.getParameter("location");
			tktName=request.getParameter("tktName");
			//查詢
			System.out.println("location "+location);
			System.out.println("tktName" +tktName);
			List<TicketVO> ticketVOs=ticketService.findByThree(status, location, tktName);
			System.out.println(ticketVOs);
			
			if(ticketVOs.isEmpty() || ticketVOs.size()==0) {
				errorMsgs.put("tickstVOs", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/front-end/ticket/ticketList.jsp");
				rd.forward(request, response);
				return;// 程式中斷
			}
			request.setAttribute("ticketVOs", ticketVOs);
			RequestDispatcher rd = request.getRequestDispatcher("/front-end/ticket/ticketFind.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			errorMsgs.put("Exception", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/front-end/ticket/ticketList.jsp");
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
