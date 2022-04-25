package com.taiwan.service.roomtype.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiwan.beans.Roomtype;
import com.taiwan.service.roomtype.RoomtypeService;

import mybatis.mapper.RoomtypeMapper;

@Service
public class RoomtypeServiceImpl implements RoomtypeService{

	@Autowired
	private RoomtypeMapper roomtypeMapper;
	
	//根據廠商編號查詢所有房型
	@Override
	public List<Roomtype> getAllRoomtypesByCmpId(String cmpId) {
		//1. 數據校驗
		int id = parseInt(cmpId, 0);
		//2. 判斷
		if(id == 0) {
			return null;
		}
		//3. 調用 dao 的方法
		List<Roomtype> roomtypes = roomtypeMapper.queryRoomtypesByCmpId(id);
		//4. 回傳結果
		return roomtypes;
	}

	//將字符串轉成int類型
	public static int parseInt(String id, int defaultValue) {
		try {
			return Integer.parseInt(id);
		} catch (Exception e) {}
		//轉換失敗返回默認值
		return defaultValue;
	}
}
