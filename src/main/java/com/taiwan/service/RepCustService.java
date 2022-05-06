package com.taiwan.service;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.RepCustVO;

public interface RepCustService {
	public RepCustVO addRepCust(Integer custId, Integer cmpId, String reason);
	
	public RepCustVO searchRepCustById(Integer repCustId);
	
	public List<RepCustVO> searchRepCustByCustId(Integer custId);
	
	public List<RepCustVO> searchRepCustByEmpId(Integer empId);

	public List<RepCustVO> searchRepCustByCmpId(Integer cmpId);
	
	public List<RepCustVO> searchRepCustByDate(Timestamp startDate,Timestamp endDate);
	
	public List<RepCustVO> searchRepCustByStatus(String status);

	public List<RepCustVO> searchAllRepCust();
	
	public RepCustVO doRepCust(Integer repCustId,Integer empId ,String status ,String result);

	public void cancelRepCust(Integer repCust);

	



}
