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

@WebServlet("/theme/selectById")
public class ThemeSelectById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ThemeService themeService = ControllerUtil.getBean(ThemeService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 接受請求的參數
			String themeIdString = request.getParameter("themeId");
			Integer themeId = null;
			// 確認用戶有沒有輸入東西
			if (themeIdString == null || themeIdString.trim().equals("")) {
				errorMsgs.put("themeId", "查詢框裡不能空白");
			} else {
				// 確認用戶是不是輸入數字
				try {
					// 字串轉成Integer
					themeId = Integer.valueOf(themeIdString);
				} catch (Exception e) {
					errorMsgs.put("themeIdChange", "請輸入數字");
				}
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/theme/theme_index.jsp");
				rd.forward(request, response);
				return;
			}

			// 開始查詢
			Theme theme = themeService.findById(themeId);
			if (theme == null) {
				errorMsgs.put("theme", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/theme/theme_index.jsp");
				rd.forward(request, response);
				return;
			}
			// 把查到的coupon放到request域中
			request.setAttribute("theme", theme);
			// 準備請求轉向跳轉頁面
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/theme/theme_id.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			errorMsgs.put("其他錯誤發生", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/theme/theme_index.jsp");
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
