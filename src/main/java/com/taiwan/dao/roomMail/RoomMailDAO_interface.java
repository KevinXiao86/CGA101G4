package com.taiwan.dao.roomMail;

import java.util.List;

import com.taiwan.beans.RoomMailVO;

public interface RoomMailDAO_interface {
	// �ھڷ|���s���μt�ӽs���d�߰T���O��
		public List<RoomMailVO> queryRoomMailByCust_idAndCmp_id(Integer cust_id, Integer cmp_id);

		// �s�W��ܬ���
		public void inser(RoomMailVO roomMailVO);

}
