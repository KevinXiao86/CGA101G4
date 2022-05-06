package com.taiwan.service.coupon;

import java.util.Set;

import com.taiwan.beans.CustCoupon;

public interface  CustCouponService2 {
	 //查詢某票券擁有的顧客(一對多)(回傳 Set)
		public Set<CustCoupon> getCustByCopId(Integer copId);

}
