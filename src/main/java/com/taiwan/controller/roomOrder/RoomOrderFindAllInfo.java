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

import com.taiwan.beans.RoomItemVO;
import com.taiwan.beans.RoomOrder;
import com.taiwan.dao.roomItem.RoomItemDAO_interface;
import com.taiwan.service.roomItem.RoomItemService;
import com.taiwan.service.roomItem.impl.RoomItemServiceImpl;
import com.taiwan.service.roomOrder.RoomOrderMyService;
import com.taiwan.utils.ControllerUtil;
@WebServlet("/roomOrder/findAllInfo")
public class RoomOrderFindAllInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RoomOrderMyService roomOrderMyService=ControllerUtil.getBean(RoomOrderMyService.class);
	RoomItemService roomItemService=new RoomItemServiceImpl();
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs=new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			//獲得請求的參數
			String roomOrderIdString=request.getParameter("roomOrderId");
			//將請求參數轉換成Integer
			Integer roomOrderId=Integer.valueOf(roomOrderIdString);
			//獲取RoomOrder的查詢結果
			RoomOrder roomOrder=roomOrderMyService.findById(roomOrderId);
			
			RoomItemVO roomItemVO=roomItemService.findByOrderId(roomOrderId);
//			System.out.println(roomItemVO);
			//對request域中塞資料
			request.setAttribute("roomOrder", roomOrder);
			request.setAttribute("roomItemVO", roomItemVO);
			//請求轉發到/back-end/roomOrder/roomOrder_allInfo.jsp
			RequestDispatcher rd=request.getRequestDispatcher("/back-end/roomOrder/roomOrder_allInfo.jsp");
			rd.forward(request, response);
			
			
			
			//其他錯誤處理
		}catch(Exception e) {
			errorMsgs.put("Exception", "其他額外的錯誤");
			RequestDispatcher rd=request.getRequestDispatcher("/roomOrder/findAll");
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
