package com.taiwan.controller.news;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.News;
import com.taiwan.service.news.NewsService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/news/selectById")
public class NewsSelectById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NewsService newsService = ControllerUtil.getBean(NewsService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 接受請求的參數
			String newsIdString = request.getParameter("newsId");
			Integer newsId = null;
			// 確認用戶有沒有輸入東西
			if (newsIdString == null || newsIdString.trim().equals("")) {
				errorMsgs.put("newsId", "查詢框裡不能空白");
			} else {
				// 確認用戶是不是輸入數字
				try {
					// 字串轉成Integer
					newsId = Integer.valueOf(newsIdString);
				} catch (Exception e) {
					errorMsgs.put("newsIdChange", "請輸入數字");
				}
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/news/news_index.jsp");
				rd.forward(request, response);
				return;
			}

			// 開始查詢
			News news = newsService.findById(newsId);
			if (news == null) {
				errorMsgs.put("news", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/news/news_index.jsp");
				rd.forward(request, response);
				return;
			}
			// 把查到的news放到request域中
			request.setAttribute("news", news);
			// 準備請求轉向跳轉頁面
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/news/news_id.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			errorMsgs.put("其他錯誤發生", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/news/news.jsp");
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
