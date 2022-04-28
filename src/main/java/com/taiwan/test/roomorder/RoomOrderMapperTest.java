package com.taiwan.test.roomorder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.CustomerVO;
import com.taiwan.beans.RoomOrder;

import mybatis.mapper.CustomerMapper;
import mybatis.mapper.RoomOrderMapper;

public class RoomOrderMapperTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	RoomOrderMapper roomOrderMapper = context.getBean(RoomOrderMapper.class);

	// 查詢所有訂單
	@Test
	public void test01() {
		List<RoomOrder> list = roomOrderMapper.queryAllRoomOrders();
		for (RoomOrder roomOrder : list) {
			System.out.println(roomOrder);
		}
	}

	//練習時間的創建
	@Test
	public void test02() throws ParseException {
		//方法返回當前時間(毫秒)。返回值的時間單位是1毫秒，該值的粒度取決於底層操作係統。(速度比較快)
		long t = System.currentTimeMillis();
		System.out.println(t);

		//創建解析器
		SimpleDateFormat sdf_default = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//設置時區
		sdf_default.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		// format: 格式化的意思; parse: 解析的意思
		System.out.println(sdf_default.format(t));
	}
	
	
	//練習時間的加減
	@Test
	public void test03() throws ParseException {
		//創建解析器
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
		
		//設置時區
		format.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		
		//創建時間
		long t = System.currentTimeMillis();
		String str = format.format(t);
		Date date = format.parse(str);
		System.out.println(date);
		
		Calendar calendar = Calendar.getInstance();
//		System.out.println(calendar.time);
		//對 calendar 設定為 date 所定的日期 
		calendar.setTime(date);
		calendar.add(0, 0);
	}
	
	//根據訂單成立日期查詢訂單
	@Test
	public void test04() throws ParseException {
		//創建解析器
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dateFormat.parse("2022-04-07 00:00:00");
		List<RoomOrder> list = roomOrderMapper.queryRoomOrdersByRoomOrderDate(date);
		for(RoomOrder roomOrder : list) {
			System.out.println(roomOrder);
		}
	}
	
	
	//根據訂單成立日期查詢訂單
	@Test
	public void test05() throws ParseException {
		//創建解析器
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dateFormat.parse("2022-04-07 00:00:00");
		List<RoomOrder> list = roomOrderMapper.queryRoomOrdersByRoomOrderDate(date);
		for(RoomOrder roomOrder : list) {
			System.out.println(roomOrder);
		}
	}
	
	
	//根據入住時間查詢訂單
	@Test
	public void test06() throws ParseException {
		//創建解析器
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dateFormat.parse("2022-04-19 00:00:00");
		List<RoomOrder> list = roomOrderMapper.queryRoomOrdersByCheckinDate(date);
		for(RoomOrder roomOrder : list) {
			System.out.println(roomOrder);
		}
	}
	
	
	//根據退房時間查詢訂單
	@Test
	public void test07() throws ParseException {
		//創建解析器
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dateFormat.parse("2022-04-20 00:00:00");
		List<RoomOrder> list = roomOrderMapper.queryRoomOrdersByCheckoutDate(date);
		for(RoomOrder roomOrder : list) {
			System.out.println(roomOrder);
		}
	}
	
	
	@Test
	public void test08() {
		CustomerVO customer = roomOrderMapper.getCustomerVOByCustId(10000);
		System.out.println(customer);
	}
	
	
	@Test
	public void test09() {
		RoomOrder roomOrder=roomOrderMapper.queryById(1);
		System.out.println(roomOrder);
	}
}
