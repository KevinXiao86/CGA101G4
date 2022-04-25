package com.taiwan.service.customer.impl;

import java.sql.Date;

import com.taiwan.beans.CustomerVO;
import com.taiwan.dao.customer.CustomerDAO_interface;
import com.taiwan.dao.customer.impl.CustomerJNDIDAO;
import com.taiwan.service.customer.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDAO_interface dao;

	public CustomerServiceImpl() {
		dao = new CustomerJNDIDAO();
	}

	@Override
	public CustomerVO setAll(String name, String sex, String tel, String email, String address, String idCard,
			Date birth, String account, String password, String custUse, String custRight) {
		CustomerVO customerVO = new CustomerVO();
		customerVO.setName(name);
		customerVO.setSex(sex);
		customerVO.setTel(tel);
		customerVO.setEmail(email);
		customerVO.setAddress(address);
		customerVO.setIdCard(idCard);
		customerVO.setBirth(birth);
		customerVO.setAccount(account);
		customerVO.setPassword(password);
		customerVO.setCustUse(custUse);
		customerVO.setCustRight(custRight);

		dao.setAll(customerVO);
		return customerVO;
	}

	@Override
	public CustomerVO setUpdate(String name, String sex, String tel, String email, String address, String idCard,
			Date birth, String account, String password, String img, String card, Integer custId) {
		CustomerVO customerVO = new CustomerVO();
		customerVO.setName(name);
		customerVO.setSex(sex);
		customerVO.setTel(tel);
		customerVO.setEmail(email);
		customerVO.setAddress(address);
		customerVO.setIdCard(idCard);
		customerVO.setBirth(birth);
		customerVO.setAccount(account);
		customerVO.setPassword(password);
		customerVO.setImg(img);
		customerVO.setCard(card);
		customerVO.setCustId(custId);
		dao.setUpdate(customerVO);
		return customerVO;
	}

	@Override
	public CustomerVO getAll(Integer custId) {
		return dao.getAll(custId);
	}

	@Override
	public String getPassword(String account) {
		return dao.getPassword(account);
	}

	@Override
	public String getEmail(String account) {
		return dao.getEmail(account);
	}

	@Override
	public void setCustRight(Integer custId, String custRight) {
		dao.setCustRight(custId, custRight);
	}

	@Override
	public CustomerVO getLogin(String account, String password) {

		CustomerVO customerVO = dao.getLogin(account, password);

		if (customerVO == null) {
			customerVO = new CustomerVO();
			customerVO.setMessage("帳號密碼錯誤");
			customerVO.setAccount(account);
			customerVO.setUrl("/cust/CustomerLogin.jsp");

			return customerVO;
		} else if ("停權".equals(customerVO.getCustRight())) {
			customerVO.setMessage("您已被停權");
			customerVO.setSuccessful(false);
			customerVO.setUrl("/cust/CustomerLogin.jsp");
			customerVO.setAccount(null);
			customerVO.setPassword(null);

			return customerVO;
		} else if ("未啟動".equals(customerVO.getCustUse())) {
			customerVO.setMessage("您的帳號尚未啟動，請確認您的電子信箱，並按下台玩所發送信件中的按鈕，以啟動帳號");
			customerVO.setSuccessful(false);
			customerVO.setUrl("/cust/CustomerLogin.jsp");

			return customerVO;
		}
		return customerVO;
	}

}
