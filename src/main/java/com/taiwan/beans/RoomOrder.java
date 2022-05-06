package com.taiwan.beans;

import java.util.Date;

public class RoomOrder {
	private Integer roomOrderId;
	private Integer custId; // 外鍵: 一個訂單只會有一個會員
	private Date roomOrderDate; // 訂單成立日期
	private Integer roomOrderPrice;
	private Date checkinDate;
	private Date checkoutDate;
	private String roomOrderStatus;
	private String cancel;
	private Integer totalPrice;
	private Integer custCopId;// 外鍵: 一個訂單只會使用一個優惠券
	private Integer cmpId;

	private CustomerVO customer;
	private Company company;

	public RoomOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomOrder(Integer roomOrderId, Integer custId, Date roomOrderDate, Integer roomOrderPrice, Date checkinDate,
			Date checkoutDate, String roomOrderStatus, String cancel, Integer totalPrice, Integer custCopId,
			Integer cmpId, CustomerVO customer, Company company) {
		super();
		this.roomOrderId = roomOrderId;
		this.custId = custId;
		this.roomOrderDate = roomOrderDate;
		this.roomOrderPrice = roomOrderPrice;
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.roomOrderStatus = roomOrderStatus;
		this.cancel = cancel;
		this.totalPrice = totalPrice;
		this.custCopId = custCopId;
		this.cmpId = cmpId;
		this.customer = customer;
		this.company = company;
	}

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

	public Date getRoomOrderDate() {
		return roomOrderDate;
	}

	public void setRoomOrderDate(Date roomOrderDate) {
		this.roomOrderDate = roomOrderDate;
	}

	public Integer getRoomOrderPrice() {
		return roomOrderPrice;
	}

	public void setRoomOrderPrice(Integer roomOrderPrice) {
		this.roomOrderPrice = roomOrderPrice;
	}

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public String getRoomOrderStatus() {
		return roomOrderStatus;
	}

	public void setRoomOrderStatus(String roomOrderStatus) {
		this.roomOrderStatus = roomOrderStatus;
	}

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getCustCopId() {
		return custCopId;
	}

	public void setCustCopId(Integer custCopId) {
		this.custCopId = custCopId;
	}

	public Integer getCmpId() {
		return cmpId;
	}

	public void setCmpId(Integer cmpId) {
		this.cmpId = cmpId;
	}

	public CustomerVO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerVO customer) {
		this.customer = customer;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "RoomOrder [roomOrderId=" + roomOrderId + ", custId=" + custId + ", roomOrderDate=" + roomOrderDate
				+ ", roomOrderPrice=" + roomOrderPrice + ", checkinDate=" + checkinDate + ", checkoutDate="
				+ checkoutDate + ", roomOrderStatus=" + roomOrderStatus + ", cancel=" + cancel + ", totalPrice="
				+ totalPrice + ", custCopId=" + custCopId + ", cmpId=" + cmpId + ", customer=" + customer + ", company="
				+ company + "]";
	}

}
