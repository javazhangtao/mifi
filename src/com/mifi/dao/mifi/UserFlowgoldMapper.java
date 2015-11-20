package com.mifi.dao.mifi;

import com.mifi.po.mifi.UserFlowgold;

public interface UserFlowgoldMapper {

	/**
	 * 通过userID获取用户的流量币信息
	 * @param userId
	 * @return
	 */
	UserFlowgold getUserFlowgold(Long userId);
	
	/**
	 * 更新
	 * @return
	 */
	int update(UserFlowgold userFlowgold);
	
	/**
	 * 插入
	 * @return
	 */
	int insert(UserFlowgold userFlowgold);
	
}
