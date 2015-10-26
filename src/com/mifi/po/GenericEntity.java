package com.mifi.po;

import java.io.Serializable;
import java.util.Date;

import com.mifi.common.Dictionary;

public class GenericEntity implements Serializable{

	private static final long serialVersionUID = -1289558495733747162L;
	
	private Integer status = Dictionary.STATUS_NORMAL;
	private Long id;
	private Date createTime ;
	private Date modifyTime ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
