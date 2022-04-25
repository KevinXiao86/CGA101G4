package com.taiwan.dao.follow;

import java.util.List;

import com.taiwan.beans.FollowVO;


public interface FollowDAO_interface {
	//查詢
	public List<FollowVO> queryFollowByCustId(Integer custId);
	//新增
	public void insertFollow(FollowVO followVO);
	//刪除
	public void deleteFollow(Integer custId, Integer cmpId);
}
