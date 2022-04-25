package com.taiwan.service.repCmp.impl;

import java.util.List;

import com.taiwan.beans.RepCmpVO;
import com.taiwan.dao.repCmp.RepCmpDao_interface;
import com.taiwan.dao.repCmp.impl.RepCmpJNDIDAO;
import com.taiwan.service.repCmp.RepCmpService;

public class RepCmpServiceImpl implements RepCmpService {
	private RepCmpDao_interface dao;

	public RepCmpServiceImpl() {
		dao = new RepCmpJNDIDAO();
	}

	@Override
	public List<RepCmpVO> getRepCmpByCustId(Integer custId) {
		return dao.getRepCmpByCustId(custId);
	}

	@Override
	public RepCmpVO setRepCmp(Integer custId, Integer roomId, String reason) {
		RepCmpVO repCmpVO = new RepCmpVO();
		repCmpVO.setCustId(custId);
		repCmpVO.setRoomId(roomId);
		repCmpVO.setReason(reason);
		dao.setRepCmp(repCmpVO);
		return repCmpVO;
	}

	@Override
	public void deleteRepCmp(Integer repCmpId) {
		dao.deleteRepCmp(repCmpId);
	}

	@Override
	public void setRepCmpResult(Integer repCmpId, Integer empId, String status, String result) {
		dao.setRepCmpResult(repCmpId, empId, status, result);
	}
}
