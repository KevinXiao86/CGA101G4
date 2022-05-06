package com.taiwan.service.roomItem.impl;

import java.util.List;

import com.taiwan.beans.RoomItemVO;
import com.taiwan.dao.roomItem.RoomItemDAO_interface;
import com.taiwan.dao.roomItem.impl.RoomItemDAO;
import com.taiwan.service.roomItem.RoomItemService;

public class RoomItemServiceImpl implements RoomItemService {
	private RoomItemDAO_interface dao;

	public RoomItemServiceImpl() {
		dao = new RoomItemDAO();
	}

	/********************** 新增訂單明細 **********************/
	@Override
	public void addRoomItem(Integer roomId, Integer roomOrderId, Integer roomAmount, Integer roomPrice) {
		RoomItemVO roomItemVO = new RoomItemVO();
		roomItemVO.setRoomId(roomId);
		roomItemVO.setRoomOrderId(roomOrderId);
		roomItemVO.setRoomItemAmount(roomAmount);
		roomItemVO.setRoomItemPrice(roomPrice);
		dao.insertRoomItem(roomItemVO);
	}

	/********************** 查詢訂單明細 **********************/
	@Override
	public List<RoomItemVO> searchRoomItem(Integer orderId) {
		return dao.queryRoomItemByRoomOrderId(orderId);
	}

	/********************** 新增訂單明細評價 **********************/
	@Override
	public void addEvaluate(Integer roomItemId, Integer evaluateSocre, String evaluateMsg) {
		RoomItemVO roomItemVO = new RoomItemVO();
		roomItemVO.setRoomItemId(roomItemId);
		roomItemVO.setRoomItemEvaluateScore(evaluateSocre);
		roomItemVO.setRoomItemEvaluateMsg(evaluateMsg);
		dao.updateRoomItem(roomItemVO);
		
	}

	@Override
	public RoomItemVO findByOrderId(Integer roomOrderId) {
		RoomItemVO roomItemVO=dao.queryByRoomOrderId(roomOrderId);
		return roomItemVO;
	}
	@Override
	public RoomItemVO searchByItemId(Integer roomItemId) {
		return dao.queryRoomItemByRoomItemId(roomItemId);
	}

	@Override
	public List<RoomItemVO> searchByRoomId(Integer roomId) {
		return dao.queryRoomItemByRoomId(roomId);
	}
}
