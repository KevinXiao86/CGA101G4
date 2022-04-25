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
	
	
	@Test
	public void test02() {
		int res = roomtypeMapper.updateRoomtypeStatusByCmpIdAndRoomtypeId(20011, 1, "上架");
		System.out.println(res);
	}
	
	
	@Test
	public void test03() {
		System.out.println((!"下架".equals("123")));
	}
}
