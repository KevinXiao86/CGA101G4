package com.taiwan.controller.roomOrder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@WebServlet("/roomOrder/cmpSelectByDate")
public class CmpSelectByDate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RoomOrderMyService roomOrderMyService = ControllerUtil.getBean(RoomOrderMyService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 獲取廠商的Id值
			Company company = (Company) session.getAttribute("loginCompany");
			Integer cmpId = company.getCmpId();
			// 獲取請求參數
			String startString = request.getParameter("startdate");
			String endString = request.getParameter("enddate");
			// 將輸入的字串參數轉成我要的格式，並且轉成util date
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date startdate = simpleDateFormat.parse(startString);
			Date enddate = simpleDateFormat.parse(endString);

			// 開始搜尋
			List<RoomOrder> roomOrders = roomOrderMyService.cmpFindByDate(cmpId, startdate, enddate);

			if (roomOrders.size() == 0) {
				errorMsgs.put("roomOrder", "查無資料");
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
			errorMsgs.put("Exception", "其他額外的錯誤");
			RequestDispatcher rd = request.getRequestDispatcher("/front-end/company/cmp_index.jsp");
			rd.forward(request, response);

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
