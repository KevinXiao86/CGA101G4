package com.taiwan.beans;

import java.sql.Date;
import java.time.LocalDate;

public class EmployeeVO  implements java.io.Serializable {
	private Integer empId;
	private String empName;
	private String empPassword;
	private String empStatus;
	private Date hiredate;
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	public String getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	@Override
	public String toString() {
		return "EmployeeVO [empId=" + empId + ", empName=" + empName + ", empPassword=" + empPassword + ", empStatus="
				+ empStatus + ", hiredate=" + hiredate + "]";
	}

	
	
	
}
