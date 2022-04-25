package com.taiwan.beans;

import java.security.Timestamp;

public class RoomMailVO {
	private Integer roomMailId;
	private Integer custId;
	private Integer cmpId;
	private String roomMailMsg;
	private Timestamp roomMailTime;
	private String roomMailIdWho;
	
	public Integer getRoomMailId() {
		return roomMailId;
	}
	public void setRoomMailId(Integer roomMailId) {
		this.roomMailId = roomMailId;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public Integer getCmpId() {
		return cmpId;
	}
	public void setCmpId(Integer cmpId) {
		this.cmpId = cmpId;
	}
	public String getRoomMailMsg() {
		return roomMailMsg;
	}
	public void setRoomMailMsg(String roomMailMsg) {
		this.roomMailMsg = roomMailMsg;
	}
	public Timestamp getRoomMailTime() {
		return roomMailTime;
	}
	public void setRoomMailTime(Timestamp roomMailTime) {
		this.roomMailTime = roomMailTime;
	}
	public String getRoomMailIdWho() {
		return roomMailIdWho;
	}
	public void setRoomMailIdWho(String roomMailIdWho) {
		this.roomMailIdWho = roomMailIdWho;
	}
	
}
