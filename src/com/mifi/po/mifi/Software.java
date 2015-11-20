package com.mifi.po.mifi;

import java.util.Date;

public class Software {
	private Long id;
    private String versionSerial;
    private String upgradeContent;
    private Integer versionType;
    private Integer isNew;
    private Integer url;
    private Date createTime = new Date();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVersionSerial() {
		return versionSerial;
	}
	public void setVersionSerial(String versionSerial) {
		this.versionSerial = versionSerial;
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
	public Integer getIsNew() {
		return isNew;
	}
	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	public Integer getUrl() {
		return url;
	}
	public void setUrl(Integer url) {
		this.url = url;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}
