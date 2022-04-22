package com.taiwan.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@Component
public class ControllerUtil {
	 public static <T> T getBean(Class<T> clazz){
		//  ContextLoader 裡面有一個
		//  private WebApplicationContext context 屬性就是 ioc 容器
		//
		//  有一個靜態方法可以獲取到 ioc 容器
		//  ContextLoader.getCurrentWebApplicationContext(); 
		  WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		  return context.getBean(clazz);
		 }
}
