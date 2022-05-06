package com.taiwan.test.roomorder;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.RoomOrder;

import mybatis.mapper.RoomOrderMapper;

public class RoomOrderMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	RoomOrderMapper roomOrderMapper = context.getBean(RoomOrderMapper.class);

	// 查詢所有訂單

//	@Test
//	public void test() {
//		
//		List<RoomOrder> roomOrders = roomOrderMapper.queryAllRoomOrders();
//		for(RoomOrder roomOrder : roomOrders) {
//			System.out.println(roomOrder);
//		}
//	}
	

	@Test
	public void test01() {
		RoomOrder roomOrder=new RoomOrder();
		roomOrder.setCustomer(roomOrderMapper.selectById(10000)); 
		System.out.println(roomOrder.getCustomer());
	}
}

