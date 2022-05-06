package com.taiwan.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.CustCoupon;
import com.taiwan.beans.RoomItemVO;
import com.taiwan.beans.RoomOrderVO;
import com.taiwan.dao.roomOrder.RoomOrderDAO_interface;
import com.taiwan.dao.roomOrder.impl.RoomOrderDAO;
import com.taiwan.service.RoomOrderService;
import com.taiwan.service.coupon.CouponService;
import com.taiwan.service.coupon.impl.CouponServiceImpl;
import com.taiwan.service.roomItem.impl.RoomItemServiceImpl;
import com.taiwan.test.news.newsTest;
import com.taiwan.utils.ControllerUtil;

public class RoomOrderServiceImpl implements RoomOrderService{
	private RoomOrderDAO_interface dao;

	public RoomOrderServiceImpl() {
		dao = new RoomOrderDAO();
	}
//沒用優惠券
	@Override
	public RoomOrderVO addRoomOrder(Integer custId,Timestamp checkin,Timestamp checkout ,Integer roomId,Integer amount,Integer price,Integer cmpId) {
		long days=(checkout.getTime()-checkin.getTime())/(1000*60*60*24);
		RoomOrderVO roomOrderVO=new RoomOrderVO();
		roomOrderVO.setCustId(custId);
		roomOrderVO.setRoomOrderCheckinDate(checkin);
		roomOrderVO.setRoomOrderCheckoutDate(checkout);
		roomOrderVO.setRoomOrderPrice(price*amount*(int)days);
		roomOrderVO.setRoomOrderTotalPrice(price*amount*(int)days);
		roomOrderVO.setCmpId(cmpId);
//		int roomOrderId=dao.insert(roomOrderVO);
		
		/***************新增明細****************************/
//		RoomItemServiceImpl roomItem=new RoomItemServiceImpl();
//		//(Integer roomId, Integer roomOrderId, Integer roomAmount, Integer roomPrice)
//		roomItem.addRoomItem(roomId, roomOrderId, amount, price);
		RoomItemVO roomItemVO = new RoomItemVO();
		roomItemVO.setRoomId(roomId);
		roomItemVO.setRoomItemAmount(amount);
		roomItemVO.setRoomItemPrice(price);
		dao.insert(roomOrderVO, roomItemVO);
		
		
		return roomOrderVO;
	}
	/****************有用優惠券*******************/
	@Override
	public RoomOrderVO addRoomOrder(Integer custId,Timestamp checkin,Timestamp checkout ,Integer roomId,Integer amount,Integer price,Integer cmpId,Integer custCopId) {
		CustCouponServiceImpl12 custCouponServiceImpl12=new CustCouponServiceImpl12();
		CustCoupon custCouponVO=custCouponServiceImpl12.searchCustCouponId(custCopId);
		Integer copId=custCouponVO.getCopId();
		CouponService couponServiceImpl = ControllerUtil.getBean(CouponService.class);
		int minusPrice=couponServiceImpl.findById(copId).getDiscount();
		long days=(checkout.getTime()-checkin.getTime())/(1000*60*60*24);
		RoomOrderVO roomOrderVO=new RoomOrderVO();
		roomOrderVO.setCustId(custId);
		roomOrderVO.setRoomOrderCheckinDate(checkin);
		roomOrderVO.setRoomOrderCheckoutDate(checkout);
		roomOrderVO.setRoomOrderPrice(price*amount*(int)days);
		roomOrderVO.setRoomOrderTotalPrice(price*amount*(int)days-minusPrice);
		roomOrderVO.setCmpId(cmpId);
		roomOrderVO.setCustCopId(custCopId);
//		int roomOrderId=dao.insert(roomOrderVO);
		
		/***************新增明細****************************/
//		RoomItemServiceImpl roomItem=new RoomItemServiceImpl();
//		//(Integer roomId, Integer roomOrderId, Integer roomAmount, Integer roomPrice)
//		roomItem.addRoomItem(roomId, roomOrderId, amount, price);
		RoomItemVO roomItemVO = new RoomItemVO();
		roomItemVO.setRoomId(roomId);
		roomItemVO.setRoomItemAmount(amount);
		roomItemVO.setRoomItemPrice(price);
		dao.insert2(roomOrderVO, roomItemVO);
		
		
		return roomOrderVO;}
	@Override
	public RoomOrderVO chancelRoomOrder(Integer roomOrderId,String reason) {
		RoomOrderVO roomOrderVO=new RoomOrderVO();
		roomOrderVO.setRoomOrderId(roomOrderId);
		roomOrderVO.setRoomOrderCancel(reason);
		
		dao.cancel(roomOrderVO);
		return roomOrderVO;
	}

	@Override
	public List<RoomOrderVO> searchRoomOrderbyCheckin(Timestamp checkinDate) {
		return dao.queryRoomOrderByRoomOrderCheckinDate(checkinDate);
	}

	@Override
	public List<RoomOrderVO> searchRoomOrderbyCheckout(Timestamp checkoutDate) {
		return dao.queryRoomOrderByRoomOrderCheckoutDate(checkoutDate);
	}

	@Override
	public List<RoomOrderVO> searchRoomOrderbyOrderDate(Timestamp startDate, Timestamp endDate) {
		return dao.queryRoomOrderByRoomOrderDate(startDate, endDate);
	}

	@Override
	public List<RoomOrderVO> searchRoomOrderbystatus(String status) {
		return dao.queryRoomOrderByRoomOrderStatus(status);
	}

	@Override
	public List<RoomOrderVO> searchRoomOrderbyCustId(Integer custId) {
		return dao.queryRoomOrderByCustId(custId);
	}
	@Override
	public RoomOrderVO searchRoomOrderbyRoomOrderId(Integer roomOrderId) {
		return dao.queryRoomOrderByRoomOrderId(roomOrderId);
	}
}
