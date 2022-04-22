package com.taiwan.dao.theme;


import java.util.List;

import com.taiwan.beans.Theme;
import com.taiwan.dao.Dao;


public interface ThemeDao extends Dao<Theme, Integer>{

	//根據title來搜尋活動主題
	public List<Theme> queryThemeByTitle(String title);
}
