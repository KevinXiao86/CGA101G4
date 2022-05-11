package com.taiwan.test.customer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.Customer;
import com.taiwan.beans.CustomerVO;
import com.taiwan.service.customer.CustomerService;

import mybatis.mapper.CustomerMapper;

public class CustomerTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	CustomerMapper customerMapper = context.getBean(CustomerMapper.class);
	CustomerService customerService=context.getBean(CustomerService.class);
	
	
//	@Test
//	public void test01() throws ParseException {
//		Customer customer = new Customer();
//		customer.setName("黃先生");
//		customer.setSex("m");
//		customer.setTel("03258333");
//		customer.setEmail("testtsee@gmail.com");
//		customer.setAddress("桃園市中壢區中正路一段");
//		customer.setIdCard("822124");
//		
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		format.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
//		Date date = format.parse("1997-02-10");
//		customer.setBirth(date);
//		
//		customer.setAccount("testkevin11");
//		customer.setPassword("testPassword");
//		customer.setImg("images/Customer/testKevin");
//		customer.setCard("kaka");
//		int res = customerMapper.insertCustomer(customer);
//		System.out.println(res);
//	}
	
	@Test
	public void test01() throws ParseException {
		Customer customer = new Customer();
		customer.setName("黃先生");
		customer.setSex("m");
		customer.setTel("0987654321");
		customer.setEmail("testtsee@gmail.com");
		customer.setAddress("桃園市中壢區中正路一段");
		customer.setIdCard("T123456789");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		Date date = format.parse("1997-02-10");
		customer.setBirth(date);
		
		customer.setAccount("testyu");
		customer.setPassword("testPassword");
		customer.setImg("images/Customer/testKevin");
		customer.setCard("kaka");
		int res = customerMapper.insertCustomer(customer);
		System.out.println(res);
	}

	public void test02() {
		System.out.println(customerService.findAll());

	}
}
