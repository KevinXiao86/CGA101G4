package com.taiwan.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiwan.beans.TicketVO;
import com.taiwan.dao.ticket.TicketDAO_interface;
import com.taiwan.dao.ticket.impl.TicketJDBCDAO;
import com.taiwan.service.TicketService;

import mybatis.mapper.TicketMapper;

@Service
public class TicketServiceImpl implements TicketService {
	@Autowired
	private TicketMapper mapper;
	
	private TicketDAO_interface dao;
	
	public TicketServiceImpl() {
		dao=new TicketJDBCDAO();
	}
	@Transactional
	@Override
	public List<TicketVO> findAll() {
		return mapper.queryAll();
	}

	@Transactional
	@Override
	public boolean addTicket(String tktName, Integer originalAmount, Integer price, Timestamp startdate,
			Timestamp enddate, String location, String instruction, String address, String notice, String howuse,
			String canxpolicy, String kind) {
		TicketVO ticketVO = new TicketVO();
		ticketVO.setTktName(tktName);
		ticketVO.setOriginalAmount(originalAmount);
		ticketVO.setPrice(price);
		ticketVO.setStartdate(startdate);
		ticketVO.setEnddate(enddate);
		ticketVO.setLocation(location);
		ticketVO.setInstruction(instruction);
		ticketVO.setAddress(address);
		ticketVO.setNotice(notice);
		ticketVO.setHowuse(howuse);
		ticketVO.setCanxpolicy(canxpolicy);
		ticketVO.setKind(kind);
		return mapper.insert(ticketVO) > 0;
	}

	@Transactional
	@Override
	public boolean deleteTkt(Integer tktId) {
		return mapper.delete(tktId) > 0;
	}

	@Transactional
	@Override
	public boolean updateStatus(Integer tktId, String status) {
		return mapper.updateTktStatusByTktId(tktId, status) > 0;
	}

	@Transactional
	@Override
	public TicketVO findById(Integer tktId) {
		TicketVO ticketVO=mapper.queryOInfoById(tktId);
		return ticketVO;
	}
	@Transactional
	@Override
	public List<TicketVO> findByName(String tktName) {
		List<TicketVO> ls=mapper.queryTicketByTktName(tktName);
		return ls;
	}
	@Transactional
	@Override
	public List<TicketVO> findByStatus(String status) {
		List<TicketVO> ls=mapper.queryTicketByStatus(status);
		return ls;
	}
	@Transactional
	@Override
	public List<TicketVO> findByLoc(String location) {
		List<TicketVO> ls=mapper.queryTicketByLocation(location);
		return ls;
	}
	@Transactional
	@Override
	public List<TicketVO> findBykind(String kind) {
		List<TicketVO> ls=mapper.queryTicketByKind(kind);
		return ls;
	}
	@Transactional
	@Override
	public int findTktId() {
		return mapper.queryMax();
	}
	@Transactional
	@Override
	public boolean updateTkt(Integer tktId, String tktName, Integer originalAmount, Integer price, Timestamp startdate,
			Timestamp enddate, String location, String instruction, String address, String notice, String howuse,
			String canxpolicy, String kind) {
		TicketVO ticketVO = new TicketVO();
		ticketVO.setTktId(tktId);
		ticketVO.setTktName(tktName);
		ticketVO.setOriginalAmount(originalAmount);
		ticketVO.setPrice(price);
		ticketVO.setStartdate(startdate);
		ticketVO.setEnddate(enddate);
		ticketVO.setLocation(location);
		ticketVO.setInstruction(instruction);
		ticketVO.setAddress(address);
		ticketVO.setNotice(notice);
		ticketVO.setHowuse(howuse);
		ticketVO.setCanxpolicy(canxpolicy);
		ticketVO.setKind(kind);
		return mapper.update(ticketVO)>0;
	}
	@Override
	public boolean update(Integer tktId, String tktName, Integer originalAmount, Integer price, Timestamp startdate,
			Timestamp enddate, String location, String instruction, String address, String notice, String howuse,
			String canxpolicy, String kind) {
		TicketVO ticketVO = new TicketVO();
		ticketVO.setTktId(tktId);
		ticketVO.setTktName(tktName);
		ticketVO.setOriginalAmount(originalAmount);
		ticketVO.setPrice(price);
		ticketVO.setStartdate(startdate);
		ticketVO.setEnddate(enddate);
		ticketVO.setLocation(location);
		ticketVO.setInstruction(instruction);
		ticketVO.setAddress(address);
		ticketVO.setNotice(notice);
		ticketVO.setHowuse(howuse);
		ticketVO.setCanxpolicy(canxpolicy);
		ticketVO.setKind(kind);
		return dao.update(ticketVO)>0;
	}

}
