package com.taiwan.service;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.RoomOrderVO;

public interface RoomOrderService {
	public RoomOrderVO addRoomOrder(Integer custId,Timestamp checkin,Timestamp checkout ,Integer roomId,Integer amount,Integer price,Integer cmpId,Integer copId);
	
	public RoomOrderVO chancelRoomOrder(Integer roomOrderId,String reason);
	
	public List<RoomOrderVO> searchRoomOrderbyCheckin(Timestamp checkinDate);

	public List<RoomOrderVO> searchRoomOrderbyCheckout(Timestamp checkoutDate);

	public List<RoomOrderVO> searchRoomOrderbyOrderDate(Timestamp startDate, Timestamp endDate);
	
	public List<RoomOrderVO> searchRoomOrderbystatus(String status);
	
	public List<RoomOrderVO> searchRoomOrderbyCustId(Integer custId);

	public RoomOrderVO searchRoomOrderbyRoomOrderId(Integer roomOrderId);

	public RoomOrderVO addRoomOrder(Integer custId, Timestamp checkin, Timestamp checkout, Integer roomId, Integer amount,
			Integer price, Integer cmpId);



	
	
}
