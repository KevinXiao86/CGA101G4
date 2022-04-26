package com.taiwan.controller.theme;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.service.theme.ThemeService;
import com.taiwan.utils.ControllerUtil;
@WebServlet("/theme/themeDelete")
public class ThemeDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ThemeService themeService=ControllerUtil.getBean(ThemeService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String,String> errorMsgs=new LinkedHashMap<String, String>();
		try {
			//接收要刪除的themeId
			Integer themeId=Integer.valueOf(request.getParameter("themeId"));
			//開始刪除資料
			themeService.delete(themeId);
			//刪除成功，準備請求轉向
			RequestDispatcher rd=request.getRequestDispatcher("/theme/findAll");
			rd.forward(request, response);
			
			
		}catch (Exception e) {
			errorMsgs.put("delete error",e.getMessage());
			RequestDispatcher rd=request.getRequestDispatcher("/theme/findAll");
			rd.forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
