package com.taiwan.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.sql.DataSource;

public class JndiUtil {
	private static Connection conn = null;
	private static Context ctx = null;
	private static DataSource ds = null;

	public static Connection getConnection() throws Exception {
		if (conn == null) {
			try {
				ctx = new javax.naming.InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
				conn = ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

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

}
