package com.taiwan.service.roomOrder;

import java.util.Date;
import java.util.List;

import com.taiwan.beans.RoomOrder;

public interface RoomOrderMyService {

	// 查詢所有訂房訂單
	public List<RoomOrder> findAll();
	
	//根據訂房訂單Id搜尋訂房訂單
	public RoomOrder findById(Integer roomOrderId);
	
	//根據廠商Id搜尋訂房訂單
	public List<RoomOrder> findByCmpId(Integer cmpId);
	
	//根據會員Id搜尋訂房訂單
	public List<RoomOrder> findByCustId(Integer custId);
	
	//根據一個時間範圍搜尋所有訂單
	public List<RoomOrder> findBydate(Date startdate,Date enddate);
	
	//根據訂房訂單Id取消此訂單的狀態,還有原因
	public boolean updateStatusAndReason(Integer roomOrderId,String roomOrderStatus,String cancel);
	
	//根據訂單狀態搜尋訂單
	public List<RoomOrder> findByStatus(String roomOrderStatus);
	
	//根據訂單Id更改訂單的狀態
	public boolean changeStatus(Integer cmpId,String roomOrderStatus);
	
	//廠商查詢
	public List<RoomOrder> cmpFindByCustId(Integer cmpId,Integer custId);
	
	public List<RoomOrder> cmpFindByDate(Integer cmpId,Date startsate ,Date enddate);
	
	public List<RoomOrder> cmpFindByStatus(Integer cmpId,String roomOrderStatus);
	

}
