package com.taiwan.service.news;

import java.util.List;

import com.taiwan.beans.News;

public interface NewsService {
	
	//搜尋全部最新消息
	public List<News> findAll();

}
