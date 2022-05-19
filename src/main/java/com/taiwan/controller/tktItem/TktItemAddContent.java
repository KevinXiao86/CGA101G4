package com.taiwan.controller.tktItem;

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
import javax.servlet.http.HttpSession;

import com.taiwan.beans.TicketVO;
import com.taiwan.beans.TktItem;
import com.taiwan.beans.TktOrder;
import com.taiwan.service.TicketService;
import com.taiwan.service.TktItemService;
import com.taiwan.service.TktOrderService;
import com.taiwan.service.tktItem.TktItemMyService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/tktItem/content")
public class TktItemAddContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	TicketService ticketService = ControllerUtil.getBean(TicketService.class);
	TktItemMyService tktItemMyService = ControllerUtil.getBean(TktItemMyService.class);
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if ("insert".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************/
			// 從頁面直接指定要查看的訂單，所以不會沒有資料
			Integer tktOrderId = Integer.valueOf(req.getParameter("tktOrderId"));
//			System.out.println("tktOrderId = "+tktOrderId);
			
			Integer tktId = Integer.valueOf(req.getParameter("tktId"));
//			System.out.println("tktId = "+tktId);
			
			String content = req.getParameter("content");
			if (content == null || content.trim().length() == 0) {
				errorMsgs.put("content","內容請勿空白");
			}
			
//			System.out.println("content 60 = " + content);
			
			String str = req.getParameter("score");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("score","請評分");
			}
			System.out.println("666666666666666");
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tktOrder/listAllTktOrder.jsp");
				failureView.forward(req, res);
				return;
			}
			
			Integer score = Integer.valueOf(str);
			System.out.println("score 73 = " + score);
			
			/*************************** 2.開始查詢資料 ***************************/
			TktItemService tktItemSvc = new TktItemService();
			tktItemSvc.updateTktItemScoreContent(tktOrderId, tktId, score, content);
			
			TktOrderService tktOrderService = new TktOrderService();
			TktOrder tktOrder = tktOrderService.getTktOrderByTktOrderId(tktOrderId);
			
			List<TktItem> itemList = tktItemMyService.selectById(tktOrderId);
//			System.out.println("itemList = " + itemList);
			
//			TicketVO ticketVO = ticketService.findById(tktId);
//			String tktName = ticketVO.getTktName();
//			System.out.println(tktName);
			
			/******************** 3.查詢完成，設定參數，送出成功頁面 ********************/
			req.setAttribute("tktOrder", tktOrder);
			req.setAttribute("itemList", itemList);
//			req.setAttribute("tktName", tktName);
			RequestDispatcher success = req.getRequestDispatcher("/front-end/tktItem/listOneTktItem.jsp"); 
			success.forward(req, res);

		}
	}
}
