package com.mifi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mifi.dto.DeviceApplyInfo;
import com.mifi.dto.ResponceInfo;
import com.mifi.po.mifi.DeviceApply;

public interface DeviceApplyService {

	/**
	 * 用户申请设备接口：注册设备申请
	 * @param realName
	 * @param phoneNum
	 * @param email
	 * @param userQq
	 * @param gender
	 * @param deliveryAddress
	 * @param cardFront
	 * @param cardBack
	 * @param cardNum
	 * @param carNum
	 * @param drivingCard
	 * @param drivingLicense
	 * 
	 */
	ResponceInfo applyDevice(String realName,String phoneNum,String email,
			String userQq,Integer gender,String deliveryAddress,String cardFront,
			String cardBack,String cardNum,String carNum,
			String drivingCard,String drivingLicense);
	
	/**
	 * 获取所有申请设备的信息
	 * @param deviceMac
	 * @return
	 */
	ResponceInfo getAllDeviceApplyInfo();
	
	/**
	 * 根据id获取用户申请设备的详细信息
	 * @param userId
	 * @return
	 */
	ResponceInfo getDeviceApplyById(String id);
}
