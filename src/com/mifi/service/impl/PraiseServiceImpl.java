package com.mifi.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifi.common.Dictionary;
import com.mifi.dao.mifi.PraiseMapper;
import com.mifi.dto.ResponceInfo;
import com.mifi.service.PraiseService;
import com.mifi.utils.DateUtils;

@Service
public class PraiseServiceImpl implements PraiseService {
	
	@Autowired
	PraiseMapper praiseMapper;

	@Override
	public ResponceInfo getPraiseTotalCount(Long userId) {
		ResponceInfo responceInfo = new ResponceInfo();
		Map<String, String> map = new HashMap<String, String>();
		Integer count = praiseMapper.getPraiseTotalCount(userId);
		String countStr = count + "";
		map.put("total", countStr);
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setData(map);
		return responceInfo;
	}

	@Override
	public ResponceInfo getPraiseByToday(Long userId) {
		ResponceInfo responceInfo = new ResponceInfo();
		Date today = new Date();
		Map<String, String> map = new HashMap<String, String>();
		// TODO 获取今天开始时间
		String beginTime = DateUtils.dateToString(DateUtils.getDayBegin(today));
		// TODO 获取今天结束时间
		String endTime = DateUtils.dateToString(DateUtils.getDayEnd(today));
		// 调用函数，获取今天个数
		Integer l = praiseMapper.getPraiseCount(userId, beginTime, endTime);
		String lStr = l + "";
		map.put("total", lStr);
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setData(map);
		return responceInfo;
	}

}
