package com.taiwan.controller.customerManage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.CustCoupon;
import com.taiwan.beans.CustomerVO;
import com.taiwan.dao.customer.impl.CustomerJNDIDAO;
import com.taiwan.service.custCoupon.impl.CustCouponServiceImpl14;
import com.taiwan.service.customer.impl.CustomerServiceImpl;
import com.taiwan.test.news.newsTest;

@WebServlet("/custManage/CustInfoManage")
public class CustInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustInfoManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 先判斷想改custRight還是查詢會員資料
		String action = request.getParameter("action");
		// 取得會員Id
		Integer custId = Integer.valueOf(request.getParameter("custId"));
		// 想修改custRight就進來這個if
		if ("setCustRight".equals(action)) {
			// 取得會員是要停權還是復權
			String custRight = request.getParameter("custRight");
			System.out.println("custRight=" + custRight);
			if (custRight.equals("復權")) {
				custRight = "正常";
			}
			// 去資料庫更改會員權限
			CustomerServiceImpl dao = new CustomerServiceImpl();
			dao.setCustRight(custId, custRight);
			//轉送至前端
			RequestDispatcher successView = request.getRequestDispatcher("/back-end/customer/custInfoManage.jsp");
			successView.forward(request, response);
		}

		// 想查詢基本資料就進來這個if
		if ("showCustomerInformation".equals(action)) {
			// 用custId到資料庫把會員資料撈出來，存入request，轉送前端
			CustomerServiceImpl dao = new CustomerServiceImpl();
			CustomerVO customerVO = dao.getAll(custId);
			request.setAttribute("customer", customerVO);
			RequestDispatcher successView = request.getRequestDispatcher("/back-end/customer/searchCustInfo.jsp");
			successView.forward(request, response);

		}
		
		// 想查看優惠券就進來這個if
				if ("showCustCoupon".equals(action)) {
					// 用custId到資料庫把custCoupon撈出來，存入request，轉送前端
					CustCouponServiceImpl14 dao = new CustCouponServiceImpl14();
					List<CustCoupon> list = dao.queryCustCouponById(custId);
					request.setAttribute("list", list);
					RequestDispatcher successView = request.getRequestDispatcher("/back-end/customer/custCouponManage.jsp");
					successView.forward(request, response);

				}

//		// 再抓一次所有會員資料往前端送
//		CustomerJNDIDAO customerJNDIDAO = new CustomerJNDIDAO();
//		List<CustomerVO> list = customerJNDIDAO.getAllNoMatterWho();
//		request.setAttribute("list", customerJNDIDAO);
		
	}

}
