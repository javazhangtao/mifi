package com.mifi.server.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.mifi.dto.ResponceInfo;
import com.mifi.main.proxy.MifiServer;
import com.mifi.server.CommonServer;
import com.mifi.service.CommonService;

@MifiServer(name = "commonServer")
public class CommonServerImp implements CommonServer {

	private static Logger log = Logger.getLogger(CommonServerImp.class);

	@Autowired
	CommonService commonService;

	@Override
	public ResponceInfo authCode(String phone) {
		log.info("获取验证码接口 method:authCode 参数phone:" + phone);
		return commonService.authCode(phone);
	}

	@Override
	public ResponceInfo register(String phone, String authcode, String password, String invitecode) {
		log.info("注册接口 method:register 参数phone:" + phone + ",authcode:" + authcode + ",password:" + password
				+ ",invitecode:" + invitecode);
		return commonService.register(phone, authcode, password, invitecode);
	}

	@Override
	public ResponceInfo recoverPass(String phone, String authcode, String password) {
		log.info("忘记密码接口 method:recoverPass 参数 phone:" + phone + ", authcode:" + authcode + ", password" + password);
		return commonService.recoverPass(phone, authcode, password);
	}

	@Override
	public ResponceInfo flowDetail(String userId) {
		log.info("app 我的流量币接口 method:flowDetail 参数 userId："+userId);
		return commonService.flowDetail(userId);
	}

	@Override
	public ResponceInfo city() {
		log.info("app 获取注册城市列表");
		return commonService.city();
	}

	@Override
	public ResponceInfo softwareInfo(String type) {
		log.info("app 获取最新版本信息");
		return commonService.softwareInfo(type);
	}

	@Override
	public ResponceInfo attentionPublic(String phone) {
		log.info("app 关注公众号");
		return commonService.attentionPublic(phone);
	}

	@Override
	public ResponceInfo authCodeForRecoverPass(String phone) {
		log.info("app 重置密码时获取验证码");
		return commonService.authCodeForRecoverPass(phone);
	}

	@Override
	public ResponceInfo getUserAttention(String userId) {
		log.info("app 获取所有公众号信息");
		return commonService.getUserAttention(userId);
	}

	@Override
	public ResponceInfo cashDetail(String userId) {
		log.info("app 我的收益 method:cashDetail 参数 userId："+userId);
		return commonService.cashDetail(userId);
	}

	@Override
	public ResponceInfo messages(String userId) {
		log.info("app 通用接口获取一条的消息 method:messages 参数 userID:"+userId);
		return commonService.messages(userId);
	}

	@Override
	public ResponceInfo authCodeNoReq(String phone) {
		log.info("app 通用无条件获取验证码");
		return commonService.authCodeNoReq(phone);
	}

}
