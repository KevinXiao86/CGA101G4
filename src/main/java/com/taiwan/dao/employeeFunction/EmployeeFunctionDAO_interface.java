package com.taiwan.dao.employeeFunction;



import java.util.*;
import com.taiwan.beans.AuthorityVO;
import com.taiwan.beans.EmployeeFunctionVO;
import com.taiwan.beans.EmployeeVO;

public interface EmployeeFunctionDAO_interface{
	//新增
	 public void insert(EmployeeFunctionVO employeeFunctionVO);
     //修改
	 public void update(EmployeeFunctionVO employeeFunctionVO);
     //依權限編號搜尋
	 public EmployeeFunctionVO findByPrimaryKey(Integer funcId );
     //搜尋全部
	 public List<EmployeeFunctionVO> getAll();
	 //刪除
	 public void delete(Integer funcId);
	 // //查詢某權限的員工(一對多)(回傳 Set)
	public Set<EmployeeVO> getEmpsByfuncId(Integer funcId);
	 }
