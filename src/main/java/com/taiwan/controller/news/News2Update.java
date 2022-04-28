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

@WebServlet("/news/news2Update")
public class News2Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NewsService newsService = ControllerUtil.getBean(NewsService.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 接收請求參數
			Integer newsId = Integer.valueOf(request.getParameter("newsId"));
			// 獲取查詢的結果
			News news = newsService.findById(newsId);
			// 對request域塞資料
			request.setAttribute("news", news);
			// 請求轉發到/news/news.update.jsp
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/news/news_update.jsp");
			rd.forward(request, response);
			// 處理其他錯誤
		} catch (Exception e) {
			errorMsgs.put("anotherMsg", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/news/findAll");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
