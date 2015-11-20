package com.mifi.dao.mifi;

import java.util.List;

import com.mifi.po.mifi.FlowSupply;

public interface FlowSupplyMapper {
	
	List<FlowSupply> getSupplyBySupplier(Integer supplier);
	
}
