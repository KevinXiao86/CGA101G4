package com.taiwan.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class CouponVO implements Serializable{
	private Integer copId;
	private String copName;
	private String introduce;
	private Integer discount;
	private Timestamp startdate;
	private Timestamp enddate;
	private String img;
	private String status;
	
	public CouponVO(Integer copId, String copName, String introduce, Integer discount, Timestamp startdate, Timestamp enddate,
			String img, String status) {
		super();
		this.copId = copId;
		this.copName = copName;
		this.introduce = introduce;
		this.discount = discount;
		this.startdate = startdate;
		this.enddate = enddate;
		this.img = img;
		this.status = status;
	}
	
	public CouponVO() {
		super();
	}

	public Integer getCopId() {
		return copId;
	}

	public void setCopId(Integer copId) {
		this.copId = copId;
	}

	public String getCopName() {
		return copName;
	}

	public void setCopName(String copName) {
		this.copName = copName;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Timestamp getStartdate() {
		return startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	public Timestamp getEnddate() {
		return enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Coupon [copId=" + copId + ", copName=" + copName + ", introduce=" + introduce + ", discount=" + discount
				+ ", startdate=" + startdate + ", enddate=" + enddate + ", img=" + img + ", status=" + status + "]";
	}
	
	
}
