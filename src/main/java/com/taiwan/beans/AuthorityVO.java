package com.taiwan.beans;

public class AuthorityVO {
	private Integer emp_id;
	private Integer func_id;
	
	


	
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public Integer getFunc_id() {
		return func_id;
	}
	public void setFunc_id(Integer func_id) {
		this.func_id = func_id;
	}
	@Override
	public String toString() {
		return " [���u�s��=" + emp_id + ", �޲z���v�O=" + func_id + "]";
	}
}
