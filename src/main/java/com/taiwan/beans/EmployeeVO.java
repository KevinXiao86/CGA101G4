package com.taiwan.beans;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class EmployeeVO  extends Core {
	private Integer empId;
	private String empName;
	private String empPassword;
	private String empStatus;
	private Integer funcID;
	
	public Integer getFuncID() {
		return funcID;
	}
	public void setFuncID(Integer funcID) {
		this.funcID = funcID;
	}

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
				+ empStatus + ", funcID=" + funcID + ", hiredate=" + hiredate + "]";
	}

	public com.taiwan.beans.EmployeeFunctionVO getEmployeeFunctionVO() {
		com.taiwan.service.employeeFuction.EmployeeFunctionService eFSvc = new com.taiwan.service.employeeFuction.EmployeeFunctionService();
		com.taiwan.beans.EmployeeFunctionVO employeeFunctionVO=eFSvc.getOneEmployeeFunction(funcID);
	    return employeeFunctionVO;
    }
	
	
}
