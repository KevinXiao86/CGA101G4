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
@WebServlet("/faq/selectById")
public class FaqSelctById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FaqService faqService=ControllerUtil.getBean(FaqService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 接受請求的參數
			String faqIdString = request.getParameter("faqId");
			Integer faqId = null;
			// 確認用戶有沒有輸入東西
			if (faqIdString == null || faqIdString.trim().equals("")) {
				errorMsgs.put("newsId", "查詢框裡不能空白");
			} else {
				// 確認用戶是不是輸入數字
				try {
					// 字串轉成Integer
					faqId = Integer.valueOf(faqIdString);
				} catch (Exception e) {
					errorMsgs.put("newsIdChange", "請輸入數字");
				}
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/faq/faq_index.jsp");
				rd.forward(request, response);
				return;
			}

			// 開始查詢
			FaqVO faqVO=faqService.findById(faqId);
			if (faqVO == null) {
				errorMsgs.put("faq", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/faq/faq_index.jsp");
				rd.forward(request, response);
				return;
			}
			// 把查到的news放到request域中
			request.setAttribute("faqVO", faqVO);
			// 準備請求轉向跳轉頁面
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/faq/faq_id.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			errorMsgs.put("其他錯誤發生", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/faq/faq_index.jsp");
			rd.forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
