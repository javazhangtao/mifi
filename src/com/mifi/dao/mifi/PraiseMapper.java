package com.mifi.dao.mifi;

import java.util.Date;

import com.mifi.po.mifi.Praise;

public interface PraiseMapper {
	
	int getPraiseTotalCount(Long userId);
	Integer getPraiseCount(Long userId, String beginTime, String endTime);
	Integer getPraiseCountByDate(Long userId, Date beginTime, Date endTime);
	
	int insert(Praise praise);

}
