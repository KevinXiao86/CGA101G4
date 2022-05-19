package com.taiwan.service.theme;

import java.util.List;

import com.taiwan.beans.Theme;
import com.taiwan.dao.theme.ThemeDao;
import com.taiwan.dao.theme.impl.ThemeJDBCDaoImpl;

public class ThemeService2 {
	
	private ThemeDao dao;

	public ThemeService2() {
		dao = new ThemeJDBCDaoImpl();
	}
	
	public List<Theme> findAll(){
		return dao.queryAll();
	}
}
