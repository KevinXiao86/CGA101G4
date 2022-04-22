package com.taiwan.dao.useFriend.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.UseFriendVO;
import com.taiwan.dao.useFriend.UseFriendDao_interface;


public class UseFriendJDBCDAO implements UseFriendDao_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

	private static final String SET_USE_FRIEND = "INSERT INTO USE_FRIEND (CUST_ID,FRIEND) VALUES(?,?);";
	private static final String UPDATE_USE_FRIEND = "UPDATE USE_FRIEND SET FRIEND =? WHERE USE_FRIEND_ID=?;";
	private static final String GET_USE_FRIEND = "SELECT USE_FRIEND_ID ,CUST_ID ,FRIEND FROM USE_FRIEND WHERE CUST_ID=?;";
	private static final String DELETE_USE_FRIEND = "DELETE FROM USE_FRIEND WHERE USE_FRIEND_ID=?;";

	@Override
	public void setUseFriend(Integer custId, String friend) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(SET_USE_FRIEND);
			ps.setInt(1, custId);
			ps.setString(2, friend);

			int count = ps.executeUpdate();
			System.out.println(count + "success");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
	}

	@Override
	public void updateUseFriend(Integer useFriendId, String friend) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(UPDATE_USE_FRIEND);
			ps.setString(1, friend);
			ps.setInt(2, useFriendId);

			int count = ps.executeUpdate();
			System.out.println(count + "success");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
	}

	@Override
	public List<UseFriendVO> getUseFriend(Integer custId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<UseFriendVO> list = new ArrayList<UseFriendVO>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_USE_FRIEND);
			ps.setInt(1, custId);
			rs = ps.executeQuery();

			while (rs.next()) {
				UseFriendVO useFriendVO = new UseFriendVO();
				useFriendVO.setUseFriendId(rs.getInt("USE_FRIEND_ID"));
				useFriendVO.setCustId(rs.getInt("CUST_ID"));
				useFriendVO.setFriend(rs.getString("FRIEND"));
				list.add(useFriendVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return list;
	}

	@Override
	public void deleteUseFriend(Integer useFriendId) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(DELETE_USE_FRIEND);
			ps.setInt(1, useFriendId);

			int count = ps.executeUpdate();
			System.out.println(count + "success");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
}
