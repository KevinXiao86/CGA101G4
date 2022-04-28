package com.taiwan.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RepCmpVO {
	private Integer repCmpId;
	private Integer custId;
	private Integer roomId;
	private Integer empId;
	private String reason;
	private Timestamp repCmpDate;
	private String status;
	private String result;

	public RepCmpVO(Integer repCmpId, Integer custId, Integer roomId, Integer empId, String reason,
			Timestamp repCmpDate, String status, String result) {
		super();
		this.repCmpId = repCmpId;
		this.custId = custId;
		this.roomId = roomId;
		this.empId = empId;
		this.reason = reason;
		this.repCmpDate = repCmpDate;
		this.status = status;
		this.result = result;
	}

	public RepCmpVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRepCmpId() {
		return repCmpId;
	}

	public void setRepCmpId(Integer repCmpId) {
		this.repCmpId = repCmpId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Timestamp getRepCmpDate() {
		return repCmpDate;
	}

	public void setRepCmpDate(Timestamp repCmpDate) {
		this.repCmpDate = repCmpDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Rep_Cmp [repCmpId=" + repCmpId + ", custId=" + custId + ", roomId=" + roomId + ", empId=" + empId
				+ ", reason=" + reason + ", repCmpDate=" + repCmpDate + ", status=" + status + ", result=" + result
				+ "]";
	}
//	public String getRoomtypeName() {
//		private static DataSource ds = null;
//		static {
//			try {
//				Context ctx = new InitialContext();
//				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//
//		}
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			conn = ds.getConnection();
//			ps = conn.prepareStatement("SELECT roomtype_name FROM ROOMTYPE WHERE roomtype_id=?;");
//			ps.setInt(1, roomId);
//			System.out.println("我進來JNDI囉!只是還沒到while裡面");
//			rs = ps.executeQuery();
//			if(rs.next()) {
//				
//				System.out.println("我在JNDI層while迴圈裡:" + repCmpVO);
//			}
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if (ps != null) {
//				try {
//					ps.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//
//		}
//		return list;
//
//	}
}
