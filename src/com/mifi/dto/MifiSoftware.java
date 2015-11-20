package com.mifi.dto;

import com.mifi.po.mifi.Software;

public class MifiSoftware {

    private String serial;
    private String upgradeContent;
    private Integer versionType;
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getUpgradeContent() {
		return upgradeContent;
	}
	public void setUpgradeContent(String upgradeContent) {
		this.upgradeContent = upgradeContent;
	}
	public Integer getVersionType() {
		return versionType;
	}
	public void setVersionType(Integer versionType) {
		this.versionType = versionType;
	}
	public MifiSoftware(Software software) {
		this.serial = software.getVersionSerial();
		this.upgradeContent = software.getUpgradeContent();
		this.versionType = software.getVersionType();
	}
	public MifiSoftware() {
		super();
	}
    
}
