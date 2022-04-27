package com.taiwan.service.faq.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiwan.beans.FaqVO;
import com.taiwan.service.faq.faqService;

import mybatis.mapper.FaqMapper;

@Service
public class faqServiceImpl implements faqService {

	@Autowired
	FaqMapper mapper;

	@Transactional
	@Override
	public List<FaqVO> findAll() {
		return mapper.queryAll();
	}

	@Transactional
	@Override
	public boolean addFaq(String title, String content) {
		FaqVO faqVO=new FaqVO();
		faqVO.setTitle(title);
		faqVO.setContent(content);
		return mapper.insert(faqVO)>0;
	}

	@Transactional
	@Override
	public boolean update(Integer faqId, String title, String content) {
		FaqVO faqVO=new FaqVO();
		faqVO.setFaqId(faqId);
		faqVO.setTitle(title);
		faqVO.setContent(content);
		return mapper.update(faqVO)>0;
	}

	@Transactional
	@Override
	public FaqVO findById(Integer faqId) {
		FaqVO faqVO=mapper.queryById(faqId);
		return faqVO;
	}

	@Transactional
	@Override
	public boolean delete(Integer faqId) {
		return mapper.delete(faqId)>0;
	}

	@Override
	public List<FaqVO> findByTitle(String title) {
		List<FaqVO> list=mapper.queryFaqByTitle(title);
		return list;
	}

}
