package com.taiwan.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CompanyUtils {

	//登入時的數據校驗
	public static Map<String, String> checkLogin(Map map){
		//用來存儲錯誤訊息
		Map<String, String> errorMap = new HashMap<String, String>();
		
		//獲取 key 的迭代器
        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext()){
        	//獲取到 key 值
            String key = (String)iterator.next();
            
            //判斷遍歷到的 key 是不是 cmpAccount
            if ("cmpAccount".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);            	
            	//進行 cmpAccount 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (values[i].trim().equals("")) {
						errorMap.put(key, "請輸入帳號");
					}
            		if (!values[i].matches("^\\w+$")) {
            			errorMap.put(key, "只能輸入由數字、26個英文字母或者下劃線組成的字符串");	
					}
            	}
            }

            //判斷遍歷到的 key 是不是 cmpPassword
            if ("cmpPassword".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);
            	//進行 cmpPassword 值的判斷
            	for(int i = 0; i < values.length; i++) {            		
            		if (values[i].trim().equals("")) {
            			errorMap.put(key, "請輸入密碼");
            		}
            	}
			}
        }
		return errorMap;
	}

	
	//註冊時的數據校驗
	public static Map<String, String> checkRegist(Map map){
		//用來存儲錯誤訊息
		Map<String, String> errorMap = new HashMap<String, String>();
		
		//獲取 key 的迭代器
        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext()){
        	//獲取到 key 值
            String key = (String)iterator.next();
            
            //判斷遍歷到的 key 是不是 cmpName
            if ("cmpName".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);            	
            	//進行 cmpAccount 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (values[i].trim().equals("")) {
            			errorMap.put(key, "廠商名稱不能為空!");	
					}
            	}
            }
        }
		return errorMap;		
	}
}
