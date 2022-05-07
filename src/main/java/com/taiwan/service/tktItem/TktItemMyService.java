package com.taiwan.service.tktItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiwan.beans.TktItem;

import mybatis.mapper.TktItemMapper;

@Service
public class TktItemMyService {

	@Autowired
	TktItemMapper mapper;

	@Transactional
	public List<TktItem> selectById(Integer tktOrderId) {
		List<TktItem> tktItems = mapper.queryTktItemByTktOrderId(tktOrderId);
		return tktItems;
	}
}