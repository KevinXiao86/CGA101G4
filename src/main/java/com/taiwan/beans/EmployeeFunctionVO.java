package com.taiwan.beans;

import java.sql.Date;
import java.time.LocalDate;

public class EmployeeFunctionVO   {
	private Integer funcId;
	private String funcName;
	public Integer getFuncId() {
		return funcId;
	}
	public void setFuncId(Integer funcId) {
		this.funcId = funcId;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	@Override
	public String toString() {
		return "EmployeeFunctionVO [funcId=" + funcId + ", funcName=" + funcName + "]";
	}

}
