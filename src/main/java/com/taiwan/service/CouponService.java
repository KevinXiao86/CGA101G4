package com.taiwan.service;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.CouponVO;

public interface CouponService {

	// 新增一筆Coupon
	public boolean addCoupon(String copName, String introduce, Integer discount, Timestamp startdate, Timestamp enddate,
			String img);

	// 查詢所有優惠券
	public List<CouponVO> findAll();

	// 刪除所選的優惠券
	public boolean delete(Integer copId);

	// 更新所選的優惠券
	public boolean update(Integer copId, String copName, String introduce, Integer discount, Timestamp startdate,
			Timestamp enddate, String img);

	// 根據id查詢所選的優惠券
	public CouponVO findById(Integer copId);

	// 根據id更改coupon的狀態
	public boolean updateStatus(Integer copId, String status);

	// 根據標題做模糊查詢
	public List<CouponVO> selectByTitle(String copName);

	// 根據上架或下架做搜尋
	public List<CouponVO> selectByStatus(String status);

}
