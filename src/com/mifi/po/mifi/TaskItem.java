package com.mifi.po.mifi;

import java.util.Date;

import com.mifi.common.Dictionary;

public class TaskItem {

	private Long id;
    private String name;
    private Integer status = Dictionary.STATUS_NORMAL;
    private Date createTime = new Date();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
    
}
