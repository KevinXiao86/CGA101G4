package com.taiwan.service.roomOrder;

import java.util.List;

import com.taiwan.beans.RoomOrder;

public interface RoomOrderMyService {

	// 查詢所有訂房訂單
	public List<RoomOrder> findAll();
	
	//根據訂房訂單Id搜尋訂房訂單
	public RoomOrder findById(Integer roomOrderId);

}
