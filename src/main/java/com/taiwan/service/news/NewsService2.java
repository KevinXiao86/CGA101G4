package com.taiwan.service.news;

import java.util.List;

import com.taiwan.beans.News;
import com.taiwan.dao.news.NewsDao;
import com.taiwan.dao.news.impl.NewsJDBCDaoImpl;

public class NewsService2 {
	
	private NewsDao dao;
	
	public NewsService2() {
		dao = new NewsJDBCDaoImpl();
	}
	
	public List<News> findAll(){
		return dao.queryAll();
	}
}
