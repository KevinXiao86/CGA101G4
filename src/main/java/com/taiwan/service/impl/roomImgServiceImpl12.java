package com.taiwan.service.impl;

import java.util.List;

import com.taiwan.beans.RoomImg;
import com.taiwan.dao.roomImg12.roomImg_interface12;
import com.taiwan.dao.roomImg12.impl.roomImgDAO12;
import com.taiwan.service.roomImgService12;

public class roomImgServiceImpl12 implements roomImgService12{
	private roomImg_interface12 dao;
	public roomImgServiceImpl12() {
		dao=new roomImgDAO12();
	}
	@Override
	public List<RoomImg> searchImgByRoomId(Integer roomId) {
		return dao.queryImgsByRoomId(roomId);

	}
	
}
