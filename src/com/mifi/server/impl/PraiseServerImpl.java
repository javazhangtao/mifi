package com.mifi.server.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mifi.dto.ResponceInfo;
import com.mifi.main.proxy.MifiServer;
import com.mifi.server.PraiseServer;
import com.mifi.service.PraiseService;

@MifiServer(name="praiseServer")
public class PraiseServerImpl implements PraiseServer {
	
	@Autowired
	PraiseService praiseService;

	@Override
	public ResponceInfo getPraiseTotalCount(String userId) {
		return praiseService.getPraiseTotalCount(Long.valueOf(userId));
	}

	@Override
	public ResponceInfo getPraiseByToday(String userId) {
		return praiseService.getPraiseByToday(Long.valueOf(userId));
	}

}
