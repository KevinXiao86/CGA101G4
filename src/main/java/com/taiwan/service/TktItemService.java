package com.taiwan.service;

import java.sql.Connection;
import java.util.List;

import com.taiwan.beans.TktItem;
import com.taiwan.dao.tktitem.TktItemDao;
import com.taiwan.dao.tktitem.impl.TktItemJDBCDao;

public class TktItemService {
	private TktItemDao dao;

	public TktItemService() {
		dao = new TktItemJDBCDao();
	}

	// 新增一筆票券訂單明細
	public TktItem addTktItem(Integer tktId, Integer tktOrderId, Integer amount, Connection conn) {

		TktItem itemVO = new TktItem();
		itemVO.setTktId(tktId);
		itemVO.setTktOrderId(tktOrderId);
		itemVO.setAmount(amount);
		dao.insertTktItem(itemVO, conn);

		return itemVO;
	}

	// 根據訂單Id取出所有的票券訂單明細
	public List<TktItem> getTktItemByTktOrderId(Integer tktOrderId) {
		return dao.queryTktItemByTktOrderId(tktOrderId);
	}

	// 根據票券訂單id及票券id來更改票券訂單明細的使用數量??
	public void updateTktItemUsed(Integer tktOrderId, Integer tktId, Integer used) {
		dao.updateTktItemUsedByTKTId(tktOrderId, tktId, used);

	}

	// 根據票券訂單id及票券id來對票券評論及評分??
	public void updateTktItemScoreContentByTktId(Integer tktOrderId, Integer tktId, Integer score, String content) {
		dao.updateTktItemScoreContentByTktId(tktOrderId, tktId, score, content);
	}

	// 查詢所有相同票券的評論
	public List<TktItem> getAllTktItemConTent(Integer tktId) {
		return dao.queryAllTktItemConTentByTktId(tktId);
	}

	// 查詢5筆票券評論依照評價高低排列
	public List<TktItem> getFiveTktItemContent(Integer tktId) {
		return dao.queryFiveTktItemContentByScore(tktId);
	}

	// 查詢所有相同票券id的總評分
	public Integer getTktItemTllScore(Integer tktId) {
		return dao.queryTktItemTllScoreByTktId(tktId);
	}

	// 查詢所有相同票券的總評分人數
	public Integer getTktItemTtlPeople(Integer tktId) {
		return dao.queryTktItemTtlPeopleByTktId(tktId);
	}

	// test
//		public static void main(String[] args) {
//			TktItemService t = new TktItemService();
//			System.out.println(t.getTktItemTtlPeople(4));
//		}
}
