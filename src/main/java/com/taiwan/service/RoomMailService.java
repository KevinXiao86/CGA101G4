package com.taiwan.service;

import java.util.List;

import com.taiwan.beans.RoomMailVO;

public interface RoomMailService {
	public void sendMsg(Integer custId,Integer cmpId,String msg,Integer who);
	
	public List<RoomMailVO> getMsg(Integer custId,Integer cmpId);
}
