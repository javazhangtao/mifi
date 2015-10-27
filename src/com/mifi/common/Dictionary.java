package com.mifi.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.mifi.utils.PropertiesUtil;

public class Dictionary {
	
	final static PropertiesUtil propertiesUtil=new PropertiesUtil("/config.properties");

	
	public final static Integer CODE_PARAM_NULL =101;//参数为空
	public final static Integer CODE_RESOURCE_NOT_FOUNT =104;//资源不存在
	public final static Integer CODE_SYSTEM_ERROT =500;//系统错误
	
	
	public final static Integer STATUS_NORMAL=1;//数据库状态   正常
	public final static Integer STATUS_DISABLED=0;//数据库状态   废弃
	
	
	public static Map<String, Object> MIFI_SERVICES=new ConcurrentHashMap<String, Object>();//service 对象
	
	
	public static String getProperty(String key) {
		return propertiesUtil.getProperty(key);
	}
}
