package com.mifi.server.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mifi.dto.ResponceInfo;
import com.mifi.main.proxy.MifiServer;
import com.mifi.server.DriverServer;
import com.mifi.service.DriverService;

@MifiServer(name="driverServer")
public class DriverServerImpl implements DriverServer{
	
	private static Logger log = Logger.getLogger(DriverServerImpl.class);

	@Autowired
	DriverService driverService;
	@Override
	public ResponceInfo login(String phone, String password) {
		log.info("app司机登录  method:login 参数 phone："+phone+" password:"+password);
		return driverService.login(phone, password);
	}

	@Override
	public ResponceInfo cashDetail(String userId) {
		log.info("app司机版我的收益 method:cashDetail 参数 userId："+userId);
		return driverService.cashDetail(userId);
	}

	@Override
	public ResponceInfo deviceGeneralizeDetail(String userId) {
		log.info("app司机版我的设备推广信息 method:deviceGeneralizeDetail 参数 userId："+userId);
		return driverService.deviceGeneralizeDetail(userId);
	}

	@Override
	public ResponceInfo deviceFlowDetail(String userId) {
		log.info("app司机版我的设备流量信息接口 method:deviceFlowDetail 参数 userId："+userId);
		return driverService.deviceFlowDetail(userId);
	}

	@Override
	public ResponceInfo bindPhone(String phone, String userMac, String apMac) {
		log.info("app司机版绑定手机号 method:bindPhone 参数 phone："+phone+" userMac:"+userMac+" apMac:"+apMac);
		return driverService.bindPhone(phone, userMac, apMac);
	}

	@Override
	public ResponceInfo checkPhoneBind(String phone) {
		log.info("app司机版验证手机号是否绑定接口 method:checkPhoneBind 参数 phone："+phone);
		return driverService.checkPhoneBind(phone);
	}

	@Override
	public ResponceInfo topInfo(String userId) {
		log.info("app司机点赞信息接口 method:topInfo 参数 userId："+userId);
		return driverService.topInfo(userId);
	}

	@Override
	public ResponceInfo startJob(String userId, String apMac) {
		log.info("app 司机开始赚钱接口 method:startJob 参数 userId："+userId+" apMac:"+apMac);
		return driverService.startJob(userId, apMac);
	}

	@Override
	public ResponceInfo stopJob(String userId, String apMac) {
		log.info("app司机结束赚钱 method:stopJob 参数 userId："+userId+" apMac:"+apMac);
		return driverService.stopJob(userId, apMac);
	}

	@Override
	public ResponceInfo unBindPhone(String phone, String userMac, String apMac) {
		log.info("app司机版绑定手机号 method:bindPhone 参数 phone："+phone+" userMac:"+userMac+" apMac:"+apMac);
		return driverService.unBindPhone(phone, userMac, apMac);
	}

	@Override
	public ResponceInfo index(String userId) {
		log.info("app司机首页 index 参数 userId："+userId);
		return driverService.index(userId);
	}

	@Override
	public ResponceInfo startJobToIp(String userId, String apIp) {
		log.info("app IP版 司机开始赚钱接口 method:startJobToIp 参数 userId："+userId+" apIp:"+apIp);
		return driverService.startJobToIp(userId, apIp);
	}

	@Override
	public ResponceInfo stopJobToIp(String userId, String apIp) {
		log.info("app IP版 司机结束赚钱接口 method:stopJobToIp 参数 userId："+userId+" apIp:"+apIp);
		return driverService.stopJobToIp(userId, apIp);
	}

	@Override
	public ResponceInfo findCashDetailByPage(String userId, String date) {
		log.info("app IP版 司机获取每天收益详情统计 method:findCashDetailByPage 参数 userId："+userId+" date:"+date);
		return driverService.findCashDetailByPage(userId, date);
	}


	
}
