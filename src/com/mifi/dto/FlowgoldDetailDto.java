package com.mifi.dto;

import java.util.ArrayList;
import java.util.List;

public class FlowgoldDetailDto {

	private String datetime;
	private List<FlowgoldDayDetailDto> daydetails = new ArrayList<FlowgoldDayDetailDto>();
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public List<FlowgoldDayDetailDto> getDaydetails() {
		return daydetails;
	}
	public void setDaydetails(List<FlowgoldDayDetailDto> daydetails) {
		this.daydetails = daydetails;
	}
	
}
