package com.taiwan.service.impl;

import com.taiwan.beans.Company;
import com.taiwan.dao.CompanyDao;
import com.taiwan.dao.impl.CompanyDaoImpl;
import com.taiwan.dao.impl.CompanyDaoJNDI;
import com.taiwan.service.CompanyService;

public class CompanyServiceImpl implements CompanyService{

//	private CompanyDao companyDao = new CompanyDaoImpl();
	private CompanyDao companyDao = new CompanyDaoJNDI();
	
	@Override
	public Company getCompany(String account, String password) {
		// TODO Auto-generated method stub
		Company company = companyDao.queryCompanyByAccountAndPassword(account, password);
		
		return company;
	}

}
