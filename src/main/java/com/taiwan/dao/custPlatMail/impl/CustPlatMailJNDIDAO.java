package com.taiwan.dao.custPlatMail.impl;

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

import com.taiwan.beans.CustPlatMailVO;
import com.taiwan.dao.custPlatMail.CustPlatMailDao_interface;

import static java.sql.Types.*;

public class CustPlatMailJNDIDAO implements CustPlatMailDao_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
	private static final String GET_CUST_PLAT_MAIL = "SELECT CUST_PLAT_ID ,CUST_ID ,EMP_ID ,MSG,CUST_PLAT_TIME ,WHO"
			+ " FROM CUST_PLAT_MAIL LIMIT ? OFFSET ? ;";
	private static final String SET_CUST_PLAT_MAIL = "INSERT INTO CUST_PLAT_MAIL(CUST_ID,EMP_ID,MSG,WHO) VALUES(?,?,?,?);";

	@Override
	public List<CustPlatMailVO> getCust_Plat_Mail(Integer rowNum, Integer offset) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CustPlatMailVO> list = new ArrayList<CustPlatMailVO>();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(GET_CUST_PLAT_MAIL);
			ps.setInt(1, rowNum);
			ps.setInt(2, offset);
			rs = ps.executeQuery();
			while (rs.next()) {
				CustPlatMailVO custPlatMailVO = new CustPlatMailVO();
				custPlatMailVO.setCustPlatId(rs.getInt(1));
				custPlatMailVO.setCustId(rs.getInt(2));
				custPlatMailVO.setEmpId(rs.getInt(3));
				custPlatMailVO.setMsg(rs.getString(4));
				custPlatMailVO.setCustPlatTime(rs.getTimestamp(5));
				custPlatMailVO.setWho(rs.getInt(6));
				list.add(custPlatMailVO);
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
	public void setCust_Plat_Mail(CustPlatMailVO mail) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(SET_CUST_PLAT_MAIL);
			ps.setInt(1, mail.getCustId());
			if (mail.getEmpId() == null) {
				ps.setNull(2, INTEGER);
			} else {
				ps.setInt(2, mail.getEmpId());
			}
			ps.setString(3, mail.getMsg());
			ps.setInt(4, mail.getWho());
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
