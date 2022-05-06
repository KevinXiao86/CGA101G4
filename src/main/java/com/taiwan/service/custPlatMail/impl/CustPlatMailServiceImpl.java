package com.taiwan.service.custPlatMail.impl;

import java.util.List;

import com.taiwan.beans.CustPlatMailVO;
import com.taiwan.dao.custPlatMail.CustPlatMailDao_interface;
import com.taiwan.dao.custPlatMail.impl.CustPlatMailJNDIDAO;
import com.taiwan.service.custPlatMail.CustPlatMailService;

public class CustPlatMailServiceImpl implements CustPlatMailService {
	private CustPlatMailDao_interface dao;

	public CustPlatMailServiceImpl() {
		dao = new CustPlatMailJNDIDAO();
	}

	@Override
	public List<CustPlatMailVO> getCust_Plat_Mail(Integer rowNum, Integer offset) {
		return dao.getCust_Plat_Mail(rowNum, offset);
	}

	@Override
	public CustPlatMailVO setCust_Plat_Mail(Integer custId, Integer empId, String msg, Integer who) {
		CustPlatMailVO custPlatMailVO = new CustPlatMailVO();
		custPlatMailVO.setCustId(custId);
		custPlatMailVO.setEmpId(empId);
		custPlatMailVO.setMsg(msg);
		custPlatMailVO.setWho(who);
		dao.setCust_Plat_Mail(custPlatMailVO);
		return custPlatMailVO;
	}

	@Override
	public List<CustPlatMailVO> getAll() {
		return dao.getAll();
	}

	@Override
	public List<CustPlatMailVO> getAllByCustId(Integer custId) {
		return dao.getAllByCustId(custId);
	}

}
