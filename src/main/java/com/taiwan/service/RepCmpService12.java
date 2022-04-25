package com.taiwan.service;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.RepCmpVO;

public interface RepCmpService12 {
	public RepCmpVO addRepCmp(Integer custId,Integer roomId,String reason);
	public RepCmpVO doRepCmp(Integer repCmpId,Integer empId,String status,String result);
	public List<RepCmpVO> getAllRepCmp();
	public List<RepCmpVO> getRepCmpByStatus(String status);
	public List<RepCmpVO> getRepCmpByDate(Timestamp startDate,Timestamp endDate);
	public List<RepCmpVO> getRepCmpByRoomId(Integer roomId);
	public List<RepCmpVO> getRepCmpByCustId(Integer custId);
	public List<RepCmpVO> getRepCmpByEmpId(Integer empId);
	public RepCmpVO getRepCmpById(Integer repCmpId);

}
