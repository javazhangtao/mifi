package com.mifi.dao.mifi;

import org.apache.ibatis.annotations.Param;

public interface AttentionUserMapper {
	
	Integer getInfoByNumber(@Param("number")String number, @Param("userId")Long userId);

}
