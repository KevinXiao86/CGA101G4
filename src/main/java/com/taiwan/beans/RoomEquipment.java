package com.taiwan.beans;

public class RoomEquipment {
	private Integer roomtypeId;
	private Integer equipmentId;
	public RoomEquipment() {
		super();
	}
	public RoomEquipment(Integer roomtypeId, Integer equipmentId) {
		super();
		this.roomtypeId = roomtypeId;
		this.equipmentId = equipmentId;
	}
	public Integer getRoomtypeId() {
		return roomtypeId;
	}
	public void setRoomtypeId(Integer roomtypeId) {
		this.roomtypeId = roomtypeId;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	@Override
	public String toString() {
		return "RoomEquipment [roomtypeId=" + roomtypeId + ", equipmentId=" + equipmentId + "]";
	}
}
