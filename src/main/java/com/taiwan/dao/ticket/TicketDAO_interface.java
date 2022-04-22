package com.taiwan.dao.ticket;

import java.util.List;

import com.taiwan.beans.TicketVO;
import com.taiwan.dao.Dao;


public interface TicketDAO_interface extends Dao<TicketVO, Integer>{
	
//	//新增一筆
//	public int insert (TicketVO ticketVO);
//	
//	//根據id修改
//	public int update (TicketVO ticketVO);
//	
//	//刪除一筆
//	public int delete (Integer tktId);
//	
//	//根據票券編號查詢一筆資料
//	public TicketVO queryById (Integer tktId);
//	
//	//搜尋全部
//	public List<TicketVO> queryAll();
	
	//查詢總評分最高前三
	public List<TicketVO> getTicketByScore (); 
	
	//查詢評分總人數最高前三
	public List<TicketVO> getTicketByPeople ();
	
	//根據票券名稱查詢多筆資料
	public List<TicketVO> queryTicketByTktName (String tktName);
	
	//根據票券種類查詢多筆資料
	public List<TicketVO> queryTicketByKind (String kind);
	
	//根據地點查詢
	public List<TicketVO> queryTicketByLocation (String location);
	
	//根據狀態查詢
	public List<TicketVO> queryTicketByStatus (String status);
	
	
}
