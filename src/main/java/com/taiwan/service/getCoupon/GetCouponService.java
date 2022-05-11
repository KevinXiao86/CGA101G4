package com.taiwan.service.getCoupon;


import java.util.List;

import com.taiwan.beans.CouponVO;
import com.taiwan.dao.getCoupon.GetCoupon_interface;
import com.taiwan.dao.getCoupon.impl.GetCouponJDBCDAO;

public class GetCouponService {

	private GetCoupon_interface dao;

	public GetCouponService() {
		dao = new GetCouponJDBCDAO();
	}

	public List<CouponVO> getAll(){
		return dao.getAll();
	}
}
