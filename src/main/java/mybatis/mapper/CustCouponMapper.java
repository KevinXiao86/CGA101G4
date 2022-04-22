package mybatis.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taiwan.beans.CustCoupon;


public interface CustCouponMapper {

	// 新增一條客人的優惠券
	public int insertCustCoupon(CustCoupon obj);

	// 查詢某一個客人所擁有的優惠券
	public List<CustCoupon> queryCustCouponById(Integer custId);

	// 查詢某一個客人已使用(未使用)(已過期)的優惠券
	public List<CustCoupon> queryCustCouponUsedById(@Param("custId") Integer custId,@Param("status") String status);
	
	//更改已使用優惠券的狀態(全部)
	public int updateCustCouponStatus(@Param("cusId") Integer cusId,@Param("tktOrderId") Integer tktOrderId,@Param("roomOrderId") Integer roomOrderId,@Param("usedate") Timestamp usedate);

	// 更改已使用優惠券的狀態值(對票券)
	public int updateCustCouponStatusByTktOrderId(@Param("custId") Integer custId,@Param("tktOrderId") Integer tktOrderId,@Param("usedate") Timestamp usedate);

	// 更改已使用優惠券的狀態值(對訂房)
	public int updateCustCouponStatusByRoomOrderId(@Param("custId") Integer custId,@Param("roomOrderId") Integer roomOrderId,@Param("usedate") Timestamp usedate);

	// 因使用期限已過而更改優惠券的狀態(未完成Join)
//	public int updateCustCouponStatusByEnddate(@Param("custId") Integer custId,@Param("endDate") Timestamp endDate,@Param("status") String status);

}
