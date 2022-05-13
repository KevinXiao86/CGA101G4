package com.taiwan.service;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.TktItem;
import com.taiwan.beans.TktOrder;
import com.taiwan.dao.tktorder.TktOrderDao;
import com.taiwan.dao.tktorder.impl.TktOrderJDBCDao;

public class TktOrderService {
	
	private TktOrderDao dao;
	
	public TktOrderService() {
		dao = new TktOrderJDBCDao();
	}
	
	//新增一筆票券訂單(有使用優惠券)
	public TktOrder addTktOrderWithCoupon(Integer custId, Integer originalPrice, Integer ttlPrice,
			Integer custCopId, String qrcode, List<TktItem> tktItem_list) {
		
		TktOrder tktOrder = new TktOrder();
		tktOrder.setCustId(custId);
		tktOrder.setOriginalPrice(originalPrice);
		tktOrder.setTtlPrice(ttlPrice);
		tktOrder.setCustCopId(custCopId);
		tktOrder.setQrcode(qrcode);
		dao.insertTktOrderWithCoupon(tktOrder, tktItem_list);
		
		return tktOrder;
	}
	
	//新增一筆票券訂單(未使用優惠券)
	public TktOrder addTktOrderNoCoupon(Integer custId, Integer originalPrice, Integer ttlPrice,
			String qrcode, List<TktItem> tktItem_list) {
		
		TktOrder tktOrder = new TktOrder();
		tktOrder.setCustId(custId);
		tktOrder.setOriginalPrice(originalPrice);
		tktOrder.setTtlPrice(ttlPrice);
		tktOrder.setQrcode(qrcode);
		dao.insertTktOrderWithCoupon(tktOrder, tktItem_list);
		
		return tktOrder;
	}
	
	//查詢全部票券訂單
	public List<TktOrder> getAll(){
		return dao.queryAllTktOrder();
	}
	
	//根據會員Id查詢票券訂單
	public List<TktOrder> getTktOrderByCustId(Integer custId){
		return dao.queryTktOrderByCustId(custId);
	}
	
	//根據日期來查詢的方法我覺得可以合併
		//根據日期查詢票券訂單
	public List<TktOrder> getTktOrderByOrderDate(Timestamp orderDate){
		return dao.queryTktOrderByOrderDate(orderDate);
	}
	
		//根據一個區間的日期查詢票券訂單
	public List<TktOrder> getTktOrderByDateToDate(Timestamp startdate,Timestamp enddate){
		return dao.queryTktOrderByDateToDate(startdate, enddate);
	}

	//會員根據日期區間查詢票券訂單
	public List<TktOrder> getTktOrderByDateCustID(Timestamp startdate,Timestamp enddate, Integer custId){
		return dao.queryTktOrderByDateCustID(startdate, enddate, custId);
	}
	
	//查詢所有票券訂單總共的金額
	public int getTktOrderTtlPrice() {
		return dao.queryTktOrderTtlPrice();
	}
	
	//根據會員Id查詢票券訂單，總共金額
	public int getTktOrderTtlPriceById(Integer custId) {
		return dao.quertTktOrderTtlPriceById(custId);
	}
	
	//根據日期來查詢票券訂單總共的金額
	public int getTktOrderTtlPriceByDateToDate(Timestamp stardate,Timestamp enddate) {
		return dao.queryTktOrderTtlPriceByDateToDate(stardate, enddate);
	}
	
	//根據訂單編號查詢
	public TktOrder getTktOrderByTktOrderId(Integer tktOrderId) {
		return dao.queryTktOrderByTktOrderId(tktOrderId);
	}
	
	//更新qrcode
	public void updateQrcode(String qrcode, Integer tktOrderId) {
		dao.updateQrcode(qrcode, tktOrderId);
	}
}
