package mybatis.mapper;

import java.util.List;

import com.taiwan.beans.Reservation;

public interface ReservationMapper {

	// 查找該房型編號的當天日期查詢往後30天的所有預約紀錄
	public List<Reservation> getAllReservationsByNow(Integer roomtypeId);
}
