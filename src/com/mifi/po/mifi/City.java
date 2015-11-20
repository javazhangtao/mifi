package com.mifi.po.mifi;

import java.io.Serializable;
import java.util.Date;

public class City implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6487511623844816684L;
	private Long id;
    private String name;
    private Long parentId;
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
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
    
}
