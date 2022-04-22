package com.taiwan.dao.roomOrder.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import com.taiwan.beans.RoomOrderVO;
import com.taiwan.dao.roomOrder.RoomOrderDAO_interface;

public class RoomOrderJDBCDAO implements RoomOrderDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";
	
	private static final String insert =
			"INSERT INTO Taiwan.ROOM_ORDER ( cust_id, room_order_price, checkin_date, checkout_date,total_price) VALUES ( ?, ?, ?, ?, ?)";

	@Override
	public List<RoomOrderVO> queryRoomOrderByRoom_order_id(Integer room_order_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomOrderVO> queryRoomOrderByRoom_order_checkin_date(Date room_order_checkin_date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomOrderVO> queryRoomOrderByRoom_order_checkout_date(Date room_order_checkout_date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomOrderVO> queryRoomOrderByRoom_order_status(String room_order_status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomOrderVO> queryRoomOrderByCust_id(Integer cust_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(RoomOrderVO roomOrderVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(insert);

			pstmt.setInt(1, roomOrderVO.getCust_id());
			pstmt.setInt(2, roomOrderVO.getRoom_order_price());
			pstmt.setDate(3, roomOrderVO.getRoom_order_checkin_date());
			pstmt.setDate(4, roomOrderVO.getRoom_order_checkout_date());
			pstmt.setInt(5, roomOrderVO.getRoom_order_total_price());


			pstmt.executeUpdate();

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

}
