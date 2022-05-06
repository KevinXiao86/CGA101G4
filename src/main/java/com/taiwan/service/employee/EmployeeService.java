package com.taiwan.service.employee;

import java.util.List;

import com.taiwan.beans.EmployeeVO;
import com.taiwan.dao.employee.EmployeeDAO_interface;
import com.taiwan.dao.employee.impl.EmployeeJDBCDAO;

public class EmployeeService {

	private EmployeeDAO_interface dao;

	public EmployeeService() {
		dao = new EmployeeJDBCDAO();
	}

	public EmployeeVO addEmp(String empName, String empPassword) {

		EmployeeVO employeeVO = new EmployeeVO();

		employeeVO.setEmpName(empName);
		employeeVO.setEmpPassword(empPassword);

		dao.insert(employeeVO);

		return employeeVO;
	}

	// 預留給 Struts 2 用的
	public void addEmp(EmployeeVO employeeVO) {
		dao.insert(employeeVO);
	}

	public EmployeeVO updateEmp(Integer empId, String empName, String empPassword, String status,
			java.sql.Date hiredate) {

		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmpId(empId);
		employeeVO.setEmpName(empName);
		employeeVO.setEmpPassword(empPassword);
		employeeVO.setEmpStatus(status);
		employeeVO.setHiredate(hiredate);

		dao.update(employeeVO);

		return dao.findByPrimaryKey(empId);
	}

	// 預留給 Struts 2 用的
	public void updateEmp(EmployeeVO employeeVO) {
		dao.update(employeeVO);
	}

	public void deleteEmp(Integer empId) {
		dao.delete(empId);
	}

	public EmployeeVO getOneEmp(Integer empId) {
		return dao.findByPrimaryKey(empId);
	}

	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}
	public EmployeeVO login(Integer empId,String empPassword) {
		
		return dao.login(empId, empPassword);
	};

}
