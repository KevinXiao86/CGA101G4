package com.taiwan.service.impl;

import java.util.List;

import com.taiwan.beans.CustCoupon;
import com.taiwan.dao.custcoupon.CustCoupon_interface;
import com.taiwan.dao.custcoupon.impl.CustCouponJDBCDao;
import com.taiwan.dao.custcoupon12.Custcoupon12;
import com.taiwan.dao.custcoupon12.impl.CustcouponDao12;

import com.taiwan.service.CustCouponService12;

public class CustCouponServiceImpl12 implements CustCouponService12{
	CustCoupon_interface dao=null;
	Custcoupon12 dao2=null;
	public CustCouponServiceImpl12() {
		dao=new CustCouponJDBCDao();
		dao2=new CustcouponDao12();
	}
	//查詢CUST可用的優惠券
	@Override
	public List<CustCoupon> searchCustCoupon(Integer custId) {
		return dao.queryCustCouponUsedById(custId, "未使用");
	}
	@Override
	public CustCoupon searchCustCouponId(Integer custCouponId) {
		return dao2.searchCustCouponById(custCouponId);
	}
	@Override
	public int updateCustCouponStatusByRoomOrderId(Integer custId, Integer roomOrderId, String status) {
		
		return 0;
	}
public static void main(String[] args) {
	CustCouponServiceImpl12 test=new CustCouponServiceImpl12();
	
	List<CustCoupon> list=test.searchCustCoupon(10028);
	System.out.println(list.get(0).getCopId());
}
}
