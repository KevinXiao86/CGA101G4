package com.taiwan.controller.reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taiwan.beans.Company;
import com.taiwan.beans.Reservation;
import com.taiwan.beans.Roomtype;
import com.taiwan.service.reservation.ReservationService;
import com.taiwan.service.roomtype.RoomtypeService;
import com.taiwan.utils.CommonUtils;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private RoomtypeService roomtypeService;

	@Autowired
	private ReservationService reservationService;

	@GetMapping("/getAllRoomtypes")
	public String getAllRoomtypes(HttpSession session, Model model) {
		Company company = (Company) session.getAttribute("loginCompany");
		List<Roomtype> roomtypes = roomtypeService.getAllRoomtypes(company.getCmpId());
		model.addAttribute("roomtypes", roomtypes);
		return "/front-end/reservation/list.jsp";
	}

	
	@GetMapping("/getReservation")
	public String getReservation(HttpServletRequest request, Model model, HttpSession session,
			@RequestParam("roomtypeId")String roomtypeIdStr, @RequestParam("roomtypeAmount")String roomtypeAmountStr) throws ParseException {
		Integer roomtypeId = CommonUtils.parseInt(roomtypeIdStr, 0);
		Integer rootypeAmount = CommonUtils.parseInt(roomtypeAmountStr, 0);
		
		List<Reservation> reservations = reservationService.getReservationsByRoomtypeId(roomtypeId, rootypeAmount);
		model.addAttribute("reservations", reservations);
		
		Company company = (Company) session.getAttribute("loginCompany");
		Roomtype roomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(company.getCmpId()+"", roomtypeIdStr);
		model.addAttribute("roomtype", roomtype);
		return "/front-end/reservation/reservation.jsp";
	}

	@GetMapping("/getReservationByDate")
	public String getReservationByDate(HttpServletRequest request, Model model, HttpSession session,
			@RequestParam("roomtypeId")String roomtypeIdStr, @RequestParam("roomtypeAmount")String roomtypeAmountStr,
			@RequestParam("start_date")String startDate, @RequestParam("end_date")String endDate) throws ParseException {

		//1. 數據校驗
		if (startDate.length() == 0 || endDate.length() == 0) {
			model.addAttribute("errorMsg", "請選擇日期!!");
			return "/reservation/getReservation";
		}
		// 創建解析器
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 設置時區
		dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		Date start = dateFormat.parse(startDate);
		Date end = dateFormat.parse(endDate);
		if (end.before(start)) {
			model.addAttribute("errorMsg", "請選擇正確的日期!!");
			return "/reservation/getReservation";
		}
		
		
		
		Integer roomtypeId = CommonUtils.parseInt(roomtypeIdStr, 0);
		System.out.println("roomtypeId: " + roomtypeId);
		Integer rootypeAmount = CommonUtils.parseInt(roomtypeAmountStr, 0);
		System.out.println("roomtypeAmount: " + rootypeAmount);

		List<Reservation> reservations = reservationService.getReservationsByDate(roomtypeId, rootypeAmount, startDate, endDate);
		model.addAttribute("reservations", reservations);

		Company company = (Company) session.getAttribute("loginCompany");
		Roomtype roomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(company.getCmpId()+"", roomtypeIdStr);
		model.addAttribute("roomtype", roomtype);
		return "/front-end/reservation/reservation.jsp";
	}
}
