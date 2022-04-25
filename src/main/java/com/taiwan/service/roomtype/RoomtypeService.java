package com.taiwan.service.roomtype;

import java.util.List;

import com.taiwan.beans.Roomtype;

public interface RoomtypeService {

	//根據廠商編號查詢所有房型
	public List<Roomtype> getAllRoomtypesByCmpId(Integer cmpId);
	
	//修改房型狀態
	public boolean updateRoomtypeStatus(String cmpId, String roomtypeId, String status);
}
