package com.mifi.server;

import com.mifi.dto.ResponceInfo;

public interface PraiseServer {

	/**
	 * 获取司机获得赞的总个数
	 * @param userId
	 * @return
	 */
	ResponceInfo getPraiseTotalCount(String userId);
	
	/**
	 * 获取司机今天获得赞的个数
	 * @param userId
	 * @return
	 */
	ResponceInfo getPraiseByToday(String userId);
	
}
