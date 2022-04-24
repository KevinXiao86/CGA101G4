package com.taiwan.service;

import java.util.List;

import com.taiwan.beans.TicketVO;

public interface TicketService {
	
	//查詢所有票券資訊
	public List<TicketVO> findAll();

}
