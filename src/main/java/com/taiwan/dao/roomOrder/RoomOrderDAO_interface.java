package com.taiwan.dao.roomOrder;

import java.sql.Date;
import java.util.List;

import com.taiwan.beans.RoomOrderVO;

public interface RoomOrderDAO_interface {
	// �ھڭq�Эq��s���d�߭q�Эq��
		public List<RoomOrderVO> queryRoomOrderByRoom_order_id(Integer room_order_id);

		// �ھڤJ���d�߭q�Эq��
		public List<RoomOrderVO> queryRoomOrderByRoom_order_checkin_date(Date room_order_checkin_date);

		// �ھڰh�Ф�d�߭q�Эq��
		public List<RoomOrderVO> queryRoomOrderByRoom_order_checkout_date(Date room_order_checkout_date);

		// �ھڭq�Эq�檬�A�d�߭q�Эq��
		public List<RoomOrderVO> queryRoomOrderByRoom_order_status(String room_order_status);

		// �ھڷ|���s���d�߭q�Эq��
		public List<RoomOrderVO> queryRoomOrderByCust_id(Integer cust_id);

		// ���ͭq�Эq��(�L�u�f��)
		public void insert(RoomOrderVO roomOrderVO);
}
