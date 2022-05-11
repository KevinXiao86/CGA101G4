package com.taiwan.beans;

import java.util.Date;
import java.util.List;

public class RoomSelectVO {
	private Integer roomtypeId;
	private Integer cmpId;
	private String roomtypeName;
	private Integer roomtypeAmount;
	private Integer roomtypePeople;
	private Integer totalScore;
	private Integer totalPeople;
	private Integer roomtypePrice;
	private String roomtypeStatus;
	private String roomtypeIntroduce;
	private String roomImg;
	private String cmpName;
	private String cmpTel;
	private String location;
	private Integer reservationId;
	private Date reserveDate;
	private Integer reserveAmount;
	
	public RoomSelectVO() {
		super();
	}
	
	public Integer getRoomtypeId() {
		return roomtypeId;
	}
	public void setRoomtypeId(Integer roomtypeId) {
		this.roomtypeId = roomtypeId;
	}
	public Integer getCmpId() {
		return cmpId;
	}
	public void setCmpId(Integer cmpId) {
		this.cmpId = cmpId;
	}
	public String getRoomtypeName() {
		return roomtypeName;
	}
	public void setRoomtypeName(String roomtypeName) {
		this.roomtypeName = roomtypeName;
	}
	public Integer getRoomtypeAmount() {
		return roomtypeAmount;
	}
	public void setRoomtypeAmount(Integer roomtypeAmount) {
		this.roomtypeAmount = roomtypeAmount;
	}
	public Integer getRoomtypePeople() {
		return roomtypePeople;
	}
	public void setRoomtypePeople(Integer roomtypePeople) {
		this.roomtypePeople = roomtypePeople;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public Integer getTotalPeople() {
		return totalPeople;
	}
	public void setTotalPeople(Integer totalPeople) {
		this.totalPeople = totalPeople;
	}
	public Integer getRoomtypePrice() {
		return roomtypePrice;
	}
	public void setRoomtypePrice(Integer roomtypePrice) {
		this.roomtypePrice = roomtypePrice;
	}
	public String getRoomtypeStatus() {
		return roomtypeStatus;
	}
	public void setRoomtypeStatus(String roomtypeStatus) {
		this.roomtypeStatus = roomtypeStatus;
	}
	public String getRoomtypeIntroduce() {
		return roomtypeIntroduce;
	}
	public void setRoomtypeIntroduce(String roomtypeIntroduce) {
		this.roomtypeIntroduce = roomtypeIntroduce;
	}
	public String getRoomImg() {
		return roomImg;
	}
	public void setRoomImg(String roomImg) {
		this.roomImg = roomImg;
	}
	public String getCmpName() {
		return cmpName;
	}
	public void setCmpName(String cmpName) {
		this.cmpName = cmpName;
	}
	public String getCmpTel() {
		return cmpTel;
	}
	public void setCmpTel(String cmpTel) {
		this.cmpTel = cmpTel;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getReservationId() {
		return reservationId;
	}
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	public Date getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}
	public Integer getReserveAmount() {
		return reserveAmount;
	}
	public void setReserveAmount(Integer reserveAmount) {
		this.reserveAmount = reserveAmount;
	}

	@Override
	public String toString() {
		return "RoomSelectVO [roomtypeId=" + roomtypeId + ", cmpId=" + cmpId + ", roomtypeName=" + roomtypeName
				+ ", roomtypeAmount=" + roomtypeAmount + ", roomtypePeople=" + roomtypePeople + ", totalScore="
				+ totalScore + ", totalPeople=" + totalPeople + ", roomtypePrice=" + roomtypePrice + ", roomtypeStatus="
				+ roomtypeStatus + ", roomtypeIntroduce=" + roomtypeIntroduce + ", roomImg=" + roomImg + ", cmpName="
				+ cmpName + ", cmpTel=" + cmpTel + ", location=" + location + ", reservationId=" + reservationId
				+ ", reserveDate=" + reserveDate + ", reserveAmount=" + reserveAmount + "]";
	}
	
}
