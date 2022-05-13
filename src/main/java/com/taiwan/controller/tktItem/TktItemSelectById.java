package com.taiwan.controller.tktItem;

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
import com.taiwan.beans.TktItem;
import com.taiwan.beans.TktOrder;
import com.taiwan.service.TicketService;
import com.taiwan.service.TktItemService;
import com.taiwan.service.TktOrderService;
import com.taiwan.service.impl.TicketServiceImpl;
import com.taiwan.service.tktItem.TktItemMyService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/tktItem/selectById")
public class TktItemSelectById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TicketService ticketService = ControllerUtil.getBean(TicketService.class);
	TktItemMyService tktItemMyService = ControllerUtil.getBean(TktItemMyService.class);

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("get_orderItem".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************/
			// 從頁面直接指定要查看的訂單，所以不會沒有資料
			Integer tktOrderId = Integer.valueOf(req.getParameter("tktOrderId"));

			/*************************** 2.開始查詢資料 ***************************/
			TktOrderService tktOrderService = new TktOrderService();
			TktOrder tktOrder = tktOrderService.getTktOrderByTktOrderId(tktOrderId);
			
			List<TktItem> itemList = tktItemMyService.selectById(tktOrderId);
			if (itemList == null || itemList.size() == 0) {
				errorMsgs.put("itmlist", "查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tktOrder/listAllTktOrder.jsp");
				failureView.forward(req, res);
				return;
			}
//				System.out.println(itemList);
			

			/******************** 3.查詢完成，設定參數，送出成功頁面 ********************/
			req.setAttribute("tktOrder", tktOrder);
			req.setAttribute("itemList", itemList);
			RequestDispatcher success = req.getRequestDispatcher("/front-end/tktItem/listOneTktItem.jsp");
			success.forward(req, res);

		}
	}
}
