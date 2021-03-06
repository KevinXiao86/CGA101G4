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
import javax.servlet.http.HttpSession;

import com.taiwan.beans.Company;
import com.taiwan.beans.RoomOrder;
import com.taiwan.service.roomOrder.RoomOrderMyService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/roomOrder/cmpSelectByStatus")
public class CmpSelectByStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RoomOrderMyService roomOrderMyService = ControllerUtil.getBean(RoomOrderMyService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 獲取是哪一家廠商
			Company company = (Company) session.getAttribute("loginCompany");
			// 獲取那位廠商的Id
			Integer cmpId = company.getCmpId();
			// 獲取參數
			String roomOrderStatus = request.getParameter("roomOrderStatus");
			List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
			roomOrders = roomOrderMyService.cmpFindByStatus(cmpId, roomOrderStatus);
			if (roomOrders.isEmpty() || roomOrders.size() == 0) {
				errorMsgs.put("List is null", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/front-end/company/cmp_index.jsp");
				rd.forward(request, response);
				return;
			}
			request.setAttribute("roomOrders", roomOrders);
			RequestDispatcher rd = request.getRequestDispatcher("/front-end/cmpRoomOrder/cmp_findOrder.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			errorMsgs.put("發生異常錯誤", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/front-end/company/cmp_index.jsp");
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
