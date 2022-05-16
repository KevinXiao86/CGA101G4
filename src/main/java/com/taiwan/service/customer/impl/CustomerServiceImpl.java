package com.taiwan.service.customer.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiwan.beans.Customer;
import com.taiwan.beans.CustomerVO;
import com.taiwan.dao.customer.CustomerDAO_interface;
import com.taiwan.dao.customer.impl.CustomerJNDIDAO;
import com.taiwan.service.customer.CustomerService;

import mybatis.mapper.CustomerMapper;
@Service
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
			customerVO.setUrl("/front-end/custLogin/CustomerLogin.jsp");

			return customerVO;
		} else if ("停權".equals(customerVO.getCustRight())) {
			customerVO.setMessage("您已被停權");
			customerVO.setSuccessful(false);
			customerVO.setUrl("/front-end/custLogin/CustomerLogin.jsp");
			customerVO.setAccount(null);
			customerVO.setPassword(null);

			return customerVO;
		}

		customerVO.setSuccessful(true);
		return customerVO;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Autowired
	CustomerMapper customerMapper;
	
	@Transactional
	@Override
	public List<Customer> findAll() {
		return customerMapper.queryAll();
	}
	


}
