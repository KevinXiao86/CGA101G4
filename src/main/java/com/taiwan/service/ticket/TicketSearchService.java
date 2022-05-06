package com.taiwan.service.ticket;

import java.util.List;
import java.util.Map;

import com.taiwan.beans.TicketVO;
import com.taiwan.dao.employee.EmployeeDAO_interface;
import com.taiwan.dao.employee.impl.EmployeeJDBCDAO;
import com.taiwan.dao.ticket.TicketSearch_interface;
import com.taiwan.dao.ticket.impl.TicketSearchJDBC;

public class TicketSearchService {

	private TicketSearch_interface dao;

	public TicketSearchService() {
		dao = new TicketSearchJDBC();
	}

	// 搜尋全部
	public List<TicketVO> getAll() {
		return dao.getAll();
	}

	// 搜尋全部by狀態
	public List<TicketVO> getAllbyStatus() {
		return dao.getAllbyStatus();
	}

	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	public List<TicketVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

}
