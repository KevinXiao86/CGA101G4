package com.taiwan.dao.roomItem;

import java.sql.Connection;
import java.util.List;

import com.taiwan.beans.RoomItemVO;

public interface RoomItemDAO_interface {
	public RoomItemVO queryRoomItemByRoomItemId(Integer roomItemId);

	public List<RoomItemVO> queryRoomItemByRoomOrderId(Integer roomOrderId);

	public List<RoomItemVO> queryRoomItemByRoomItemEvaluateScore(Integer roomItemEvaluateScore);

	public void insertRoomItem(RoomItemVO roomItemVO);

	public void updateRoomItem(RoomItemVO roomItemVO);

	public RoomItemVO queryByRoomOrderId(Integer roomOrderId);

	//同時新增訂單與訂單明細
	public void insertRoomItem(RoomItemVO roomItemVO, Connection con);
	
	public List<RoomItemVO> queryRoomItemByRoomId(Integer roomId);
}
