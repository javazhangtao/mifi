package com.mifi.dto;

public class LoginUserInfo {

	private long userId;
	private String head;
	private String nickname;
	private String intro;
	private int informationnum;
	private int surplusflow;
	private int consequentday;
	private int apprenticecount;
	private int isresign;

	public int getIsresign() {
		return isresign;
	}

	public void setIsresign(int isresign) {
		this.isresign = isresign;
	}

	public int getConsequentday() {
		return consequentday;
	}

	public void setConsequentday(int consequentday) {
		this.consequentday = consequentday;
	}

	public int getApprenticecount() {
		return apprenticecount;
	}

	public void setApprenticecount(int apprenticecount) {
		this.apprenticecount = apprenticecount;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getInformationnum() {
		return informationnum;
	}

	public void setInformationnum(int informationnum) {
		this.informationnum = informationnum;
	}

	public int getSurplusflow() {
		return surplusflow;
	}

	public void setSurplusflow(int surplusflow) {
		this.surplusflow = surplusflow;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public LoginUserInfo() {
		super();
	}

	public LoginUserInfo(long userId, int informationnum, int surplusflow, int consequentday, int apprenticecount,
			int isresign) {
		this.userId = userId;
		this.informationnum = informationnum;
		this.surplusflow = surplusflow;
		this.consequentday = consequentday;
		this.apprenticecount = apprenticecount;
		this.isresign = isresign;
	}

}
