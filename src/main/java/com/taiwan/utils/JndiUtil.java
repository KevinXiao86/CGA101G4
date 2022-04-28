package com.taiwan.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.sql.DataSource;

public class JndiUtil {
//	private static Connection conn = null;
	private static Context ctx = null;
	private static DataSource ds = null;

	public static Connection getConnection() throws Exception {
		Connection conn=null;
//		if (conn == null) {
			try {
				ctx = new javax.naming.InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
				conn = ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
//		}

		return conn;
	}

	public void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	public static void closeResource(Connection conn, Statement ps, ResultSet rs){
		try {
			if(ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeResource(Connection conn, Statement ps){
		try {
			if(ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
