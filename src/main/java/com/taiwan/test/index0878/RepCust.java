package com.taiwan.test.index0878;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.RepCustVO;
import com.taiwan.service.impl.RepCustServiceImpl;

public class RepCust {
	public static void main(String[] args) {
		RepCustServiceImpl test=new RepCustServiceImpl();
//		test.addRepCust(10000, 20000, "酒後鬧事");
		RepCustVO repCustVO=new RepCustVO();
		repCustVO=test.searchRepCustById(1);
		String start="2022-04-06 18:52:54";
		String end="2022-04-08 18:52:54";
//		System.out.println(repCustVO.getRepCustId());
		List<RepCustVO> list=null;
//		list=test.searchRepCustByCustId(10000);
//		list=test.searchRepCustByEmpId(30000);
//		list=test.searchRepCustByCmpId(20011);
//		list=test.searchRepCustByDate(Timestamp.valueOf(start),Timestamp.valueOf(end));
//		list=test.searchRepCustByStatus("未處理");
//		list=test.searchAllRepCust();
//		repCustVO=test.doRepCust(1, 30000, "檢舉失敗", "不處理");
		for (RepCustVO repCust : list) {
			System.out.println(repCust.getRepCustId());
		}
		
	}
}
