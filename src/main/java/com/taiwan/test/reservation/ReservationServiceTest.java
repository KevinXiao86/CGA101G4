package com.taiwan.test.reservation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.Reservation;
import com.taiwan.service.reservation.ReservationService;
import com.taiwan.utils.CommonUtils;

public class ReservationServiceTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	ReservationService reservationService = context.getBean(ReservationService.class);
	
	
	@Test
	public void test01() throws ParseException {
		List<Reservation> reservations = reservationService.getReservationsByRoomtypeId(1, 3);
		for(Reservation reservation : reservations) {
			System.out.println(reservation);
		}
	}
	
	
	@Test
	public void test02() {
//		for(int i = 0; i <= 42; i++) {
//			Calendar calendar = new GregorianCalendar();
//			
//			calendar.add(Calendar.DATE, i);
//			System.out.println(calendar.getTime());
//		}
	}
}
