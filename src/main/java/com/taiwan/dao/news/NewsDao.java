package com.taiwan.dao.news;


import java.util.List;

import com.taiwan.beans.News;
import com.taiwan.dao.Dao;


public interface NewsDao extends Dao<News, Integer>{
	//新增一條最新消息
//	public boolean insertNews(NewsVO obj);
	//搜尋全部的最新消息
//	public List<NewsVO> queryAllNews();
	//根據id來搜尋最新消息
//	public NewsVO queryNewsById(Integer newsId);
	//根據title來搜尋最新消息
	public List<News> queryNewsByTitle(String title);
	//根據id更新最新消息
//	public boolean updateNewsById(NewsVO obj);
	//刪除最新消息
//	public boolean deleteNewsById(Integer newsId);	

}

