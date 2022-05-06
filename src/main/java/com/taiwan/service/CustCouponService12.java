package com.taiwan.service;

import java.util.List;

import com.taiwan.beans.CustCoupon;

public interface CustCouponService12 {
public List<CustCoupon> searchCustCoupon(Integer custId);

public int updateCustCouponStatusByRoomOrderId(Integer custId,Integer roomOrderId,String status);

public CustCoupon searchCustCouponId(Integer custCouponId);
}