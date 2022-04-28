package com.taiwan.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.RoomOrderVO;
import com.taiwan.dao.roomOrder.RoomOrderDAO_interface;
import com.taiwan.dao.roomOrder.impl.RoomOrderDAO;
import com.taiwan.service.RoomOrderService;
import com.taiwan.service.roomItem.impl.RoomItemServiceImpl;

public class RoomOrderServiceImpl implements RoomOrderService{
	private RoomOrderDAO_interface dao;

	public RoomOrderServiceImpl() {
		dao = new RoomOrderDAO();
	}

	@Override
	public RoomOrderVO addRoomOrder(Integer custId,Timestamp checkin,Timestamp checkout ,Integer roomId,Integer amount,Integer price,Integer cmpId) {
		RoomOrderVO roomOrderVO=new RoomOrderVO();
		roomOrderVO.setCustId(custId);
		roomOrderVO.setRoomOrderCheckinDate(checkin);
		roomOrderVO.setRoomOrderCheckoutDate(checkout);
		roomOrderVO.setRoomOrderPrice(price*amount);
		roomOrderVO.setRoomOrderTotalPrice(price*amount);
		roomOrderVO.setCmpId(cmpId);
		int roomOrderId=dao.insert(roomOrderVO);
		
		/***************新增明細****************************/
		RoomItemServiceImpl roomItem=new RoomItemServiceImpl();
		//(Integer roomId, Integer roomOrderId, Integer roomAmount, Integer roomPrice)
		roomItem.addRoomItem(roomId, roomOrderId, amount, price);
		
		
		return roomOrderVO;
	}
	/****************有用優惠券*******************/
//	public RoomOrderVO addRoomOrder(Integer custId,Timestamp checkin,Timestamp checkout ,Integer roomId,Integer amount,Integer price,Integer copId) {
//		RoomOrderVO roomOrderVO=new RoomOrderVO();
//		roomOrderVO.setCustId(custId);
//		roomOrderVO.setRoomOrderCheckinDate(checkin);
//		roomOrderVO.setRoomOrderCheckoutDate(checkout);
//		roomOrderVO.setRoomOrderPrice(price*amount);
//		roomOrderVO.setRoomOrderTotalPrice(price*amount);
//		
//		dao.insert(roomOrderVO);
//		return roomOrderVO;
//	}
	@Override
	public RoomOrderVO chancelRoomOrder(Integer roomOrderId,String reason) {
		RoomOrderVO roomOrderVO=new RoomOrderVO();
		roomOrderVO.setRoomOrderId(roomOrderId);
		roomOrderVO.setRoomOrderCancel(reason);
		
		dao.cancel(roomOrderVO);
		return roomOrderVO;
	}

	@Override
	public List<RoomOrderVO> searchRoomOrderbyCheckin(Timestamp checkinDate) {
		return dao.queryRoomOrderByRoomOrderCheckinDate(checkinDate);
	}

	@Override
	public List<RoomOrderVO> searchRoomOrderbyCheckout(Timestamp checkoutDate) {
		return dao.queryRoomOrderByRoomOrderCheckoutDate(checkoutDate);
	}

	@Override
	public List<RoomOrderVO> searchRoomOrderbyOrderDate(Timestamp startDate, Timestamp endDate) {
		return dao.queryRoomOrderByRoomOrderDate(startDate, endDate);
	}

	@Override
	public List<RoomOrderVO> searchRoomOrderbystatus(String status) {
		return dao.queryRoomOrderByRoomOrderStatus(status);
	}

	@Override
	public List<RoomOrderVO> searchRoomOrderbyCustId(Integer custId) {
		return dao.queryRoomOrderByCustId(custId);
	}
	
}
