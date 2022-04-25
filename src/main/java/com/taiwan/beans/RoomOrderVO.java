package com.taiwan.beans;

import java.sql.Timestamp;

public class RoomOrderVO {
	private Integer roomOrderId;
	private Integer custId;
	private Timestamp roomOrderDate;
	private Integer roomOrderPrice;
	private Timestamp roomOrderCheckinDate;
	private Timestamp roomOrderCheckoutDate;
	private String roomOrderStatus;
	private String roomOrderCancel;
	private Integer roomOrderTotalPrice;
	private Integer custCopId;
	public Integer getRoomOrderId() {
		return roomOrderId;
	}
	public void setRoomOrderId(Integer roomOrderId) {
		this.roomOrderId = roomOrderId;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public Timestamp getRoomOrderDate() {
		return roomOrderDate;
	}
	public void setRoomOrderDate(Timestamp roomOrderDate) {
		this.roomOrderDate = roomOrderDate;
	}
	public Integer getRoomOrderPrice() {
		return roomOrderPrice;
	}
	public void setRoomOrderPrice(Integer roomOrderPrice) {
		this.roomOrderPrice = roomOrderPrice;
	}
	public Timestamp getRoomOrderCheckinDate() {
		return roomOrderCheckinDate;
	}
	public void setRoomOrderCheckinDate(Timestamp roomOrderCheckinDate) {
		this.roomOrderCheckinDate = roomOrderCheckinDate;
	}
	public Timestamp getRoomOrderCheckoutDate() {
		return roomOrderCheckoutDate;
	}
	public void setRoomOrderCheckoutDate(Timestamp roomOrderCheckoutDate) {
		this.roomOrderCheckoutDate = roomOrderCheckoutDate;
	}
	public String getRoomOrderStatus() {
		return roomOrderStatus;
	}
	public void setRoomOrderStatus(String roomOrderStatus) {
		this.roomOrderStatus = roomOrderStatus;
	}
	public String getRoomOrderCancel() {
		return roomOrderCancel;
	}
	public void setRoomOrderCancel(String roomOrderCancel) {
		this.roomOrderCancel = roomOrderCancel;
	}
	public Integer getRoomOrderTotalPrice() {
		return roomOrderTotalPrice;
	}
	public void setRoomOrderTotalPrice(Integer roomOrderTotalPrice) {
		this.roomOrderTotalPrice = roomOrderTotalPrice;
	}
	public Integer getCustCopId() {
		return custCopId;
	}
	public void setCustCopId(Integer custCopId) {
		this.custCopId = custCopId;
	}
	
	
	
	
	
}
