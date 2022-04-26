package com.taiwan.service.theme.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiwan.beans.Theme;
import com.taiwan.service.theme.ThemeService;
import com.taiwan.test.news.newsTest;

import mybatis.mapper.ThemeMapper;

@Service
public class ThemeServiceImpl implements ThemeService {

	@Autowired
	private ThemeMapper mapper;

	@Transactional
	@Override
	public List<Theme> findAll() {
		return mapper.queryAll();
	}

	@Transactional
	@Override
	public boolean addtheme(String title, String content, String img) {
		Theme theme=new Theme();
		theme.setTitle(title);
		theme.setContent(content);
		theme.setImg(img);
		return mapper.insert(theme)>0;
	}

	@Transactional
	@Override
	public boolean delete(Integer themeId) {
		return mapper.delete(themeId)>0;
	}

	@Transactional
	@Override
	public boolean update(Integer themeId,String title, String content, String img) {
		Theme theme=new Theme();
		theme.setThemeId(themeId);
		theme.setTitle(title);
		theme.setContent(content);
		theme.setImg(img);
		return mapper.updateById(theme)>0;
	}

	@Transactional
	@Override
	public Theme findById(Integer themeId) {
		return mapper.queryById(themeId);
	}

	@Transactional
	@Override
	public List<Theme> findByTitle(String title) {
		return mapper.queryThemeByTitle(title);
	}

}
