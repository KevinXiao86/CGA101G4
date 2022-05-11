package com.taiwan.test.reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.Reservation;
import com.taiwan.beans.RoomOrder;
import com.taiwan.utils.CommonUtils;

import mybatis.mapper.ReservationMapper;

public class ReservationMapperTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	ReservationMapper reservationMapper = context.getBean(ReservationMapper.class);


	@Test
	public void test02() throws ParseException {
		// 創建解析器
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 設置時區
		dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		
		List<Reservation> list = reservationMapper.getAllReservationsByNow(1);
		for(Reservation reservation : list) {
			System.out.println(reservation);
		}
	}

	@Test
	public void test03() throws ParseException {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = dateFormat.format(date);
		System.out.println(dateStr);
		System.out.println(dateStr.substring(0, 4));
		System.out.println(dateStr.substring(5, 7));
		System.out.println("-----------------------------------------------");
		System.out.println(judgeDate(dateStr.substring(0, 4), dateStr.substring(5, 7)));
	}
	
	
	@Test
	public void test04() {
		List<Reservation> reservations = reservationMapper.getAllReservationsByDate("2022-05-08", "2", 1);
		for(Reservation reservation : reservations) {
			System.out.println(reservation);
		}
	}
	
	
	
	//判斷日期
	public static int judgeDate(String yearStr, String month) {
		int totalDay = 0;
		switch (month) {
		case "12":
			totalDay = 31;
			break;

		case "11":
			totalDay = 30;
			break;

		case "10":
			totalDay = 31;
			break;

		case "09":
			totalDay = 30;
			break;

		case "08":
			totalDay = 31;
			break;

		case "07":
			totalDay = 31;
			break;

		case "06":
			totalDay = 30;
			break;

		case "05":
			totalDay = 31;
			break;

		case "04":
			totalDay = 30;
			break;

		case "03":
			totalDay = 31;
			break;

		case "02":
			int year = CommonUtils.parseInt(yearStr, 0);
			// 判斷是否為閏年
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				totalDay = 29;
			} else {
				totalDay = 28;
			}
			break;

		case "01":
			totalDay = 31;
			break;

		default:
			System.out.println("輸入的數值有誤, 月份應在 1 ~ 12");
			break;
		}
		
		return totalDay;
	}
}
