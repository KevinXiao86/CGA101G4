package com.taiwan.dao.company12.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.taiwan.beans.Company;
import com.taiwan.dao.company12.Company12_interface;

public class CompanyDAO12 implements Company12_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";
	private static final String find = "SELECT * FROM Taiwan.COMPANY where cmp_id=? ";

	@Override
	public Company searchCmpByCmpId(Integer cmpId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Company company=new Company();
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(find);
			pstmt.setInt(1, cmpId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				company.setCmpId(rs.getInt("cmp_id"));
				company.setCmpName(rs.getString("cmp_name"));
				company.setCmpTel(rs.getString("cmp_tel"));
				company.setCmpMail(rs.getString("cmp_mail"));
				company.setCmpIntroduce(rs.getString("cmp_introduce"));
				company.setCheckinTime(rs.getString("checkin_time"));
				company.setCheckoutTime(rs.getString("checkout_time"));
				company.setLocation(rs.getString("location"));
				company.setCanx(rs.getString("canx"));
				company.setNotice(rs.getString("notice"));
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
	
		return company;
		
	}
		
}
