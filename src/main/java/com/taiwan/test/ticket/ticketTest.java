package com.taiwan.test.ticket;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.TicketVO;
import com.taiwan.service.TicketService;

public class ticketTest {
	ApplicationContext ap=new ClassPathXmlApplicationContext("applicationContext.xml");
	TicketService ticketService=ap.getBean(TicketService.class);
	
	@Test
	public void test01() {
		List<TicketVO> ls=new ArrayList<TicketVO>();
		ls=ticketService.findAll();
		for(TicketVO ticketVO:ls) {
			System.out.println(ticketVO);
		}
	}

}
