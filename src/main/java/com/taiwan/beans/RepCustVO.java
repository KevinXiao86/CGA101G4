package com.taiwan.beans;

import java.sql.Timestamp;

public class RepCustVO {
	private Integer repCustId;

	private Integer custId;
	private Integer empId;
	private Integer cmpId;
	private String repCustReason;
	private Timestamp repCustDate;
	private String repCustStatus;
	private String repCustResult;
	public Integer getRepCustId() {
		return repCustId;
	}
	public void setRepCustId(Integer repCustId) {
		this.repCustId = repCustId;
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
	public Integer getCmpId() {
		return cmpId;
	}
	public void setCmpId(Integer cmpId) {
		this.cmpId = cmpId;
	}
	public String getRepCustReason() {
		return repCustReason;
	}
	public void setRepCustReason(String repCustReason) {
		this.repCustReason = repCustReason;
	}
	public Timestamp getRepCustDate() {
		return repCustDate;
	}
	public void setRepCustDate(Timestamp repCustDate) {
		this.repCustDate = repCustDate;
	}
	public String getRepCustStatus() {
		return repCustStatus;
	}
	public void setRepCustStatus(String repCustStatus) {
		this.repCustStatus = repCustStatus;
	}
	public String getRepCustResult() {
		return repCustResult;
	}
	public void setRepCustResult(String repCustResult) {
		this.repCustResult = repCustResult;
	}
	
	
	
}
