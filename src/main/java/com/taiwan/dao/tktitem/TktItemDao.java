package com.taiwan.dao.tktitem;

import java.util.List;

import com.taiwan.beans.TktItem;


public interface TktItemDao {
	//新增一筆票券訂單明細
	public int insertTktItem(TktItem obj);
	//根據訂單Id取出所有的票券訂單明細
	public List<TktItem> queryTktItemByTktOrderId(Integer tktOrderId);
	//根據票券訂單id及票券id來更改票券訂單明細的使用數量
	public int updateTktItemUsedByTKTId(Integer tktOrderId,Integer tktId,Integer used);
	//根據票券訂單id及票券id來對票券評論及評分
	public int updateTktItemScoreContentByTktId(Integer tktOrderId,Integer tktId,Integer score,String content);
	//查詢所有相同票券的評論
	public List<String> queryAllTktItemConTentByTktId(Integer tktId);
	//查詢5筆票券評論依照評價高低排列
	public List<String> queryFiveTktItemContentByScore(Integer tktId);
	//查詢所有相同票券id的總評分
	public int queryTktItemTllScoreByTktId(Integer tktId);
	//查詢所有相同票券的總評分人數
	public int queryTktItemTtlPeopleByTktId(Integer tktId);
		
	
	

}

