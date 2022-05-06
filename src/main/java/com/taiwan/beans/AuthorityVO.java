package com.taiwan.beans;

import java.util.List;

public class AuthorityVO {
	private Integer empId;
	private Integer funcId;
	
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public Integer getFuncId() {
		return funcId;
	}
	public void setFuncId(Integer funcId) {
		this.funcId = funcId;
	}
	@Override
	public String toString() {
		return "AuthorityVO [empId=" + empId + ", funcId=" + funcId + "]";
	}
	public com.taiwan.beans.EmployeeVO getEmployeeVOs() {
		com.taiwan.service.employee.EmployeeService empSvc = new com.taiwan.service.employee.EmployeeService();
		com.taiwan.beans.EmployeeVO employeeVO=empSvc.getOneEmp(empId);
	    return employeeVO;
	    
    }
}
