package com.mifi.service;

import java.io.File;

import com.mifi.dto.ResponceInfo;

public interface UserService {

	/**
	 * 3.1.8 获取用户基本信息接口
	 * @param userId
	 * @return
	 */
	ResponceInfo getUserInfo(String userId);
	
	/**
	 * 3.1.9 提交用户基本信息
	 * @param userId //TODO 新添加的参数用户ID（待商议）
	 * @param nickname
	 * @param gender
	 * @param birth
	 * @param city
	 * @param cardid
	 * @param intro
	 * @param carnum
	 * @param drivingcard
	 * @param drivinglicense
	 * @return
	 */
	ResponceInfo editInfo(String userId, String nickname, String gender, String birth, String city, String cardid,
			String intro, String carnum, String drivingcard, String drivinglicense, File head);
	
	/**
	 * 2.1.1    请求登录
	 * @param phone
	 * @param password
	 * @return
	 */
	ResponceInfo login(String phone, String password);
	
	/**
	 * 2.2.1 请求 点赞
	 * @param userId
	 * @param apMac
	 * @param deriverId
	 * @return
	 */
	ResponceInfo top(String userId, String apMac, String driverId);
	
	/**
	 * 登录后"我"的首页显示数据
	 * @param userId
	 * @return
	 */
	ResponceInfo myHomeInfo(String userId);
	
	/**
	 * 用户版首页接口
	 * @param userId
	 * @return
	 */
	ResponceInfo homeInfo(String userId);
	
	/**
	 * 用户签到接口
	 * @param userId
	 * @return
	 */
	ResponceInfo userCheckIn(String userId);
	
}
