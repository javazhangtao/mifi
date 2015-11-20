package com.mifi.server.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mifi.dto.ResponceInfo;
import com.mifi.main.proxy.MifiServer;
import com.mifi.server.DeviceServer;
import com.mifi.service.DeviceService;

@MifiServer(name = "deviceServer")
public class DeviceServerImpl implements DeviceServer {

	private static Logger log = Logger.getLogger(DeviceServerImpl.class);

	@Autowired
	DeviceService deviceService;

	@Override
	public ResponceInfo joinWifi(String userId, String deviceMac) {
		log.info("app用户直连设备接口 method:joinWifi 参数 userId:" + userId);
		return deviceService.joinWifi(userId, deviceMac);
	}

	@Override
	public ResponceInfo getDeviceInfoById(Long deviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponceInfo getDeviceInfoByMac(String deviceMac) {
		log.info("通过设备mac地址获取设备信息接口 method:getDeviceInfo 参数 deviceMac:" + deviceMac);
		return deviceService.getDeviceInfo(deviceMac);
	}

}
