package com.mifi.service;

import com.mifi.dto.ResponceInfo;

public interface FlowGoldService {
	
	/**
	 * 3.1.4 我的流量币接口
	 * @param userId
	 * @return
	 */
	ResponceInfo flowGoldDetail(String userId);

	/**
	 * 3.1.5 提现账号编辑接口
	 * @param userId
	 * @param password
	 * @param real_name
	 * @param phone
	 * @param alipay
	 * @param qq
	 * @return
	 */
	ResponceInfo extractAccountInfo(String userId, String password, String real_name, String phone, String alipay, String qq);

	/**
	 * 3.1.6 请求流量币提现
	 * @param userId
	 * @return 用户流量币个数
	 */
	ResponceInfo extractPage(String userId);

	/**
	 * 3.1.7 流量币提现接口
	 * @param userId
	 * @param password
	 * @param extractNum
	 * @param explain
	 * @param qq
	 * @return
	 */
	ResponceInfo applyExtract(String userId, String password, String extractNum, String explain, String qq);
	
	/**
	 * 获取对应供应商提供的流量币兑换参数和用户剩余流量币个数
	 * @param supplier
	 * @return
	 */
	ResponceInfo getExchangeDetail(String userId, String phone);
	
	/**
	 * 流量币兑换流量接口
	 * @return
	 */
	ResponceInfo exchangeFlow(String phone, String supplyId, String flow, String flowgold);
	
	/**
	 * 流量币兑换流量兑换记录
	 * @return
	 */
	ResponceInfo exchangeFlowHistory(String phone);
	
}
