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
import com.taiwan.service.tktImg.TktImgService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/ticket/tktDelete")
public class TicketDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TicketService ticketService = ControllerUtil.getBean(TicketService.class);
	TktImgService tktImgService = ControllerUtil.getBean(TktImgService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 獲取請求參數
			Integer tktId = Integer.valueOf(request.getParameter("tktId"));
			// 開始刪除
			tktImgService.deleteById(tktId);
			ticketService.deleteTkt(tktId);
			
			// 開始請求導向(導回搜尋全部那隻servlet)
			RequestDispatcher rd = request.getRequestDispatcher("/ticket/findAll");
			rd.forward(request, response);
		} catch (NumberFormatException e) {
			errorMsgs.put("發生未知錯誤", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/ticket/findAll");
			rd.forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
