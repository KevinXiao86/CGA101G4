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

@WebServlet("/cust/ShowRoomMail")
public class ShowRoomMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowRoomMail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 從session中取得會員的custId
		HttpSession session = request.getSession();
		CustomerVO customerVO = (CustomerVO) session.getAttribute("customer");
		Integer custId = customerVO.getCustId();
		// 用會員的custId到資料庫抓住宿信箱的資料
		RoomMailJDBCDAO dao = new RoomMailJDBCDAO();
		List<RoomMailVO> list = dao.getAllByCustId(custId);
		// 去資料庫抓所有廠商的資料，並轉送到前端
		CompanyDaoJNDI14 companyDaoJNDI14 = new CompanyDaoJNDI14();
		List<Company> companyList = companyDaoJNDI14.queryCompanyAll();
		request.setAttribute("companyList", companyList);
		// 把資料丟到request的list屬性裡，並轉送到前端
		request.setAttribute("list", list);
		RequestDispatcher successView = request.getRequestDispatcher("/front-end/cust/showRoomMail.jsp");
		successView.forward(request, response);
	}

}
