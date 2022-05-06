package com.taiwan.service.customer;

import java.util.List;
import java.util.Map;

import com.taiwan.beans.CustCoupon;

public interface CustCouponCompositeQueryService {
	public List<CustCoupon> getAll(Map<String, String[]> map);
}
