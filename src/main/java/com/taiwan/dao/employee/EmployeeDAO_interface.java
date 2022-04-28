package com.taiwan.dao.employee;

import java.sql.Date;
import java.util.List;

import com.taiwan.beans.EmployeeVO;

public interface EmployeeDAO_interface {
	//新增
	 public void insert(EmployeeVO employeeVO);
    //修改
	 public void update(EmployeeVO employeeVO);
    //用編號搜尋
	 public EmployeeVO findByPrimaryKey(Integer empId );
	 
//	 //用姓名搜尋
//	 public EmployeeVO findByName(String empName );
//	 //用日期搜尋
//	 public EmployeeVO findByTimeName(Date HIREDATE );
    //搜尋全部-用編號排序
	 public List<EmployeeVO> getAll();
	 //刪除
	public void delete(Integer empId);
}


