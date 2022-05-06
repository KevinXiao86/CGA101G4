package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taiwan.beans.RoomImg;

public interface RoomImgMapper {
	// 根據房型編號查找圖片
	public List<RoomImg> queryRoomImgsByRoomtypeId(Integer rootypeId);

	// 新增房型圖片
	public int insertRoomImg(@Param("roomImgs") List<RoomImg> roomImgs);

	// 根據流水號刪除圖片
	public int deleteRoomImgByRoomImgId(@Param("roomImgIds") Integer[] roomImgIds);
	
	// 根據流水號查詢圖片
	public RoomImg queryRoomImgByRoomImgId(Integer roomImgId);
}
