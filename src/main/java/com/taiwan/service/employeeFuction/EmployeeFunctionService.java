package com.taiwan.service.employeeFuction;

import com.taiwan.dao.employeeFunction.EmployeeFunctionDAO_interface;
import com.taiwan.dao.employeeFunction.impl.EmployeeFunctionJDBCDAO;

import java.util.List;
import java.util.Set;

import com.taiwan.beans.EmployeeFunctionVO;
import com.taiwan.beans.EmployeeVO;

public class EmployeeFunctionService {

	private EmployeeFunctionDAO_interface dao;

	public EmployeeFunctionService() {
		dao = new EmployeeFunctionJDBCDAO();
	}

	public List<EmployeeFunctionVO> getAll() {
		return dao.getAll();
	}

	public EmployeeFunctionVO getOneEmployeeFunction(Integer funcId) {
		return dao.findByPrimaryKey(funcId);
	}

	public Set<EmployeeVO> getEmpsByfuncId(Integer funcId) {
		return dao.getEmpsByfuncId(funcId);
	}

	public void deleteEmployeeFunction(Integer funcId) {
		dao.delete(funcId);
	}
}