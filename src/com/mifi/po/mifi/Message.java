package com.mifi.po.mifi;

import java.io.Serializable;
import java.util.Date;

import com.mifi.common.Dictionary;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4821447643353281251L;

	private Long id;
	private String subject;
    private Long fromId;
    private Long toId;
    private Date createTime = new Date();
    private Integer status = Dictionary.STATUS_NORMAL;
    private String message;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Long getFromId() {
		return fromId;
	}
	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}
	public Long getToId() {
		return toId;
	}
	public void setToId(Long toId) {
		this.toId = toId;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
}
