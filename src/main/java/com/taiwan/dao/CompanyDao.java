package com.taiwan.dao;

import com.taiwan.beans.Company;

public interface CompanyDao {

	// �ھ� cmp_account �M cmp_password �d�߼t��
	public Company queryCompanyByAccountAndPassword(String account, String password);
}
