package com.taiwan.utils;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory sessionFactory;
	
	private MyBatisUtil() {
	}
	
	private static SqlSessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try(InputStream is = Resources.getResourceAsStream("mybatis-config.xml")){
				sessionFactory = new SqlSessionFactoryBuilder().build(is);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
	
	public static SqlSession getSession() {
		return getSessionFactory().openSession();
	}
}