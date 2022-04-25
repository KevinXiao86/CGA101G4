package com.taiwan.test.index0878;

import java.util.List;

import com.taiwan.beans.FollowVO;
import com.taiwan.service.impl.FollowServiceImpl;

public class Follow {
	public static void main(String[] args) {
		FollowServiceImpl test=new FollowServiceImpl();
		/***************************找自己關注的廠商*****************************/
		List<FollowVO> list =test.searchAllFollow(10000);
		for (FollowVO followVO : list) {
			System.out.println(followVO.getCustId());
			System.out.println(followVO.getCmpId());
		}
		/***************************新增關注的廠商*****************************/

//		FollowVO addTest=test.addFollow(10000, 20012);
//		String out=addTest.getCustId().toString()+" "+addTest.getCmpId().toString();
//		System.out.println(out);
		/***************************刪除關注的廠商*****************************/

//		test.deletFollow(10000, 20012);
		
		
		
	}
}
