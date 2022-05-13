package com.taiwan.dao.custcoupon;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.CustCoupon;


public interface CustCoupon_interface{
	//新增一條客人的優惠券
	public int insertCustCoupon(CustCoupon obj);
	//查詢某一個客人所擁有的優惠券
	public List<CustCoupon> queryCustCouponById(Integer custId);
	//查詢某一個客人已使用(未使用)(已過期)的優惠券
	public List<CustCoupon> queryCustCouponUsedById(Integer custId,String status);
	//更改已使用優惠券的狀態值(對票券)
	public int updateCustCouponStatusByTktOrderId(Integer custId,Integer tktOrderId,String status);
	//更改已使用優惠券的狀態值(對票券)
	public int updateCustCouponStatusByTkt(Integer custId,Integer tktOrderId,String status, Timestamp usedate);
	//更改已使用優惠券的狀態值(對訂房)
	public int updateCustCouponStatusByRoomOrderId(Integer custId,Integer roomOrderId,String status);
	//因使用期限已過而更改優惠券的狀態(未完成Join)
	public int updateCustCouponStatusByEnddate(Integer custId,Timestamp enddate,String status);
	//查詢全部
	public List<CustCoupon> getAll();
}
