package com.taiwan.dao.reservation12.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.taiwan.dao.reservation12.Reservation12_interface;
import com.taiwan.service.roomtype.impl.RoomtypeService12;

public class Reservation12DAO implements Reservation12_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";
	// SELECT * FROM Taiwan.RESERVATION where roomtype_id=1 and reserve_date between
	// '2022-04-01' and '2022-06-02';
	private static final String update = "UPDATE Taiwan.RESERVATION SET reserve_amount = reserve_amount+1 WHERE roomtype_id = ? and reserve_date between ? and ?";
	private static final String query = " SELECT min(roomtype_amount-reserve_amount) FROM Taiwan.RESERVATION where roomtype_id = ? and reserve_date between ? and ? ";
	private static final String INSERT = 
			"INSERT ignore INTO Taiwan.RESERVATION (roomtype_id,reserve_date,roomtype_amount) VALUES (?, ?, ?)";

	@Override
	public Integer searchMinAmount(Timestamp ckin, Timestamp ckout, Integer roomtypeId) {
		// ckout當天不能算，需減一天
		Timestamp beforeCkout = new Timestamp(ckout.getTime() - (1000 * 60 * 60 * 24));
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer amount = -1;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, roomtypeId);
			pstmt.setTimestamp(2, ckin);
			pstmt.setTimestamp(3, beforeCkout);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				amount = rs.getInt("min(roomtype_amount-reserve_amount)");

			}

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
		return amount;
	}

	@Override
	public void updateReserveAmount(Timestamp ckin, Timestamp ckout, Integer roomtypeId, Integer amount) {
		// ckout當天不能算，需減一天
		Timestamp beforeCkout = new Timestamp(ckout.getTime() - (1000 * 60 * 60 * 24));
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(update);

			pstmt.setInt(1, roomtypeId);
			pstmt.setTimestamp(2, ckin);
			pstmt.setTimestamp(3, beforeCkout);
			for(int i=0;i<amount;i++) {
				pstmt.executeUpdate();
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
	public void insertReserve(Timestamp ckin, Timestamp ckout, Integer roomtypeId) {
		// ckout當天不能算，需減一天
		Timestamp beforeCkin = ckin;
		Connection con = null;
		PreparedStatement pstmt = null;
		RoomtypeService12 rtSVC=new RoomtypeService12();
		int roomtypeAmount=rtSVC.searchRoomtype(roomtypeId).getRoomtypeAmount();
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
for(;;) {
			if(beforeCkin.getTime()>= ckout.getTime()) {
				break;
			}

			pstmt.setInt(1, roomtypeId);
			pstmt.setTimestamp(2, beforeCkin);
			pstmt.setInt(3, roomtypeAmount);

			pstmt.executeUpdate();
			beforeCkin=new Timestamp(beforeCkin.getTime() + (1000 * 60 * 60 * 24));
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
public static void main(String[] args) {
	
	String ckin="2022-05-03 00:00:00";
	String ckout="2022-05-06 00:00:00";
	int roomtypeid=1;
	Reservation12DAO dao=new Reservation12DAO();
//	查詢
//	int i=dao.searchMinAmount(Timestamp.valueOf(ckin), Timestamp.valueOf(ckout), roomtypeid);
//	System.out.println(i);
	//更新
//	int amount =2;
//	dao.updateReserveAmount(Timestamp.valueOf(ckin), Timestamp.valueOf(ckout), roomtypeid,amount);
	//新增，有衝突則不新增(設日期和房型編號為組合唯一鍵)
	dao.insertReserve(Timestamp.valueOf(ckin), Timestamp.valueOf(ckout), roomtypeid);
}

}
