package com.taiwan.service.faq;

import java.util.List;

import com.taiwan.beans.FaqVO;

public interface FaqService {
	
	// 搜尋全部FAQ
	public List<FaqVO> findAll();
	
	//新增一筆FAQ
	public boolean addFaq(String title,String content);
	
	//更新一筆FAQ
	public boolean update(Integer faqId,String title,String content);
	
	//根據Id查詢FAQ
	public FaqVO findById(Integer faqId);
	
	//刪除一筆FAQ
	public boolean delete(Integer faqId);
	
	//根據標題搜尋FAQ
	public List<FaqVO> findByTitle(String title);

}