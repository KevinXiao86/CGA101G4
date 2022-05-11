package com.taiwan.service.reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiwan.beans.Reservation;

import mybatis.mapper.ReservationMapper;

@Service
public class ReservationService {

	@Autowired
	private ReservationMapper reservationMapper;

	// 沒有選擇日期, 默認就是當天日期
	@Transactional
	public List<Reservation> getReservationsByRoomtypeId(Integer roomtypeId, Integer roomtypeAmount) throws ParseException{
		// 創建解析器
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 設置時區
		dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));

		// 先獲取所有的預約紀錄
		List<Reservation> temp = reservationMapper.getAllReservationsByNow(roomtypeId);

		// 要回傳的結果
		List<Reservation> reservations = new ArrayList<Reservation>();

		// 創建往後的 30 天預約紀錄
		for (int i = 0; i <= 30; i++) {
			Reservation reservation = new Reservation();
			reservation.setRoomtypeId(roomtypeId);//設置房型編號
			reservation.setRoomtypeAmount(roomtypeAmount);// 設置房型數量
			reservation.setReserveAmount(0);//設置預約數量
			
			//
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.DATE, i);
			String calenderString = dateFormat.format(calendar.getTime());
			reservation.setDateString(calenderString);//設置字符串日期
			Date date = dateFormat.parse(calenderString);
			reservation.setReserveDate(date);//設置 Date 日期
			
			reservations.add(reservation);
		}
		
		if (temp == null) {
			//說明數據庫沒有預約紀錄, 直接回傳創建好的
			return reservations;
		}
		
		//將已有的預約數量填入
		String tempString = "";
		for(int i = 0; i < reservations.size(); i++) {
			tempString = reservations.get(i).getDateString();
			for(int j = 0; j < temp.size(); j++) {
				String dateString = dateFormat.format(temp.get(j).getReserveDate());
				if (tempString.equals(dateString)) {
					reservations.get(i).setReserveAmount(temp.get(j).getReserveAmount());
				}
			}
		}

		return reservations;
	}
	
	
	// 沒有選擇日期, 默認就是當天日期
	@Transactional
	public List<Reservation> getReservationsByDate(Integer roomtypeId, Integer roomtypeAmount,
			String startDate, String endDate) throws ParseException{
		// 創建解析器
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 設置時區
		dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		
		Date tempDate = dateFormat.parse(startDate);
		Date tempDate2 = dateFormat.parse(endDate);
		
		long diff = tempDate2.getTime() - tempDate.getTime();
		long days = diff / 24 / 60 / 60 / 1000;
		
		// 先獲取所有的預約紀錄
		List<Reservation> temp = reservationMapper.getAllReservationsByDate(startDate, days+"", roomtypeId);

		// 要回傳的結果
		List<Reservation> reservations = new ArrayList<Reservation>();

		// 創建往後的 30 天預約紀錄
		for (int i = 0; i <= days; i++) {
			Reservation reservation = new Reservation();
			reservation.setRoomtypeId(roomtypeId);//設置房型編號
			reservation.setRoomtypeAmount(roomtypeAmount);// 設置房型數量
			reservation.setReserveAmount(0);//設置預約數量
			
			//
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(tempDate);
			calendar.add(Calendar.DATE, i);
			String calenderString = dateFormat.format(calendar.getTime());
			reservation.setDateString(calenderString);//設置字符串日期
			Date date = dateFormat.parse(calenderString);
			reservation.setReserveDate(date);//設置 Date 日期
			
			reservations.add(reservation);
		}
		
		if (temp == null) {
			//說明數據庫沒有預約紀錄, 直接回傳創建好的
			return reservations;
		}
		
		//將已有的預約數量填入
		String tempString = "";
		for(int i = 0; i < reservations.size(); i++) {
			tempString = reservations.get(i).getDateString();
			for(int j = 0; j < temp.size(); j++) {
				String dateString = dateFormat.format(temp.get(j).getReserveDate());
				if (tempString.equals(dateString)) {
					reservations.get(i).setReserveAmount(temp.get(j).getReserveAmount());
				}
			}
		}

		return reservations;
	}
}
