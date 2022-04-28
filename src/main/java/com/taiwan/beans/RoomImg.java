package com.taiwan.beans;

public class RoomImg {
	private Integer roomImgId;
	private Integer roomtypeId;
	private String roomImg;
	public RoomImg() {
		super();
	}
	public RoomImg(Integer roomImgId, Integer roomtypeId, String roomImg) {
		super();
		this.roomImgId = roomImgId;
		this.roomtypeId = roomtypeId;
		this.roomImg = roomImg;
	}
	
	public Integer getRoomImgId() {
		return roomImgId;
	}
	public void setRoomImgId(Integer roomImgId) {
		this.roomImgId = roomImgId;
	}
	public Integer getRoomtypeId() {
		return roomtypeId;
	}
	public void setRoomtypeId(Integer roomtypeId) {
		this.roomtypeId = roomtypeId;
	}
	public String getRoomImg() {
		return roomImg;
	}
	public void setRoomImg(String roomImg) {
		this.roomImg = roomImg;
	}
	@Override
	public String toString() {
		return "RoomImg [roomImgId=" + roomImgId + ", roomtypeId=" + roomtypeId + ", roomImg=" + roomImg + "]";
	}
}
