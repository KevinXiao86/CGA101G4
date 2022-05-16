package com.taiwan.controller.roomOrder12;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;
import com.taiwan.beans.RoomOrder;
import com.taiwan.beans.RoomOrderVO;
import com.taiwan.service.impl.RoomOrderServiceImpl;



/**
 * Servlet implementation class AllRoomOrder
 */
@WebServlet("/roomOrder12/AllRoomOrder")
public class AllRoomOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
				doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//開始查詢
		Integer cmpId=Integer.valueOf(req.getParameter("cmpId"));
		RoomOrderServiceImpl roomOrderServiceImpl=new RoomOrderServiceImpl();
		List<RoomOrderVO> list = roomOrderServiceImpl.searchRoomOrderbyCustId(cmpId);
		
		/********************* 2.查詢完成，設定參數，送出成功頁面 **********************/
		req.setAttribute("list", list);
		String url = "/front-end/addOrder/selectOrder.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
		successView.forward(req, res);
		// TODO Auto-generated method stub
	}

}
