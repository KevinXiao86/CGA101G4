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

import com.taiwan.service.faq.FaqService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/faq/faqCreator")
public class FaqCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FaqService faqService=ControllerUtil.getBean(FaqService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 這邊確認輸入的FAQ名稱不是空值或是空字串
			String title = request.getParameter("title");
			if (title == null || title.trim().equals("")) {
				errorMsgs.put("title", "FAQ名稱請勿全部都輸入空白");
			}
			// 這邊確認FAQ內文是不是空值或是空字串
			String content = request.getParameter("content");
			if (content == null || content.trim().equals("")) {
				errorMsgs.put("content", "FAQ內文請勿不輸入，或是只有空白");
			}
			// 如果錯誤訊息的map不是空值的話，就請求轉發回/news/news_create.jsp
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/faq/faq_create.jsp");
				rd.forward(request, response);
				return;
			}
			// 開始新增資料
			faqService.addFaq(title, content);
			// 新增完成，請求轉發到theme首頁
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/faq/faq_index.jsp");
			rd.forward(request, response);
			// 其他錯誤處理
		} catch (Exception e) {
			errorMsgs.put("Exception", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/faq/faq_create.jsp");
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
