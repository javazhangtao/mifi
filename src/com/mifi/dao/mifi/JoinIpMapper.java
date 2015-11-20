package com.mifi.dao.mifi;

import org.apache.ibatis.annotations.Param;

import com.mifi.po.mifi.JoinIp;

public interface JoinIpMapper {

	int insert(JoinIp joinIp);
	
	/**
	 * 查询一个用户在当前设备连接的最后一条记录
	 * @param userId
	 * @param deviceId
	 * @param joinType 连接类型（0:断开连接；1：加入连接）
	 * @return
	 */
	JoinIp getUserJoinDetailByDeviceMac(@Param("joinUserId")Long joinUserId, @Param("deviceIp")String deviceIp, @Param("joinType")Integer joinType);
}
