package com.mifi.po.mifi;

import java.io.Serializable;
import java.util.Date;
import com.mifi.common.Dictionary;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5872034759483008281L;
	private Long id;
	private String phone;
	private String password;
	private String realName = "佚名";
	private String nickName = "无名氏";
	private Integer gender = 1;
	private Date birth = new Date(673974000000l);
	private String cardId = "188888888888888888";
	private Long city = 61l;
	private String inviteCode;
	private Integer userType = 0;
	private Long recommender;
	private Date createTime = new Date();
	private Date modifyTime ;
	private String head = "default";
    private String carNumber = "京A8888";
    private String drivingCard;
    private String drivingLicense;
    private String alipay;
    private String intro = "高大上！";
	private Integer status = Dictionary.STATUS_NORMAL;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public Long getCity() {
		return city;
	}
	public void setCity(Long city) {
		this.city = city;
	}
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Long getRecommender() {
		return recommender;
	}
	public void setRecommender(Long recommender) {
		this.recommender = recommender;
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
	public User() {
		super();
		
	}
	public User(String phone, String password) {
		super();
		this.phone = phone;
		this.password = password;
	}
	public User(String phone, String password, Date createTime) {
		super();
		this.phone = phone;
		this.password = password;
		this.createTime = createTime;
	}
	
}
