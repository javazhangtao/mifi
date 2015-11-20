package com.mifi.dto;

import com.mifi.po.mifi.User;
import com.mifi.utils.DateUtils;

/**
 * 用户详细信息dto
 * 
 * @author Administrator
 *
 */
public class UserDetail {

	private Long userId;
	private String head;
	private String nickName;
	private Integer gender;
	private String birth;
	private Long cityId;
	private String cityName;
	private String cardId;
	private String intro;
	private String carNum;
	private String drivingCard;
	private String drivingLicense;
	private Integer userType;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
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
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public UserDetail(User user, String cityName) {
		this.userId = user.getId();
		this.head = user.getHead();
		this.nickName = user.getNickName();
		this.gender = user.getGender();
		if(user.getBirth()!=null){
			this.birth = DateUtils.simpleDateToString(user.getBirth());
		}else{
			this.birth = "";
		}
		this.cityId = user.getCity();
		this.cityName = cityName;
		this.cardId = user.getCardId();
		this.intro = user.getIntro();
		this.carNum = user.getCarNumber();
		this.drivingCard = user.getDrivingCard();
		this.drivingLicense = user.getDrivingLicense();
		this.userType = user.getUserType();
	}
	public UserDetail() {
		super();
	}

	
	
}
