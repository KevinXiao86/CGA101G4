package com.taiwan.service.coupon.impl;

import java.util.Set;

import com.taiwan.beans.CustCoupon;
import com.taiwan.service.coupon.CustCouponService2;

public class CustCouponServiceImpl2 implements CustCouponService2 {
	
	private CustCouponServiceImpl2 dao;
@Override
	public Set<CustCoupon> getCustByCopId(Integer copId) {
		
		return dao.getCustByCopId(copId);
	}
}
