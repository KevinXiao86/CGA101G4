package com.taiwan.service;

import java.util.List;

import com.taiwan.beans.RoomItemVO;


public interface RoomItemService {

	/**********************新增訂單明細**********************/

	public void addRoomItem(Integer roomId,Integer roomOrderId,Integer roomAmount,Integer roomPrice);
	/**********************查詢訂單明細**********************/

	public List<RoomItemVO> searchRoomItem(Integer orderId);
	
	/**********************新增訂單明細評價**********************/
	public void addEvaluate(Integer roomItemId,Integer evaluateSocre,String evaluateMsg);
}
