package com.mifi.server.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mifi.dto.DeviceApplyInfo;
import com.mifi.dto.ResponceInfo;
import com.mifi.main.proxy.MifiServer;
import com.mifi.po.mifi.DeviceApply;
import com.mifi.server.DeviceApplyServer;
import com.mifi.service.DeviceApplyService;
@MifiServer(name="deviceApplyServer")
public class DeviceApplyServerImpl implements DeviceApplyServer {
	@Autowired
	DeviceApplyService deviceApplyService;
	
	private static Logger log = Logger.getLogger(DeviceApplyServerImpl.class);
	
	@Override
	public ResponceInfo applyDevice(String realName, String phoneNum, String email, String userQq, String gender,
			String deliveryAddress, String cardFront, String cardBack, String cardNum, String carNum,
			String drivingCard, String drivingLicense) {
		
		log.info("app 提交设备申请的基本信息 method:applyDevice 参数：realName"+realName+"phoneNum"+phoneNum+" email"+ email
				+"userQq"+ userQq+"gender"+ gender+ "deliveryAddress"+deliveryAddress+" cardFront"+cardFront+ 
				"cardBack"+cardBack+ "cardNum"+cardNum+"carNum"+carNum+"drivingCard"+ drivingCard+"drivingLicense"+drivingLicense);
		
		return this.deviceApplyService.applyDevice(realName, 
				phoneNum, email, userQq, Integer.valueOf(gender), deliveryAddress, cardFront, cardBack, cardNum, carNum, drivingCard, drivingLicense);
		
	}


	@Override
	public ResponceInfo getAllDeviceApplyInfo() {
		log.info("app获取申请设备的所有信息的接口  method:getAllDeviceApplyInfo 参数：无");
		return this.deviceApplyService.getAllDeviceApplyInfo();
	}

	@Override
	public ResponceInfo getDeviceApplyById(String id) {
		log.info("app获取申请设备详细信息的接口 method:getDeviceApplyById 参数：id"+id);
		return this.deviceApplyService.getDeviceApplyById(id);
	}

}
