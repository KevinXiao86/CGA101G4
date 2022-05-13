package com.taiwan.dao.getCoupon.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.CouponVO;
import com.taiwan.beans.CustCoupon;
import com.taiwan.dao.coupon.impl.CouponJDBCDAO;
import com.taiwan.dao.getCoupon.GetCoupon_interface;

public class GetCouponJDBCDAO implements GetCoupon_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";
	
	private static final String GET_ALL = "SELECT cop_id, cop_name, introduce, discount, startdate, enddate"
			 								+ ", img, status FROM COUPON  where STATUS = '上架'";

	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCopId(rs.getInt(1));
				couponVO.setCopName(rs.getString(2));
				couponVO.setIntroduce(rs.getString(3));
				couponVO.setDiscount(rs.getInt(4));
				couponVO.setStartdate(rs.getTimestamp(5));
				couponVO.setEnddate(rs.getTimestamp(6));
				couponVO.setImg(rs.getString(7));
				couponVO.setStatus(rs.getString(8));
				list.add(couponVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
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
	public int insertCustCoupon(CustCoupon obj) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
//public static void main(String[] args) {
//	
//	CouponJDBCDAO dao = new CouponJDBCDAO();
//	
//	List<CouponVO> list3 = dao.queryAll();
//	for (CouponVO cop : list3) {
//		System.out.println(cop.toString());
//	}
//}
}
