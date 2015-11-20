package com.mifi.po.mifi;

import java.io.Serializable;
import java.util.Date;

public class FlowSupply implements Serializable {

	private static final long serialVersionUID = -2173987306661879390L;

	private Long id;
	private int supplier;
	private String volume;
	private Long price;
	private Date createTime = new Date();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSupplier() {
		return supplier;
	}

	public void setSupplier(int supplier) {
		this.supplier = supplier;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
