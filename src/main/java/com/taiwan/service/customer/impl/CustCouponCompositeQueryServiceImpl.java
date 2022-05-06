package com.taiwan.service.customer.impl;

import java.util.List;
import java.util.Map;

import com.taiwan.beans.CustCoupon;
import com.taiwan.dao.customer.CustCouponCompositeQueryDAO_interface;
import com.taiwan.dao.customer.impl.CustCouponCompositeQueryJNDIDAO;
import com.taiwan.service.customer.CustCouponCompositeQueryService;

public class CustCouponCompositeQueryServiceImpl implements CustCouponCompositeQueryService {
	private CustCouponCompositeQueryDAO_interface dao;

	public CustCouponCompositeQueryServiceImpl() {
		dao = new CustCouponCompositeQueryJNDIDAO();
	}

	@Override
	public List<CustCoupon> getAll(Map<String, String[]> map) {
		List<CustCoupon> list = dao.getAll(map);
		return list;
	}

}
