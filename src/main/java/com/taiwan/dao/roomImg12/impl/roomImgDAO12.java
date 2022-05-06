package com.taiwan.dao.roomImg12.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.RoomImg;
import com.taiwan.dao.roomImg12.roomImg_interface12;

public class roomImgDAO12 implements roomImg_interface12 {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";
	private static final String query = "SELECT * FROM Taiwan.ROOM_IMG WHERE ROOMTYPE_ID=?";

	@Override
	public List<RoomImg> queryImgsByRoomId(Integer roomId) {
		List<RoomImg> roomImgs=new ArrayList<RoomImg>();
		RoomImg roomImg=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, roomId);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomImg=new RoomImg();
				roomImg.setRoomImgId(rs.getInt("room_img_id"));
				roomImg.setRoomtypeId(rs.getInt("roomtype_id"));
				roomImg.setRoomImg(rs.getString("room_img"));
				
				roomImgs.add(roomImg);

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
		return roomImgs;
	}

}
