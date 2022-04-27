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

import com.taiwan.service.news.NewsService;
import com.taiwan.utils.ControllerUtil;
@WebServlet("/news/newsDelete")
public class NewsDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NewsService newsService=ControllerUtil.getBean(NewsService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String,String> errorMsgs=new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			//接收要刪除的newsId
			Integer newsId=Integer.valueOf(request.getParameter("newsId"));
			//開始刪除資料
			newsService.delete(newsId);
			//刪除成功，準備請求轉向
			RequestDispatcher rd=request.getRequestDispatcher("/news/findAll");
			rd.forward(request, response);
			
			
		}catch (Exception e) {
			errorMsgs.put("delete error",e.getMessage());
			RequestDispatcher rd=request.getRequestDispatcher("/news/findAll");
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
