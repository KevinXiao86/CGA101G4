package com.taiwan.service.roomtype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiwan.beans.Roomtype;
import com.taiwan.utils.CommonUtils;

import mybatis.mapper.RoomImgMapper;
import mybatis.mapper.RoomtypeMapper;

@Service
public class RoomtypeService {
	
	@Autowired
	private RoomtypeMapper roomtypeMapper;
	
	@Autowired
	private RoomImgMapper roomImgMapper;
	
	//獲取廠商的所有房型
	@Transactional(readOnly = true)
	public List<Roomtype> getAllRoomtypes(Integer cmpId){
		List<Roomtype> roomtypes = roomtypeMapper.queryAllRoomtypes(cmpId);
		return roomtypes;
	}
	
	
	//根據廠商編號和房型編號更新狀態
	@Transactional
	public boolean updateStatusByCmpIdAndRoomtypeId(Integer cmpId, Integer rootypeId, String status) {
		int result = roomtypeMapper.updateStatusByRoomtypeIdAndCmpId(cmpId, rootypeId, status);
		return result != 0;
	}
	
	
	//根據廠商編號和房型編號更新價格
	@Transactional
	public boolean updatePriceByCmpIdAndRoomtypeId(Integer cmpId, Integer rootypeId, Integer price) {
		int result = roomtypeMapper.updatePriceByCmpIdAndRoomtypeId(cmpId, rootypeId, price);
		return result != 0;
	}
	
	//查看房型詳情
	@Transactional(readOnly = true)
	public Roomtype getRoomtypeByCmpIdAndRoomtypeId(Integer cmpId, Integer rootypeId) {
		Roomtype roomtype = roomtypeMapper.getRoomtypeByCmpIdAndRoomtypeId(cmpId, rootypeId);
		return roomtype;
	}
	
	//新增房型
	@Transactional
	public boolean insertRoomtype(Roomtype roomtype) {
		int result1 = roomtypeMapper.insertRoomtype(roomtype);
		int result2 = roomImgMapper.insertRoomImg(roomtype.getRoomImgs());
		
		if (result1 == 0 || result2 == 0) {
			return false;
		}
		
		return true;
	}
	
	
	//返回目前的 roomtypeId
	public int getRoomtypeId() {
		int roomtypeId = roomtypeMapper.queryRoomtypeId();
		return roomtypeId;
	}


	//根據房型圖片的流水號刪除圖片
	public boolean deleteRoomImg(String[] roomImgIds) {
		boolean flag = false;
		Integer[] ids = new Integer[roomImgIds.length];
		for(int i = 0; i < roomImgIds.length; i++) {
			//轉成 int 
			int id = CommonUtils.parseInt(roomImgIds[0], 0);
			//轉換失敗
			if (id == 0) {
				flag = true;
				break;
			}
			//轉換成功
			ids[0] = id;
		}
		
		for(Integer i : ids) {
			System.out.println(i);
		}
		if (flag) {
			return false;
		}
//		int result = roomImgMapper.deleteRoomImgByRoomImgId(roomImgIds);
//		return result != 0;
		return true;
	}
}
