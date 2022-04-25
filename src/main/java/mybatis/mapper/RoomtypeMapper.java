package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taiwan.beans.Roomtype;

public interface RoomtypeMapper {

	//根據廠商編號查詢所有房型
	public List<Roomtype> queryRoomtypesByCmpId(Integer cmpId);
	
	//根據廠商編號和房型編號修改狀態
	public int updateRoomtypeStatusByCmpIdAndRoomtypeId(@Param("cmpId")Integer cmpId, @Param("roomtypeId")Integer roomtypeId, @Param("roomtypeStatus")String roomtypeStatus);
	
	//根據廠商編號和房型編號修改房型價格
}
