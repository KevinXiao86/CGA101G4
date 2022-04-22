package com.taiwan.dao.coupon;



import java.util.List;

import com.taiwan.beans.CouponVO;
import com.taiwan.dao.Dao;


public interface CouponDAO_interface extends Dao<CouponVO, Integer>{
	
//	//新增一筆
//	public int insert (CouponVO couponVO);
//	
//	//根據id修改
//	public int update (CouponVO couponVO);
//	
//	//刪除一筆
//	public int delete (Integer couponId);
//	
//	//根據票券編號查詢一筆資料
//	public CouponVO queryById (Integer couponId);
//	
//	//搜尋全部
//	public List<CouponVO> queryAll();
	
	//根據優惠券名稱查詢多筆資料
	public List<CouponVO> queryCouponByCopName(String copName);
	
	//根據狀態查詢多筆資料
	public List<CouponVO> queryCouponByStatus(String status);
	
//	//更改狀態因時間過期的優惠券
//	public int updateByEnddate();
}
