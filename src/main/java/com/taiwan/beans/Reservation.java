package com.taiwan.beans;

import java.util.Date;
import java.util.List;

public class Reservation {
	private Integer reservationId;
	private Integer roomtypeId;
	private Date reserveDate;
	private Integer roomtypeAmount;
	private Integer reserveAmount;
	private String dateString;
	
	public Reservation() {
		super();
	}

	public Reservation(Integer reservationId, Integer roomtypeId, Date reserveDate, Integer roomtypeAmount,
			Integer reserveAmount) {
		super();
		this.reservationId = reservationId;
		this.roomtypeId = roomtypeId;
		this.reserveDate = reserveDate;
		this.roomtypeAmount = roomtypeAmount;
		this.reserveAmount = reserveAmount;
	}

	public Reservation(Integer reservationId, Integer roomtypeId, Date reserveDate, Integer roomtypeAmount,
			Integer reserveAmount, String dateString) {
		super();
		this.reservationId = reservationId;
		this.roomtypeId = roomtypeId;
		this.reserveDate = reserveDate;
		this.roomtypeAmount = roomtypeAmount;
		this.reserveAmount = reserveAmount;
		this.dateString = dateString;
	}

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public Integer getRoomtypeId() {
		return roomtypeId;
	}

	public void setRoomtypeId(Integer roomtypeId) {
		this.roomtypeId = roomtypeId;
	}

	public Date getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}

	public Integer getRoomtypeAmount() {
		return roomtypeAmount;
	}

	public void setRoomtypeAmount(Integer roomtypeAmount) {
		this.roomtypeAmount = roomtypeAmount;
	}

	public Integer getReserveAmount() {
		return reserveAmount;
	}

	public void setReserveAmount(Integer reserveAmount) {
		this.reserveAmount = reserveAmount;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", roomtypeId=" + roomtypeId + ", reserveDate="
				+ reserveDate + ", roomtypeAmount=" + roomtypeAmount + ", reserveAmount=" + reserveAmount
				+ ", dateString=" + dateString + "]";
	}
}
