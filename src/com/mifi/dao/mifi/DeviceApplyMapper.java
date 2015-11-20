package com.mifi.dao.mifi;

import java.util.List;

import com.mifi.po.mifi.DeviceApply;
/**
 * 设备申请管理接口
 * @author Administrator
 *
 */
public interface DeviceApplyMapper {
	/**
	 * 获取所有的申请了设备的用户信息
	 */
	List<DeviceApply> getAllDeviceApply();
	/**
	 * 详细显示单个用户申请信息
	 */
	DeviceApply getDeviceApplyById(Long id);
	
	/**
	 * 添加用户申请设备信息
	 */
	int insertDeviceApply(DeviceApply deviceApply);
}

