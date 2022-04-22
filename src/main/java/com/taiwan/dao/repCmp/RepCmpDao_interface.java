package com.taiwan.dao.repCmp;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.RepCmpVO;

public interface RepCmpDao_interface {
	public List<RepCmpVO> getRepCmpByCustId(Integer custId);

	public void setRepCmp(RepCmpVO repCmp);

	public void deleteRepCmp(Integer repCmpId);

	public void setRepCmpResult(Integer repCmpId, Integer empId, String status, String result);
	public RepCmpVO queryRepCmpByRep_cmp_id(Integer rep_cmp_id);
	public List<RepCmpVO> queryRepCmpByRoom_id(Integer room_id);
	public List<RepCmpVO> queryRepCmpByEmp_id(Integer emp_id);
	public List<RepCmpVO> queryRepCmpByRep_cmp_date(Timestamp startDate, Timestamp endDate);
	public List<RepCmpVO> queryRepCmpByStatus(String status);
	public List<RepCmpVO> queryRepCmpAll();
	public Integer updateRepCmp(Integer rep_cmp_id,Integer emp_id ,String status ,String result);

	
}
