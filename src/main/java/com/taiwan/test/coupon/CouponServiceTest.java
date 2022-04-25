package com.taiwan.test.coupon;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.CouponVO;
import com.taiwan.service.CouponService;
import com.taiwan.utils.MyBatisUtil;

import mybatis.mapper.CouponMapper;

public class CouponServiceTest {
	
	ApplicationContext ap=new ClassPathXmlApplicationContext("applicationContext.xml");
	CouponService couponService=ap.getBean(CouponService.class);
	
	
//	@Test
//	public void test01() {
//		List<CouponVO> ls=new ArrayList<CouponVO>();
//		ls=couponService.findAll();
//		System.out.println(ls);
//	}
//	@Test
//	public void test02() {
//		String startString="2022-04-30 00:00:00";
//		String endString ="2022-05-01 00:00:00";
//		Timestamp startdate=Timestamp.valueOf(startString);
//		Timestamp enddate=Timestamp.valueOf(endString);
//		System.out.println(couponService.addCoupon("最新大放送，搶到賺到，不搶可惜", "網站新開幕 做測試 快來拿優惠券", 200, startdate, enddate, "asdfghjkl;"));
//	}
	
//	@Test
//	public void test03() {
//		System.out.println(couponService.delete(3));
//	}
//	SqlSession session=MyBatisUtil.getSession();
//	CouponMapper mapper=session.getMapper(CouponMapper.class);
//	
//	@Test
//	public void test05() {
//		CouponVO couponVO=new CouponVO();
//		String startString="2022-04-30 00:00:00";
//		String endString ="2022-05-01 00:00:00";
//		Timestamp startdate=Timestamp.valueOf(startString);
//		Timestamp enddate=Timestamp.valueOf(endString);
//		couponVO.setCopId(5);
//		couponVO.setCopName("test");
//		couponVO.setIntroduce("test");
//		couponVO.setDiscount(120);
//		couponVO.setStartdate(startdate);
//		couponVO.setEnddate(enddate);
//		couponVO.setImg("images/coupon/d6d15dd1-fcd9-4bcf-93eb-d7fc9080f059t.gif");
//		
//		mapper.update(couponVO);
//		
//	}
	
	
	
//	@Test
//	public void test04() {
//		String startString="2022-04-30 00:00:00";
//		String endString ="2022-05-01 00:00:00";
//		Timestamp startdate=Timestamp.valueOf(startString);
//		Timestamp enddate=Timestamp.valueOf(endString);
//		System.out.println(couponService.update(5, "test", "test", 120, startdate, enddate, "images/coupon/d6d15dd1-fcd9-4bcf-93eb-d7fc9080f059t.gif"));
//	}
	
//	@Test
//	public void test06() {
//		System.out.println(couponService.updateStatus(1, "上架"));
//	}
	
	@Test
	public void test07() {
		System.out.println(couponService.selectByStatus("下架"));
	}
}
