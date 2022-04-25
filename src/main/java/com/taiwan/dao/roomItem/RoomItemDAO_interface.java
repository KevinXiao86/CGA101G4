package com.taiwan.dao.roomItem;

import java.util.List;

import com.taiwan.beans.RoomItemVO;

public interface RoomItemDAO_interface {
	public List<RoomItemVO> queryRoomItemByRoomItemId(Integer roomItemId);

	public List<RoomItemVO> queryRoomItemByRoomOrderId(Integer roomOrderId);

	public List<RoomItemVO> queryRoomItemByRoomItemEvaluateScore(Integer roomItemEvaluateScore);

	public void insertRoomItem(RoomItemVO roomItemVO);

	public void updateRoomItem(RoomItemVO roomItemVO);
}
