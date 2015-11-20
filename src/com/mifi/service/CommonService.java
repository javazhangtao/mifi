package com.mifi.service;

import com.mifi.dto.ResponceInfo;

/**
 * 公用接口
 * @author Administrator
 *
 */
public interface CommonService {

	/**
	 * 3.1.1 获取验证码接口
	 * @param phone
	 * @return
	 */
	ResponceInfo authCode(String phone);
	
	/**
	 * 3.1.2 注册接口
	 * @param phone 手机号
	 * @param authcode 验证码
	 * @param password 密码
	 * @param invitecode 邀请码
	 * @return
	 */
	ResponceInfo register(String phone, String authcode, String password, String invitecode);
	
	/**
	 * 3.1.3 找回密码接口
	 * @param phone 手机号
	 * @param authcode 验证码
	 * @param password 密码
	 * @return
	 */
	ResponceInfo recoverPass(String phone, String authcode, String password);
	
	/**
	 * 3.1.4 我的流量币接口
	 * @param userId 用户id
	 * @return
	 */
	ResponceInfo flowDetail(String userId);
	
	/**
	 * 3.1.11 获取注册城市地址
	 * @return
	 */
	ResponceInfo city();
	
	ResponceInfo softwareInfo(String type);
	
	/**
	 * 修改密码获取验证码
	 * @param phone
	 * @return
	 */
	ResponceInfo authCodeForRecoverPass(String phone);
	
	/**
	 * 关注公众号奖励流量币
	 * @return
	 */
	ResponceInfo getUserAttention(String userId);
	
	/**
	 * 关注公众号奖励流量币
	 * @return
	 */
	ResponceInfo attentionPublic(String phone);
	/**
	 * *****not use	我的收益
	 * @param userId
	 * @return
	 */
	ResponceInfo cashDetail(String userId);
	
	/**
	 * 3.1.14 消息详情
	 * @param userId
	 * @return
	 */
	ResponceInfo messages(String userId);
	
	/**
	 * 无条件获取验证码接口
	 * @param phone
	 * @return
	 */
	ResponceInfo authCodeNoReq(String phone);
}
