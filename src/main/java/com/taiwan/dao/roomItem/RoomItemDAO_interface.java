package com.taiwan.dao.roomItem;

import java.util.List;

import com.taiwan.beans.RoomItemVO;


public interface RoomItemDAO_interface {
	// �ھڭq�Эq����ӽs���d�߭q�Эq�����
		public List<RoomItemVO> queryRoomItemByRoom_item_id(Integer room_item_id);

		// �ھڭq�Эq��s���d�߭q�Эq�����
		public List<RoomItemVO> queryRoomItemByRoom_order_id(Integer room_order_id);

		// �ھڵ������Ƭd�߭q�Эq�����
		public List<RoomItemVO> queryRoomItemByRoom_item_evaluate_score(Integer room_item_evaluate_score);

		// ���ͭq�Эq�����
		public void insertRoomItem(RoomItemVO roomItemVO);

		// �s�W�q�Эq����ӵ���(�s�ʭq�Эq��)�L���媩
			public void updateRoomItem(RoomItemVO roomItemVO);
}
