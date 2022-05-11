package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taiwan.beans.TicketVO;
import com.taiwan.beans.TktImgVO;

public interface TicketMapper {

	public int insert(TicketVO ticketVO);

	public int update(TicketVO ticketVO);

	public int delete(Integer tktId);

	public TicketVO queryById(Integer tktId);

	public List<TicketVO> queryAll();

	public List<TicketVO> getTicketByScore();

	public List<TicketVO> getTicketByPeople();

	public List<TicketVO> queryTicketByTktName(String tktName);

	public List<TicketVO> queryTicketByKind(String kind);

	public List<TicketVO> queryTicketByLocation(String location);

	public List<TicketVO> queryTicketByStatus(String status);

	// 根據票券id修改票券狀態
	public int updateTktStatusByTktId(@Param("tktId") Integer tktId, @Param("status") String status);

	// 根據票券id搜尋全部的資訊
	public TicketVO queryOInfoById(Integer tktId);

	// 找到最新的Id
	public int queryMax();

	// 根據票券id查找票券圖片資料
	public TktImgVO queryImgByTicket(Integer tktId);

	// 根據地點名稱搜尋已上架票券
	public List<TicketVO> queryLocName(@Param("status") String status, @Param("location") String location,
			@Param("tktName") String tktName);

}