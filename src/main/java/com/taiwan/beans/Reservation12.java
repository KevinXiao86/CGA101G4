package com.taiwan.beans;

import java.sql.Timestamp;

public class Reservation12 {
private Integer reservationId;
private Integer roomtypeId;

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
public Timestamp getReserveDate() {
	return reserveDate;
}
public void setReserveDate(Timestamp reserveDate) {
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
private Timestamp reserveDate;
private Integer roomtypeAmount;
private Integer reserveAmount;
}
