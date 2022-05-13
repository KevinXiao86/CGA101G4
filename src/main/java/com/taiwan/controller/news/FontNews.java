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
@WebServlet("/news/fontNews")
public class FontNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NewsService newsService=ControllerUtil.getBean(NewsService.class);
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs=new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			Integer newsId=Integer.valueOf(request.getParameter("newsId"));
			News news=newsService.findById(newsId);
			System.out.println(news);
			
			request.setAttribute("news",news);
			RequestDispatcher rd=request.getRequestDispatcher("/front-end/news/news.jsp");
			rd.forward(request, response);
			
			
		} catch (Exception e) {
			errorMsgs.put("Exception", "exception");
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
