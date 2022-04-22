package com.taiwan.dao.authority;

import java.util.List;

import com.taiwan.beans.AuthorityVO;


public interface AuthorityDAO_interface {
	// �s�W
	public void insert(AuthorityVO authorityVO);

	// �ק�
	public void update(AuthorityVO authorityVO, Integer newfunc_id);

	// �R���@��
	public void delete(Integer func_id, Integer emp_id);

	// �ھڥ\��j�M
	public List<AuthorityVO> findByFunction(Integer func_id);

	// �ھکm�W�j�M
	public List<AuthorityVO> findById(Integer emp_id);

	// �j�M����(�̥\��Ƨ�
	public List<AuthorityVO> AllFunctuon();

	// �j�M����(�̥\��Ƨ�
	public List<AuthorityVO> AllID();

}
