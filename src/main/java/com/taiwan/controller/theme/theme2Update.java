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
import javax.servlet.http.HttpSession;

import com.taiwan.beans.Theme;
import com.taiwan.service.theme.ThemeService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/theme/theme2Update")
public class theme2Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ThemeService themeService = ControllerUtil.getBean(ThemeService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Map<String, String> errorMsgs=new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			//接收請求參數
			Integer themeId=Integer.valueOf(request.getParameter("themeId"));
			String whichPage=request.getParameter("whichPage");
			//獲取查詢的結果
			Theme theme=themeService.findById(themeId);
			//對request跟session域塞資料
			request.setAttribute("theme", theme);
			session.setAttribute("whichPage", whichPage);
			//請求轉發到/theme/theme.update.jsp
			RequestDispatcher rd=request.getRequestDispatcher("/back-end/theme/theme_update.jsp");
			rd.forward(request, response);
			//處理其他錯誤
		}catch(Exception e){
			errorMsgs.put("anotherMsg", e.getMessage());
			RequestDispatcher rd=request.getRequestDispatcher("/theme/findAll");
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
