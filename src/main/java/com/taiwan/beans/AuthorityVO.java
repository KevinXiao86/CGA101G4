package com.taiwan.beans;

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
	
}
