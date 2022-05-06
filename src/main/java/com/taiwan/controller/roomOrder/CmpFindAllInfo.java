package com.taiwan.controller.roomOrder;

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
import com.taiwan.beans.RoomOrder;
import com.taiwan.beans.Roomtype;
import com.taiwan.service.roomItem.RoomItemService;
import com.taiwan.service.roomItem.impl.RoomItemServiceImpl;
import com.taiwan.service.roomOrder.RoomOrderMyService;
import com.taiwan.service.roomtype.RoomtypeService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/roomOrder/cmpFindAllInfo")
public class CmpFindAllInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RoomOrderMyService roomOrderMyService=ControllerUtil.getBean(RoomOrderMyService.class);
	RoomItemService roomItemService = new RoomItemServiceImpl();
	RoomtypeService roomtypeService = ControllerUtil.getBean(RoomtypeService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 獲得請求的參數
			String roomOrderIdString = request.getParameter("roomOrderId");
			// 將請求參數轉換成Integer
			Integer roomOrderId = Integer.valueOf(roomOrderIdString);
			// 獲取RoomOrder的查詢結果
			RoomOrder roomOrder = roomOrderMyService.findById(roomOrderId);
			//獲取廠商Id
			Integer cmpId = roomOrder.getCmpId();
			RoomItemVO roomItemVO = roomItemService.findByOrderId(roomOrderId);
			//獲取房型編號
			Integer roomId = roomItemVO.getRoomId();
			//查詢房型名稱
			Roomtype roomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(cmpId+"", roomId+"");
//			System.out.println(roomtype);
			// 對request域中塞資料
			request.setAttribute("roomOrder", roomOrder);
			request.setAttribute("roomItemVO", roomItemVO);
			request.setAttribute("roomtype", roomtype);
			// 請求轉發到/back-end/roomOrder/roomOrder_allInfo.jsp
			RequestDispatcher rd = request.getRequestDispatcher("/front-end/cmpRoomOrder/roomOrder_allInfo.jsp");
			rd.forward(request, response);

			// 其他錯誤處理
		} catch (Exception e) {
			errorMsgs.put("Exception", "其他額外的錯誤");
			RequestDispatcher rd = request.getRequestDispatcher("/roomOrder/cmpFindOrder");
			rd.forward(request, response);
		}
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
