package com.taiwan.service.custCoupon.impl;

import java.util.List;

import com.taiwan.beans.CustCoupon;
import com.taiwan.dao.custcoupon.CustCoupon_interface;
import com.taiwan.dao.custcoupon.impl.CustCouponJDBCDao;
import com.taiwan.service.custCoupon.CustCouponService14;

public class CustCouponServiceImpl14 {
	private CustCoupon_interface dao;

	public CustCouponServiceImpl14() {
		dao = new CustCouponJDBCDao();
	}

	public List<CustCoupon> queryCustCouponById(Integer custId) {
		System.out.println("我進來CustCouponServiceImpl14裡了");
		return dao.queryCustCouponById(custId);
	}

}
