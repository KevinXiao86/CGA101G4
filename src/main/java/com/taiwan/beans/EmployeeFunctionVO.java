package com.taiwan.beans;

import java.sql.Date;
import java.time.LocalDate;

public class EmployeeFunctionVO implements java.io.Serializable {
	private Integer func_id;
	private String func_name;


	public Integer getFunc_id() {
		return func_id;
	}
	public void setFunc_id(Integer func_id) {
		this.func_id = func_id;
	}
	public String getFunc_name() {
		return func_name;
	}
	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}

}
