package com.mifi.po.mifi;

import java.util.Date;

/**
 * 关注公众号用户表
 * 
 * @author Administrator
 *
 */
public class AttentionUser {

	private Long id;
	private Long userId;
	private String phone;
	private Date attentionTime;
	private String publicNum;
	private Integer adwardAccount;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getAttentionTime() {
		return attentionTime;
	}

	public void setAttentionTime(Date attentionTime) {
		this.attentionTime = attentionTime;
	}

	public String getPublicNum() {
		return publicNum;
	}

	public void setPublicNum(String publicNum) {
		this.publicNum = publicNum;
	}

	public Integer getAdwardAccount() {
		return adwardAccount;
	}

	public void setAdwardAccount(Integer adwardAccount) {
		this.adwardAccount = adwardAccount;
	}

}
