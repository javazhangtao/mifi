package com.mifi.dao.mifi;

import java.util.List;

import com.mifi.po.mifi.TaskItem;

public interface TaskItemMapper {

	TaskItem getTaskItem(Long id);
	
	List<TaskItem> selectTaskItem();
}
