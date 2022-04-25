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
	public List<Roomtype> getAllRoomtypesByCmpId(Integer cmpId) {
		//1. 調用 dao 的方法
		List<Roomtype> roomtypes = roomtypeMapper.queryRoomtypesByCmpId(cmpId);
		//2. 回傳結果
		return roomtypes;
	}

	//修改房型狀態
	@Override
	public boolean updateRoomtypeStatus(String cmpId, String roomtypeId, String status) {
		//1. 數據校驗
		int idcmp = parseInt(cmpId, 0);
		int idRoomtype = parseInt(roomtypeId, 0);
		if (idcmp == 0 || idRoomtype == 0) {
			return false;
		}
		
		//2. 檢查狀態
		if ("下架".equals(status) || "上架".equals(status)) {
			//3. 調用業務方法
			int result = roomtypeMapper.updateRoomtypeStatusByCmpIdAndRoomtypeId(idcmp, idRoomtype, status);
			//4. 回傳結果
			return result != 0;
		}
		
		return false;
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
