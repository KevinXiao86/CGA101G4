package com.taiwan.dao.cmpPlaMail.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.CmpPlatMailVO;
import com.taiwan.dao.cmpPlaMail.CmpPlatMailDAO_interface;


public class CmpPlatMailDAO implements CmpPlatMailDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

	private static final String insert = 
			"INSERT INTO Taiwan.CMP_PLAT_MAIL (CMP_ID, EMP_ID, CMP_PLAT_MAIL_MSG, CMP_PLAT_MAIL_WHO) VALUES (?, ?, ?, ?) ";
	private static final String find=
			"SELECT * FROM Taiwan.CMP_PLAT_MAIL where CMP_ID=? and EMP_ID=? order by CMP_PLAT_MAIL_SEND_TIME";

	@Override
	public List<CmpPlatMailVO> queryCmpPlatMailByEmpIdAndCmpId(Integer empId, Integer cmpId) {
		List<CmpPlatMailVO> list = new ArrayList<CmpPlatMailVO>();
		CmpPlatMailVO cmpPlatMailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(find);
			pstmt.setInt(1, cmpId);
			pstmt.setInt(2, empId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cmpPlatMailVO = new CmpPlatMailVO();
				cmpPlatMailVO.setCmpPlatMailWho(rs.getString("cmp_plat_mail_who"));
				cmpPlatMailVO.setCmpPlatMailMsg(rs.getString("cmp_plat_mail_msg"));

				list.add(cmpPlatMailVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void insert(CmpPlatMailVO cmpPlatMailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(insert);

			pstmt.setInt(1, cmpPlatMailVO.getCmpId());
			pstmt.setInt(2, cmpPlatMailVO.getEmpId());
			pstmt.setString(3, cmpPlatMailVO.getCmpPlatMailMsg());
			pstmt.setString(4, cmpPlatMailVO.getCmpPlatMailWho());
	

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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


}
