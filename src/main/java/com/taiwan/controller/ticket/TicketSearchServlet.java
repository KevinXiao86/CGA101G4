package com.taiwan.controller.ticket;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taiwan.beans.TicketVO;
import com.taiwan.service.ticket.TicketSearchService;

@WebServlet("/ticket/TicketSearchServlet")
public class TicketSearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	

		if ("listTicket_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.將輸入資料轉為Map **********************************/
			// 採用Map<String,String[]> getParameterMap()的方法
			// 注意:an immutable java.util.Map
			// Map<String, String[]> map = req.getParameterMap();
			HttpSession session = req.getSession();
			Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");

			// 以下的 if 區塊只對第一次執行時有效
			if (req.getParameter("whichPage") == null) {
				Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
				session.setAttribute("map", map1);
				map = map1;
			}

			/*************************** 2.開始複合查詢 ***************************************/
			TicketSearchService tikSearchSvc = new TicketSearchService();
			List<TicketVO> ticketVOs = tikSearchSvc.getAll(map);
			

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("ticketVOs", ticketVOs); // 資料庫取出的list物件,存入request
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/ticketSearch/listTicket_ByCompositeQuery.jsp"); // 成功轉交listTicket_ByCompositeQuery.jsp
			successView.forward(req, res);
			
		}
	}
}
