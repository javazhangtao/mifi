package com.mifi.dao;

import java.io.Serializable;
import java.util.List;

import com.mifi.po.GenericEntity;

public interface GenericDao<T extends GenericEntity,ID extends Serializable> {

	
	ID insert(String namedSql , T t);
	
	T insert(T t);
	
	T findById(ID id);
	
	List<T> findAll();
	
	T update(T t);
	
	boolean deleteById(ID id);
	
	boolean batchInsert(Iterable<T> iterable);
	
}
