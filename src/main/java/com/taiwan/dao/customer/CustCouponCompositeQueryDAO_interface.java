package com.taiwan.dao.customer;

import java.util.List;
import java.util.Map;

import com.taiwan.beans.CustCoupon;

public interface CustCouponCompositeQueryDAO_interface {
	public List<CustCoupon> getAll(Map<String, String[]> map);
}
