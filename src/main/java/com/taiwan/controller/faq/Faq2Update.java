package com.taiwan.controller.faq;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.FaqVO;
import com.taiwan.beans.News;
import com.taiwan.service.faq.FaqService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/faq/faq2Update")
public class Faq2Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FaqService faqService=ControllerUtil.getBean(FaqService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 接收請求參數
			Integer faqId = Integer.valueOf(request.getParameter("faqId"));
			// 獲取查詢的結果
			FaqVO faqVO=faqService.findById(faqId);
			// 對request域塞資料
			request.setAttribute("faqVO", faqVO);
			// 請求轉發到/news/news.update.jsp
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/faq/faq_update.jsp");
			rd.forward(request, response);
			// 處理其他錯誤
		} catch (Exception e) {
			errorMsgs.put("anotherMsg", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/faq/findAll");
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
