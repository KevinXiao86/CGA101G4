package com.taiwan.test.roomtype;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.RoomImg;
import com.taiwan.beans.Roomtype;
import com.taiwan.utils.CommonUtils;

import mybatis.mapper.RoomImgMapper;
import mybatis.mapper.RoomtypeMapper;
import oracle.net.aso.l;

public class RoomtypeMapperTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	RoomtypeMapper roomtypeMapper = context.getBean(RoomtypeMapper.class);
	RoomImgMapper roomImgMapper = context.getBean(RoomImgMapper.class);

	@Test
	public void test01() {
		List<Roomtype> roomtypes = roomtypeMapper.queryAllRoomtypes(20011);
		for(Roomtype roomtype : roomtypes) {
			System.out.println(roomtype);
		}
	}
	
	
	@Test
	public void test02() {
		List<RoomImg> imgs = roomImgMapper.queryRoomImgsByRoomtypeId(1);
		for(RoomImg roomImg : imgs) {
			System.out.println(roomImg);
		}
	}
	
	
	@Test
	public void test03() {
		Roomtype roomtype = roomtypeMapper.getRoomtypeByCmpIdAndRoomtypeId(20011, 1);
		System.out.println(roomtype.getRoomtypeName());
		for(RoomImg roomImg : roomtype.getRoomImgs()) {
			System.out.println(roomImg);
		}
	}
	
	
	@Test
	public void test04() {
		int roomtypeId = roomtypeMapper.queryRoomtypeId();
		System.out.println(roomtypeId);
	}
	
	
	@Test
	public void test05() {
		Integer cmpId = 20011;
		String roomtypeName = "單人房";
		Integer roomtypeAmount = 1;
		Integer roomtypePeople = 1;
		Integer totalScore = 8;
		Integer totalPeople = 90;
		Integer roomtypePrice = 1500;
		String roomtypeStatus = "上架";
		Integer roomtypeArea = 16;
		String roomtypeIntroduce = "測試用單人房";

		Roomtype roomtype = new Roomtype(null, cmpId, roomtypeName, roomtypeAmount, roomtypePeople, totalScore,
				totalPeople, roomtypePrice, roomtypeStatus, roomtypeArea, roomtypeIntroduce);
		
		int roomtypeId = roomtypeMapper.queryRoomtypeId();
		System.out.println(roomtypeId);
		System.out.println("--------------------------------");
//		插入房型測試成功
//		int res = roomtypeMapper.insertRoomtype(roomtype);
//		System.out.println(res);
		
		List<RoomImg> imgs = new ArrayList<RoomImg>();
		RoomImg img1 = new RoomImg(null, roomtypeId, "images/Company/caesar/5/43a6eb23-b5e2-4b0b-ae1c-f6ca4689f391.jpg");
		RoomImg img2 = new RoomImg(null, roomtypeId, "images/Company/caesar/5/556fdb03-b45b-4f8b-8ae2-28505e08672a.jpg");
		RoomImg img3 = new RoomImg(null, roomtypeId, "images/Company/caesar/5/611a599f-67b0-4fcd-afe3-b81d01d68a48.jpg");
		
		imgs.add(img1);
		imgs.add(img2);
		imgs.add(img3);
		
		roomtype.setRoomImgs(imgs);
		int res = roomImgMapper.insertRoomImg(roomtype.getRoomImgs());
		System.out.println(res);
	}


	@Test
	public void test06() {
		Roomtype roomtype = roomtypeMapper.getRoomtypeByCmpIdAndRoomtypeId(20011, 5);
		System.out.println(roomtype);
		System.out.println("--------------------------------");
		for(RoomImg img : roomtype.getRoomImgs()) {
			System.out.println(img);
		}
	}
}
