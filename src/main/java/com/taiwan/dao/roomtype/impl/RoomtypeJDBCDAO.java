package com.taiwan.dao.roomtype.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.taiwan.beans.Roomtype;
import com.taiwan.dao.roomtype.Roomtype_interface;

public class RoomtypeJDBCDAO implements Roomtype_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";
	private String update="UPDATE Taiwan.ROOMTYPE SET total_people = total_people+1,total_score= ?  WHERE roomtype_id = ?";

	private String find ="SELECT * FROM Taiwan.ROOMTYPE ";
	@Override
	public Roomtype searchRoomtype(Integer roomid) {
		Roomtype roomtypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(find + " where roomtype_id = ? ");

			pstmt.setInt(1, roomid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomtypeVO = new Roomtype();
				roomtypeVO.setRoomtypeId(rs.getInt("roomtype_id"));
				roomtypeVO.setCmpId(rs.getInt("cmp_id"));
				roomtypeVO.setRoomtypeName(rs.getString("roomtype_name"));
				roomtypeVO.setRoomtypeAmount(rs.getInt("roomtype_amount"));
				roomtypeVO.setRoomtypePeople(rs.getInt("roomtype_people"));
				roomtypeVO.setTotalScore(rs.getInt("total_score"));
				roomtypeVO.setTotalPeople(rs.getInt("total_people"));
				roomtypeVO.setRoomtypePrice(rs.getInt("roomtype_price"));
				roomtypeVO.setRoomtypeStatus(rs.getString("roomtype_status"));
				roomtypeVO.setRoomtypeArea(rs.getInt("roomtype_area"));
//				roomtypeVO.setRoomtypeIntroduce(rs.getString("roomtype_introduce"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return roomtypeVO;
	}
	@Override
	public void addEvaluate(Integer roomId, Integer score) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(update);
			pstmt.setInt(1,score);
			pstmt.setInt(2,roomId);
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

}
