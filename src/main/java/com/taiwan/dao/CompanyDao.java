package com.taiwan.dao;

import com.taiwan.beans.Company;

public interface CompanyDao {

	// 根據 cmp_account 和 cmp_password 查詢廠商
	public Company queryCompanyByAccountAndPassword(String account, String password);
}
