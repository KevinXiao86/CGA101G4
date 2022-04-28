package com.taiwan.service.roomOrder.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiwan.beans.RoomOrder;
import com.taiwan.service.roomOrder.RoomOrderMyService;

import mybatis.mapper.RoomOrderMapper;

@Service
public class RoomOrderMyServiceImpl implements RoomOrderMyService {

	@Autowired
	RoomOrderMapper mapper;

	@Override
	public List<RoomOrder> findAll() {
		List<RoomOrder> ls = mapper.queryAllRoomOrders();
		return ls;

	}

	@Override
	public RoomOrder findById(Integer roomOrderId) {
		RoomOrder roomOrder=mapper.queryById(roomOrderId);
		return roomOrder;
	}
}
