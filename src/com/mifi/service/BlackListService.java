package com.mifi.service;

import java.util.List;

import com.mifi.po.mifi.BlackList;

public interface BlackListService {

	
	List<BlackList> selectAll();
	
	boolean isExistBlack(String _black);
}
