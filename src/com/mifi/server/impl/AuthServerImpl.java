package com.mifi.server.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mifi.dto.ResponceInfo;
import com.mifi.main.proxy.MifiServer;
import com.mifi.server.AuthServer;
import com.mifi.service.AuthService;

@MifiServer(name="authServer")
public class AuthServerImpl implements AuthServer {

	private static Logger log = Logger.getLogger(AuthServerImpl.class);
	@Autowired
	AuthService authService;
	@Override
	public String login(String username, String password, String regcity, String mac, String apIp, String apMac,
			String device) {
		log.info("登录验证 login 参数 username：" + username + " password:" + password + " regcity:" + regcity + " mac:" + mac
				+ " apIp:" + apIp + " apMac:" + apMac + " device:" + device);
		
		return authService.login(username, password, regcity, mac, apIp, apMac, device);
	}
	@Override
	public ResponceInfo loginMac(String phone, String password, String type, String apMac) {
		log.info("登录验证 login 参数 phone：" + phone + " password:" + password + " userType:" + type + " apMac:" + apMac );
		return authService.loginMac(phone, password, type, apMac);
	}
	@Override
	public ResponceInfo login(String phone, String password, String type, String apIp) {
		log.info("登录验证 login 参数 phone：" + phone + " password:" + password + " userType:" + type + " apIp:" + apIp );
		return authService.login(phone, password, type, apIp);
	}

}
