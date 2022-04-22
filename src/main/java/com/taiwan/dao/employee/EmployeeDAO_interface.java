package com.taiwan.dao.employee;

import java.sql.Date;
import java.util.List;

import com.taiwan.beans.EmployeeVO;

public interface EmployeeDAO_interface {
		//�s�W
		 public void insert(EmployeeVO employeeVO);
	     //�ק�
		 public void update(EmployeeVO employeeVO);
	     //�ھڽs���j�M
		 public EmployeeVO findByPrimaryKey(Integer emp_id );
//		 //���u�m�W�j�M
//		 public List<EmployeeVO> findByName(String emp_name );
//		 //���u�ɶ��j�M
//		 public EmployeeVO findByTimeName(Date HIREDATE );
	     //�j�M����
		 public List<EmployeeVO> getAll();
	}


