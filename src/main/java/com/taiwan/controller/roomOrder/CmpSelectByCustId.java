package com.taiwan.controller.roomOrder;

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

import com.taiwan.beans.Company;
import com.taiwan.beans.RoomOrder;
import com.taiwan.service.roomOrder.RoomOrderMyService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/roomOrder/cmpSelectByCustId")
public class CmpSelectByCustId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RoomOrderMyService roomOrderMyService = ControllerUtil.getBean(RoomOrderMyService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 這邊要獲取廠商的Id
			Company company = (Company) session.getAttribute("loginCompany");
			// 把廠商Id從company物件取出
			Integer cmpId = company.getCmpId();
			// 獲取廠商要查詢的custId
			String custIdString = request.getParameter("custId");
			Integer custId = null;
			// 確認用戶有沒有輸入東西
			if (custIdString == null || custIdString.trim().equals("")) {
				errorMsgs.put("custId", "查詢框裡不能空白");
			} else {
				// 確認用戶是不是輸入數字
				try {
					// 字串轉成Integer
					custId = Integer.valueOf(custIdString);
				} catch (Exception e) {
					errorMsgs.put("custIdChange", "請輸入數字");
				}
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/front-end/company/cmp_index.jsp");
				rd.forward(request, response);
				return;
			}
			// 開始查詢
			List<RoomOrder> roomOrders = roomOrderMyService.cmpFindByCustId(cmpId, custId);
			if (roomOrders.isEmpty() || roomOrders.size() == 0) {
				errorMsgs.put("roomOrder", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/front-end/company/cmp_index.jsp");
				rd.forward(request, response);
				return;
			}
			// 把查到的roomOrders放到request域中
			request.setAttribute("roomOrders", roomOrders);
			// 準備請求轉向跳轉頁面
			RequestDispatcher rd = request.getRequestDispatcher("/front-end/cmpRoomOrder/cmp_findOrder.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			errorMsgs.put("其他錯誤發生", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/front-end/company/cmp_index.jsp");
			rd.forward(request, response);

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}