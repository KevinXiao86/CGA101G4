package com.taiwan.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.RepCustVO;
import com.taiwan.dao.repCust.RepCustDAO_interface;
import com.taiwan.dao.repCust.impl.RepCustDAO;
import com.taiwan.service.RepCustService;

public class RepCustServiceImpl implements RepCustService {
	private RepCustDAO_interface dao;

	public RepCustServiceImpl() {
		dao = new RepCustDAO();
	}

	@Override
	public RepCustVO addRepCust(Integer custId, Integer cmpId, String reason) {
		RepCustVO repCustVO = new RepCustVO();
		repCustVO.setCustId(custId);
		repCustVO.setCmpId(cmpId);
		repCustVO.setRepCustReason(reason);
		dao.insertRepCust(repCustVO);
		return null;
	}

	@Override
	public RepCustVO searchRepCustById(Integer repCustId) {
		RepCustVO repCustVO = dao.queryRepCustByRepCustId(repCustId);

		return repCustVO;
	}

	@Override
	public List<RepCustVO> searchRepCustByCustId(Integer custId) {
		List<RepCustVO> list = dao.queryRepCustByCustId(custId);
		return list;
	}

	@Override
	public List<RepCustVO> searchRepCustByEmpId(Integer empId) {
		List<RepCustVO> list = dao.queryRepCustByEmpId(empId);
		return list;
	}

	@Override
	public List<RepCustVO> searchRepCustByCmpId(Integer cmpId) {
		List<RepCustVO> list = dao.queryRepCustByCmpId(cmpId);
		return list;
	}

	@Override
	public List<RepCustVO> searchRepCustByDate(Timestamp startDate, Timestamp endDate) {
		List<RepCustVO> list = dao.queryRepCustByRepCustDate(startDate,endDate);
		return list;
	}

	@Override
	public List<RepCustVO> searchRepCustByStatus(String status) {
		List<RepCustVO> list = dao.queryRepCustByStatus(status);
		return list;
	}

	@Override
	public List<RepCustVO> searchAllRepCust() {
		List<RepCustVO> list = dao.queryRepCustAll();
		return list;
	}

	@Override
	public RepCustVO doRepCust(Integer repCustId, Integer empId, String status, String result) {
		RepCustVO repCustVO=new RepCustVO();
		repCustVO.setRepCustId(repCustId);
		repCustVO.setEmpId(empId);
		repCustVO.setRepCustStatus(status);
		repCustVO.setRepCustResult(result);
		dao.updateRepCust(repCustVO);
		return null;
	}
	@Override
	public void cancelRepCust(Integer repCust) {
		dao.deleteRepCust(repCust);
	}
//	public static void main(String[] args) {
//		RepCustServiceImpl dao=new RepCustServiceImpl();
//		List<RepCustVO> list=dao.searchRepCustByCmpId(20000);
//		for (RepCustVO repCustVO : list) {
//			System.out.println(repCustVO.getRepCustId());
//		}
//	}

}
