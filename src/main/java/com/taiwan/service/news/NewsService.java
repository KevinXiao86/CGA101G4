package com.taiwan.service.news;

import java.util.List;

import com.taiwan.beans.News;
import com.taiwan.beans.Theme;

public interface NewsService {

	// 搜尋全部最新消息
	public List<News> findAll();

	// 新增一筆主題活動
	public boolean addNews(String title, String content, String img);

	// 刪除一筆主題活動
	public boolean delete(Integer newsId);

	// 更新一筆主題活動
	public boolean update(Integer newsId, String title, String content, String img);

	// 根據Id查詢主題活動
	public News findById(Integer newsId);

	// 根據標題作模糊查詢
	public List<News> findByTitle(String title);
}
