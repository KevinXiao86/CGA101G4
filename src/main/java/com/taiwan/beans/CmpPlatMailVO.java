package com.taiwan.beans;

import java.security.Timestamp;
import java.time.LocalDate;

public class CmpPlatMailVO {
	private Integer cmpPlatMailId;
	private Integer cmpId;
	private Integer empId;
	private String cmpPlatMailMsg;
	private Timestamp cmpPlatMailSendTime;
	private String cmpPlatMailWho;


	public Integer getCmpPlatMailId() {
		return cmpPlatMailId;
	}

	public void setCmpPlatMailId(Integer cmpPlatMailId) {
		this.cmpPlatMailId = cmpPlatMailId;
	}

	public Integer getCmpId() {
		return cmpId;
	}

	public void setCmpId(Integer cmpId) {
		this.cmpId = cmpId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getCmpPlatMailMsg() {
		return cmpPlatMailMsg;
	}

	public void setCmpPlatMailMsg(String cmpPlatMailMsg) {
		this.cmpPlatMailMsg = cmpPlatMailMsg;
	}

	public Timestamp getCmpPlatMailSendTime() {
		return cmpPlatMailSendTime;
	}

	public void setCmpPlatMailSendTime(Timestamp cmpPlatMailSendTime) {
		this.cmpPlatMailSendTime = cmpPlatMailSendTime;
	}

	public String getCmpPlatMailWho() {
		return cmpPlatMailWho;
	}

	public void setCmpPlatMailWho(String cmpPlatMailWho) {
		this.cmpPlatMailWho = cmpPlatMailWho;
	}


	
}
