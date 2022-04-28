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

@WebServlet("/tktItem/selectById")
public class TktItemSelectById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("get_orderItem".equals(action)) {
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數***************************/
				//從頁面直接指定要查看的訂單，所以不會沒有資料
				String str = req.getParameter("tktOrderId");
				//將訂單編號轉換成數字
				Integer tktOrderId = Integer.valueOf(str);
				
				/***************************2.開始查詢資料***************************/
				TktItemService tktItemSvc = new TktItemService();
				List<TktItem> itemList = tktItemSvc.getTktItemByTktOrderId(tktOrderId);
				if (itemList == null || itemList.size() == 0) {
					errorMsgs.put("itmlist", "查無資料");
				}
//				System.out.println(itemList);
				
				/********************3.查詢完成，設定參數，送出成功頁面********************/
				req.setAttribute("itemList", itemList);
				RequestDispatcher success = req.getRequestDispatcher("/front-end/tktItem/listOneTktItem.jsp");
				success.forward(req, res);
				
				/**************************其他可能的錯誤處理**************************/
			} catch (Exception e) {
				errorMsgs.put("Exception","無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/tktOrder/listOneTktOrder.jsp");
				failureView.forward(req, res);
			}
			
		}
	}
}
