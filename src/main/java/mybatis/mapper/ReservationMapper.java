package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taiwan.beans.Reservation;

public interface ReservationMapper {

	// 查找該房型編號的當天日期查詢往後30天的所有預約紀錄
	public List<Reservation> getAllReservationsByNow(Integer roomtypeId);

	// 根據日期查找該房型編號的預約紀錄
	public List<Reservation> getAllReservationsByDate(@Param("startDate")String startDate,
			@Param("days")String days, @Param("roomtypeId")Integer roomtypeId);
}
