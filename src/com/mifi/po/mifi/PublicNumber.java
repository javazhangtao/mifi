package com.mifi.po.mifi;

import java.util.Date;

/**
 * 公众号表
 * @author Administrator
 *
 */
public class PublicNumber {

	private Long id;
	private String name;
	private String photo;
	private String number;
	private Integer awardAccount;
	private Date extendStartDate;
	private Date extendEndDate;
	private Integer useStatus;

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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getAwardAccount() {
		return awardAccount;
	}

	public void setAwardAccount(Integer awardAccount) {
		this.awardAccount = awardAccount;
	}

	public Date getExtendStartDate() {
		return extendStartDate;
	}

	public void setExtendStartDate(Date extendStartDate) {
		this.extendStartDate = extendStartDate;
	}

	public Date getExtendEndDate() {
		return extendEndDate;
	}

	public void setExtendEndDate(Date extendEndDate) {
		this.extendEndDate = extendEndDate;
	}

	public Integer getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}

}
