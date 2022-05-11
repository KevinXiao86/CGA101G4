package com.taiwan.service.coupon.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiwan.beans.CouponVO;
import com.taiwan.beans.CustCoupon;
import com.taiwan.service.coupon.CouponService;

import mybatis.mapper.CouponMapper;
import mybatis.mapper.CustCouponMapper;

@Service
public class CouponServiceImpl implements CouponService {

 @Autowired
 private CouponMapper mapper;
 @Autowired
 private CustCouponMapper custCouponMapper;

 @Transactional
 @Override
 public boolean addCoupon(String copName, String introduce, Integer discount, Timestamp startdate, Timestamp enddate,
   String img) {
  CouponVO couponVO = new CouponVO();
  couponVO.setCopName(copName);
  couponVO.setIntroduce(introduce);
  couponVO.setDiscount(discount);
  couponVO.setStartdate(startdate);
  couponVO.setEnddate(enddate);
  couponVO.setImg(img);

  return mapper.insert(couponVO) > 0;
 }

 @Transactional(readOnly = true)
 @Override
 public List<CouponVO> findAll() {
  return mapper.queryAll();
 }

 @Transactional
 @Override
 public boolean delete(Integer copId) {
  return mapper.delete(copId) > 0;
 }

 @Transactional
 @Override
 public boolean update(Integer copId, String copName, String introduce, Integer discount, Timestamp startdate,
   Timestamp enddate, String img) {
  CouponVO couponVO = new CouponVO();
  couponVO.setCopId(copId);
  couponVO.setCopName(copName);
  couponVO.setIntroduce(introduce);
  couponVO.setDiscount(discount);
  couponVO.setStartdate(startdate);
  couponVO.setEnddate(enddate);
  couponVO.setImg(img);

  return mapper.update(couponVO) > 0;
 }

 @Transactional
 @Override
 public CouponVO findById(Integer copId) {
  return mapper.queryById(copId);
 }
 @Transactional
 @Override
 public boolean updateStatus(Integer copId, String status) {
  return mapper.updateStatus(copId, status)>0;
 }

 @Override
 public List<CouponVO> selectByTitle(String copName) {
  return mapper.queryCouponByCopName(copName);
 }

 @Override
 public List<CouponVO> selectByStatus(String status) {
  return mapper.queryCouponByStatus(status);
 }

@Override
public boolean addCustCoupon(Integer copId, Integer custId, Integer discount) {
	CustCoupon custCoupon=new CustCoupon();
	custCoupon.setCopId(copId);
	custCoupon.setCustId(custId);
	custCoupon.setDiscount(discount);
	return custCouponMapper.insertCustCoupon(custCoupon)>0;
}

}
