package com.taiwan.controller.roomOrder12;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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

import com.taiwan.beans.Roomtype;
import com.taiwan.service.roomtype.*;
import com.taiwan.utils.ControllerUtil;

/**
 * Servlet implementation class ROSelectCmp
 */
@WebServlet("/roomOrder12/ROSelectCmp")

public class ROSelectCmp extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getContextPath());
		System.out.println("訪問成功46");

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);
		System.out.println("訪問成功50");

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		String str = req.getParameter("cmpId");
		if (str == null || (str.trim()).length() == 0) {
			errorMsgs.put("cmpId", "請輸入廠商");
		}
		System.out.println("訪問成功57");

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/addOrder/selectCMP.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}
		System.out.println("訪問成功66");

		Integer cmpId = null;
		try {
			cmpId = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.put("cmpId", "廠商格式不正確");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/addOrder/selectCMP.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}
		System.out.println("訪問成功81");

		/*************************** 2.開始查詢資料 *****************************************/
		RoomtypeService roomTypeSvc = ControllerUtil.getBean(RoomtypeService.class);
		System.out.println("訪問成功85");

		List<Roomtype> list = roomTypeSvc.getAllRoomtypes(cmpId);
		System.out.println("訪問成功88");

		if (list == null) {
			errorMsgs.put("cmpId", "查無資料");
		}
		System.out.println("訪問成功93");

		if (list.isEmpty()) {
			errorMsgs.put("cmpId", "查無房型");
		}
		System.out.println("訪問成功98");

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/addOrder/selectCMP.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}
		//存入日期
		String ckin = req.getParameter("ckin").trim();
		String ckout = req.getParameter("ckout").trim();
		Map<String, String> map = new HashMap<>();
		map.put("ckin", ckin);
		map.put("ckout", ckout);

		System.out.println("訪問成功107");

		/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
		req.setAttribute("roomtypeVOs", list); // 資料庫取出的list物件,存入req
		HttpSession session = req.getSession();
		session.setAttribute("dateMap", map);
		session.setAttribute("roomtypeVOs", list);
		session.setAttribute("cmpId", cmpId);

		String url = "/front-end/addOrder/cmpRoomtype.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 addorder.jsp
		successView.forward(req, res);
		System.out.println("訪問成功114");
		System.out.println(map.get("ckin"));

	}

}
