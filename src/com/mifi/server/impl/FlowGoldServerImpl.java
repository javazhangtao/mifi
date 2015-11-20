package com.mifi.server.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mifi.dto.ResponceInfo;
import com.mifi.main.proxy.MifiServer;
import com.mifi.server.FlowGoldServer;
import com.mifi.service.FlowGoldService;

@MifiServer(name = "flowGoldServer")
public class FlowGoldServerImpl implements FlowGoldServer {

	private static Logger log = Logger.getLogger(FlowGoldServerImpl.class);
	@Autowired
	FlowGoldService flowGoldService;

	@Override
	public ResponceInfo extractAccountInfo(String userId, String password, String real_name, String phone,
			String alipay, String qq) {
		log.info("app提现账号编辑接口 method:extractAccountInfo 参数：userId：" + userId + " password:" + password + " real_name:"
				+ real_name + " phone:" + phone + " alipay:" + alipay + " qq:" + qq);
		return flowGoldService.extractAccountInfo(userId, password, real_name, phone, alipay, qq);
	}

	@Override
	public ResponceInfo extractPage(String userId) {
		log.info("app请求流量币提现 method:extractPage 参数：userId：" + userId);
		return flowGoldService.extractPage(userId);
	}

	@Override
	public ResponceInfo applyExtract(String userId, String password, String extractNum, String explain, String qq) {
		log.info("app流量币提现接口 method:applyExtract 参数：userId：" + userId + " password:" + password + " extractNum:"
				+ extractNum + " explain:" + explain + " qq:" + qq);
		return flowGoldService.applyExtract(userId, password, extractNum, explain, qq);
	}

	@Override
	public ResponceInfo getExchangeDetail(String userId, String phone) {
		log.info("app请求流量币兑换价目表 method:getExchangeDetail 参数：userId：" + userId + ",supplier" + phone);
		return flowGoldService.getExchangeDetail(userId, phone);
	}

	@Override
	public ResponceInfo flowGoldDetail(String userId) {
		log.info("app请求获取流量币详情 method:flowGoldDetail 参数：userId：" + userId);
		return flowGoldService.flowGoldDetail(userId);
	}

	@Override
	public ResponceInfo exchangeFlow(String phone, String supplyId, String flow, String flowgold) {
		log.info("app请求兑换流量 method:exchangeFlow 参数：userId：" + phone + "supplyId:" + supplyId + "flow:" + flow + "flowgold:" +flowgold);
		return flowGoldService.exchangeFlow(phone, supplyId, flow, flowgold);
	}

	@Override
	public ResponceInfo exchangeFlowHistory(String phone) {
		log.info("app请求兑换记录method:exchangeFlowHistory 参数：phone：" + phone);
		return flowGoldService.exchangeFlowHistory(phone);
	}

}
