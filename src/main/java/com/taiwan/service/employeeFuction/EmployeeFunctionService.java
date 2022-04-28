package com.taiwan.service.employeeFuction;

import com.taiwan.dao.employeeFunction.EmployeeFunctionDAO_interface;
import com.taiwan.dao.employeeFunction.impl.EmployeeFunctionJDBCDAO;

import java.util.List;

import com.taiwan.beans.EmployeeFunctionVO;

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

//	public Set<EmpVO> getEmpsByDeptno(Integer deptno) {
//		return dao.getEmpsByDeptno(deptno);
//	}

	public void deleteEmployeeFunction(Integer funcId) {
		dao.delete(funcId);
	}
}