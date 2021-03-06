package com.taiwan.beans;

import java.util.List;

public class Roomtype extends Core{
	private static final long serialVersionUID = 1L;
	private Integer roomtypeId;
	private Integer cmpId;
	private String roomtypeName;
	private Integer roomtypeAmount;
	private Integer roomtypePeople;
	private Integer totalScore;
	private Integer totalPeople;
	private Integer roomtypePrice;
	private String roomtypeStatus;
	private Integer roomtypeArea;
	private String roomtypeIntroduce;
	private List<RoomImg> roomImgs;
	public Roomtype() {
		super();
	}
	public Roomtype(Integer roomtypeId, Integer cmpId, String roomtypeName, Integer roomtypeAmount,
			Integer roomtypePeople, Integer totalScore, Integer totalPeople, Integer roomtypePrice,
			String roomtypeStatus, Integer roomtypeArea, String roomtypeIntroduce) {
		super();
		this.roomtypeId = roomtypeId;
		this.cmpId = cmpId;
		this.roomtypeName = roomtypeName;
		this.roomtypeAmount = roomtypeAmount;
		this.roomtypePeople = roomtypePeople;
		this.totalScore = totalScore;
		this.totalPeople = totalPeople;
		this.roomtypePrice = roomtypePrice;
		this.roomtypeStatus = roomtypeStatus;
		this.roomtypeArea = roomtypeArea;
		this.roomtypeIntroduce = roomtypeIntroduce;
	}
	public Roomtype(Integer roomtypeId, Integer cmpId, String roomtypeName, Integer roomtypeAmount,
			Integer roomtypePeople, Integer totalScore, Integer totalPeople, Integer roomtypePrice,
			String roomtypeStatus, Integer roomtypeArea, String roomtypeIntroduce, List<RoomImg> roomImgs) {
		super();
		this.roomtypeId = roomtypeId;
		this.cmpId = cmpId;
		this.roomtypeName = roomtypeName;
		this.roomtypeAmount = roomtypeAmount;
		this.roomtypePeople = roomtypePeople;
		this.totalScore = totalScore;
		this.totalPeople = totalPeople;
		this.roomtypePrice = roomtypePrice;
		this.roomtypeStatus = roomtypeStatus;
		this.roomtypeArea = roomtypeArea;
		this.roomtypeIntroduce = roomtypeIntroduce;
		this.roomImgs = roomImgs;
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
	public Integer getRoomtypeArea() {
		return roomtypeArea;
	}
	public void setRoomtypeArea(Integer roomtypeArea) {
		this.roomtypeArea = roomtypeArea;
	}
	public String getRoomtypeIntroduce() {
		return roomtypeIntroduce;
	}
	public void setRoomtypeIntroduce(String roomtypeIntroduce) {
		this.roomtypeIntroduce = roomtypeIntroduce;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<RoomImg> getRoomImgs() {
		return roomImgs;
	}
	public void setRoomImgs(List<RoomImg> roomImgs) {
		this.roomImgs = roomImgs;
	}
	@Override
	public String toString() {
		return "Roomtype [roomtypeId=" + roomtypeId + ", cmpId=" + cmpId + ", roomtypeName=" + roomtypeName
				+ ", roomtypeAmount=" + roomtypeAmount + ", roomtypePeople=" + roomtypePeople + ", totalScore="
				+ totalScore + ", totalPeople=" + totalPeople + ", roomtypePrice=" + roomtypePrice + ", roomtypeStatus="
				+ roomtypeStatus + ", roomtypeArea=" + roomtypeArea + ", roomtypeIntroduce=" + roomtypeIntroduce;
//				+ ", roomImgs=" + roomImgs + "]";
	}
}
