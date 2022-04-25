package com.taiwan.dao.repCmp;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.RepCmpVO;

public interface RepCmpDao_interface {
	public List<RepCmpVO> getRepCmpByCustId(Integer custId);

	public void setRepCmp(RepCmpVO repCmp);

	public void deleteRepCmp(Integer repCmpId);

	public void setRepCmpResult(Integer repCmpId, Integer empId, String status, String result);
	public RepCmpVO queryRepCmpByRepCmpId(Integer repCmpId);
	public List<RepCmpVO> queryRepCmpByRoomId(Integer roomId);
	public List<RepCmpVO> queryRepCmpByEmpId(Integer empId);
	public List<RepCmpVO> queryRepCmpByRepCmpDate(Timestamp startDate, Timestamp endDate);
	public List<RepCmpVO> queryRepCmpByStatus(String status);
	public List<RepCmpVO> queryRepCmpAll();
	//Integer repCmpId,Integer empId ,String status ,String result
	public RepCmpVO updateRepCmp(RepCmpVO repCmpVO);
	public RepCmpVO insertRepCmp(RepCmpVO repCmpVO);

	
}
