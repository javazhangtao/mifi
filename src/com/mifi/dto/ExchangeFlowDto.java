package com.mifi.dto;

import java.util.List;
import java.util.Map;

public class ExchangeFlowDto {

	Long balance;
	String mobileArea;
	String mobileType;
	Integer mobileTypeId;
	List<Map<String, String>> detail;

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public List<Map<String, String>> getDetail() {
		return detail;
	}

	public void setDetail(List<Map<String, String>> detail) {
		this.detail = detail;
	}

	public String getMobileArea() {
		return mobileArea;
	}

	public void setMobileArea(String mobileArea) {
		this.mobileArea = mobileArea;
	}

	public String getMobileType() {
		return mobileType;
	}

	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}

	public Integer getMobileTypeId() {
		return mobileTypeId;
	}

	public void setMobileTypeId(Integer mobileTypeId) {
		this.mobileTypeId = mobileTypeId;
	}

}
