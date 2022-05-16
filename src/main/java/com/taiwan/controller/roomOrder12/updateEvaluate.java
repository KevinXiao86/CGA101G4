package com.taiwan.controller.roomOrder12;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.RoomItemVO;
import com.taiwan.service.roomItem.impl.RoomItemServiceImpl;
import com.taiwan.service.roomtype.impl.RoomtypeService12;

/**
 * Servlet implementation class updateEvaluate
 */
@WebServlet("/addOrder/updateEvaluate")
public class updateEvaluate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getContextPath());
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		int evaluateScore = Integer.valueOf(req.getParameter("evaluateScore"));
		int roomItemId = Integer.valueOf(req.getParameter("roomItemId"));
		String evaluateMsg = "";
		try {
			evaluateMsg = req.getParameter("evaluateMsg");
		} catch (Exception e) {
			evaluateMsg = "";
		}
		/*************************** 2.開始更新資料 *****************************************/
		RoomItemServiceImpl roomItemServiceImpl = new RoomItemServiceImpl();
		roomItemServiceImpl.addEvaluate(roomItemId, evaluateScore, evaluateMsg);
		
		RoomItemVO roomItemVO=roomItemServiceImpl.searchByItemId(roomItemId);
		Integer roomId=roomItemVO.getRoomId();
		RoomtypeService12 roomtypeService12=new RoomtypeService12();
		Integer newTotalScore= roomtypeService12.searchRoomtype(roomId).getTotalScore()+evaluateScore;
		System.out.println(newTotalScore);
		roomtypeService12.addEvaluate(roomId, newTotalScore);
		/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
		String url = "/front-end/addOrder/seeItem.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 addorder.jsp
		successView.forward(req, res);
	}

}
