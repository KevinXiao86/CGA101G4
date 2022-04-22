package com.taiwan.beans;

public class CartVO {
	private Integer tktId;
	private Integer custId;
	
	public CartVO(Integer tktId, Integer custId) {
		super();
		this.tktId = tktId;
		this.custId = custId;
	}
	
	public CartVO() {
		super();
	}

	public Integer getTktId() {
		return tktId;
	}

	public void setTktId(Integer tktId) {
		this.tktId = tktId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		return "Cart [tktId=" + tktId + ", custId=" + custId + "]";
	}
}
