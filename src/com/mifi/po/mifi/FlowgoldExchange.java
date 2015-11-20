package com.mifi.po.mifi;

import java.util.Date;

public class FlowgoldExchange {

	private Long id;
	private Long flowgoldId;
	private Long figure;
	private Date createTime = new Date();
	private Date modifyTime;
	private Integer status;
	private Integer supplyId;
	private String flow;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFlowgoldId() {
		return flowgoldId;
	}

	public void setFlowgoldId(Long flowgoldId) {
		this.flowgoldId = flowgoldId;
	}

	public Long getFigure() {
		return figure;
	}

	public void setFigure(Long figure) {
		this.figure = figure;
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

	public Integer getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

}
