package com.taiwan.dao.follow.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.FollowVO;
import com.taiwan.dao.follow.FollowDAO_interface;

public class FollowJDBCDAO implements FollowDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";
	private static final String insert = "INSERT INTO Taiwan.FOLLOW (CUST_ID, CMP_ID) VALUES (?, ?)";
	private static final String find = "SELECT * FROM Taiwan.FOLLOW where CUST_ID=? ";
	private static final String delete = "SELECT * FROM Taiwan.FOLLOW where CMP_ID=? and EMP_ID=? ";

	@Override
	public List<FollowVO> queryFollowByCust_id(Integer cust_id) {
		List<FollowVO> list = new ArrayList<FollowVO>();
		FollowVO FollowVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(find);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				FollowVO = new FollowVO();
				FollowVO.setCust_id(rs.getInt("cmp_plat_mail_who"));
				FollowVO.setCmp_id(rs.getInt("cmp_plat_mail_msg"));

				list.add(FollowVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public void insertFollow(FollowVO followVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(insert);

			pstmt.setInt(1, followVO.getCust_id());
			pstmt.setInt(2, followVO.getCmp_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void deleteFollow(Integer cust_id, Integer cmp_id) {

	}
}
