package com.mifi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifi.common.Dictionary;
import com.mifi.dao.mifi.FlowSupplyMapper;
import com.mifi.dao.mifi.FlowgoldDetailMapper;
import com.mifi.dao.mifi.FlowgoldExchangeMapper;
import com.mifi.dao.mifi.MobileAreaMapper;
import com.mifi.dao.mifi.UserFlowgoldMapper;
import com.mifi.dao.mifi.UserMapper;
import com.mifi.dto.ExchangeFlowDto;
import com.mifi.dto.FlowgoldDayDetailDto;
import com.mifi.dto.FlowgoldDetailDto;
import com.mifi.dto.FlowgoldExchangeDto;
import com.mifi.dto.ResponceInfo;
import com.mifi.dto.Statistics;
import com.mifi.po.mifi.FlowSupply;
import com.mifi.po.mifi.FlowgoldExchange;
import com.mifi.po.mifi.MobileArea;
import com.mifi.po.mifi.User;
import com.mifi.po.mifi.UserFlowgold;
import com.mifi.service.FlowGoldService;
import com.mifi.utils.DateUtils;

@Service
public class FlowGoldServiceImpl implements FlowGoldService {

	@Autowired
	UserFlowgoldMapper userFlowgoldMapper;
	@Autowired
	FlowSupplyMapper flowSupplyMapper;
	@Autowired
	FlowgoldDetailMapper flowgoldDetailMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	FlowgoldExchangeMapper flowgoldExchangeMapper;
	@Autowired
	MobileAreaMapper mobileAreaMapper;

	@Override
	public ResponceInfo extractAccountInfo(String userId, String password, String real_name, String phone,
			String alipay, String qq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponceInfo extractPage(String userId) {
		ResponceInfo responceInfo = new ResponceInfo();
		Map<String, String> flowgoldMap = new HashMap<String, String>();
		UserFlowgold userFlowgold = userFlowgoldMapper.getUserFlowgold(Long.valueOf(userId));
		if (userFlowgold != null) {
			String surplusGold = userFlowgold.getBalance().toString();
			flowgoldMap.put("surplusGold", surplusGold);
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setData(flowgoldMap);
		} else {
			responceInfo.setCode(Dictionary.CODE_RESOURCE_NOT_FOUNT);
			responceInfo.setMessage("无数据");
		}
		return responceInfo;
	}

	@Override
	public ResponceInfo applyExtract(String userId, String password, String extractNum, String explain, String qq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponceInfo getExchangeDetail(String userId, String phone) {
		ResponceInfo responceInfo = new ResponceInfo();
		if(phone.length() != 11){
			responceInfo.setCode(Dictionary.CODE_PARAM_ERROR);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_ERROR);
			return responceInfo;
		}
		ExchangeFlowDto exchangeFlowDto = new ExchangeFlowDto();
		UserFlowgold userFlowgold = userFlowgoldMapper.getUserFlowgold(Long.valueOf(userId));
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		if (userFlowgold != null) {
			Long surplusGold = userFlowgold.getBalance();
			exchangeFlowDto.setBalance(surplusGold);
		} else {
			exchangeFlowDto.setBalance(0L);
		}
		
		String phonePrefix = phone.substring(0, 7);
		MobileArea mobileArea = mobileAreaMapper.selectAreaById(phonePrefix);
		if(mobileArea == null){
			responceInfo.setCode(Dictionary.CODE_RESOURCE_NOT_FOUNT);
			responceInfo.setMessage("查询归属地失败");
			return responceInfo;
		} 

		int supplier = 1;
		switch (mobileArea.getMobileType()) {
			case "中国移动":
				supplier = 1;
				break;
			case "中国电信":
				supplier = 2;
				break;
			case "中国联通":
				supplier = 3;
				break;
		}

		List<FlowSupply> exchangeDetail = flowSupplyMapper.getSupplyBySupplier(supplier);
		for (int i = 0; i < exchangeDetail.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("volume", exchangeDetail.get(i).getVolume());
			map.put("price", exchangeDetail.get(i).getPrice().toString());
			list.add(map);
		}
		exchangeFlowDto.setDetail(list);
		exchangeFlowDto.setMobileArea(mobileArea.getMobileArea());
		exchangeFlowDto.setMobileType(mobileArea.getMobileType());
		exchangeFlowDto.setMobileTypeId(supplier);
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setData(exchangeFlowDto);

		return responceInfo;
	}

	@Override
	public ResponceInfo flowGoldDetail(String userId) {
		ResponceInfo responceInfo = new ResponceInfo();
		if (userId == null) {
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		UserFlowgold userFlowgold = userFlowgoldMapper.getUserFlowgold(Long.valueOf(userId));
		if (userFlowgold == null) {
			responceInfo.setCode(Dictionary.CODE_RESOURCE_NOT_FOUNT);
			responceInfo.setMessage(Dictionary.MESSAGE_RESOURCE_NOT_FOUNT);
		} else {
			Date date = new Date();
			Statistics statistics = new Statistics();
			statistics.setToday(
					flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.getDayBegin(date), date));
			statistics.setYesterday(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(),
					DateUtils.getYesterdayBegin(date), DateUtils.getYesterdayEnd(date)));
			statistics.setWeek(
					flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.curWeekBegin(date), date));
			statistics.setLastweek(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(),
					DateUtils.lastWeekBegin(date), DateUtils.lastWeekEnd(date)));
			statistics.setMonth(
					flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.curMonthBegin(date), date));
			statistics.setLastmonth(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(),
					DateUtils.lastMonthBegin(date), DateUtils.lastMonthEnd(date)));

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("flowcollect", 11);
			map.put("collect", statistics);

			// TODO 测试假数据：收益的详情 需要查询几天的数据？
			Long flowgoldId = 2l;
			// flowgoldDetailMapper.getFlowgoldTotalCount(flowgoldId);
			FlowgoldDayDetailDto flowgoldDayDetailDto = new FlowgoldDayDetailDto();
			flowgoldDayDetailDto.setDescribe("推广设备");
			flowgoldDayDetailDto.setCash(2);
			FlowgoldDayDetailDto flowgoldDayDetailDto1 = new FlowgoldDayDetailDto();
			flowgoldDayDetailDto1.setDescribe("邀请乘客下载");
			flowgoldDayDetailDto1.setCash(1);
			FlowgoldDayDetailDto flowgoldDayDetailDto2 = new FlowgoldDayDetailDto();
			flowgoldDayDetailDto2.setDescribe("邀请乘客下载");
			flowgoldDayDetailDto2.setCash(1);
			List<FlowgoldDayDetailDto> daydetails = new ArrayList<FlowgoldDayDetailDto>();
			daydetails.add(flowgoldDayDetailDto);
			daydetails.add(flowgoldDayDetailDto1);
			daydetails.add(flowgoldDayDetailDto2);

			FlowgoldDetailDto flowgoldDetailDto = new FlowgoldDetailDto();
			flowgoldDetailDto.setDatetime("2015-10-10");
//			flowgoldDetailDto.setDaycollect(4);
			flowgoldDetailDto.setDaydetails(daydetails);
			map.put("detail", flowgoldDetailDto);

			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setData(map);
		}

		return responceInfo;
	}

	@Override
	public ResponceInfo exchangeFlow(String phone, String supplyId, String flow, String flowgold) {
		ResponceInfo responceInfo = new ResponceInfo();
		if (phone == null || "".equals(phone) || 11 != phone.length()) {
			responceInfo.setCode(Dictionary.CODE_PARAM_ERROR);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_ERROR);
			return responceInfo;
		}

		User user = userMapper.getUserByPhone(phone);
		if (user == null) {
			responceInfo.setCode(Dictionary.CODE_USER_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_EXIST);
			return responceInfo;
		}

		UserFlowgold userFlowgold = userFlowgoldMapper.getUserFlowgold(user.getId());
		if (userFlowgold == null) {
			UserFlowgold flowgoldInsert = new UserFlowgold();
			flowgoldInsert.setUserId(user.getId());
			int insert = userFlowgoldMapper.insert(flowgoldInsert);
			if (insert > 0) {
				userFlowgold = userFlowgoldMapper.getUserFlowgold(user.getId());
			} else {
				responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
				responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
				return responceInfo;
			}
		}
		FlowgoldExchange exchangeInsert = new FlowgoldExchange();
		exchangeInsert.setFigure(Long.valueOf(flowgold));
		exchangeInsert.setFlowgoldId(userFlowgold.getId());
		exchangeInsert.setFlow(flow);
		exchangeInsert.setSupplyId(Integer.valueOf(supplyId));
		int exInsert = flowgoldExchangeMapper.insert(exchangeInsert);
		if (exInsert > 0) {
			// 流量币表减去兑换所用的流量币
			userFlowgold.setBalance(userFlowgold.getBalance() - Long.valueOf(flowgold));
			int updateFG = userFlowgoldMapper.update(userFlowgold);
			if(updateFG > 0){
				// 获取用户兑换信息
				List<FlowgoldExchange> exchange = flowgoldExchangeMapper.getExchangeByPhone(userFlowgold.getId());
				List<FlowgoldExchangeDto> dtos = new ArrayList<FlowgoldExchangeDto>();
				for (int i = 0; i < exchange.size(); i++) {
					FlowgoldExchangeDto dto = new FlowgoldExchangeDto();
					dto.setStatus(exchange.get(i).getStatus());
					dto.setFlow(exchange.get(i).getFlow().toString());
					dto.setDateTime(DateUtils.dateToString(exchange.get(i).getCreateTime()));
					dto.setStatusStr(Dictionary.exchageStatus.get(exchange.get(i).getStatus()));
					dtos.add(dto);
				}
				responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
				responceInfo.setData(dtos);
			} else {
				responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
				responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
				return responceInfo;
			}
		} else {
			responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
			responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
			return responceInfo;
		}

		return responceInfo;
	}

	@Override
	public ResponceInfo exchangeFlowHistory(String phone) {
		ResponceInfo responceInfo = new ResponceInfo();
		if (phone == null || "".equals(phone) || 11 != phone.length()) {
			responceInfo.setCode(Dictionary.CODE_PARAM_ERROR);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_ERROR);
			return responceInfo;
		}

		User user = userMapper.getUserByPhone(phone);
		if (user == null) {
			responceInfo.setCode(Dictionary.CODE_USER_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_EXIST);
			return responceInfo;
		}
		
		UserFlowgold userFlowgold = userFlowgoldMapper.getUserFlowgold(user.getId());
		if(userFlowgold == null){
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setMessage("暂无数据");
			return responceInfo;
		} else {
			List<FlowgoldExchange> exchange = flowgoldExchangeMapper.getExchangeByPhone(userFlowgold.getId());
			List<FlowgoldExchangeDto> dtos = new ArrayList<FlowgoldExchangeDto>();
			for (int i = 0; i < exchange.size(); i++) {
				FlowgoldExchangeDto dto = new FlowgoldExchangeDto();
				dto.setStatus(exchange.get(i).getStatus());
				dto.setFlow(exchange.get(i).getFlow().toString());
				dto.setDateTime(DateUtils.dateToString(exchange.get(i).getCreateTime()));
				dto.setStatusStr(Dictionary.exchageStatus.get(exchange.get(i).getStatus()));
				dtos.add(dto);
				responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
				responceInfo.setData(dtos);
			}
		}
		
		return responceInfo;
	}

}
