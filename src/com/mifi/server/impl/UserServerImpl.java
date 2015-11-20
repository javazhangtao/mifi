package com.mifi.server.impl;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mifi.dto.ResponceInfo;
import com.mifi.main.proxy.MifiServer;
import com.mifi.server.UserServer;
import com.mifi.service.UserService;

@MifiServer(name="userServer")
public class UserServerImpl implements UserServer {
	
	private static Logger log = Logger.getLogger(UserServerImpl.class);
	@Autowired
	UserService userService;

	@Override
	public ResponceInfo getUserInfo(String userId) {
		log.info("app获取用户基本信息接口 method:getUserInfo 参数：userId"+userId);
		return userService.getUserInfo(userId);
	}

	@Override
	public ResponceInfo editInfo(String userId, String nickname, String gender, String birth, String city,
			String cardid, String intro, String carnum, String drivingcard, String drivinglicense, File head) {
		log.info("app提交用户基本信息  method:editInfo 参数：userId" + userId + " nickname:" + nickname + " gender:" + gender
				+ " birth:" + birth + " city:" + city + " cardid:" + cardid + " intro:" + intro
				+ " carnum:" + carnum + " drivingcard:" + drivingcard + " drivinglicense:" + drivinglicense);
		return userService.editInfo(userId, nickname, gender, birth, city, cardid, intro, carnum, drivingcard, drivinglicense,head);
	}

	@Override
	public ResponceInfo login(String phone, String password) {
		log.info("app登录 method:login 参数 phone："+phone+" password:"+password);
		return userService.login(phone, password);
	}

	@Override
	public ResponceInfo top(String userId, String apMac, String driverId) {
		log.info("app点赞 method:top 参数 userId："+userId+" apMac:"+apMac+" deriverId:"+driverId);
		return userService.top(userId, apMac, driverId);
	}

	@Override
	public ResponceInfo myHomeInfo(String userId) {
		log.info("获取用户“我”首页数据 method:myHomeInfo 参数 userId："+userId);
		return userService.myHomeInfo(userId);
	}

	@Override
	public ResponceInfo homeInfo(String userId) {
		log.info("获取用户首页数据 method:homeInfo 参数 userId："+userId);
		return userService.homeInfo(userId);
	}

	@Override
	public ResponceInfo userCheckIn(String userId) {
		log.info("用户签到 method:userCheckIn 参数 userId："+userId);
		return userService.userCheckIn(userId);
	}

}
