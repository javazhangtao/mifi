package com.mifi.dao.mifi;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mifi.po.mifi.Message;

public interface MessageMapper {

	/**
	 * 查询未读消息的总条数
	 * @param toId 用户id
	 * @return
	 */
	Integer selectUnreadMessageCount(Long toId);
	
	/**
	 * 查询总消息
	 * @param toId 用户id
	 * @return
	 */
	List<Message> selectMessageListByToId(Long toId);
	
	/**
	 * 获取一天的消息
	 * @param toId 用户id
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<Message> selectMessageListByDay(@Param("toId")Long toId, @Param("beginTime")Date beginTime, @Param("endTime")Date endTime);
}
