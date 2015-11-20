package com.mifi.po.mifi;

import java.io.Serializable;

public class DeviceApply implements Serializable{
	private Long id;
	private String realName;
	private String phoneNum;
	private String email;
	private String userQq;
	private Integer gender;
	private String deliveryAddress;
	private String cardFront;
	private String cardBack;
	private String cardNum;
	private String carNum;
	private String drivingCard;
	private String drivingLicense;
	private Integer applyStatus;
	public DeviceApply() {
		// TODO Auto-generated constructor stub
	}
	
	public DeviceApply(Long id, String realName, String phoneNum, String email, String userQq, Integer gender,
			String deliveryAddress, String cardFront, String cardBack, String cardNum, String carNum,
			String drivingCard, String drivingLicense, Integer applyStatus) {
		super();
		this.id = id;
		this.realName = realName;
		this.phoneNum = phoneNum;
		this.email = email;
		this.userQq = userQq;
		this.gender = gender;
		this.deliveryAddress = deliveryAddress;
		this.cardFront = cardFront;
		this.cardBack = cardBack;
		this.cardNum = cardNum;
		this.carNum = carNum;
		this.drivingCard = drivingCard;
		this.drivingLicense = drivingLicense;
		this.applyStatus = applyStatus;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserQq() {
		return userQq;
	}
	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getCardFront() {
		return cardFront;
	}
	public void setCardFront(String cardFront) {
		this.cardFront = cardFront;
	}
	public String getCardBack() {
		return cardBack;
	}
	public void setCardBack(String cardBack) {
		this.cardBack = cardBack;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
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
	public Integer getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}
	@Override
	public String toString() {
		return "DeviceApply [id=" + id + ", realName=" + realName + ", phoneNum=" + phoneNum + ", email=" + email
				+ ", userQq=" + userQq + ", gender=" + gender + ", deliveryAddress=" + deliveryAddress + ", cardFront="
				+ cardFront + ", cardBack=" + cardBack + ", cardNum=" + cardNum + ", carNum=" + carNum
				+ ", drivingCard=" + drivingCard + ", drivingLicense=" + drivingLicense + ", applyStatus=" + applyStatus
				+ "]";
	}
	
}
