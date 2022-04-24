package com.taiwan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiwan.beans.TicketVO;
import com.taiwan.service.TicketService;

import mybatis.mapper.TicketMapper;
@Service
public class TicketServiceImpl implements TicketService{
	@Autowired
	private TicketMapper mapper;
	@Transactional
	@Override
	public List<TicketVO> findAll() {
		return mapper.queryAll();
	}

}
