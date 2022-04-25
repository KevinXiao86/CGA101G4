package com.taiwan.service.custPlatMail;

import java.util.List;

import com.taiwan.beans.CustPlatMailVO;

public interface CustPlatMailService {
	public List<CustPlatMailVO> getCust_Plat_Mail(Integer rowNum, Integer offset);

	public CustPlatMailVO setCust_Plat_Mail(Integer custId, Integer empId, String msg);

	List<CustPlatMailVO> getAll();
}
