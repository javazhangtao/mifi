package com.mifi.po.mifi;

import java.io.Serializable;
import java.util.Date;

public class FlowgoldDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2050775707703286237L;
 	private Long id;
    private Long flowgoldId;
    private String describes;
    private Integer figure;
    private Long expenseType;//1:收入；2：支出
    private Date createTime = new Date();
    private Date modifyTime;
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
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public Integer getFigure() {
		return figure;
	}
	public void setFigure(Integer figure) {
		this.figure = figure;
	}
	public Long getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(Long expenseType) {
		this.expenseType = expenseType;
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

}
