package com.taiwan.beans;

import java.sql.Timestamp;

public class TktOrder {
	private Integer tktOrderId;
	private Integer custId;
	private Integer originalPrice;
	private Timestamp orderdate;
	private Integer ttlPrice;
	private Integer custCopId;
	private String qrcode;
	private String orderName;
	private String orderEmail;
	private String orderMobile;	

	public TktOrder(Integer tktOrderId, Integer custId, Integer originalPrice, Timestamp orderdate, Integer ttlPrice,
			Integer custCopId, String qrcode, String orderName, String orderEmail, String orderMobile) {
		super();
		this.tktOrderId = tktOrderId;
		this.custId = custId;
		this.originalPrice = originalPrice;
		this.orderdate = orderdate;
		this.ttlPrice = ttlPrice;
		this.custCopId = custCopId;
		this.qrcode = qrcode;
		this.orderName = orderName;
		this.orderEmail = orderEmail;
		this.orderMobile = orderMobile;
	}

	@Override
	public String toString() {
		return "TktOrder [tktOrderId=" + tktOrderId + ", custId=" + custId + ", originalPrice=" + originalPrice
				+ ", orderdate=" + orderdate + ", ttlPrice=" + ttlPrice + ", custCopId=" + custCopId + ", qrcode="
				+ qrcode + ", orderName=" + orderName + ", orderEmail=" + orderEmail + ", orderMobile=" + orderMobile
				+ "]";
	}

	public Integer getTktOrderId() {
		return tktOrderId;
	}

	public void setTktOrderId(Integer tktOrderId) {
		this.tktOrderId = tktOrderId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Timestamp getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}

	public Integer getTtlPrice() {
		return ttlPrice;
	}

	public void setTtlPrice(Integer ttlPrice) {
		this.ttlPrice = ttlPrice;
	}

	public Integer getCustCopId() {
		return custCopId;
	}

	public void setCustCopId(Integer custCopId) {
		this.custCopId = custCopId;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderEmail() {
		return orderEmail;
	}

	public void setOrderEmail(String orderEmail) {
		this.orderEmail = orderEmail;
	}

	public String getOrderMobile() {
		return orderMobile;
	}

	public void setOrderMobile(String orderMobile) {
		this.orderMobile = orderMobile;
	}

	public TktOrder() {
		super();
	}

}