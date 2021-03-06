package com.mifi.service;

import com.mifi.dto.ResponceInfo;

public interface DeviceService {

	/**
	 * 通过设备ID获取设备信息
	 * @param deviceId
	 * @return
	 */
	ResponceInfo getDeviceInfo(Long deviceId);
	
	/**
	 * 通过设备Mac获取设备信息
	 * @param deviceMac
	 * @return
	 */
	ResponceInfo getDeviceInfo(String deviceMac);
	
	/**
	 * 3.1.10 用户直连设备接口
	 * @param userId
	 * @return
	 */
	ResponceInfo joinWifi(String userId, String deviceMac);
}
