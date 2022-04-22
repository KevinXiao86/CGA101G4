package com.taiwan.dao;

import java.util.List;

public interface Dao<T, I> {
	
	 List<T> queryAll();
	
	 T queryById(I id);
	
	 int insert(T obj);
	
	 int update(T obj);
	
	 int delete(I id);

}