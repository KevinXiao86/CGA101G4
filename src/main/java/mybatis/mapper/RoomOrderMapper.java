package mybatis.mapper;

import java.util.Date;
import java.util.List;

import com.taiwan.beans.CustomerVO;
import com.taiwan.beans.RoomOrder;

public interface RoomOrderMapper {

	//查詢所有訂單
	public List<RoomOrder> queryAllRoomOrders();
	
	//根據訂單成立日期查詢訂單
	public List<RoomOrder> queryRoomOrdersByRoomOrderDate(Date roomOrderDate);
	
	//根據入住時間查詢訂單
	public List<RoomOrder> queryRoomOrdersByCheckinDate(Date checkinDate);
	
	//根據退房時間查詢訂單
	public List<RoomOrder> queryRoomOrdersByCheckoutDate(Date checkoutDate);
	
	//根據 id 查詢會員
	public CustomerVO getCustomerVOByCustId(Integer custId);
}
