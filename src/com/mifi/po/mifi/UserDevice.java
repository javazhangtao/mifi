package com.mifi.po.mifi;

import java.io.Serializable;
import java.util.Date;
import com.mifi.common.Dictionary;

public class UserDevice implements Serializable {

	private static final long serialVersionUID = 5834795829654557948L;

	private Long id; // 主键
	private Long userId; // 用户ID
	private Long deviceId; // 设备ID
	private Integer internetStatus = Dictionary.STATUS_DISABLED; //上网状态
	private Date createTime = new Date(); // 创建时间
	private Date modifyTime; // 修改时间
	private Integer rank; // 排序
	private Integer status = Dictionary.STATUS_NORMAL; // 状态
	
	public UserDevice(){
		super();
	}
	
	public UserDevice(Long userId, Long deviceMac, Date createTime){
		super();
		this.userId = userId;
		this.deviceId = deviceMac;
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getInternetStatus() {
		return internetStatus;
	}

	public void setInternetStatus(Integer internetStatus) {
		this.internetStatus = internetStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
