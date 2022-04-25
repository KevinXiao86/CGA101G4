package com.taiwan.test.roomtype;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.Roomtype;

import mybatis.mapper.RoomtypeMapper;

public class RoomtypeMapperTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	RoomtypeMapper roomtypeMapper = context.getBean(RoomtypeMapper.class);
	
	
	@Test
	public void test01() {
		List<Roomtype> list = roomtypeMapper.queryRoomtypesByCmpId(20011);
		for(Roomtype roomtype : list) {
			System.out.println(roomtype);
		}
	}
}
