package mybatis.mapper;

import java.util.List;

import com.taiwan.beans.Roomtype;

public interface RoomtypeMapper {

	//根據廠商編號查詢所有房型
	public List<Roomtype> queryRoomtypesByCmpId(Integer cmpId);
}
