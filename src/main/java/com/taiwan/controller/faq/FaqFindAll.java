package com.taiwan.controller.faq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.FaqVO;
import com.taiwan.service.faq.FaqService;
import com.taiwan.utils.ControllerUtil;
@WebServlet("/faq/findAll")
public class FaqFindAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FaqService faqService=ControllerUtil.getBean(FaqService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<FaqVO> faqList = new ArrayList<FaqVO>();
		faqList = faqService.findAll();
		request.setAttribute("list", faqList);
		RequestDispatcher rd = request.getRequestDispatcher("/back-end/faq/faq_findAll.jsp");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
