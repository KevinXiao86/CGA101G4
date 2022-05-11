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
	
	@Test
	public void test04() {
		TicketVO ticketVO=new TicketVO();
		ticketVO.setTktId(58);
		ticketVO.setOriginalAmount(1000);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Test
//	public void test20() {
//		System.out.println(mapper.queryLocName("上架", "基隆市","test"));
//	}
	@Test
	public void test21() {
		System.out.println(ticketService.findByThree("上架", "基隆市","test"));
	}
}
