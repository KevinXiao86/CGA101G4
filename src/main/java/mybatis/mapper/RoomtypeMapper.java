package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taiwan.beans.Roomtype;

public interface RoomtypeMapper {

	// 根據廠商編號獲取所有房型
	public List<Roomtype> queryAllRoomtypes(Integer cmpId);

	// 根據房型編號和廠商編號修改狀態
	public int updateStatusByRoomtypeIdAndCmpId(@Param("cmpId") Integer cmpId, @Param("roomtypeId") Integer roomtypeId,
			@Param("status") String status);

	// 根據房型編號和廠商編號修改價錢
	public int updatePriceByCmpIdAndRoomtypeId(@Param("cmpId") Integer cmpId, @Param("roomtypeId") Integer roomtypeId,
			@Param("price") Integer price);

	// 根據房型編號和廠商編號查詢房型
	public Roomtype getRoomtypeByCmpIdAndRoomtypeId(@Param("cmpId") Integer cmpId,
			@Param("roomtypeId") Integer roomtypeId);

	// 查找目前最大的房型編號
	public int queryRoomtypeId();

	// 新增房型
	public int insertRoomtype(Roomtype roomtype);

	// 修改房型
	public int updateRoomtype(Roomtype roomtype);

}
