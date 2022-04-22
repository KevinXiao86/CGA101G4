package com.taiwan.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiwan.beans.CouponVO;
import com.taiwan.service.CouponService;

import mybatis.mapper.CouponMapper;

@Service
public class CouponServiceImpl implements CouponService{
	
	@Autowired
	private CouponMapper mapper;

	@Override
	public boolean addCoupon(String copName, String introduce, Integer discount, Timestamp startdate, Timestamp enddate,
			String img) {
		CouponVO couponVO=new CouponVO();
		couponVO.setCopName(copName);
		couponVO.setIntroduce(introduce);
		couponVO.setDiscount(discount);
		couponVO.setStartdate(startdate);
		couponVO.setEnddate(enddate);
		couponVO.setImg(img);
		
		return mapper.insert(couponVO)>0;
	}

	@Override
	public List<CouponVO> findAll() {
		return mapper.queryAll();
	}

}
