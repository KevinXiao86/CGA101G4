package com.taiwan.service.useFriend.impl;

import java.util.List;

import com.taiwan.beans.UseFriendVO;
import com.taiwan.dao.useFriend.UseFriendDao_interface;
import com.taiwan.dao.useFriend.impl.UseFriendJNDIDAO;
import com.taiwan.service.useFriend.UseFriendService;

public class UseFriendServiceImpl implements UseFriendService {
	private UseFriendDao_interface dao;

	public UseFriendServiceImpl() {
		dao = new UseFriendJNDIDAO();
	}

	@Override
	public void setUseFriend(Integer custId, String friend) {
		dao.setUseFriend(custId, friend);
	}

	@Override
	public void updateUseFriend(Integer useFriendId, String friend) {
		dao.updateUseFriend(useFriendId, friend);
	}

	@Override
	public List<UseFriendVO> getUseFriend(Integer custId) {
		return dao.getUseFriend(custId);
	}

	@Override
	public void deleteUseFriend(Integer useFriendId) {
		dao.deleteUseFriend(useFriendId);
	}
}
