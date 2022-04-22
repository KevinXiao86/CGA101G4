package com.taiwan.dao.customer;

import com.taiwan.beans.CustomerVO;

public interface CustomerDAO_interface {
	public void setAll(CustomerVO customer);

	public void setUpdate(CustomerVO customer);

	public CustomerVO getAll(Integer custId);

	public String getPassword(String account);

	public String getEmail(String account);

	public void setCustRight(Integer custId, String custRight);

	public Integer getLogin(String account, String password);
}
