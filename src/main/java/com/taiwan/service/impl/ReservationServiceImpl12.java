package com.taiwan.service.impl;

import java.sql.Timestamp;

import com.taiwan.dao.reservation12.impl.Reservation12DAO;
import com.taiwan.service.ReservationService12;
import com.taiwan.dao.reservation12.Reservation12_interface;


public class ReservationServiceImpl12 implements ReservationService12{
	private Reservation12_interface dao=null;
	public ReservationServiceImpl12() {
		dao=new Reservation12DAO();
	}
	@Override
	public Integer maxBuyAmount(Timestamp ckin, Timestamp ckout, Integer roomtypeId) {
		dao.insertReserve(ckin, ckout, roomtypeId);
		return dao.searchMinAmount(ckin, ckout, roomtypeId);
	}
	@Override
	public void updateReserveAmount(Timestamp ckin, Timestamp ckout, Integer roomtypeId, Integer amount) {
		dao.insertReserve(ckin, ckout, roomtypeId);
		dao.updateReserveAmount(ckin, ckout, roomtypeId, amount);
		
	}
	
}
