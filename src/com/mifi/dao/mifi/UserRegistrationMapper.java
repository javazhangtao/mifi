package com.mifi.dao.mifi;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mifi.po.mifi.UserRegistration;

public interface UserRegistrationMapper {

	List<UserRegistration> getLastRegist(@Param("userId")Long userId, @Param("date")Date date);
	Integer insert(UserRegistration userRegistration);
	
}
