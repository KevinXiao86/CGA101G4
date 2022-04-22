package com.taiwan.beans;


import java.sql.Timestamp;

public class CustPlatMailVO {
	private Integer custPlatId;
	private Integer custId;
	private Integer empId;
	private String msg;
	private Timestamp custPlatTime;
	private Integer who;


	public Integer getCustPlatId() {
		return custPlatId;
	}

	public void setCustPlatId(Integer custPlatId) {
		this.custPlatId = custPlatId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Timestamp getCustPlatTime() {
		return custPlatTime;
	}

	public void setCustPlatTime(Timestamp custPlatTime) {
		this.custPlatTime = custPlatTime;
	}

	public Integer getWho() {
		return who;
	}

	public void setWho(Integer who) {
		this.who = who;
	}

	public CustPlatMailVO() {
		super();
	}

	public CustPlatMailVO(Integer custPlatId, Integer custId, Integer empId, String msg, Timestamp custPlatTime,
			Integer who) {
		super();
		this.custPlatId = custPlatId;
		this.custId = custId;
		this.empId = empId;
		this.msg = msg;
		this.custPlatTime = custPlatTime;
		this.who = who;
	}

	@Override
	public String toString() {
		return "Cust_Plat_Mail [custPlatId=" + custPlatId + ", custId=" + custId + ", empId=" + empId + ", msg=" + msg
				+ ", custPlatTime=" + custPlatTime + ", who=" + who + "]";
	}
}
