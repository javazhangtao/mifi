package com.mifi.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifi.common.Dictionary;
import com.mifi.dao.mifi.DeviceMapper;
import com.mifi.dao.mifi.UserDeviceMapper;
import com.mifi.dto.ResponceInfo;
import com.mifi.po.mifi.Device;
import com.mifi.po.mifi.UserDevice;
import com.mifi.service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	DeviceMapper deviceMapper;
	@Autowired
	UserDeviceMapper userDeviceMapper;

	@Override
	public ResponceInfo joinWifi(String userId, String deviceMac) {
		ResponceInfo responceInfo = new ResponceInfo();

		// 参数有空值
		if (userId == null || "".equals(userId) || deviceMac == null || "".equals(deviceMac)) {
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage("参数不合法");
			return responceInfo;
		}

		// TODO 用户 设备是否需要验证存在性

		Date createTime = new Date();
		UserDevice userDevice = new UserDevice(Long.valueOf(userId), Long.valueOf(deviceMac), createTime);
		int u = userDeviceMapper.insert(userDevice);
		if (u > 0) {
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setMessage("success");
		} else {
			responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
			responceInfo.setMessage("failed");
		}
		return responceInfo;
	}

	@Override
	public ResponceInfo getDeviceInfo(Long deviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponceInfo getDeviceInfo(String deviceMac) {
		ResponceInfo responceInfo = new ResponceInfo();
		Device device = deviceMapper.getDeviceByMac(deviceMac);
		if (device != null) {
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setData(device);
		} else {
			responceInfo.setCode(Dictionary.CODE_RESOURCE_NOT_FOUNT);
			responceInfo.setMessage("无数据");
		}

		return responceInfo;
	}

}
