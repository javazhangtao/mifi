package com.mifi.service;

import com.mifi.dto.ResponceInfo;

public interface DriverService {

	/**
	 * 1.1	司机版登录接口
	 * @param phone
	 * @param password
	 * @return
	 */
	ResponceInfo login(String phone, String password);
	
	/**
	 * 1.2	司机版我的收益
	 * @param userId
	 * @return
	 */
	ResponceInfo cashDetail(String userId);
	
	/**
	 * 1.3	司机版我的设备推广信息
	 * @param userId
	 * @return
	 */
	ResponceInfo deviceGeneralizeDetail(String userId);
	
	/**
	 * 1.4	司机版我的设备流量信息接口
	 * @param userId
	 * @return
	 */
	ResponceInfo deviceFlowDetail(String userId);
	
	/**
	 * 1.5	司机版绑定手机号
	 * @param phone 输入手机号
	 * @param userMac 用户手机mac
	 * @param apMac 设备mac
	 * @return
	 */
	ResponceInfo bindPhone(String phone, String userMac, String apMac);
	
	/**
	 * 1.6	司机版验证手机号是否绑定接口
	 * @param phone
	 * @return
	 */
	ResponceInfo checkPhoneBind(String phone);
	
	/**
	 * 1.7	司机点赞信息接口
	 * @param userId
	 * @return
	 */
	ResponceInfo topInfo(String userId);
	
	/**
	 * 1.8	司机开始赚钱接口
	 * @param userId
	 * @param apMac
	 * @return
	 */
	ResponceInfo startJob(String userId, String apMac);
	
	/**
	 * 1.9	司机结束赚钱
	 * @param userId
	 * @param apMac
	 * @return
	 */
	ResponceInfo stopJob(String userId, String apMac);

	/**
	 * 1.10	司机版解除绑定手机号
	 * @param phone 输入手机号
	 * @param userMac 用户手机mac
	 * @param apMac 设备mac
	 * @return
	 */
	ResponceInfo unBindPhone(String phone, String userMac, String apMac);
	
	/**
	 * 1.12 分页查询收益详情
	 * @param userId
	 * @return
	 */
	ResponceInfo findCashDetailByPage(String userId,String date);

	/**
	 * 1.11 司机版首页
	 * @param userId
	 * @return
	 */
	ResponceInfo index(String userId);
	
	/**
	 * 4.3	IP版司机开始赚钱接口
	 * @param userId
	 * @param apMac
	 * @return
	 */
	ResponceInfo startJobToIp(String userId, String apIp);
	
	/**
	 * 4.4	IP版司机结束赚钱
	 * @param userId
	 * @param apMac
	 * @return
	 */
	ResponceInfo stopJobToIp(String userId, String apIp);
	
}
