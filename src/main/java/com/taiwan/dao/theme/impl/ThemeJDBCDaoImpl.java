package com.taiwan.dao.theme.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.Theme;
import com.taiwan.dao.theme.ThemeDao;
import com.taiwan.utils.DbUtil;

public class ThemeJDBCDaoImpl implements ThemeDao {

	@Override
	public List<Theme> queryAll() {
		List<Theme> ls = new ArrayList<Theme>();
		String sql = "select theme_id,title,content,create_date,img from THEME order by theme_id desc;";
		try (Connection conn = DbUtil.getConnection(); 
				PreparedStatement prep = conn.prepareStatement(sql)) {
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer themeId = (Integer) rs.getInt("theme_id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp createDate = rs.getObject("create_date", Timestamp.class);
				String img = rs.getString("img");
				Theme themeVO = new Theme(themeId, title, content, createDate, img);
				ls.add(themeVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public Theme queryById(Integer id) {
		Theme themeVO = new Theme();
		String sql = "select theme_id,title,content,create_date,img from THEME where theme_id=?;";
		try (Connection conn = DbUtil.getConnection(); 
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				themeVO.setThemeId(rs.getInt("theme_id"));
				themeVO.setTitle(rs.getString("title"));
				themeVO.setContent(rs.getString("content"));
				themeVO.setDate(rs.getObject("create_date", Timestamp.class));
				themeVO.setImg(rs.getString("img"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return themeVO;
	}

	@Override
	public int insert(Theme obj) {
		int count = 0;
		String sql = "insert into THEME(title,content,img) values(?,?,?)";
		try (Connection conn = DbUtil.getConnection(); 
				PreparedStatement prep = conn.prepareStatement(sql)) {
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
	public int update(Theme obj) {
		int count = 0;
		String sql = "update THEME set title=?,content=?,img=? where theme_id=?;";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setString(1, obj.getTitle());
			prep.setString(2, obj.getContent());
			prep.setString(3, obj.getImg());
			prep.setInt(4, obj.getThemeId());
			count = prep.executeUpdate();
			System.out.println("success " + count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int delete(Integer id) {
		int count = 0;
		String sql = "delete from THEME where theme_id=?";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, id);
			count = prep.executeUpdate();
			System.out.println("success " + count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Theme> queryThemeByTitle(String title) {
		List<Theme> ls = new ArrayList<Theme>();
		String sql = "select theme_id,title,content,create_date,img from THEME where title like ?;";
		try (Connection conn = DbUtil.getConnection(); 
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setString(1, "%" + title + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer themeId = rs.getInt("theme_id");
				String queryTitle = rs.getString("title");
				String content = rs.getString("content");
				Timestamp createDate = rs.getObject("create_date", Timestamp.class);
				String img = rs.getString("img");
				Theme themeVO = new Theme(themeId, queryTitle, content, createDate, img);
				ls.add(themeVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
}
