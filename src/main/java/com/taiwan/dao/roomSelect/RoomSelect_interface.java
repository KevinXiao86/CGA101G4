package com.taiwan.dao.roomSelect;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


import com.taiwan.beans.Company;
import com.taiwan.beans.Reservation;
import com.taiwan.beans.RoomSelectVO;
import com.taiwan.beans.Roomtype;
import com.taiwan.beans.TicketVO;

public interface RoomSelect_interface {
	//用地點搜尋
	public List<Company> fineByLocation(String location) ;
	//調用預約表查房間
	public List<Reservation> fineByTime(Integer roomtype_id ,Timestamp reserve_date ,Timestamp reserve_date2) ;
	//用房行人數 搜尋
	public List<Roomtype> fineByPeople(Integer roomtype_people  ,Integer  cmp_id) ;
	 
	//萬用複合查詢(傳入參數型態Map)(回傳 List) 
    public List<RoomSelectVO> getAllTable(String location,Integer roomtype_people,Date reserve_date,Date reserve_date2 ); 
}
