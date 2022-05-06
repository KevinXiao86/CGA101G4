package com.taiwan.service;

import java.sql.Timestamp;

public interface ReservationService12 {
	//查詢至多可購買的房數
	public Integer maxBuyAmount(Timestamp ckin,Timestamp ckout,Integer roomtypeId);
	//訂單完成後更新預約表
	public void updateReserveAmount(Timestamp ckin,Timestamp ckout,Integer roomtypeId,Integer amount);
	
}
