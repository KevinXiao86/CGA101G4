package com.taiwan.controller.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taiwan.beans.Company;
import com.taiwan.beans.CustomerVO;
import com.taiwan.beans.RoomMailVO;
import com.taiwan.dao.impl.CompanyDaoJNDI14;
import com.taiwan.dao.roomMail.impl.RoomMailJDBCDAO;
import com.taiwan.service.impl.RoomMailServiceImpl;

@WebServlet("/cust/SendRoomMail")
public class SendRoomMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SendRoomMail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取得會員想寄信詢問的廠商
		Integer cmpId = Integer.valueOf(request.getParameter("cmpId"));
		// 取得訊息內容
		String msg = request.getParameter("msg");
		// 取得會員Id
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO) session.getAttribute("customer");
		Integer custId = customerVO.getCustId();
		// 把資料存到資料庫裡
		RoomMailServiceImpl dao = new RoomMailServiceImpl();
		dao.sendMsg(custId, cmpId, msg, custId);
		// 用會員的custId到資料庫抓住宿信箱的資料，存入request的屬性中
		RoomMailJDBCDAO roomMailJDBCDAO = new RoomMailJDBCDAO();
		List<RoomMailVO> list = roomMailJDBCDAO.getAllByCustId(custId);
		request.setAttribute("list", list);
		// 去資料庫抓所有廠商的資料，並轉送到前端
		CompanyDaoJNDI14 companyDaoJNDI14 = new CompanyDaoJNDI14();
		List<Company> companyList = companyDaoJNDI14.queryCompanyAll();
		request.setAttribute("companyList", companyList);
		// 轉送到前端
		RequestDispatcher successView = request.getRequestDispatcher("/front-end/cust/showRoomMail.jsp");
		successView.forward(request, response);
	}

}
