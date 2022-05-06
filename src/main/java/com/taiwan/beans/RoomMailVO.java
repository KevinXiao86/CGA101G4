package com.taiwan.beans;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoomMailVO {
	private Integer roomMailId;
	private Integer custId;
	private Integer cmpId;
	private String roomMailMsg;
	private Timestamp roomMailTime;
	private Integer roomMailIdWho;

	public Integer getRoomMailId() {
		return roomMailId;
	}

	public void setRoomMailId(Integer roomMailId) {
		this.roomMailId = roomMailId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getCmpId() {
		return cmpId;
	}

	public void setCmpId(Integer cmpId) {
		this.cmpId = cmpId;
	}

	public String getRoomMailMsg() {
		return roomMailMsg;
	}

	public void setRoomMailMsg(String roomMailMsg) {
		this.roomMailMsg = roomMailMsg;
	}

	public Timestamp getRoomMailTime() {
		return roomMailTime;
	}

	public void setRoomMailTime(Timestamp roomMailTime) {
		this.roomMailTime = roomMailTime;
	}

	public Integer getRoomMailIdWho() {
		return roomMailIdWho;
	}

	public void setRoomMailIdWho(Integer roomMailIdWho) {
		this.roomMailIdWho = roomMailIdWho;
	}

	public String getCmpNameById() {
		DataSource ds = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cmpName = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement("SELECT cmp_name FROM COMPANY WHERE cmp_id=?;");
			ps.setInt(1, cmpId);
			System.out.println("我進來JNDI囉!只是還沒到if裡面");
			rs = ps.executeQuery();
			if (rs.next()) {
				cmpName = rs.getString("cmp_name");
				System.out.println("我在JNDI層if裡:" + cmpName);
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
		return cmpName;
	}

}
