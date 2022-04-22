package com.taiwan.beans;

import java.security.Timestamp;
import java.time.LocalDate;

public class CmpPlatMailVO {
	private Integer cmp_plat_mail_id;
	private Integer cmp_id;
	private Integer emp_id;
	private String cmp_plat_mail_msg;
	private Timestamp cmp_plat_mail_send_time;
	private String cmp_plat_mail_who;

	public CmpPlatMailVO() {
		super();
	}

	public CmpPlatMailVO(Integer cmp_plat_mail_id, Integer cmp_id, Integer emp_id, String cmp_plat_mail_msg,
			Timestamp cmp_plat_mail_send_time, String cmp_plat_mail_who) {
		super();
		this.cmp_plat_mail_id = cmp_plat_mail_id;
		this.cmp_id = cmp_id;
		this.emp_id = emp_id;
		this.cmp_plat_mail_msg = cmp_plat_mail_msg;
		this.cmp_plat_mail_send_time = cmp_plat_mail_send_time;
		this.cmp_plat_mail_who = cmp_plat_mail_who;
	}


	public Integer getCmp_plat_mail_id() {
		return cmp_plat_mail_id;
	}

	public void setCmp_plat_mail_id(Integer cmp_plat_mail_id) {
		this.cmp_plat_mail_id = cmp_plat_mail_id;
	}

	public Integer getCmp_id() {
		return cmp_id;
	}

	public void setCmp_id(Integer cmp_id) {
		this.cmp_id = cmp_id;
	}

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public String getCmp_plat_mail_msg() {
		return cmp_plat_mail_msg;
	}

	public void setCmp_plat_mail_msg(String cmp_plat_mail_msg) {
		this.cmp_plat_mail_msg = cmp_plat_mail_msg;
	}

	public Timestamp getCmp_plat_mail_send_time() {
		return cmp_plat_mail_send_time;
	}

	public void setCmp_plat_mail_send_time(Timestamp cmp_plat_mail_send_time) {
		this.cmp_plat_mail_send_time = cmp_plat_mail_send_time;
	}

	public String getCmp_plat_mail_who() {
		return cmp_plat_mail_who;
	}

	public void setCmp_plat_mail_who(String cmp_plat_mail_who) {
		this.cmp_plat_mail_who = cmp_plat_mail_who;
	}


	@Override
	public String toString() {
		return "CmpPlatMailVO [cmp_plat_mail_id=" + cmp_plat_mail_id + ", cmp_id=" + cmp_id + ", emp_id=" + emp_id
				+ ", cmp_plat_mail_msg=" + cmp_plat_mail_msg + ", cmp_plat_mail_send_time=" + cmp_plat_mail_send_time
				+ ", cmp_plat_mail_who=" + cmp_plat_mail_who + "]";
	}
}
