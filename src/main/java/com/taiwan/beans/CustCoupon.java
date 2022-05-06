package com.taiwan.beans;

import java.sql.Timestamp;

public class CustCoupon {
	private Integer custCopId;
	private Integer custId;
	private Integer copId;
	private Timestamp getdate;
	private Timestamp usedate;
	private Integer roomOrderId;
	private Integer tktOrderId;
	private Integer discount;
	private String status;

	public CustCoupon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustCoupon(Integer custCopId, Integer custId, Integer copId, Timestamp getdate, Timestamp usedate,
			Integer roomOrderId, Integer tktOrderId, Integer discount, String status) {
		super();
		this.custCopId = custCopId;
		this.custId = custId;
		this.copId = copId;
		this.getdate = getdate;
		this.usedate = usedate;
		this.roomOrderId = roomOrderId;
		this.tktOrderId = tktOrderId;
		this.discount = discount;
		this.status = status;
	}

	public Integer getCustCopId() {
		return custCopId;
	}

	public void setCustCopId(Integer custCopId) {
		this.custCopId = custCopId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getCopId() {
		return copId;
	}

	public void setCopId(Integer copId) {
		this.copId = copId;
	}

	public Timestamp getGetdate() {
		return getdate;
	}

	public void setGetdate(Timestamp getdate) {
		this.getdate = getdate;
	}

	public Timestamp getUsedate() {
		return usedate;
	}

	public void setUsedate(Timestamp usedate) {
		this.usedate = usedate;
	}

	public Integer getRoomOrderId() {
		return roomOrderId;
	}

	public void setRoomOrderId(Integer roomOrderId) {
		this.roomOrderId = roomOrderId;
	}

	public Integer getTktOrderId() {
		return tktOrderId;
	}

	public void setTktOrderId(Integer tktOrderId) {
		this.tktOrderId = tktOrderId;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustCoupon [custCopId=" + custCopId + ", custId=" + custId + ", copId=" + copId + ", getdate=" + getdate
				+ ", usedate=" + usedate + ", roomOrderId=" + roomOrderId + ", tktOrderId=" + tktOrderId + ", discount="
				+ discount + ", status=" + status + "]";
	}
	// for join couponStatus from copId
	public com.taiwan.beans.CouponVO getCouponVO() {
		 com.taiwan.service.coupon.impl.CouponServiceImpl copSvcImpl = new com.taiwan.service.coupon.impl.CouponServiceImpl();
		 com.taiwan.beans.CouponVO coupon = copSvcImpl.findById(copId);
	    return coupon;
    }
	
}
