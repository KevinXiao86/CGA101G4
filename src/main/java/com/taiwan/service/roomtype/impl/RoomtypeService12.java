package com.taiwan.service.roomtype.impl;

import com.taiwan.beans.Roomtype;
import com.taiwan.dao.roomtype.Roomtype_interface;
import com.taiwan.dao.roomtype.impl.RoomtypeDAO;

public class RoomtypeService12 {
	private Roomtype_interface dao=null;
	public RoomtypeService12() {
		dao=new RoomtypeDAO();		
	}
	public Roomtype searchRoomtype(Integer roomid) {
		return dao.searchRoomtype(roomid);
	}
	public void addEvaluate(Integer roomId,Integer score) {
		dao.addEvaluate(roomId, score);
	}
}
