package com.taiwan.beans;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class Company extends Core{
	private static final long serialVersionUID = 1L;
	private Integer cmpId;

//	@Length(min = 1 , message = "廠商名稱未輸入")
	private String cmpName;
	
//	@Pattern(regexp = "(\\d{2,3}-?|\\(\\d{2,3}\\))\\d{3,4}-?\\d{4}|09\\d{2}(\\d{6}|-\\d{3}-\\d{3})", message = "電話號碼格式錯誤")
	private String cmpTel;
	
//	@Email(regexp = "^[a-z\\d]+(\\.[a-z\\d]+)*@([\\da-z](-[\\da-z])?)+(\\.{1,2}[a-z]+)+$", message = "信箱格式不正确")
	private String cmpMail;
	
//	@Length(min = 1 , message = "負責人未輸入")
	private String cmper;
	
//	@Pattern(regexp = "(\\d{2,3}-?|\\(\\d{2,3}\\))\\d{3,4}-?\\d{4}|09\\d{2}(\\d{6}|-\\d{3}-\\d{3})", message = "手機號碼格式錯誤")
	private String cmperTel;
	private String cmpStatus;
	private String auditStatus;
	private String serialNo;
	
//	@Pattern(regexp = "^\\w{4,12}$", message = "必須由字母,數字下滑線組成,並且長度為5到12位")
	private String cmpAccount;
	
//	@NotNull(message = "密碼未輸入")
	private String cmpPassword;
	
//	@Length(min = 1 , message = "廠商名稱未輸入")
	private String cmpIntroduce;
	
//	@Length(min = 1 , message = "請輸入入住時間")
	private String checkinTime;
	
//	@Length(min = 1 , message = "請輸入退房時間")
	private String checkoutTime;
	
//	@Length(min = 1 , message = "請輸入地點")
	private String location;
	
//	@Length(min = 1 , message = "請輸入注意事項")
	private String notice;
	
//	@Length(min = 1 , message = "請輸入取消政策")
	private String canx;
	
	private String bankAccount;
	
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
