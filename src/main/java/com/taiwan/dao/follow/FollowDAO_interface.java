package com.taiwan.dao.follow;

import java.util.List;

import com.taiwan.beans.FollowVO;


public interface FollowDAO_interface {
	// �ھڷ|���s���d�����`
	public List<FollowVO> queryFollowByCust_id(Integer cust_id);

	// �s�W�|�����`
	public void insertFollow(FollowVO followVO);

	// �����|�����`
	public void deleteFollow(Integer cust_id, Integer cmp_id);
}
