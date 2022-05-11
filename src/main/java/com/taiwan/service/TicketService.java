package com.taiwan.service;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.TicketVO;

public interface TicketService {

	// 查詢所有票券資訊
	public List<TicketVO> findAll();

	// 新增一筆票券
	public boolean addTicket(String tktName, Integer originalAmount, Integer price, Timestamp startdate,
			Timestamp enddate, String location, String instruction, String address, String notice, String howuse,
			String canxpolicy, String kind);

	// 刪除一筆票券
	public boolean deleteTkt(Integer tktId);

	// 更改優惠券的上架 下架狀態
	public boolean updateStatus(Integer tktId, String status);

	// 根據票券id搜尋資料
	public TicketVO findById(Integer tktId);

	// 根據票票標題作模糊搜尋
	public List<TicketVO> findByName(String tktName);

	// 根據上架狀態做查詢
	public List<TicketVO> findByStatus(String status);

	// 根據地點做查詢
	public List<TicketVO> findByLoc(String location);

	// 根據票券種類做查詢
	public List<TicketVO> findBykind(String kind);

	// 查詢最新一筆的tktId
	public int findTktId();

	// 更新票券
	public boolean updateTkt(Integer tktId, String tktName, Integer originalAmount, Integer price, Timestamp startdate,
			Timestamp enddate, String location, String instruction, String address, String notice, String howuse,
			String canxpolicy, String kind);

	// 更新票券 JDBC
	public boolean update(Integer tktId, String tktName, Integer originalAmount, Integer price, Timestamp startdate,
			Timestamp enddate, String location, String instruction, String address, String notice, String howuse,
			String canxpolicy, String kind);

	// 前台搜尋
	public List<TicketVO> findByThree(String status, String location, String tktName);

	// 查詢總評分最高前三
	public List<TicketVO> findTicketByScore();

	// 查詢評分總人數最高前三
	public List<TicketVO> findTicketByPeople();

}
