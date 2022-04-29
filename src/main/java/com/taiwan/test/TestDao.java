package com.taiwan.test;

import com.taiwan.beans.RoomOrder;
import static com.taiwan.utils.Common.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDao {

	public static void main(String[] args) throws SQLException {
		RoomOrder roomOrder = getRoomOrder(10000);
		System.out.println(roomOrder);
	}
	
	public static RoomOrder getRoomOrder(Integer id) throws SQLException {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		
		String sql = "SELECT 	`room_order_id`, `cust_id`, `room_order_date`, `room_order_price`, `checkin_date`,\r\n"
				+ "			 `checkout_date`, `room_order_status`, `cancel`, `total_price`, `cust_cop_id`,`cmp_id`\r\n"
				+ "	 	FROM  `Taiwan`.`ROOM_ORDER`"
				+ "WHERE `cust_id` = ?";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		RoomOrder roomOrder = null;
		while(rs.next()) {
			roomOrder = new RoomOrder();
			roomOrder.setCustId(rs.getInt(1));
			roomOrder.setCmpId(rs.getInt(11));
		}
		
		return roomOrder;
	}
}
