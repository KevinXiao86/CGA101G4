package com.taiwan.dao.employee;

import java.sql.Date;
import java.util.List;

import com.taiwan.beans.EmployeeVO;

public interface EmployeeDAO_interface {
		//新增
		 public void insert(EmployeeVO employeeVO);
	     //修改
		 public void update(EmployeeVO employeeVO);
	     //依員工編號搜尋
		 public EmployeeVO findByPrimaryKey(Integer emp_id );
//		 //依員工姓名
//		 public List<EmployeeVO> findByName(String emp_name );
//		 //依員工入職日期
//		 public EmployeeVO findByTimeName(Date HIREDATE );
	     //全部搜尋，依員工編號排列
		 public List<EmployeeVO> getAll();
	}


