package com.taiwan.service.news.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiwan.beans.News;
import com.taiwan.service.news.NewsService;

import mybatis.mapper.NewsMapper;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsMapper mapper;

	@Transactional
	@Override
	public List<News> findAll() {
		return mapper.queryAll();
	}
	@Transactional
	@Override
	public boolean addNews(String title, String content, String img) {
		News news=new News();
		news.setTitle(title);
		news.setContent(content);
		news.setImg(img);
		return mapper.insert(news)>0;
	}
	@Transactional
	@Override
	public boolean delete(Integer newsId) {
		return mapper.delete(newsId)>0;
	}
	@Transactional
	@Override
	public boolean update(Integer newsId, String title, String content, String img) {
		News news=new News();
		news.setNewsId(newsId);
		news.setTitle(title);
		news.setContent(content);
		news.setImg(img);
		return mapper.updateById(news)>0;
	}
	@Transactional
	@Override
	public News findById(Integer newsId) {
		News news=mapper.queryById(newsId);
		return news;
	}
	@Transactional
	@Override
	public List<News> findByTitle(String title) {
		List<News> ls=new ArrayList<News>();
		ls=mapper.queryNewsByTitle(title);
		return ls;
	}

}
