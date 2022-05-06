package com.taiwan.controller.reservation;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping("/getAllRoomtypes")
	public String getAllRoomtypes(HttpSession session, Model model) {
		Company company = (Company) session.getAttribute("loginCompany");
		List<Roomtype> roomtypes = roomtypeService.getAllRoomtypes(company.getCmpId());
		model.addAttribute("roomtypes", roomtypes);
		return "/front-end/reservation/list.jsp";
	}

	
	@RequestMapping("/getReservation")
	public String getReservation(HttpServletRequest request, Model model) throws ParseException{
		Integer roomtypeId = CommonUtils.parseInt(request.getParameter("roomtypeId"), 0);
		Integer rootypeAmount = CommonUtils.parseInt(request.getParameter("roomtypeAmount"), 0);
		List<Reservation> reservations = reservationService.getReservationsByRoomtypeId(roomtypeId, rootypeAmount);
		model.addAttribute("reservations", reservations);
		return "/front-end/reservation/reservation.jsp";
	}
}
