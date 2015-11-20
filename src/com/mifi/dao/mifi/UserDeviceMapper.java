package com.mifi.dao.mifi;


import org.apache.ibatis.annotations.Param;

import com.mifi.po.mifi.UserDevice;

public interface UserDeviceMapper {
	
	int insert(UserDevice userDevice);
	
	int update(UserDevice userDevice);
	
	int getUserDeviceCountByDeviceMac(Long deviceId);

	/**
	 * 获取可用的UserDevice
	 * @param userId
	 * @return
	 */
	UserDevice getAvailableUserDevice(@Param("userId")Long userId);
	
	UserDevice getUserDeviceStatus(@Param("userId")Long userId,@Param("deviceId")Long deviceId);
	
	UserDevice getUserDeviceByUserId(@Param("userId")Long userId);
	
	/**
	 * 根据设备MAC，修改时间倒叙查找一条开始赚钱（允许用户连接网络）的司机设备
	 */
	UserDevice getStartJobUserDevice(@Param("deviceId")Long deviceId);
	
}
