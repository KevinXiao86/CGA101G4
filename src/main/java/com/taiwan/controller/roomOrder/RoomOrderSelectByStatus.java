package com.taiwan.controller.roomOrder;

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

import com.taiwan.beans.RoomOrder;
import com.taiwan.service.roomOrder.RoomOrderMyService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/roomOrder/selectByStatus")
public class RoomOrderSelectByStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RoomOrderMyService roomOrderMyService=ControllerUtil.getBean(RoomOrderMyService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs=new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			//獲取參數
			String roomOrderStatus=request.getParameter("roomOrderStatus");
			List<RoomOrder> roomOrders=new ArrayList<RoomOrder>(); 
			roomOrders=roomOrderMyService.findByStatus(roomOrderStatus);
			if(roomOrders.isEmpty() || roomOrders.size() == 0) {
				errorMsgs.put("List is null", "查無資料");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher rd=request.getRequestDispatcher("/back-end/roomOrder/roomOrder_index.jsp");
				rd.forward(request, response);
				return;
			}
			request.setAttribute("roomOrders", roomOrders);
			RequestDispatcher rd=request.getRequestDispatcher("/back-end/roomOrder/roomOrder_selectAll.jsp");
			rd.forward(request, response);
			
		}catch(Exception e) {
			errorMsgs.put("發生異常錯誤", e.getMessage());
			RequestDispatcher rd=request.getRequestDispatcher("/back-end/roomOrder/roomOrder_index.jsp");
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
