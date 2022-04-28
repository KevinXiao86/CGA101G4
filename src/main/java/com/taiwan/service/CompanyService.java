package com.taiwan.service;

import com.taiwan.beans.Company;

public interface CompanyService {
	//獲取廠商
	public Company getCompany(String account, String password);
}
