package com.mifi.dao.mifi;


import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.mifi.po.mifi.User;

public interface UserMapper {

	int insert(User user);
	User getUser(User user);
	int update(User user);
	User getUserByPhone(String phone);
	User getUserById(long id);
	User getUserByInvite(String inviteCode);
	int getUserGeneralizeTotal(long recommender);
	int getUserGeneralize(@Param("userId")long userId, @Param("beginTime")Date beginTime, @Param("endTime")Date endTime);
//	int getUserGeneralizeByToday(@Param("userId")long userId, @Param("date")Date date);
//	int getUserGeneralizeByYesterday(@Param("userId")long userId, @Param("beginTime")Date beginTime, @Param("endTime")Date endTime);
//	int getUserGeneralizeByCurWeek(@Param("userId")long userId, @Param("beginTime")Date beginTime, @Param("endTime")Date endTime);
//	int getUserGeneralizeByLastWeek(@Param("userId")long userId, @Param("beginTime")Date beginTime, @Param("endTime")Date endTime);
//	int getUserGeneralizeByCurMonth(@Param("userId")long userId, @Param("beginTime")Date beginTime, @Param("endTime")Date endTime);
//	int getUserGeneralizeByLastMonth(@Param("userId")long userId, @Param("beginTime")Date beginTime, @Param("endTime")Date endTime);
}
