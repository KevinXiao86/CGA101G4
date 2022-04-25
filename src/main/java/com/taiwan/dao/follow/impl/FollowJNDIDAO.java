package com.taiwan.dao.follow.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

import com.taiwan.beans.FollowVO;
import com.taiwan.dao.follow.FollowDAO_interface;


public class FollowJNDIDAO implements FollowDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String insert = "INSERT INTO Taiwan.FOLLOW (CUST_ID, CMP_ID) VALUES (?, ?)";
	private static final String find = "SELECT * FROM Taiwan.FOLLOW where CUST_ID=? ";
	private static final String delete = "DELETE from Taiwan.FOLLOW where cust_id= ? and cmp_id = ?";

	@Override
	public List<FollowVO> queryFollowByCustId(Integer custId) {
		List<FollowVO> list = new ArrayList<FollowVO>();
		FollowVO FollowVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(find);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				FollowVO = new FollowVO();
				FollowVO.setCustId(rs.getInt("cmp_plat_mail_who"));
				FollowVO.setCmpId(rs.getInt("cmp_plat_mail_msg"));

				list.add(FollowVO); // Store the row in the list
			}

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(insert);

			pstmt.setInt(1, followVO.getCustId());
			pstmt.setInt(2, followVO.getCmpId());

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public void deleteFollow(Integer custId, Integer cmpId) {

	}
}
