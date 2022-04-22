package com.taiwan.dao.custPlatMail;


import java.util.List;

import com.taiwan.beans.CustPlatMailVO;

public interface CustPlatMailDao_interface {
	public List<CustPlatMailVO> getCust_Plat_Mail(Integer rowNum, Integer offset);

	public void setCust_Plat_Mail(CustPlatMailVO mail);
}
