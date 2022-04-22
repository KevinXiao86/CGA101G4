package com.taiwan.dao.roomItem.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.taiwan.beans.RoomItemVO;
import com.taiwan.dao.roomItem.RoomItemDAO_interface;

public class RoomItemJDBCDAO implements RoomItemDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

	private static final String insert =
			"INSERT INTO Taiwan.ROOM_ITEM ( room_id, room_order_id, room_amount, room_price) VALUES ( ?, ?, ?, ?)";

	@Override
	public List<RoomItemVO> queryRoomItemByRoom_item_id(Integer room_item_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomItemVO> queryRoomItemByRoom_order_id(Integer room_order_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomItemVO> queryRoomItemByRoom_item_evaluate_score(Integer room_item_evaluate_score) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertRoomItem(RoomItemVO roomItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(insert);

			pstmt.setInt(1, roomItemVO.getRoom_id());
			pstmt.setInt(2, roomItemVO.getRoom_order_id());
			pstmt.setInt(3, roomItemVO.getRoom_item_amount());
			pstmt.setInt(4, roomItemVO.getRoom_item_price());


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

	@Override
	public void updateRoomItem(RoomItemVO roomItemVO) {
		// TODO Auto-generated method stub
		
	}
}
