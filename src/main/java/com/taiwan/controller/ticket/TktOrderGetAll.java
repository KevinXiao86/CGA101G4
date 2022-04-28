package com.taiwan.controller.ticket;

import java.io.IOException;
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

@WebServlet("/tktOrder/getAll")
public class TktOrderGetAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	TicketService ticketService = ControllerUtil.getBean(TicketService.class);
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		req.setCharacterEncoding("UTF-8");
		/******************開始查詢資料**********************/
		List<TicketVO> list = ticketService.findAll();
		
		/**************查詢完成，設定參數，送出成功頁面***************/
		req.setAttribute("list", list);
		RequestDispatcher success = req.getRequestDispatcher("/front-end/ticket11/listAllTicket.jsp");
		success.forward(req, res);
	}

}
