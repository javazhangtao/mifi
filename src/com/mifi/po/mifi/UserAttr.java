package com.mifi.po.mifi;

import java.io.Serializable;
import java.util.Date;

public class UserAttr implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 597937493632421880L;
	private Long id;
    private Long userId;
    private String head;
    private String carNumber;
    private String drivingCard;
    private String drivingLicense;
    private Date createTime = new Date();
    private Date modifyTime;
    private String alipay;
    private String intro;
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
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getDrivingCard() {
		return drivingCard;
	}
	public void setDrivingCard(String drivingCard) {
		this.drivingCard = drivingCard;
	}
	public String getDrivingLicense() {
		return drivingLicense;
	}
	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
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
	public String getAlipay() {
		return alipay;
	}
	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
    
}
