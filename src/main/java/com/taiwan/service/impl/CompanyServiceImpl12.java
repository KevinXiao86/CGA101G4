package com.taiwan.service.impl;

import com.taiwan.beans.Company;
import com.taiwan.dao.company12.Company12_interface;
import com.taiwan.dao.company12.impl.CompanyDAO12;

public class CompanyServiceImpl12 implements Company12_interface{
	private Company12_interface dao = null;
	public CompanyServiceImpl12() {
		dao=new CompanyDAO12();
	}
	@Override
	public Company searchCmpByCmpId(Integer cmpId) {
		return dao.searchCmpByCmpId(cmpId);
	}
	
}
