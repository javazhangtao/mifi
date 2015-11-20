package com.mifi.dto;

import com.mifi.po.mifi.City;

public class CityDto {

	private Long id;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CityDto(City city) {
		this.id = city.getId();
		this.name = city.getName();
	}
	public CityDto() {
		super();
	}
	
}
