package com.mifi.po;

import java.util.Date;

import com.mifi.common.Dictionary;

public class GenericEntity {

	private Integer status = Dictionary.STATUS_NORMAL;
	private Date createTime ;
	private Date modifyTime ;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
}
