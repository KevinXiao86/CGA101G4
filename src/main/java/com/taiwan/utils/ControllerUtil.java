package com.taiwan.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@Component
public class ControllerUtil {

	public static <T> T getBean(Class<T> clazz) {
		// ContextLoader 裡面有一個
		// private WebApplicationContext context 屬性就是 ioc 容器
		//
		// 有一個靜態方法可以獲取到 ioc 容器
		// ContextLoader.getCurrentWebApplicationContext();
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		return context.getBean(clazz);
	}
	
	
	// 檢查表單是否重複提交
	public static boolean checkRepeatSubmit(HttpServletRequest request) {
		// 這是獲取 session 中的 token
		Object sessionTokenObj = request.getSession().getAttribute("token");
		if (sessionTokenObj == null) {
			// 表單重複提交
			System.out.println("表單重複提交");
			return true;
		}

		// 獲取從頁面發送過來的 token
		String paramToken = request.getParameter("token");
		if (paramToken == null) {
			// 非法請求
			System.out.println("非法請求");
			return true;
		}

		if (!paramToken.equals(sessionTokenObj.toString())) {
			// 非法請求
			System.out.println("非法請求");
			return true;
		}

		return false;
	}
	
	
	
}
