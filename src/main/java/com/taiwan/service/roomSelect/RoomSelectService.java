package com.taiwan.service.roomSelect;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.Company;
import com.taiwan.beans.Reservation;
import com.taiwan.beans.RoomSelectVO;
import com.taiwan.beans.Roomtype;
import com.taiwan.dao.roomSelect.RoomSelect_interface;
import com.taiwan.dao.roomSelect.impl.RoomSelectJDBCDAO;
import com.taiwan.dao.ticket.TicketSearch_interface;
import com.taiwan.dao.ticket.impl.TicketSearchJDBC;

public class RoomSelectService {

	private RoomSelect_interface dao;

	public RoomSelectService() {
		dao = new RoomSelectJDBCDAO();
	}
	
	
	//用地點搜尋
	public List<Company> fineByLocation(String location){
		return dao.fineByLocation(location);
	}
	
	//用房行人數 搜尋
	public List<Roomtype> fineByPeople(Integer roomtype_people  ,Integer cmp_id){
	return dao.fineByPeople(roomtype_people, cmp_id);	
	}
	
	//調用預約表查房間
	public List<Reservation> fineByTime(Integer roomtype_id ,Timestamp reserve_date ,Timestamp reserve_date2){
		return dao.fineByTime(roomtype_id, reserve_date, reserve_date2);
	}

	public List<RoomSelectVO> getAllTable(String location, Integer roomtype_people, Date reserve_date,
			Date reserve_date2){
		return dao.getAllTable(location, roomtype_people, reserve_date, reserve_date2);
	}
		
}
