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

import com.taiwan.beans.Theme;
import com.taiwan.service.theme.ThemeService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/theme/fontSelect")
public class FontTheme extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ThemeService themeService=ControllerUtil.getBean(ThemeService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs=new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			Integer themeId=Integer.valueOf(request.getParameter("themeId"));
			Theme theme=themeService.findById(themeId);
			
			request.setAttribute("theme",theme);
			RequestDispatcher rd=request.getRequestDispatcher("/front-end/theme/theme.jsp");
			rd.forward(request, response);
			
			
		} catch (Exception e) {
			errorMsgs.put("Exception", "exception");
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
