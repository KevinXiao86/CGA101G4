package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taiwan.beans.TicketVO;
import com.taiwan.beans.TktItem;

public interface TktItemMapper {
	//新增一筆票券訂單明細(ok)
	public int insertTktItem(TktItem obj);
	//根據訂單Id取出所有的票券訂單明細(ok)
	public List<TktItem> queryTktItemByTktOrderId(Integer tktOrderId);
	//根據票券訂單id及票券id來更改票券訂單明細的使用數量(ok)
	public int updateTktItemUsedByTKTId(@Param("tktOrderId") Integer tktOrderId,@Param("tktId") Integer tktId,@Param("used") Integer used);
	//根據票券訂單id及票券id來對票券評論及評分(ok)
	public int updateTktItemScoreContentByTktId(@Param("tktOrderId") Integer tktOrderId,@Param("tktId") Integer tktId,@Param("score") Integer score,@Param("content") String content);
	//查詢所有相同票券的評論(ok)
	public List<String> queryAllTktItemConTentByTktId(Integer tktId);
	//查詢5筆票券評論依照評價高低排列(ok)
	public List<String> queryFiveTktItemContentByScore(Integer tktId);
	//查詢所有相同票券id的總評分(ok)
	public int queryTktItemTllScoreByTktId(Integer tktId);
	//查詢所有相同票券的總評分人數(ok)
	public int queryTktItemTtlPeopleByTktId(Integer tktId);
	
	//藉由訂單Id查詢票券
	public TicketVO queryByTktId(Integer tktId);

}
