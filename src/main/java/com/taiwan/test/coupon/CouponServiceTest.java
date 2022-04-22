package com.taiwan.test.coupon;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.CouponVO;
import com.taiwan.service.CouponService;

public class CouponServiceTest {
	
	ApplicationContext ap=new ClassPathXmlApplicationContext("applicationContext.xml");
	CouponService couponService=ap.getBean(CouponService.class);
	
	
//	@Test
//	public void test01() {
//		List<CouponVO> ls=new ArrayList<CouponVO>();
//		ls=couponService.findAll();
//		System.out.println(ls);
//	}
	@Test
	public void test02() {
		String startString="2022-04-22 00:00:00";
		String endString ="2022-04-23 00:00:00";
		Timestamp startdate=Timestamp.valueOf(startString);
		Timestamp enddate=Timestamp.valueOf(endString);
		System.out.println(couponService.addCoupon("最新大放送，搶到賺到，不搶可惜", "網站新開幕 做測試 快來拿優惠券", 200, startdate, enddate, "asdfghjkl;"));
	}
}
