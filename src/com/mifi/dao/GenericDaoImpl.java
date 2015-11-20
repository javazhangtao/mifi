package com.mifi.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.mifi.po.GenericEntity;

@Repository
public class GenericDaoImpl<T extends GenericEntity,ID extends Serializable> implements GenericDao<T, ID> {
	
//	@Autowired
//	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;

	@Override
	public T insert(T t) {
		return null ;
	}

	@Override
	public T findById(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T update(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteById(ID id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean batchInsert(Iterable<T> iterable) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ID insert(String namedSql, T t) {
		SqlParameterSource sqlParameterSource=new BeanPropertySqlParameterSource(t);
		return null;
	}

}
