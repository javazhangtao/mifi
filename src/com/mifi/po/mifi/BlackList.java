package com.mifi.po.mifi;

import com.mifi.po.GenericEntity;

public class BlackList extends GenericEntity {

	private static final long serialVersionUID = 6251351801336246535L;
	
	private String ip;
	private String mac;
	private String phone;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
