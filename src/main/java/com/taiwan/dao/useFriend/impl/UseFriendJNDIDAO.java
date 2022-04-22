package com.taiwan.dao.useFriend.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.taiwan.beans.UseFriendVO;
import com.taiwan.dao.useFriend.UseFriendDao_interface;


public class UseFriendJNDIDAO implements UseFriendDao_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
	private static final String SET_USE_FRIEND = "INSERT INTO USE_FRIEND (CUST_ID,FRIEND) VALUES(?,?);";
	private static final String UPDATE_USE_FRIEND = "UPDATE USE_FRIEND SET FRIEND =? WHERE USE_FRIEND_ID=?;";
	private static final String GET_USE_FRIEND = "SELECT USE_FRIEND_ID ,CUST_ID ,FRIEND FROM USE_FRIEND WHERE CUST_ID=?;";
	private static final String DELETE_USE_FRIEND = "DELETE FROM USE_FRIEND WHERE USE_FRIEND_ID=?;";

	@Override
	public void setUseFriend(Integer custId, String friend) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(SET_USE_FRIEND);
			ps.setInt(1, custId);
			ps.setString(2, friend);

			int count = ps.executeUpdate();
			System.out.println(count + "success");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void updateUseFriend(Integer useFriendId, String friend) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(UPDATE_USE_FRIEND);
			ps.setString(1, friend);
			ps.setInt(2, useFriendId);

			int count = ps.executeUpdate();
			System.out.println(count + "success");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<UseFriendVO> getUseFriend(Integer custId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<UseFriendVO> list = new ArrayList<UseFriendVO>();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(GET_USE_FRIEND);
			ps.setInt(1, custId);
			rs = ps.executeQuery();

			while (rs.next()) {
				UseFriendVO useFriendVO = new UseFriendVO();
				useFriendVO.setUseFriendId(rs.getInt("USE_FRIEND_ID"));
				useFriendVO.setCustId(rs.getInt("CUST_ID"));
				useFriendVO.setFriend(rs.getString("FRIEND"));
				list.add(useFriendVO);
			}
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return list;
	}

	@Override
	public void deleteUseFriend(Integer useFriendId) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(DELETE_USE_FRIEND);
			ps.setInt(1, useFriendId);

			int count = ps.executeUpdate();
			System.out.println(count + "success");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

}
