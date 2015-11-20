package com.mifi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mifi.common.Dictionary;
import com.mifi.dao.mifi.DeviceApplyMapper;
import com.mifi.dto.DeviceApplyInfo;
import com.mifi.dto.ResponceInfo;
import com.mifi.po.mifi.DeviceApply;
import com.mifi.service.DeviceApplyService;
@Service
public class DeviceApplyServiceImpl implements DeviceApplyService {
	@Autowired
	DeviceApplyMapper deviceApplyMapper;

	@Override
	public ResponceInfo applyDevice(String realName, String phoneNum, String email, String userQq, Integer gender,
			String deliveryAddress, String cardFront, String cardBack, String cardNum, String carNum,
			String drivingCard, String drivingLicense) {
		
		ResponceInfo responceInfo = new ResponceInfo();
		DeviceApply deviceApply = new DeviceApply(null,realName, phoneNum, email, userQq, gender,
				deliveryAddress, cardFront, cardBack, cardNum, carNum,drivingCard, drivingLicense,null);
		System.out.println(deviceApply);
		
		int flag = this.deviceApplyMapper.insertDeviceApply(deviceApply);
		if(flag > 0){
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setMessage("true");
		}else{
			responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
			responceInfo.setMessage("false");
		}
		
		return responceInfo;
	}

	@Override
	public ResponceInfo getAllDeviceApplyInfo() {
		ResponceInfo responceInfo = new ResponceInfo();
		List<DeviceApplyInfo> deviceApplyInfos = new ArrayList<DeviceApplyInfo>();
		List<DeviceApply> deviceApplys = this.deviceApplyMapper.getAllDeviceApply();
		for (DeviceApply deviceApply : deviceApplys) {
			DeviceApplyInfo deviceApplyInfo = new DeviceApplyInfo();
			deviceApplyInfo.setId(deviceApply.getId());
			deviceApplyInfo.setRealName(deviceApply.getRealName());
			deviceApplyInfo.setPhoneNum(deviceApply.getPhoneNum());
			deviceApplyInfo.setGender(deviceApply.getGender());
			deviceApplyInfo.setCardNum(deviceApply.getCardNum());
			deviceApplyInfo.setCarNum(deviceApply.getCarNum());
			deviceApplyInfo.setApplyStatus(deviceApply.getApplyStatus());
			deviceApplyInfos.add(deviceApplyInfo);
			System.out.println(deviceApplyInfo);
		}
		responceInfo.setCode(200);
		responceInfo.setData(deviceApplyInfos);
		
		return responceInfo;
	}

	@Override
	public ResponceInfo getDeviceApplyById(String id) {
		ResponceInfo responceInfo = new ResponceInfo();
		System.out.println(id);
		
		DeviceApply deviceApply = this.deviceApplyMapper.getDeviceApplyById(Long.valueOf(id));
		System.out.println(deviceApply);
		responceInfo.setCode(200);
		responceInfo.setData(deviceApply);
		return responceInfo;
	}

}
