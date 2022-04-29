package com.taiwan.test.roomorder;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.RoomOrder;

import mybatis.mapper.RoomOrderMapper;

public class RoomOrderMapperTest {

	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		RoomOrderMapper roomOrderMapper = context.getBean(RoomOrderMapper.class);
		List<RoomOrder> roomOrders = roomOrderMapper.queryAllRoomOrders();
		for(RoomOrder roomOrder : roomOrders) {
			System.out.println(roomOrder);
		}
	}
}

