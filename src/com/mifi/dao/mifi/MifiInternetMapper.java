package com.mifi.dao.mifi;

import com.mifi.po.mifi.MifiInternet;

public interface MifiInternetMapper {

	int insert(MifiInternet mifiInternet);
	
	MifiInternet getMifiInternetByIp(String ip);
	
	int update(MifiInternet mifiInternet);
}
