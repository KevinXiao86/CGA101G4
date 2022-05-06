package com.taiwan.dao.roomMail.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.RoomMailVO;
import com.taiwan.dao.roomMail.RoomMailDAO_interface;

public class RoomMailJDBCDAO implements RoomMailDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

	private static final String insert = "INSERT INTO Taiwan.ROOM_MAIL (CUST_ID, CMP_ID, ROOM_MAIL_MSG, ROOM_MAIL_ID_WHO) VALUES (?, ?, ?, ?) ";
	private static final String find = "SELECT * FROM Taiwan.ROOM_MAIL where CUST_ID=? and CMP_ID=? order by ROOM_MAIL_TIME";
	private static final String GET_ALL_BY_CUSTID = "SELECT * FROM ROOM_MAIL WHERE CUST_ID=? ORDER BY ROOM_MAIL_TIME DESC;";

	@Override
	public List<RoomMailVO> queryRoomMailByCustIdAndCmpId(Integer custId, Integer cmpId) {
		List<RoomMailVO> list = new ArrayList<RoomMailVO>();
		RoomMailVO roomMailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(find);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomMailVO = new RoomMailVO();
				roomMailVO.setRoomMailIdWho(rs.getInt("room_mail_id_who"));
				roomMailVO.setRoomMailMsg(rs.getString("room_mail_msg"));

				list.add(roomMailVO); // Store the row in the list
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
		return list;
	}

	@Override
	public void insert(RoomMailVO roomMailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(insert);

			pstmt.setInt(1, roomMailVO.getCustId());
			pstmt.setInt(2, roomMailVO.getCmpId());
			pstmt.setString(3, roomMailVO.getRoomMailMsg());
			pstmt.setInt(4, roomMailVO.getRoomMailIdWho());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<RoomMailVO> getAllByCustId(Integer custId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RoomMailVO> list = new ArrayList<RoomMailVO>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_CUSTID);
			pstmt.setInt(1, custId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RoomMailVO roomMailVO = new RoomMailVO();
				roomMailVO.setRoomMailId(rs.getInt("ROOM_MAIL_ID"));
				roomMailVO.setCustId(rs.getInt("CUST_ID"));
				roomMailVO.setCmpId(rs.getInt("CMP_ID"));
				roomMailVO.setRoomMailMsg(rs.getString("room_mail_msg"));
				roomMailVO.setRoomMailTime(rs.getTimestamp("ROOM_MAIL_TIME"));
				roomMailVO.setRoomMailIdWho(rs.getInt("room_mail_id_who"));

				list.add(roomMailVO); // Store the row in the list
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
		return list;
	}
}
