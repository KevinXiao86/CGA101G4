package com.taiwan.dao.custcoupon12.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.taiwan.beans.CustCoupon;
import com.taiwan.dao.custcoupon12.Custcoupon12;

public class CustcouponDao12 implements Custcoupon12 {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";
	private static final String find = "SELECT * FROM Taiwan.CUST_COUPON where CUST_COP_ID=? ";
	@Override
	public int updateCustCouponStatusByRoomOrderId(Integer custId, Integer roomOrderId,Integer copId, Connection con) {
		int count = 0;
		PreparedStatement pstmt = null;
		String sql = "update CUST_COUPON set room_order_id=?,status=?,USEDATE=? where cust_id=? and cop_id=? ;";
		try { 
			Long datetime = System.currentTimeMillis();
        Timestamp now = new Timestamp(datetime);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, roomOrderId);
			pstmt.setString(2, "已使用");
			pstmt.setTimestamp(3, now);
			pstmt.setInt(4, custId);
			pstmt.setInt(5, copId);

			count = pstmt.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-roomItem");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
		}
		return count;
	}
	@Override
	public CustCoupon searchCustCouponById(Integer custCouponId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustCoupon custCoupon=new CustCoupon();
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(find);
			pstmt.setInt(1, custCouponId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				custCoupon.setCustCopId(rs.getInt("CUST_COP_ID"));
				custCoupon.setCustId(rs.getInt("CUST_ID"));
				custCoupon.setCopId(rs.getInt("COP_ID"));
				custCoupon.setDiscount(rs.getInt("DISCOUNT"));
				custCoupon.setStatus(rs.getString("STATUS"));
			
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
	
		return custCoupon;
		
	}
}