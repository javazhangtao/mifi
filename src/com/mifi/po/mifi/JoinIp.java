package com.mifi.po.mifi;

import java.util.Date;

public class JoinIp {

	private Long id;
    private String deviceIp;
    private Integer joinType;
    private Long userId;
    private Long joinUserId;
    private Date createTime = new Date();
    private Date modifyTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeviceIp() {
		return deviceIp;
	}
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public JoinIp() {
		super();
	}
	public JoinIp(String deviceIp, Integer joinType, Long userId, Long joinUserId) {
		this.deviceIp = deviceIp;
		this.joinType = joinType;
		this.userId = userId;
		this.joinUserId = joinUserId;
	}

}
