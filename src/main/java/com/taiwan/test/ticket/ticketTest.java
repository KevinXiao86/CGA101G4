package com.taiwan.test.ticket;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.TicketVO;
import com.taiwan.service.TicketService;

import mybatis.mapper.TicketMapper;

public class ticketTest {
	ApplicationContext ap=new ClassPathXmlApplicationContext("applicationContext.xml");
	TicketService ticketService=ap.getBean(TicketService.class);
	TicketMapper mapper=ap.getBean(TicketMapper.class);
	
//	@Test
//	public void test01() {
//		List<TicketVO> ls=new ArrayList<TicketVO>();
//		ls=ticketService.findAll();
//		for(TicketVO ticketVO:ls) {
//			System.out.println(ticketVO);
//		}
//	}
	
//	@Test
//	public void test02() {
//		TicketVO ticketVO=ticketService.findById(3);
//		System.out.println(ticketVO);
//	}
//	@Test
//	public void test03() {
//		List<TicketVO> ticketVOs=ticketService.findByStatus("上架");
//		System.out.println(ticketVOs);
//	}
	
//	@Test
//	public void test04() {
//		TicketVO ticketVO=new TicketVO();
//		ticketVO.setTktId(58);
//		ticketVO.setOriginalAmount(1000);
//	}
	
//	
//	@Test
//	public void test05() {
//		System.out.println(ticketService.findById(4));
//	}
//	
	@Test
	public void test06() {
		System.out.println(mapper.queryImgByTicket(4));
	}
	
//	@Test
//	public void test07() {
//		System.out.println(mapper.queryById(4));
//	}
	
//	@Test
//	public void test08() {
//		System.out.println(mapper.queryAll());
//	}
//	@Test
//	public void test09() {
//		System.out.println(ticketService.findAll());
//	}
	
//	@Test 
//	public void test10() {
//		System.out.println(mapper.getTicketByScore());
//	}
	
//	@Test
//	public void test11() {
//		System.out.println(ticketService.findTicketByScore());
//	}
	@Test
	public void test12() {
		System.out.println(mapper.queryOInfoById(4));
	}
}
