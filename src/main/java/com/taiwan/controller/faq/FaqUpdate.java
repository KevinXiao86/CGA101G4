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
import com.taiwan.service.faq.FaqService;
import com.taiwan.utils.ControllerUtil;
@WebServlet("/faq/faqUpdate")
public class FaqUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FaqService faqService=ControllerUtil.getBean(FaqService.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		FaqVO faqVO=new FaqVO();
		try {
			// 獲取faqId的值
			String faqIdString = request.getParameter("faqId");
			Integer faqId = Integer.valueOf(faqIdString);
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
			
			faqVO.setFaqId(faqId);
			faqVO.setTitle(title);
			faqVO.setContent(content);

			// 如果錯誤訊息的map不是空值的話，就請求轉發回/news/news_update.jsp
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("faqVO", faqVO);
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/faq/faq_update.jsp");
				rd.forward(request, response);
				return;
			}
			// 開始新增資料
			faqService.update(faqId, title, content);
			// 新增完成，請求轉發到news首頁
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/faq/faq_index.jsp");
			rd.forward(request, response);
			// 其他錯誤處理
		} catch (Exception e) {
			request.setAttribute("faqVO", faqVO);
			errorMsgs.put("Exception", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/faq/faq_update.jsp");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
