package com.taiwan.service;

import java.util.List;

import com.taiwan.beans.TktImgVO;
import com.taiwan.dao.tktImg.TktImgDAO_interface;
import com.taiwan.dao.tktImg.impl.TktImgJDBCDAO;

public class TktImgService {
	
	private TktImgDAO_interface dao;
	
	public TktImgService (){
		dao = new TktImgJDBCDAO();
	}
	
	public TktImgVO addTktImg(String img, Integer tktId) {
		
		TktImgVO tktImgVO = new TktImgVO();
		
		tktImgVO.setImg(img);
		tktImgVO.setTktId(tktId);
		dao.insert(tktImgVO);
		
		return tktImgVO;
	}
	
	public TktImgVO updateTktImg(Integer tktImgId, String img, Integer tktId) {
		
		TktImgVO tktImgVO = new TktImgVO();
		
		tktImgVO.setTktImgId(tktImgId);
		tktImgVO.setImg(img);
		tktImgVO.setTktId(tktId);
		dao.update(tktImgVO);
		
		return tktImgVO;
	}
	
	public void deleteTktImg(Integer tktImgId) {
		dao.delete(tktImgId);
	}
	
	public TktImgVO getOneTktImg(Integer tktImgId) {
		return dao.queryById(tktImgId);
	}
	
	public List<TktImgVO> getAll(){
		return dao.queryAll();
	}
	
	public List<TktImgVO> getByTktId(Integer tktId){
		return dao.queryByTktId(tktId);
	}
}
