package com.mifi.dao.mifi;

public interface TaskDetailMapper {

	/**
	 * 获取用户完成的总任务数
	 * @param userId
	 * @return
	 */
	Integer getUserTaskCount(Long userId);
}
