package com.taiwan.beans;

import java.sql.Date;
import java.time.LocalDate;

public class EmployeeVO  implements java.io.Serializable {
	private Integer emp_id;
	private String emp_name;
	private String emp_password;
	private String emp_status;
	private Date hiredate;

	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_password() {
		return emp_password;
	}
	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}
	public String getEmp_status() {
		return emp_status;
	}
	public void setEmp_status(String emp_status) {
		this.emp_status = emp_status;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	@Override
	public String toString() {
		return "�d�ߵ��G [���u�s��=" + emp_id + ", ���u�m�W=" + emp_name + ", �n�J�K�X=" + emp_password
				+ ", �ҥΪ��A=" + emp_status + ", �Х߮ɶ�=" + hiredate + "]";
	}
	
	
}
