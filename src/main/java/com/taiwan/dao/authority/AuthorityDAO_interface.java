package com.taiwan.dao.authority;

import java.util.List;

import com.taiwan.beans.AuthorityVO;


public interface AuthorityDAO_interface {
	// 新增
	public void insert(AuthorityVO authorityVO);

	// 修改
	public void update(AuthorityVO authorityVO, Integer newfuncId);

	// 刪除
	public void delete(Integer funcId, Integer empId);

	// 依權限搜尋
	public List<AuthorityVO> findByFunction(Integer funcId);

	// 依員工編號搜尋
	public List<AuthorityVO> findById(Integer empId);

	// 依權限排序
	public List<AuthorityVO> AllFunctuon();

	// 依ID排序
	public List<AuthorityVO> AllID();

}
