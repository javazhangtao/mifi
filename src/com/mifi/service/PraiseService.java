package com.mifi.service;

import com.mifi.dto.ResponceInfo;

public interface PraiseService {
	
	/**
	 * 获取司机获得赞的总个数
	 * @param userId
	 * @return
	 */
	ResponceInfo getPraiseTotalCount(Long userId);
	
	/**
	 * 获取司机今天获得赞的个数
	 * @param userId
	 * @return
	 */
	ResponceInfo getPraiseByToday(Long userId);

}
