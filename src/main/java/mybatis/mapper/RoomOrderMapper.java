package mybatis.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taiwan.beans.RoomOrder;

public interface RoomOrderMapper {

	// 查詢所有訂單
	public List<RoomOrder> queryAllRoomOrders();

	// 根據訂單成立日期查詢訂單
	public List<RoomOrder> queryRoomOrdersByRoomOrderDate(Date roomOrderDate);

	// 根據入住時間查詢訂單
	public List<RoomOrder> queryRoomOrdersByCheckinDate(Date checkinDate);

	// 根據退房時間查詢訂單
	public List<RoomOrder> queryRoomOrdersByCheckoutDate(Date checkoutDate);

	// 根據訂單Id搜尋訂房訂單
	public RoomOrder queryById(Integer roomOrderId);

	// 根據廠商Id搜尋他的訂單
	public List<RoomOrder> queryByCmpId(Integer cmpId);

	// 根據會員Id搜尋他的訂單
	public List<RoomOrder> queryByCustId(Integer custId);

	// 搜尋一段時間內的訂單
	public List<RoomOrder> queryByDate(@Param("startdate") Date startdate, @Param("enddate") Date enddate);

	// 根據訂單id取消訂單
	public int updateStatusAndReason(@Param("roomOrderId") Integer roomOrderId,	@Param("roomOrderStatus") String roomOrderStatus, @Param("cancel") String cancel);

	// 根據訂單狀態搜尋訂單
	public List<RoomOrder> queryByStatus(String roomOrderStatus);

	// 根據會員與廠商Id搜尋的訂單
	public List<RoomOrder> queryByCustIdAndCmpId(@Param("cmpId") Integer cmpId,@Param("custId") Integer custId);

	// 搜尋同一家廠商一段時間內的訂單
	public List<RoomOrder> queryByDateAndCmpId(@Param("cmpId") Integer cmpId,@Param("startdate") Date startdate, @Param("enddate") Date enddate);
	
	// 根據訂單狀態搜尋同一家訂單
	public List<RoomOrder> queryByStatusAndCmpId(@Param("cmpId") Integer cmpId,@Param("roomOrderStatus") String roomOrderStatus);
	
	//根據訂單Id修改狀態
	public int updateStatus(@Param("roomOrderId") Integer roomOrderId,@Param("roomOrderStatus") String roomOrderStatus); 
}
