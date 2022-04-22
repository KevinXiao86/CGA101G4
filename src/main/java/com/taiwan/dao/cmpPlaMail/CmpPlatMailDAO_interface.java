package com.taiwan.dao.cmpPlaMail;

import java.util.List;

import com.taiwan.beans.CmpPlatMailVO;


public interface CmpPlatMailDAO_interface {
	public List<CmpPlatMailVO> queryCmpPlatMailByEmp_idAndCmp_id(Integer emp_id, Integer cmp_id);

	public void insert(CmpPlatMailVO cmpPlatMailVO);

}
