package com.taiwan.beans;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class Company extends Core {
	private static final long serialVersionUID = 1L;
	private Integer cmpId;
	private String cmpName;
	private String cmpTel;
	private String cmpMail;
	private String cmper;
	private String cmperTel;
	private String cmpStatus;
	private String auditStatus;
	private String serialNo;
	private String cmpAccount;
	private String cmpPassword;
	private String cmpIntroduce;
	private String checkinTime;
	private String checkoutTime;
	private String location;
	private String notice;
	private String canx;
	private String bankAccount;
	
	private List<Roomtype> roomtypes;

	public Company() {
		super();
	}

	public Company(Integer cmpId, String cmpName, String cmpTel, String cmpMail, String cmper, String cmperTel,
			String cmpStatus, String auditStatus, String serialNo, String cmpAccount, String cmpPassword,
			String cmpIntroduce, String checkinTime, String checkoutTime, String location, String notice, String canx,
			String bankAccount) {
		super();
		this.cmpId = cmpId;
		this.cmpName = cmpName;
		this.cmpTel = cmpTel;
		this.cmpMail = cmpMail;
		this.cmper = cmper;
		this.cmperTel = cmperTel;
		this.cmpStatus = cmpStatus;
		this.auditStatus = auditStatus;
		this.serialNo = serialNo;
		this.cmpAccount = cmpAccount;
		this.cmpPassword = cmpPassword;
		this.cmpIntroduce = cmpIntroduce;
		this.checkinTime = checkinTime;
		this.checkoutTime = checkoutTime;
		this.location = location;
		this.notice = notice;
		this.canx = canx;
		this.bankAccount = bankAccount;
	}
	public Company(Integer cmpId, String cmpName, String cmpTel, String cmpMail, String cmper, String cmperTel,
			String cmpStatus, String auditStatus, String serialNo, String cmpAccount, String cmpPassword,
			String cmpIntroduce, String checkinTime, String checkoutTime, String location, String notice, String canx,
			String bankAccount, List<Roomtype> roomtypes) {
		super();
		this.cmpId = cmpId;
		this.cmpName = cmpName;
		this.cmpTel = cmpTel;
		this.cmpMail = cmpMail;
		this.cmper = cmper;
		this.cmperTel = cmperTel;
		this.cmpStatus = cmpStatus;
		this.auditStatus = auditStatus;
		this.serialNo = serialNo;
		this.cmpAccount = cmpAccount;
		this.cmpPassword = cmpPassword;
		this.cmpIntroduce = cmpIntroduce;
		this.checkinTime = checkinTime;
		this.checkoutTime = checkoutTime;
		this.location = location;
		this.notice = notice;
		this.canx = canx;
		this.bankAccount = bankAccount;
		this.roomtypes = roomtypes;
	}

	public Integer getCmpId() {
		return cmpId;
	}

	public void setCmpId(Integer cmpId) {
		this.cmpId = cmpId;
	}

	public String getCmpName() {
		return cmpName;
	}

	public void setCmpName(String cmpName) {
		this.cmpName = cmpName;
	}

	public String getCmpTel() {
		return cmpTel;
	}

	public void setCmpTel(String cmpTel) {
		this.cmpTel = cmpTel;
	}

	public String getCmpMail() {
		return cmpMail;
	}

	public void setCmpMail(String cmpMail) {
		this.cmpMail = cmpMail;
	}

	public String getCmper() {
		return cmper;
	}

	public void setCmper(String cmper) {
		this.cmper = cmper;
	}

	public String getCmperTel() {
		return cmperTel;
	}

	public void setCmperTel(String cmperTel) {
		this.cmperTel = cmperTel;
	}

	public String getCmpStatus() {
		return cmpStatus;
	}

	public void setCmpStatus(String cmpStatus) {
		this.cmpStatus = cmpStatus;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getCmpAccount() {
		return cmpAccount;
	}

	public void setCmpAccount(String cmpAccount) {
		this.cmpAccount = cmpAccount;
	}

	public String getCmpPassword() {
		return cmpPassword;
	}

	public void setCmpPassword(String cmpPassword) {
		this.cmpPassword = cmpPassword;
	}

	public String getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(String checkinTime) {
		this.checkinTime = checkinTime;
	}

	public String getCheckoutTime() {
		return checkoutTime;
	}

	public void setCheckoutTime(String checkoutTime) {
		this.checkoutTime = checkoutTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCanx() {
		return canx;
	}

	public void setCanx(String canx) {
		this.canx = canx;
	}

	public String getCmpIntroduce() {
		return cmpIntroduce;
	}

	public void setCmpIntroduce(String cmpIntroduce) {
		this.cmpIntroduce = cmpIntroduce;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Roomtype> getRoomtypes() {
		return roomtypes;
	}

	public void setRoomtypes(List<Roomtype> roomtypes) {
		this.roomtypes = roomtypes;
	}

	@Override
	public String toString() {
		return "Company [cmpId=" + cmpId + ", cmpName=" + cmpName + ", cmpTel=" + cmpTel + ", cmpMail=" + cmpMail
				+ ", cmper=" + cmper + ", cmperTel=" + cmperTel + ", cmpStatus=" + cmpStatus + ", auditStatus="
				+ auditStatus + ", serialNo=" + serialNo + ", cmpAccount=" + cmpAccount + ", cmpPassword=" + cmpPassword
				+ ", cmpIntroduce=" + cmpIntroduce + ", checkinTime=" + checkinTime + ", checkoutTime=" + checkoutTime
				+ ", location=" + location + ", notice=" + notice + ", canx=" + canx + ", bankAccount=" + bankAccount
				+ "]";
	}
}
