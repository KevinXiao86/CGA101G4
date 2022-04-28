package com.taiwan.service.authority;

import java.util.List;
import java.util.Set;

import com.taiwan.beans.AuthorityVO;
import com.taiwan.beans.EmployeeVO;
import com.taiwan.dao.authority.AuthorityDAO_interface;
import com.taiwan.dao.authority.impl.AuthorityJDBCDAO;

public class AuthorityService {
	private AuthorityDAO_interface dao;

	public AuthorityService() {
		dao = new AuthorityJDBCDAO();
	}

	public List<AuthorityVO> getAll() {
		//依據ID排序
		return dao.AllID();
	}

	public List<AuthorityVO> findById(Integer empId) {
		return dao.findById(empId);
	}

	public Set<EmployeeVO> getEmployeeByAuthority(Integer empId) {
		return dao.getEmployeeByAuthority(empId);
	}

	public void deleteAuthority(Integer funcId, Integer empId) {
		dao.delete(empId,funcId);
	}
}
