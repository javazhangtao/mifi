package com.mifi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifi.dao.mifi.BlackListMapper;
import com.mifi.po.mifi.BlackList;
import com.mifi.service.BlackListService;

@Service
public class BlackListServiceImpl implements BlackListService {

	@Autowired
	BlackListMapper blackListMapper;
	
	@Override
	public List<BlackList> selectAll() {
		return blackListMapper.selectAll();
	}
	
	@Override
	public boolean isExistBlack(String _black){
		return (blackListMapper.selectByBlackCount(_black)>0)?true:false;
	}

}
