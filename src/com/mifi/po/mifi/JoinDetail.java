package com.mifi.po.mifi;

import java.io.Serializable;
import java.util.Date;

public class JoinDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6710265337817010858L;

	private Long id;
    private Long deviceId;
    private String deviceMac;
    private Integer joinType;
    private Long userId;
    private Long joinUserId;
    private Integer currentDaysJoinNum;
    private Date modifyTime;
    private Date createTime = new Date();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceMac() {
		return deviceMac;
	}
	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}
	public Integer getJoinType() {
		return joinType;
	}
	public void setJoinType(Integer joinType) {
		this.joinType = joinType;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getJoinUserId() {
		return joinUserId;
	}
	public void setJoinUserId(Long joinUserId) {
		this.joinUserId = joinUserId;
	}
	public Integer getCurrentDaysJoinNum() {
		return currentDaysJoinNum;
	}
	public void setCurrentDaysJoinNum(Integer currentDaysJoinNum) {
		this.currentDaysJoinNum = currentDaysJoinNum;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public JoinDetail() {
		super();
	}
	public JoinDetail(Long deviceId, String deviceMac, Integer joinType, Long userId, Long joinUserId) {
		this.deviceId = deviceId;
		this.deviceMac = deviceMac;
		this.joinType = joinType;
		this.userId = userId;
		this.joinUserId = joinUserId;
	}
	
}

