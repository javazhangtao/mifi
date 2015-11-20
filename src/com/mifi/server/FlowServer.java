package com.mifi.server;

import com.mifi.dto.ResponceInfo;

public interface FlowServer {

	/**
	 * 3.1.4 我的流量币接口
	 * @param userId
	 * @return
	 */
	ResponceInfo flowDetail(String userId);
}
