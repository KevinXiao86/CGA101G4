package com.taiwan.test.tktItem;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.TktItem;
import com.taiwan.service.tktItem.TktItemMyService;

public class tktItemTest {
	ApplicationContext ap=new ClassPathXmlApplicationContext("applicationContext.xml");
	TktItemMyService tktItemMyService=ap.getBean(TktItemMyService.class);
	
	@Test
	public void test01() {
		List<TktItem> ls=tktItemMyService.selectById(13);
		System.out.println(ls);
	}

}
