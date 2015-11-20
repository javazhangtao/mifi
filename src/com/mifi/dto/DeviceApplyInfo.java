package com.mifi.dto;
/**
 * 设备申请管理信息
 * @author Administrator
 *
 */
public class DeviceApplyInfo {
	Long id;
	String realName;
	String phoneNum;
	String cardNum;
	Integer gender;
	String carNum;
	Integer applyStatus;
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
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public Integer getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}
	@Override
	public String toString() {
		return "DeviceApplyInfo [id=" + id + ", realName=" + realName + ", phoneNum=" + phoneNum + ", cardNum="
				+ cardNum + ", gender=" + gender + ", carNum=" + carNum + ", applyStatus=" + applyStatus + "]";
	}
	
}
