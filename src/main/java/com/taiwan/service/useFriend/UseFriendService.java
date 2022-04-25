package com.taiwan.service.useFriend;

import java.util.List;

import com.taiwan.beans.UseFriendVO;

public interface UseFriendService {
	public void setUseFriend(Integer custId, String friend);

	public void updateUseFriend(Integer useFriendId, String friend);

	public List<UseFriendVO> getUseFriend(Integer custId);

	public void deleteUseFriend(Integer useFriendId);
}
