package com.taiwan.test.news;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.News;
import com.taiwan.service.coupon.CouponService;
import com.taiwan.service.news.NewsService;

public class newsTest {
	
	ApplicationContext ap=new ClassPathXmlApplicationContext("applicationContext.xml");
	NewsService newsService=ap.getBean(NewsService.class);
	
	@Test
	public void test01() {
		List<News> ls=new ArrayList<News>();
		ls=newsService.findAll();
		System.out.println(ls);
	}
	
}
