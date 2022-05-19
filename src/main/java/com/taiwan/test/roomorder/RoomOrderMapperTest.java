package com.taiwan.test.roomorder;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.RoomOrder;
import com.taiwan.service.roomOrder.RoomOrderMyService;
import com.taiwan.service.roomOrder.RoomOrderServicePlus;

import mybatis.mapper.RoomOrderMapper;

public class RoomOrderMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	RoomOrderMapper roomOrderMapper = context.getBean(RoomOrderMapper.class);

	@Test
	public void test01() {
		RoomOrder roomOrder = new RoomOrder();
		roomOrder.setCustomer(roomOrderMapper.selectById(10000));
		System.out.println(roomOrder.getCustomer());
	}

	@Test
	public void test02() {
		// 創建解析器
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 設置時區
		dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		
		Date date = new Date();
		String dateString = dateFormat.format(date);
		String year = dateString.substring(0, 4);
		System.out.println("year: " + year);
		String month = dateString.substring(5, 7);
		System.out.println("month: " + month);
		
		Integer price = roomOrderMapper.getTotalPrice(20011, year, month);
		System.out.println("總金額為: " + price);
	}
	
	
	@Test
	public void test03() {
		int res = roomOrderMapper.updateStatus(31, "正常");
		System.out.println("res: " + res);
	}
	
	
	@Test
	public void test04() {
		RoomOrderServicePlus roomOrderServicePlus = context.getBean(RoomOrderServicePlus.class);
		boolean res = roomOrderServicePlus.updateRoomOrderStatus("31", "正常");
		System.out.println(res);
	}
	
	
	
}
