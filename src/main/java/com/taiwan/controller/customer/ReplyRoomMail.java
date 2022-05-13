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

@WebServlet("/cust/ReplyRoomMail")
public class ReplyRoomMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReplyRoomMail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取得會員想回復的廠商Id
		Integer hiddenWho = Integer.valueOf(request.getParameter("hiddenWho"));
		System.out.println("我進來ReplyRoomMail裡了");
		// 取得會員Id
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO) session.getAttribute("customer");
		Integer custId = customerVO.getCustId();
		// 取得訊息內容
		String msg = request.getParameter("msg");
		// 把資料送進資料庫
		RoomMailServiceImpl dao = new RoomMailServiceImpl();
		dao.sendMsg(custId, hiddenWho, msg, custId);
		// 去資料庫抓所有廠商的資料，並轉送到前端
		CompanyDaoJNDI14 companyDaoJNDI14 = new CompanyDaoJNDI14();
		List<Company> companyList = companyDaoJNDI14.queryCompanyAll();
		request.setAttribute("companyList", companyList);
		// 再去資料庫把住宿信箱的資料抓出來，存入request，在前端把資料呈現出來
		RoomMailJDBCDAO dao2 = new RoomMailJDBCDAO();
		List<RoomMailVO> list = dao2.getAllByCustId(custId);
		request.setAttribute("list", list);
		RequestDispatcher successView = request.getRequestDispatcher("/front-end/cust/showRoomMail.jsp");
		successView.forward(request, response);
	}

}
