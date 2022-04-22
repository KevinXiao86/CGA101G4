package com.taiwan.dao.tktorder;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.TktOrder;

public interface TktOrderDao{
	//新增一筆票券訂單(有使用優惠券)
	public int insertTktOrderWithCoupon(TktOrder obj);
	//新增一筆票券訂單(未使用優惠券)
	public int insertTktOrderNoCoupon(TktOrder obj);
	//查詢全部票券訂單
	public List<TktOrder> queryAllTktOrder();
	//根據會員Id查詢票券訂單
	public List<TktOrder> queryTktOrderByCustId(Integer custId);
	//根據日期來查詢的方法我覺得可以合併
		//根據日期查詢票券訂單
	public List<TktOrder> queryTktOrderByOrderDate(Timestamp orderDate);
		//根據一個區間的日期查詢票券訂單
	public List<TktOrder> queryTktOrderByDateToDate(Timestamp startdate,Timestamp enddate);
	//查詢所有票券訂單總共的金額
	public int queryTktOrderTtlPrice();
	//根據會員Id查詢票券訂單，總共金額
	public int quertTktOrderTtlPriceById(Integer custId);
	//根據日期來查詢票券訂單總共的金額
	public int queryTktOrderTtlPriceByDateToDate(Timestamp stardate,Timestamp enddate);
	//根據訂單編號查詢
	public TktOrder queryTktOrderByTktOrderId(Integer tktOrderId);
	//訂單複合查詢(未完成)
	public List<TktOrder> queryXX(Integer tktOrderId,Integer custId,Timestamp orderDate);
	
}