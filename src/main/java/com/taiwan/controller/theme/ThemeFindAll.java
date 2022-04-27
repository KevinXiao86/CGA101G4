package com.taiwan.controller.theme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.taiwan.beans.Theme;
import com.taiwan.service.theme.ThemeService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/theme/findAll")
public class ThemeFindAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ThemeService themeService = ControllerUtil.getBean(ThemeService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Theme> themeList = new ArrayList<Theme>();
		themeList = themeService.findAll();
		request.setAttribute("list", themeList);
		RequestDispatcher rd = request.getRequestDispatcher("/back-end/theme/theme_findAll.jsp");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
