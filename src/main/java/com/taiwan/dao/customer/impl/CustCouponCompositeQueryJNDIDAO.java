package com.taiwan.dao.customer.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.taiwan.beans.CustCoupon;
import com.taiwan.dao.customer.CustCouponCompositeQueryDAO_interface;

public class CustCouponCompositeQueryJNDIDAO implements CustCouponCompositeQueryDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = (Context) new InitialContext();
			ds = (DataSource) ((InitialContext) ctx).lookup("java:comp/env/jdbc/TestDB2");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<CustCoupon> getAll(Map<String, String[]> map) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CustCoupon> list = new ArrayList<CustCoupon>();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(
					"SELECT * FROM CUST_COUPON " + get_whereCondition(map) + " ORDER BY GETDATE DESC;");

			rs = ps.executeQuery();
			while (rs.next()) {
				CustCoupon custCoupon = new CustCoupon();
				custCoupon.setCustCopId(rs.getInt("CUST_COP_ID"));
				custCoupon.setCustId(rs.getInt("CUST_ID"));
				custCoupon.setCopId(rs.getInt("COP_ID"));
				custCoupon.setGetdate(rs.getTimestamp("GETDATE"));
				custCoupon.setUsedate(rs.getTimestamp("USEDATE"));
				custCoupon.setRoomOrderId(rs.getInt("ROOM_ORDER_ID"));
				custCoupon.setTktOrderId(rs.getInt("TKT_ORDER_ID"));
				custCoupon.setDiscount(rs.getInt("DISCOUNT"));
				custCoupon.setStatus(rs.getString("STATUS"));
				list.add(custCoupon);
			}
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

	public static String get_whereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition(key, value.trim());
				if (count == 1) {
					whereCondition.append(" WHERE " + aCondition);
				} else {
					whereCondition.append(" AND " + aCondition);
				}
			}
		}
		return whereCondition.toString();
	}

	private static String get_aCondition(String key, String value) {
		String aCondition = null;
		if ("GETDATE".equals(key) || "USEDATE".equals(key) || "STATUS".equals(key)) {
			aCondition = key + "=" + "'" + value + "'";
		} else if("DISCOUNT".equals(key)){
			aCondition=key+" "+value;
		}else if("COP_ID".equals(key)){
			aCondition=key+" IN "+"(SELECT COP_ID FROM COUPON WHERE COP_NAME LIKE '%"+value+"%')";
		}else {
			aCondition = key + "=" + value;
		}
		return aCondition + " ";
	}
}
