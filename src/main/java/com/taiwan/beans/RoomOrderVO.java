package com.taiwan.beans;

import java.security.Timestamp;
import java.sql.Date;

public class RoomOrderVO {
	private Integer room_order_id;
	private Integer cust_id;
	private Timestamp room_order_date;
	private Integer room_order_price;
	private Date room_order_checkin_date;
	private Date room_order_checkout_date;
	private String prroom_order_status;
	private String room_order_cancel;
	private Integer room_order_total_price;
	private Integer cust_cop_id;
	public Integer getRoom_order_id() {
		return room_order_id;
	}
	public void setRoom_order_id(Integer room_order_id) {
		this.room_order_id = room_order_id;
	}
	public Integer getCust_id() {
		return cust_id;
	}
	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}
	public Timestamp getRoom_order_date() {
		return room_order_date;
	}
	public void setRoom_order_date(Timestamp room_order_date) {
		this.room_order_date = room_order_date;
	}
	public Integer getRoom_order_price() {
		return room_order_price;
	}
	public void setRoom_order_price(Integer room_order_price) {
		this.room_order_price = room_order_price;
	}
	public Date getRoom_order_checkin_date() {
		return room_order_checkin_date;
	}
	public void setRoom_order_checkin_date(Date room_order_checkin_date) {
		this.room_order_checkin_date = room_order_checkin_date;
	}
	public Date getRoom_order_checkout_date() {
		return room_order_checkout_date;
	}
	public void setRoom_order_checkout_date(Date room_order_checkout_date) {
		this.room_order_checkout_date = room_order_checkout_date;
	}
	public String getPrroom_order_status() {
		return prroom_order_status;
	}
	public void setPrroom_order_status(String prroom_order_status) {
		this.prroom_order_status = prroom_order_status;
	}
	public String getRoom_order_cancel() {
		return room_order_cancel;
	}
	public void setRoom_order_cancel(String room_order_cancel) {
		this.room_order_cancel = room_order_cancel;
	}
	public Integer getRoom_order_total_price() {
		return room_order_total_price;
	}
	public void setRoom_order_total_price(Integer room_order_total_price) {
		this.room_order_total_price = room_order_total_price;
	}
	public Integer getCust_cop_id() {
		return cust_cop_id;
	}
	public void setCust_cop_id(Integer cust_cop_id) {
		this.cust_cop_id = cust_cop_id;
	}
	
	
	
	
}
