package com.mifi.dao.mifi;

import java.util.List;

import com.mifi.po.mifi.BlackList;

public interface BlackListMapper {

	/**
	 * 查询所有黑名单
	 * @return
	 */
	List<BlackList> selectAll();
	
	/**
	 * 根据过滤条件查询黑名单数量
	 * @param _black
	 * @return
	 */
	Integer selectByBlackCount(String _black);
	
}
