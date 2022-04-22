package com.taiwan.utils;

public class Common {
	// MySQL 8之後連線URL需加上SSL與時區設定
	public final static String URL = "jdbc:mysql://104.199.153.224:3306/Taiwan?allowMultiQueries=true&useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	// MySQL 8之前
	// public final static String URL = "jdbc:mysql://localhost:3306/bookshop_jdbc";
	
	public final static String USER = "root";
	public final static String PASSWORD = "rootitri";
}
