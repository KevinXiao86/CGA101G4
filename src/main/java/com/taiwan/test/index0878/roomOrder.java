package com.taiwan.test.index0878;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.RoomOrderVO;
import com.taiwan.service.impl.*;

public class roomOrder {
	public static void main(String[] args) {
		RoomOrderServiceImpl test = new RoomOrderServiceImpl();
		String ckin = "2022-04-29 00:00:00";
		String ckout = "2022-04-30 00:00:00";
		String start = "2022-04-10 00:00:00";
		String end = "2022-04-12 00:00:00";
		List<RoomOrderVO> list =null;
//		參數提示addRoomOrder( custId, checkin, checkout , roomId, amount, price) 
//		test.addRoomOrder(10000, Timestamp.valueOf(ckin), Timestamp.valueOf(ckout), 1, 2, 4200);
//		test.chancelRoomOrder(3, "test");
//		list=test.searchRoomOrderbyCheckin(Timestamp.valueOf(ckin));
//		list=test.searchRoomOrderbyCheckout(Timestamp.valueOf(ckout));
//		list=test.searchRoomOrderbyCustId(10000);
//		list=test.searchRoomOrderbyOrderDate(Timestamp.valueOf(start),Timestamp.valueOf(end));
//		list=test.searchRoomOrderbystatus("正常");

		
		
		for (RoomOrderVO roomOrderVO : list) {
			System.out.println(roomOrderVO.getRoomOrderId());
		}
		
	}

}
