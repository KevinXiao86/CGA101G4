package com.taiwan.controller.roomSelect;

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
import com.taiwan.beans.*;
import com.taiwan.service.employee.EmployeeService;
import com.taiwan.service.roomSelect.RoomSelectService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/Room/RoomSelect")
public class RoomSelect extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(req.getContextPath());
		
		System.out.println("訪問成功1111111111111");
		if ("find_threeTable".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/* ==========================確認數據============================ */
System.out.println("41");
			// 確認地點
			String location = req.getParameter("location");
			
			if (location == null || location.trim().length() == 0) {
				errorMsgs.put("location", "地點: 請勿空白");
			}
				// 確認入住人數
				String str = req.getParameter("roomtype_people");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("roomtype_people", "請輸入入住人數");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/roomSearch/roomSelect_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer roomtype_people = null;
				try {
					roomtype_people = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("roomtype_people", "人數請輸入數字");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/roomSearch/roomSelect_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				// 開始時間
				java.sql.Date reserve_date = null;
				try {
					reserve_date = java.sql.Date.valueOf(req.getParameter("reserve_date"));
				} catch (IllegalArgumentException e) {
					errorMsgs.put("reserve_date", "請輸入起始日期");
				}
				// 結束時間
				java.sql.Date reserve_date2 = null;
				try {
					reserve_date2 = java.sql.Date.valueOf(req.getParameter("reserve_date2"));
				} catch (IllegalArgumentException e) {
					errorMsgs.put("迄日", "請填寫");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/roomSearch/roomSelect_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
System.out.println(location+","+ roomtype_people+","+ reserve_date+","+ reserve_date2);
				/* =======================2.開始查詢資料=============================== */
				RoomSelectService roomSvc = new RoomSelectService();
				List<RoomSelectVO> roomSelectVO = roomSvc.getAllTable(location, roomtype_people, reserve_date, reserve_date2);
System.out.println("99");
				System.out.println(roomSelectVO);
				if (roomSelectVO == null) {
					errorMsgs.put("location", "查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/roomSearch/roomSelect_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("roomSelectVO", roomSelectVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/roomSearch/roomSearch_findAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			}
		}
	}

