package com.taiwan.dao.cmpPlaMail.impl;

import java.util.List;

import com.taiwan.beans.CmpPlatMailVO;

public interface CmpPlatMailService {
	public void sendMsg(Integer empid,Integer cmpId,String msg,Integer who);
	
	public List<CmpPlatMailVO> getMsg(Integer empId,Integer cmpId);
}
