package com.taiwan.dao.reservation12;

import java.sql.Timestamp;

public interface Reservation12_interface {
	public Integer searchMinAmount(Timestamp ckin,Timestamp ckout,Integer roomtypeId);
	public void updateReserveAmount(Timestamp ckin,Timestamp ckout,Integer roomtypeId,Integer amount);
	public void insertReserve(Timestamp ckin, Timestamp ckout, Integer roomtypeId);
	
}
