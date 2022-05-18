package mybatis.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taiwan.beans.CustomerVO;
import com.taiwan.beans.TicketVO;
import com.taiwan.beans.TktOrder;

public interface TktOrderMapper {

	// 新增一筆票券訂單(有使用優惠券)(ok)
	public int insertTktOrderWithCoupon(TktOrder obj);

	// 新增一筆票券訂單(未使用優惠券)(ok)
	public int insertTktOrderNoCoupon(TktOrder obj);

	// 查詢全部票券訂單
	public List<TktOrder> queryAll();

	// 根據會員Id查詢票券訂單
	public List<TktOrder> queryTktOrderByCustId(Integer custId);

	// 根據日期來查詢的方法我覺得可以合併
	// 根據日期查詢票券訂單
//	public List<TktOrder> queryTktOrderByOrderDate(Timestamp orderDate);

	// 根據一個區間的日期查詢票券訂單
	public List<TktOrder> queryTktOrderByDateToDate(@Param("startdate") Timestamp startdate,@Param("enddate") Timestamp enddate);

	// 查詢所有票券訂單總共的金額
	public int queryTktOrderTtlPrice();

	// 根據會員Id查詢票券訂單，總共金額
	public int quertTktOrderTtlPriceById(Integer custId);

	// 根據日期來查詢票券訂單總共的金額
	public int queryTktOrderTtlPriceByDateToDate(@Param("startdate") Timestamp startdate,@Param("enddate") Timestamp enddate);

	// 根據訂單編號查詢
	public TktOrder queryTktOrderByTktOrderId(Integer tktOrderId);
	
	//根據custId查詢資料
//	public CustomerVO queryByCustId(Integer custId);
//	
//	//根據票券Id查詢資料
//	public TicketVO queryByTktId(Integer tktId);
	
	//根據訂購人來做查詢

}
