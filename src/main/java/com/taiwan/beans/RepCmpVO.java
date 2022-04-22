package com.taiwan.beans;

import java.sql.Timestamp;

public class RepCmpVO {
	private Integer repCmpId;
	private Integer custId;
	private Integer roomId;
	private Integer empId;
	private String reason;
	private Timestamp repCmpDate;
	private String status;
	private String result;

	public RepCmpVO(Integer repCmpId, Integer custId, Integer roomId, Integer empId, String reason,
			Timestamp repCmpDate, String status, String result) {
		super();
		this.repCmpId = repCmpId;
		this.custId = custId;
		this.roomId = roomId;
		this.empId = empId;
		this.reason = reason;
		this.repCmpDate = repCmpDate;
		this.status = status;
		this.result = result;
	}

	public RepCmpVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRepCmpId() {
		return repCmpId;
	}

	public void setRepCmpId(Integer repCmpId) {
		this.repCmpId = repCmpId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Timestamp getRepCmpDate() {
		return repCmpDate;
	}

	public void setRepCmpDate(Timestamp repCmpDate) {
		this.repCmpDate = repCmpDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Rep_Cmp [repCmpId=" + repCmpId + ", custId=" + custId + ", roomId=" + roomId + ", empId=" + empId
				+ ", reason=" + reason + ", repCmpDate=" + repCmpDate + ", status=" + status + ", result=" + result
				+ "]";
	}
}
