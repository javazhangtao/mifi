package com.mifi.dto;

public class UserHomeInfo {

	private Long userId;
	private Long flowgoldbalance;
	private Integer yestodayFlowgoldCount;
	private Integer todayFlowgoldCount;
	private Integer yestodayApprentice;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFlowgoldbalance() {
		return flowgoldbalance;
	}

	public void setFlowgoldbalance(Long flowgoldbalance) {
		this.flowgoldbalance = flowgoldbalance;
	}

	public Integer getYestodayFlowgoldCount() {
		return yestodayFlowgoldCount;
	}

	public void setYestodayFlowgoldCount(Integer yestodayFlowgoldCount) {
		this.yestodayFlowgoldCount = yestodayFlowgoldCount;
	}

	public Integer getTodayFlowgoldCount() {
		return todayFlowgoldCount;
	}

	public void setTodayFlowgoldCount(Integer todayFlowgoldCount) {
		this.todayFlowgoldCount = todayFlowgoldCount;
	}

	public Integer getYestodayApprentice() {
		return yestodayApprentice;
	}

	public void setYestodayApprentice(Integer yestodayApprentice) {
		this.yestodayApprentice = yestodayApprentice;
	}

}
