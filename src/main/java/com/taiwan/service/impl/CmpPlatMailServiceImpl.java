package com.taiwan.service.impl;

import java.util.List;

import com.taiwan.beans.CmpPlatMailVO;
import com.taiwan.dao.cmpPlaMail.CmpPlatMailDAO_interface;
import com.taiwan.dao.cmpPlaMail.impl.CmpPlatMailDAO;
import com.taiwan.dao.cmpPlaMail.impl.CmpPlatMailService;


public class CmpPlatMailServiceImpl implements CmpPlatMailService {
	CmpPlatMailDAO_interface dao=null;
	public CmpPlatMailServiceImpl() {
		dao=new CmpPlatMailDAO();
	}

	@Override
	public void sendMsg(Integer empId, Integer cmpId, String msg, Integer who) {
		CmpPlatMailVO cmpPlatMailVO=new CmpPlatMailVO();
		cmpPlatMailVO.setEmpId(empId);
		cmpPlatMailVO.setCmpId(cmpId);
		cmpPlatMailVO.setCmpPlatMailMsg(msg);
//		String acount=查察訊人ID
//		cmpPlatMailVO.setCmpPlatMailWho(acount);
		dao.insert(cmpPlatMailVO);
}

	@Override
	public List<CmpPlatMailVO> getMsg(Integer empId, Integer cmpId) {
		
		return dao.queryCmpPlatMailByEmpIdAndCmpId(empId, cmpId);
	}
}