package com.taiwan.service.customer;

import java.sql.Date;

import com.taiwan.beans.CustomerVO;

public interface CustomerService {
	public CustomerVO setAll(String name, String sex, String tel, String email, String address, String idCard,
			Date birth, String account, String password, String custUse, String custRight);

	public CustomerVO getAll(Integer custId);

	public String getPassword(String account);

	public String getEmail(String account);

	public void setCustRight(Integer custId, String custRight);

	public CustomerVO getLogin(String account, String password);

	public CustomerVO setUpdate(String name, String sex, String tel, String email, String address, String idCard,
			Date birth, String account, String password, String img, String card, Integer custId);
}
