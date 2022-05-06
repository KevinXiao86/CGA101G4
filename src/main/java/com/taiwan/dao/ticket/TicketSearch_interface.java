package com.taiwan.dao.ticket;

import java.util.List;
import java.util.Map;

import com.taiwan.beans.TicketVO;
import com.taiwan.dao.Dao;

public interface TicketSearch_interface   {
	//搜尋全部
	public List<TicketVO> getAll();
	//搜尋全部by狀態
	 public List<TicketVO> getAllbyStatus();
     //萬用複合查詢(傳入參數型態Map)(回傳 List)
     public List<TicketVO> getAll(Map<String, String[]> map); 
     
}
