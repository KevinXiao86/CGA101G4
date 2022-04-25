package com.taiwan.beans;

public class Equipment {
	private Integer equipmentId;
	private String equipmentName;
	public Equipment() {
		super();
	}
	public Equipment(Integer equipmentId, String equipmentName) {
		super();
		this.equipmentId = equipmentId;
		this.equipmentName = equipmentName;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	@Override
	public String toString() {
		return "Equipment [equipmentId=" + equipmentId + ", equipmentName=" + equipmentName + "]";
	}
}
