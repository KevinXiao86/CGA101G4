package com.taiwan.service.roomOrder.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiwan.beans.RoomOrder;
import com.taiwan.service.roomOrder.RoomOrderMyService;

import mybatis.mapper.RoomOrderMapper;

@Service
public class RoomOrderMyServiceImpl implements RoomOrderMyService {

	@Autowired
	RoomOrderMapper mapper;

	@Transactional
	@Override
	public List<RoomOrder> findAll() {
		List<RoomOrder> ls = mapper.queryAllRoomOrders();
		return ls;

	}

	@Transactional
	@Override
	public RoomOrder findById(Integer roomOrderId) {
		RoomOrder roomOrder = mapper.queryById(roomOrderId);
		return roomOrder;
	}

	@Transactional
	@Override
	public List<RoomOrder> findByCmpId(Integer cmpId) {
		List<RoomOrder> ls = mapper.queryByCmpId(cmpId);
		return ls;
	}

	@Transactional
	@Override
	public List<RoomOrder> findByCustId(Integer custId) {
		List<RoomOrder> ls = mapper.queryByCustId(custId);
		return ls;
	}

	@Transactional
	@Override
	public List<RoomOrder> findBydate(Date startdate, Date enddate) {
		List<RoomOrder> ls = mapper.queryByDate(startdate, enddate);
		return ls;
	}

	@Override
	public boolean updateStatusAndReason(Integer roomOrderId, String roomOrderStatus, String cancel) {

		return mapper.updateStatusAndReason(roomOrderId, roomOrderStatus, cancel) > 0;
	}

	@Override
	public List<RoomOrder> findByStatus(String roomOrderStatus) {
		List<RoomOrder> ls = mapper.queryByStatus(roomOrderStatus);
		return ls;
	}
}
