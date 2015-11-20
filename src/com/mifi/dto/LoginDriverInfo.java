package com.mifi.dto;


public class LoginDriverInfo {

	private Integer connect = 0;
	private Integer generalize = 0;
	private Long expendflow = 0l;
	private Integer task = 0;
	private Integer cash = 0;
	private Integer voucher = 0;
	private Integer top = 0;
	private UserDetail userDetail;
	public Integer getConnect() {
		return connect;
	}
	public void setConnect(Integer connect) {
		this.connect = connect;
	}
	public Integer getGeneralize() {
		return generalize;
	}
	public void setGeneralize(Integer generalize) {
		this.generalize = generalize;
	}
	public Long getExpendflow() {
		return expendflow;
	}
	public void setExpendflow(Long expendflow) {
		this.expendflow = expendflow;
	}
	public Integer getTask() {
		return task;
	}
	public void setTask(Integer task) {
		this.task = task;
	}
	public Integer getCash() {
		return cash;
	}
	public void setCash(Integer cash) {
		this.cash = cash;
	}
	public Integer getVoucher() {
		return voucher;
	}
	public void setVoucher(Integer voucher) {
		this.voucher = voucher;
	}
	public Integer getTop() {
		return top;
	}
	public void setTop(Integer top) {
		this.top = top;
	}
	public UserDetail getUserDetail() {
		return userDetail;
	}
	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}
	
	
}
