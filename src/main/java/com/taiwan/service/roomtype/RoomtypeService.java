package com.taiwan.service.roomtype;

import java.util.List;

import com.taiwan.beans.Roomtype;

public interface RoomtypeService {

	//根據廠商編號查詢所有房型
	public List<Roomtype> getAllRoomtypesByCmpId(String cmpId);
}
