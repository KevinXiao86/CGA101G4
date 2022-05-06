package com.taiwan.dao.roomMail;

import java.util.List;

import com.taiwan.beans.RoomMailVO;

public interface RoomMailDAO_interface {
	public List<RoomMailVO> queryRoomMailByCustIdAndCmpId(Integer custId, Integer cmpId);

	public void insert(RoomMailVO roomMailVO);

	public List<RoomMailVO> getAllByCustId(Integer custId);

}
