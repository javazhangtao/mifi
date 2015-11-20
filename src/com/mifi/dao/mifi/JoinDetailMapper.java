package com.mifi.dao.mifi;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.mifi.po.mifi.JoinDetail;

public interface JoinDetailMapper {
	
	int insert(JoinDetail joinDetail);

	/**
	 * 获取当前的设备连接人数,设备的加入连接减去设备的断开连接
	 * @param date 当天日期
	 * @param userId 司机用户表的ID
	 * @param deviceId 设备ID
	 * @param joinType 连接类型（0:断开连接；1：加入连接）
	 * @return
	 */
	Integer getDeviceConnect(@Param("date")Date date, @Param("userId")Long userId,@Param("deviceId")Long deviceId,@Param("joinType")Integer joinType);
	
	/**
	 * 统计司机的连接人数
	 * @param userId 司机用户ID
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	Integer getDeviceConnectByStatistics(@Param("userId")Long userId, @Param("beginTime")Date beginTime, @Param("endTime")Date endTime);

	/**
	 * 查询一个用户在当前设备连接的最后一条记录
	 * @param userId
	 * @param deviceId
	 * @param joinType 连接类型（0:断开连接；1：加入连接）
	 * @return
	 */
	JoinDetail getUserJoinDetailByDeviceMac(@Param("userId")Long userId, @Param("deviceId")Long deviceId, @Param("joinType")Integer joinType);
	
}
