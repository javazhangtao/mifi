package com.mifi.server;

import com.mifi.dto.ResponceInfo;

public interface CommonServer {

	/**
	 * 3.1.1 获取验证码接口
	 * @param phone
	 * @return
	 */
	ResponceInfo authCode(String phone);
	
	/**
	 * 3.1.2 注册接口
	 * @param phone
	 * @param authcode
	 * @param password
	 * @param invitecode
	 * @return
	 */
	ResponceInfo register(String phone, String authcode, String password, String invitecode);
	
	/**
	 * 3.1.3 找回密码接口
	 * @param phone
	 * @param authcode
	 * @param password
	 * @return
	 */
	ResponceInfo recoverPass(String phone, String authcode, String password);
	
	/**
	 * 3.1.4 我的流量币接口
	 * @param userId
	 * @return
	 */
	ResponceInfo flowDetail(String userId);
	
	/**
	 * 3.1.11 获取注册城市地址
	 * @return
	 */
	ResponceInfo city();
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	ResponceInfo softwareInfo(String type);
	
	/**
	 * 关注公众号奖励流量币
	 * @return
	 */
	ResponceInfo attentionPublic(String phone);
	
	/**
	 * 关注公众号奖励流量币
	 * @return
	 */
	ResponceInfo getUserAttention(String userId);
	
	/**
	 * 修改密码获取验证码
	 * @param phone
	 * @return
	 */
	ResponceInfo authCodeForRecoverPass(String phone);
	/**
	 * ****	我的收益
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
