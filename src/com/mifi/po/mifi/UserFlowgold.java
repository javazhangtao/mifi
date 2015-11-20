package com.mifi.po.mifi;

import java.io.Serializable;
import java.util.Date;

import com.mifi.common.Dictionary;

public class UserFlowgold implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7607681919384634544L;

	private Long id;
    private Long userId;
    private Long balance;
    private Date createTime = new Date();
    private Date modifyTime;
    private Integer status = Dictionary.STATUS_NORMAL;
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
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
    
}
