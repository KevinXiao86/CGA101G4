package com.taiwan.service.news.impl;

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

}
