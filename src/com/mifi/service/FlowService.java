package com.mifi.service;

import com.mifi.dto.ResponceInfo;

public interface FlowService {

	/**
	 * 3.1.4 我的流量币接口
	 * @param userId
	 * @return
	 */
	ResponceInfo flowDetail(String userId);
}
