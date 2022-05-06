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

import com.taiwan.beans.CustPlatMailVO;
import com.taiwan.beans.CustomerVO;
import com.taiwan.service.custPlatMail.impl.CustPlatMailServiceImpl;

@WebServlet("/cust/ShowEmail")
public class ShowEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ShowEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		CustomerVO customerVO=(CustomerVO) session.getAttribute("customer");
		Integer custId=customerVO.getCustId();
		//用custId抓她跟台玩之間通的信
		CustPlatMailServiceImpl dao=new CustPlatMailServiceImpl();
		List<CustPlatMailVO> list=dao.getAllByCustId(custId);
		request.setAttribute("list", list);
		RequestDispatcher successView=request.getRequestDispatcher("/front-end/cust/showEmail.jsp");
		successView.forward(request, response);
	}

}
