package com.taiwan.dao.custcoupon12;

import java.sql.Connection;

import com.taiwan.beans.CustCoupon;

public interface Custcoupon12 {
	public int updateCustCouponStatusByRoomOrderId(Integer custId,Integer roomOrderId,Integer copId,Connection con);

	public CustCoupon searchCustCouponById(Integer custCouponId);

}
