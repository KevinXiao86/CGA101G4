package com.taiwan.dao.employeeFunction;

import java.util.List;

import com.taiwan.beans.EmployeeFunctionVO;

public interface EmployeeFunctionDAO_interface{
	//�s�W
	 public void insert(EmployeeFunctionVO employeeFunctionVO);
     //�ק�
	 public void update(EmployeeFunctionVO employeeFunctionVO);
     //�ھڥ\��s���j�M
	 public EmployeeFunctionVO findByPrimaryKey(Integer func_id );
     //�j�M����
	 public List<EmployeeFunctionVO> getAll();
}
