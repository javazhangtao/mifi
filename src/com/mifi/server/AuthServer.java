package com.mifi.server;

import com.mifi.dto.ResponceInfo;

public interface AuthServer {

	/**
	 * 
	 * @param username
	 * @param password
	 * @param regcity
	 * @param mac
	 * @param apIp
	 * @param apMac
	 * @param device
	 * @return
	 */
	String login(String username, String password, String regcity, String mac, String apIp, String apMac, String device);
	
	/**
	 * 测试一键登录上网 mac版
	 * @author xuxk
	 * @param user
	 * @param password
	 * @param userType
	 * @param appMac
	 * @return
	 */
	ResponceInfo loginMac(String phone, String password, String type, String apMac);
	/**
	 * 测试一键登录上网 ip版
	 * @author xuxk
	 * @param user
	 * @param password
	 * @param userType
	 * @param appMac
	 * @return
	 */
	ResponceInfo login(String phone, String password, String type, String apIp);
}
