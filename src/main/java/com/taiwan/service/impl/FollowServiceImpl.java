package com.taiwan.service.impl;

import java.util.List;

import com.taiwan.beans.FollowVO;
import com.taiwan.dao.follow.FollowDAO_interface;
import com.taiwan.dao.follow.impl.*;
import com.taiwan.service.FollowService;


public class FollowServiceImpl implements FollowService {
	private FollowDAO_interface dao;
	/**********************無參數建構子，利用多型創DAO物件**********************/

	public FollowServiceImpl() {
		dao=new FollowDAO();
	}
	/**********************新增關注**********************/
	public FollowVO addFollow(Integer custId,Integer cmpId) {
		FollowVO followVO=new FollowVO();
		followVO.setCustId(custId);
		followVO.setCmpId(cmpId);
		dao.insertFollow(followVO);
		return followVO;
	}
	
	/**********************查詢關注**********************/
	public List<FollowVO> searchAllFollow(Integer custId){
		
	return dao.queryFollowByCustId(custId);
	}
	
	/**********************刪除關注**********************/

	public void deletFollow(Integer custId,Integer cmpId) {
	dao.deleteFollow(custId, cmpId);
	}
	
}
