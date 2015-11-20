package com.mifi.po.mifi;

import java.io.Serializable;
import java.util.Date;
import com.mifi.common.Dictionary;

/**
 * 设备实例
 * 
 * @author Administrator
 *
 */
public class Device implements Serializable  {

	private static final long serialVersionUID = -6045345564348446696L;

	private Long id; // 主键
	private String deviceMode; // 设备型号
	private String deviceType; // 设备类型
	private String deviceMac; // 设备mac地址
	private String deviceNo; // 设备编号
	private String deviceCode; // 设备代码
	private String deviceIp; // 设备IP地址
	private Long surplusFlow; // 剩余流量
	private Date createTime = new Date(); // 创建时间
	private Date modifyTime; // 修改时间
	private Integer status = Dictionary.STATUS_NORMAL; // 状态

	public Long getId() {
		return id;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceMode() {
		return deviceMode;
	}

	public void setDeviceMode(String deviceMode) {
		this.deviceMode = deviceMode;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public Long getSurplusFlow() {
		return surplusFlow;
	}

	public void setSurplusFlow(Long surplusFlow) {
		this.surplusFlow = surplusFlow;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
