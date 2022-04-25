package com.taiwan.service;

import java.util.List;

import com.taiwan.beans.FollowVO;


public  interface FollowService {
	

	
	/**********************新增關注**********************/
	public FollowVO addFollow(Integer custId,Integer cmpId) ;
		
	
	/**********************查詢關注**********************/

	public List<FollowVO> searchAllFollow(Integer custId);
		
	
	/**********************刪除關注**********************/

	public void deletFollow(Integer custId,Integer cmpId) ;
	
	
}
