package com.mifi.po.mifi;

import java.io.Serializable;
import java.util.Date;

public class UserRegistration implements Serializable {

	private static final long serialVersionUID = 3171797201658121790L;
	
	private Long id;
	private Long userId;
	private Date date;
	private Integer days;
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	
}
