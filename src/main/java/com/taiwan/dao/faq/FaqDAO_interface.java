package com.taiwan.dao.faq;


import java.util.List;

import com.taiwan.beans.FaqVO;
import com.taiwan.dao.Dao;

public interface FaqDAO_interface extends Dao<FaqVO, Integer>{

//	//新增一筆
//	public int insert (FaqVO faqVO);
//	
//	//根據id修改
//	public int update (FaqVO faqVO);
//	
//	//刪除一筆
//	public int delete (Integer faqId);
//	
//	//根據票券編號查詢一筆資料
//	public FaqVO queryById (Integer faqId);
//	
//	//搜尋全部
//	public List<FaqVO> queryAll();
	
	//根據標題查詢
	public List<FaqVO> queryFaqByTitle (String title);

}
