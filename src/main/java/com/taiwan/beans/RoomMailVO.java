package com.taiwan.beans;

import java.security.Timestamp;

public class RoomMailVO {
	private Integer room_mail_id;
	private Integer cust_id;
	private Integer cmp_id;
	private String room_mail_msg;
	private Timestamp room_mail_time;
	private String room_mail_id_who;
	
	public Integer getRoom_mail_id() {
		return room_mail_id;
	}
	public void setRoom_mail_id(Integer room_mail_id) {
		this.room_mail_id = room_mail_id;
	}
	public Integer getCust_id() {
		return cust_id;
	}
	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}
	public Integer getCmp_id() {
		return cmp_id;
	}
	public void setCmp_id(Integer cmp_id) {
		this.cmp_id = cmp_id;
	}
	public String getRoom_mail_msg() {
		return room_mail_msg;
	}
	public void setRoom_mail_msg(String room_mail_msg) {
		this.room_mail_msg = room_mail_msg;
	}
	public Timestamp getRoom_mail_time() {
		return room_mail_time;
	}
	public void setRoom_mail_time(Timestamp room_mail_time) {
		this.room_mail_time = room_mail_time;
	}
	public String getRoom_mail_id_who() {
		return room_mail_id_who;
	}
	public void setRoom_mail_id_who(String room_mail_id_who) {
		this.room_mail_id_who = room_mail_id_who;
	}
	
}
