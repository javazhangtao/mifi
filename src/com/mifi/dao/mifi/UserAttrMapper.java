package com.mifi.dao.mifi;

import com.mifi.po.mifi.UserAttr;

public interface UserAttrMapper {

	int insertUserAttr(UserAttr userAttr);
	UserAttr getUserAttr(long id);
	int updateUserAttr(UserAttr userAttr);
	
}
