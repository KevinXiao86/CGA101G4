package com.taiwan.dao.tktImg;

import java.util.List;

import com.taiwan.beans.TktImgVO;
import com.taiwan.dao.Dao;


public interface TktImgDAO_interface extends Dao<TktImgVO, Integer>{
	
//	//新增
//	public int insert(TktImgVO tktImgVO);
//	
//	//修改
//	public int update(TktImgVO tktImgVO);
//	
//	//刪除
//	public int delete(Integer tktImgId);
//	
//	//根據id查詢
//	public TktImgVO queryById(Integer tktImgId); 	
//	
//	//搜尋全部
//	public List<TktImgVO> queryAll();
	
    //根據票券id查詢所有照片
	public List<TktImgVO> queryByTktId(Integer tktId);
	
}
