package com.mifi.dao.mifi;

import java.util.List;

import com.mifi.po.mifi.FlowgoldExchange;

public interface FlowgoldExchangeMapper {

	int insert(FlowgoldExchange flowgoldExchange);
	List<FlowgoldExchange> getExchangeByPhone(Long flowgoldId);

}
