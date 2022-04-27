package com.taiwan.service.theme;

import java.util.List;

import com.taiwan.beans.Theme;

public interface ThemeService {

	// 查詢所有主題活動
	public List<Theme> findAll();

	// 新增一筆主題活動
	public boolean addtheme(String title, String content, String img);

	// 刪除一筆主題活動
	public boolean delete(Integer themeId);

	// 更新一筆主題活動
	public boolean update(Integer themeId,String title, String content, String img);

	// 根據Id查詢主題活動
	public Theme findById(Integer themeId);

	// 根據標題作模糊查詢
	public List<Theme> findByTitle(String title);
}
