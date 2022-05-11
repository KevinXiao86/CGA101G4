package com.taiwan.dao.getCoupon;

import java.util.List;

import com.taiwan.beans.CouponVO;
import com.taiwan.beans.CustCoupon;

public interface GetCoupon_interface {
	// 查詢多筆"上架"資料
	public List<CouponVO> getAll();

	// 新增一條客人的優惠券
	public int insertCustCoupon(CustCoupon obj);

}
