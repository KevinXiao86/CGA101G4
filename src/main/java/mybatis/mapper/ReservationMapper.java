package mybatis.mapper;

import java.util.Date;
import java.util.List;

import com.taiwan.beans.Reservation;

public interface ReservationMapper {

	// 根據訂單編號找到訂單詳情中的房型編號
	public int queryRoomtypeId(Integer roomtypeId);

	// 根據日期查詢往後30天的所有預約紀錄
	public List<Reservation> getAllReservationsByDate(Date date);

	// 查找該房型編號的當天日期查詢往後30天的所有預約紀錄
	public List<Reservation> getAllReservationsByNow(Integer roomtypeId);
}
