package com.mifi.server.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mifi.dto.ResponceInfo;
import com.mifi.main.proxy.MifiServer;
import com.mifi.server.FlowServer;
import com.mifi.service.FlowService;

@MifiServer(name="flowServer")
public class FlowServerImpl implements FlowServer {

	private static Logger log = Logger.getLogger(FlowServerImpl.class);
	
	@Autowired
	FlowService flowService;
	
	@Override
	public ResponceInfo flowDetail(String userId) {
		log.info("我的流量币接口 method:flowDetail 参数 userId:"+userId);
		return flowService.flowDetail(userId);
	}

}
