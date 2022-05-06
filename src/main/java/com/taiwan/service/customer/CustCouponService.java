package com.taiwan.service.customer;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.CustCoupon;
import com.taiwan.beans.EmployeeVO;
import com.taiwan.dao.custcoupon.CustCoupon_interface;
import com.taiwan.dao.custcoupon.impl.CustCouponJDBCDao;
import com.taiwan.dao.employee.EmployeeDAO_interface;
import com.taiwan.dao.employee.impl.EmployeeJDBCDAO;

public class CustCouponService {

	private CustCoupon_interface dao;

	public CustCouponService() {
		dao = new CustCouponJDBCDao();
	}

	// 新增
	public CustCoupon addCustCoupon(CustCoupon obj) {

		CustCoupon custCouponVO = new CustCoupon();

		custCouponVO.setCustId(1);
		custCouponVO.setCopId(2);
		custCouponVO.setDiscount(3);
		dao.insertCustCoupon(custCouponVO);
//		System.out.println(custCouponVO);

		return custCouponVO;
	}

//查詢bycustId
	public List<CustCoupon> queryCustCouponById(Integer custId) {
		return dao.queryCustCouponById(custId);
	}

	// 查詢某一個客人已使用(未使用)(已過期)的優惠券
	public List<CustCoupon> queryCustCouponUsedById(Integer custId, String status) {

		return dao.queryCustCouponUsedById(custId, status);
	}

	// 更改已使用優惠券的狀態值(對票券)
	public int updateCustCouponStatusByTktOrderId(Integer custId, Integer tktOrderId, String status) {

		return dao.updateCustCouponStatusByTktOrderId(custId, tktOrderId, status);

	}
	//搜尋全部
	public List<CustCoupon> getAll() {
		return dao.getAll();
	}
	
}

//	//更改已使用優惠券的狀態值(對訂房)
//	public int updateCustCouponStatusByRoomOrderId(Integer custId,Integer roomOrderId,String status);
//	//因使用期限已過而更改優惠券的狀態(未完成Join)
//	public int updateCustCouponStatusByEnddate(Integer custId,Timestamp enddate,String status);
