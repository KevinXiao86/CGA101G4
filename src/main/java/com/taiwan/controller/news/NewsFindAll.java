package com.taiwan.controller.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.News;
import com.taiwan.service.news.NewsService;
import com.taiwan.utils.ControllerUtil;
@WebServlet("/news/findAll")
public class NewsFindAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NewsService newsService = ControllerUtil.getBean(NewsService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> newsList = new ArrayList<News>();
		newsList = newsService.findAll();
		request.setAttribute("list", newsList);
		RequestDispatcher rd = request.getRequestDispatcher("/back-end/news/news_findAll.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
