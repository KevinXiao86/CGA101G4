package com.taiwan.service.coupon.impl;

import java.sql.Timestamp;
import java.util.Set;

import com.taiwan.beans.CustCoupon;
import com.taiwan.dao.custcoupon.CustCoupon_interface;
import com.taiwan.dao.custcoupon.impl.CustCouponJDBCDao;
import com.taiwan.service.coupon.CustCouponService2;

public class CustCouponServiceImpl2 implements CustCouponService2 {
	
	private CustCouponServiceImpl2 dao;
	private CustCoupon_interface dao1;
@Override
	public Set<CustCoupon> getCustByCopId(Integer copId) {
		
		return dao.getCustByCopId(copId);
	}







	public CustCouponServiceImpl2() {
		dao1 = new CustCouponJDBCDao();
	}
	@Override
	public void updateCustCouponStatusByTkt(Integer custId, Integer tktOrderId, String status, Timestamp usedate) {
		dao1.updateCustCouponStatusByTkt(custId, tktOrderId, status, usedate);
	}
}
