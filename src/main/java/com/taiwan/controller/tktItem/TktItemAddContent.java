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

import com.taiwan.beans.TktItem;
import com.taiwan.service.TktItemService;

@WebServlet("/tktItem/content")
public class TktItemAddContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************/
			// 從頁面直接指定要查看的訂單，所以不會沒有資料
			Integer tktOrderId = Integer.valueOf(req.getParameter("tktOrderId"));
			
			Integer tktId = Integer.valueOf(req.getParameter("tktId"));
			
			String content = req.getParameter("content");
			if (content == null || content.trim().length() == 0) {
				errorMsgs.put("content","內容請勿空白");
			}
			
			Integer score = Integer.valueOf(req.getParameter("score"));

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tktItem/listOneTktItem.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/*************************** 2.開始查詢資料 ***************************/
			TktItemService tktItemSvc = new TktItemService();
			TktItem item = tktItemSvc.updateTktItemScoreContent(tktOrderId, tktId, score, content);

			/******************** 3.查詢完成，設定參數，送出成功頁面 ********************/
			req.setAttribute("item", item);
			RequestDispatcher success = req.getRequestDispatcher("/front-end/tktItem/listOneTktItem.jsp"); //怪怪的
			success.forward(req, res);

		}
	}
}
