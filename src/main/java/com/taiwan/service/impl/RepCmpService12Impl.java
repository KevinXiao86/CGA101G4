package com.taiwan.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.RepCmpVO;
import com.taiwan.dao.repCmp.RepCmpDao_interface;
import com.taiwan.dao.repCmp.impl.RepCmpDAO;
import com.taiwan.service.RepCmpService12;

public class RepCmpService12Impl implements RepCmpService12{
	RepCmpDao_interface dao=null;
	public RepCmpService12Impl() {
		dao= new RepCmpDAO();
	}

	@Override
	public RepCmpVO addRepCmp(Integer custId, Integer roomId, String reason) {
		RepCmpVO repCmpVO=new RepCmpVO();
		repCmpVO.setCustId(custId);
		repCmpVO.setRoomId(roomId);
		repCmpVO.setReason(reason);
		dao.insertRepCmp(repCmpVO);
		return repCmpVO;
	}

	@Override
	public RepCmpVO doRepCmp(Integer repCmpId, Integer empId, String status, String result) {
		RepCmpVO repCmpVO=new RepCmpVO();
		repCmpVO.setRepCmpId(repCmpId);
		repCmpVO.setEmpId(empId);
		repCmpVO.setStatus(status);
		repCmpVO.setResult(result);
		dao.updateRepCmp(repCmpVO);
		return repCmpVO;
	}

	@Override
	public List<RepCmpVO> getAllRepCmp() {
		List<RepCmpVO> list=dao.queryRepCmpAll();
		return list;
	}

	@Override
	public List<RepCmpVO> getRepCmpByStatus(String status) {
		List<RepCmpVO> list=dao.queryRepCmpByStatus(status);
		return list;
	}

	@Override
	public List<RepCmpVO> getRepCmpByDate(Timestamp startDate, Timestamp endDate) {
		List<RepCmpVO> list=dao.queryRepCmpByRepCmpDate(startDate,endDate);
		return list;
	}

	@Override
	public List<RepCmpVO> getRepCmpByRoomId(Integer roomId) {
		List<RepCmpVO> list=dao.queryRepCmpByRoomId(roomId);
		return list;
	}

	@Override
	public List<RepCmpVO> getRepCmpByCustId(Integer custId) {
		List<RepCmpVO> list=dao.getRepCmpByCustId(custId);
		return list;
	}

	@Override
	public List<RepCmpVO> getRepCmpByEmpId(Integer empId) {
		List<RepCmpVO> list=dao.queryRepCmpByEmpId(empId);
		return list;
	}

	@Override
	public RepCmpVO getRepCmpById(Integer repCmpId) {
		RepCmpVO repCmpVO=dao.queryRepCmpByRepCmpId(repCmpId);
		return repCmpVO;
	}

}
