package com.mifi.dao.mifi;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mifi.po.mifi.FlowgoldDetail;

public interface FlowgoldDetailMapper {

	Integer getFlowgoldCount(@Param("flowgoldId")Long flowgoldId, @Param("beginTime")Date beginTime, @Param("endTime")Date endTime);
	Integer getFlowgoldCountByType(@Param("flowgoldId")Long flowgoldId, @Param("expenseType")Long expenseType, @Param("beginTime")Date beginTime, @Param("endTime")Date endTime);
	Integer getFlowgoldTotalCount(Long flowgoldId);
	Integer insert(FlowgoldDetail flowgoldDetail);
	FlowgoldDetail getFlowgoldByFlowgoldId(@Param("id")Long id, @Param("type")Long type);
	
	List<FlowgoldDetail> selectFlowgoldDetailByDay(@Param("flowgoldId")Long flowgoldId, @Param("beginTime")Date beginTime, @Param("endTime")Date endTime);
}
