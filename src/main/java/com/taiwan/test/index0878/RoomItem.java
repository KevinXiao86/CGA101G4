package com.taiwan.test.index0878;

import java.util.List;

import com.taiwan.beans.RoomItemVO;
import com.taiwan.service.impl.RoomItemServiceImpl;

public class RoomItem {
public static void main(String[] args) {
	RoomItemServiceImpl test=new RoomItemServiceImpl();
//	test.addEvaluate(1, 2, "差強人意!");
	List<RoomItemVO> lsit =test.searchRoomItem(1);
for (RoomItemVO roomItemVO : lsit) {
	System.out.println(	roomItemVO.getRoomItemId());
	System.out.println(	roomItemVO.getRoomId());
	System.out.println(	roomItemVO.getRoomOrderId());
	System.out.println(	roomItemVO.getRoomItemAmount());
	System.out.println(	roomItemVO.getRoomItemEvaluateScore());
	System.out.println(	roomItemVO.getRoomItemEvaluateMsg());
	System.out.println(	roomItemVO.getRoomItemPrice());



	
}
}

}
