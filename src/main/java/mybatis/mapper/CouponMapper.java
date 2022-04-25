package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taiwan.beans.CouponVO;

public interface CouponMapper {
	
	public int insert (CouponVO couponVO);
	
	public int update (CouponVO couponVO);
	
	public int delete (Integer couponId);
	
	public CouponVO queryById (Integer couponId);
	
	public List<CouponVO> queryAll();
	
	public List<CouponVO> queryCouponByCopName(String copName);
	
	public List<CouponVO> queryCouponByStatus(String status);
	
	public int updateStatus(@Param("copId") Integer copId,@Param("status") String status);


}
