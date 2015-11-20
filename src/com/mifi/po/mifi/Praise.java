package com.mifi.po.mifi;

import java.io.Serializable;
import java.util.Date;

public class Praise implements Serializable {

	private static final long serialVersionUID = 5344420269336684710L;

	private Long id;
	private Long passengerId;
	private Long driverId;
	private Date createTime = new Date();
	private String apMac;

	public Praise() {
		super();
	}

	public Praise(Long passengerId, String apMac, Long driverId) {
		super();
		this.passengerId = passengerId;
		this.apMac = apMac;
		this.driverId = driverId;
	}

	public String getApMac() {
		return apMac;
	}

	public void setApMac(String apMac) {
		this.apMac = apMac;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
