package com.taiwan.utils;

import com.google.gson.Gson;

public class JsonUtils {

	public static <T> T jason2Pojo(String jsonString, Class<T> cls) {
		T t = null;
		try {
			Gson gson = new Gson();
			t = gson.fromJson(jsonString, cls);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String pojo2Json(Object obj) {
		String str = null;
		try {
			Gson gson = new Gson();
			str = gson.toJson(obj);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
