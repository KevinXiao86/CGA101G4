package com.taiwan.dao.news.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.News;
import com.taiwan.dao.news.NewsDao;
import com.taiwan.utils.DbUtil;

public class NewsJDBCDaoImpl implements NewsDao {

	@Override
	public List<News> queryAll() {
		List<News> ls = new ArrayList<News>();
		String sql = "select news_id,title,content,create_date,img from NEWS;";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer newsId = (Integer) rs.getInt("news_id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp createDate = rs.getObject("create_date", Timestamp.class);
				String img = rs.getString("img");
				News newsVO = new News(newsId, title, content, createDate, img);
				ls.add(newsVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return ls;
	}

	@Override
	public News queryById(Integer id) {
		News newsVO = new News();
		String sql = "select news_id,title,content,create_date,img from NEWS where news_id=?;";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				newsVO.setNewsId(rs.getInt("news_id"));
				newsVO.setTitle(rs.getString("title"));
				newsVO.setContent(rs.getString("content"));
				newsVO.setCreateDate(rs.getObject("create_date", Timestamp.class));
				;
				newsVO.setImg(rs.getString("img"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return newsVO;
	}

	@Override
	public int insert(News obj) {
		int count = 0;
		String sql = "insert into NEWS(title,content,img) values(?,?,?);";
		try (Connection conn = DbUtil.getConnection(); 
				PreparedStatement prep = conn.prepareStatement(sql);) {
			prep.setString(1, obj.getTitle());
			prep.setString(2, obj.getContent());
			prep.setString(3, obj.getImg());
			prep.addBatch();
			count = prep.executeUpdate();
			System.out.println("success " + count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int update(News obj) {
		int count = 0;
		String sql = "update NEWS set title=?,content=?,img=? where news_id=?;";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setString(1, obj.getTitle());
			prep.setString(2, obj.getContent());
			prep.setString(3, obj.getImg());
			prep.setInt(4, obj.getNewsId());
			count = prep.executeUpdate();
			System.out.println("success " + count);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int delete(Integer id) {
		int count = 0;
		String sql = "delete from NEWS where news_id=?";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, id);
			count = prep.executeUpdate();
			System.out.println("success " + count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<News> queryNewsByTitle(String title) {
		List<News> ls = new ArrayList<News>();
		String sql = "select news_id,title,content,create_date,img from NEWS where title like ?;";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setString(1, "%" + title + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer newsId = rs.getInt("news_id");
				String queryTitle = rs.getString("title");
				String content = rs.getString("content");
				Timestamp createDate = rs.getObject("create_date", Timestamp.class);
				String img = rs.getString("img");
				News newsVO = new News(newsId, queryTitle, content, createDate, img);
				ls.add(newsVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return ls;
	}
}





