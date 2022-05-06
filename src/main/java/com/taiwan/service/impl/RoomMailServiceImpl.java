package com.taiwan.service.impl;

import java.util.List;

import com.taiwan.beans.RoomMailVO;
import com.taiwan.dao.roomMail.RoomMailDAO_interface;
import com.taiwan.dao.roomMail.impl.RoomMailDAO;
import com.taiwan.service.RoomMailService;

public class RoomMailServiceImpl implements RoomMailService {
	RoomMailDAO_interface dao = null;

	public RoomMailServiceImpl() {
		dao = new RoomMailDAO();
	}

	@Override
	public void sendMsg(Integer custId, Integer cmpId, String msg, Integer who) {
		RoomMailVO roomMailVO = new RoomMailVO();
		roomMailVO.setCustId(custId);
		roomMailVO.setCmpId(cmpId);
		roomMailVO.setRoomMailMsg(msg);
//		String acount=查察訊人ID
//		roomMailVO.setRoomMailIdWho(acount);
		roomMailVO.setRoomMailIdWho(who);
		dao.insert(roomMailVO);
	}

	@Override
	public List<RoomMailVO> getMsg(Integer custId, Integer cmpId) {

		return dao.queryRoomMailByCustIdAndCmpId(custId, cmpId);

	}

}
