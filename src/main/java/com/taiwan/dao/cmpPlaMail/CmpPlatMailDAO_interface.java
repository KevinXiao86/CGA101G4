package com.taiwan.dao.cmpPlaMail;

import java.util.List;

import com.taiwan.beans.CmpPlatMailVO;


public interface CmpPlatMailDAO_interface {
	public List<CmpPlatMailVO> queryCmpPlatMailByEmpIdAndCmpId(Integer empId, Integer cmpId);

	public void insert(CmpPlatMailVO cmpPlatMailVO);

}
