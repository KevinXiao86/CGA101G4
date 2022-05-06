package com.taiwan.controller.ticket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.TicketVO;
import com.taiwan.service.TicketService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/ticket/findAllFront")
public class TicketFindAllFront extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TicketService ticketService = ControllerUtil.getBean(TicketService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TicketVO> ls = new ArrayList<TicketVO>();
		//搜尋全部的票券
		ls = ticketService.findAll();
		//把搜尋到的List放到request域中
		request.setAttribute("list", ls);
		//請求導向到findAll.jsp頁面
		RequestDispatcher rd = request.getRequestDispatcher("/front-end/ticketSearch/ticketSearch_findAll.jsp");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
