package com.taiwan.beans;

import java.sql.Timestamp;

public class RepCustVO {
	private Integer rep_cust_id;
	public Integer getCmp_id() {
		return cmp_id;
	}
	public void setCmp_id(Integer cmp_id) {
		this.cmp_id = cmp_id;
	}
	private Integer cust_id;
	private Integer emp_id;
	private Integer cmp_id;
	private String rep_cust_reason;
	private Timestamp rep_cust_date;
	private String rep_cust_status;
	private String rep_cust_result;
	public Integer getRep_cust_id() {
		return rep_cust_id;
	}
	public void setRep_cust_id(Integer rep_cust_id) {
		this.rep_cust_id = rep_cust_id;
	}
	public Integer getCust_id() {
		return cust_id;
	}
	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public String getReason() {
		return rep_cust_reason;
	}
	public void setReason(String reason) {
		this.rep_cust_reason = reason;
	}
	public Timestamp getRep_cust_date() {
		return rep_cust_date;
	}
	public void setRep_cust_date(Timestamp rep_cust_date) {
		this.rep_cust_date = rep_cust_date;
	}
	public String getStatus() {
		return rep_cust_status;
	}
	public void setStatus(String status) {
		this.rep_cust_status = status;
	}
	public String getResult() {
		return rep_cust_result;
	}
	public void setResult(String result) {
		this.rep_cust_result = result;
	}
	
	
}
