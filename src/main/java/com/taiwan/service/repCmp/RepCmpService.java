package com.taiwan.service.repCmp;

import java.util.List;

import com.taiwan.beans.RepCmpVO;

public interface RepCmpService {
	public List<RepCmpVO> getRepCmpByCustId(Integer custId);

	public RepCmpVO setRepCmp(Integer custId, Integer roomId, String reason);

	public void deleteRepCmp(Integer repCmpId);

	public void setRepCmpResult(Integer repCmpId, Integer empId, String status, String result);
}
