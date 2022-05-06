package com.taiwan.utils;

public class CommonUtils {
	//將字符串轉成int類型
	public static int parseInt(String id, int defaultValue) {
		try {
			return Integer.parseInt(id);
		} catch (Exception e) {}
		//轉換失敗返回默認值
		return defaultValue;
	}
}
