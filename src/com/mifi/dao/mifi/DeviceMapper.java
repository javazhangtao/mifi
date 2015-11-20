package com.mifi.dao.mifi;

import com.mifi.po.mifi.Device;

public interface DeviceMapper {
	
	/**
	 * 根据设备mac地址获取设备信息
	 * @param mac
	 * @return
	 */
	Device getDeviceByMac(String deviceMac);
	
	/**
	 * 根据设备id获取设备信息
	 * @param deviceId
	 * @return
	 */
	Device getDeviceById(String deviceId);

}
