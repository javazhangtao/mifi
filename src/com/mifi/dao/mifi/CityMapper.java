package com.mifi.dao.mifi;

import java.util.List;

import com.mifi.po.mifi.City;

public interface CityMapper {

	List<City> selectAll();
	String getCityById(Long id);
}
