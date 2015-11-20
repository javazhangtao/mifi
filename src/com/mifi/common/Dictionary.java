package com.mifi.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.mifi.utils.PropertiesUtil;

public class Dictionary {
	
	final static PropertiesUtil propertiesUtil=new PropertiesUtil("/config.properties");

	/**参数为空*/
	public final static Integer CODE_PARAM_NULL =101;
	public final static String MESSAGE_PARAM_NULL ="参数为空";
	/**参数错误*/
	public final static Integer CODE_PARAM_ERROR =102;
	public final static String MESSAGE_PARAM_ERROR ="参数错误";
	/**用户名或密码错误*/
	public final static Integer CODE_LOGIN_ERROR =103;
	public final static String MESSAGE_LOGIN_ERROR = "用户名或密码错误";
	/**资源不存在*/
	public final static Integer CODE_RESOURCE_NOT_FOUNT =104;
	public final static String MESSAGE_RESOURCE_NOT_FOUNT ="资源不存在";
	/**改手机号已被注册*/
	public final static Integer CODE_USER_EXIST = 105;
	public final static String MESSAGE_USER_EXIST = "该手机号已被注册";
	/**没有该邀请码*/
	public final static Integer CODE_INVITECODE_ERROR = 106;
	public final static String MESSAGE_INVITECODE_ERROR = "没有该邀请码";
	/**验证码错误*/
	public final static Integer CODE_AUTH_ERROR = 107;
	public final static String MESSAGE_AUTH_ERROR = "验证码错误";
	public final static String MESSAGE_AUTH_NOT_FOUND = "验证码不存在或已超时，请重新发送";
	/**用户不存在*/
	public final static Integer CODE_USER_NOT_EXIST =121;
	public final static String MESSAGE_USER_NOT_EXIST ="用户不存在";
	/**密码错误*/
	public final static Integer CODE_USER_NOT_RIGHT =122;
	public final static String MESSAGE_USER_NOT_RIGHT ="密码错误";
	/**mifi开通上网失败*/
	public final static Integer CODE_USER_HAS_LOGIN =123;
	public final static String MESSAGE_USER_HAS_LOGIN ="用户已经登录";
	/**mifi开通上网失败*/
	public final static Integer CODE_MIFI_OPEN_ERROR =131;
	public final static String MESSAGE_MIFI_OPEN_ERROR ="mifi开通上网失败";
	/**mifi关闭上网失败*/
	public final static Integer CODE_MIFI_CLOSE_ERROR =132;
	public final static String MESSAGE_MIFI_CLOSE_ERROR ="mifi关闭上网失败";
	/**mifi绑定用户超过上限*/
	public final static Integer CODE_MIFI_BIND_OVER =133;
	public final static String MESSAGE_MIFI_BIND_OVER ="mifi绑定用户已经上限";
	/**mifi盒子没有注册*/
	public final static Integer CODE_MIFI_NOT_EXIST =134;
	public final static String MESSAGE_MIFI_NOT_EXIST ="mifi盒子没有注册";
	/**司机没有绑定mifi盒子*/
	public final static Integer CODE_MIFI_NOT_BIND =135;
	public final static String MESSAGE_MIFI_NOT_BIND ="没有绑定mifi盒子";
	/** 司机没有开启mifi盒子上网*/
	public final static Integer CODE_MIFI_NOT_AUTH =136;
	public final static String MESSAGE_MIFI_NOT_AUTH ="司机没有开启mifi盒子的上网功能";
	/**系统错误*/
	public final static Integer CODE_SYSTEM_ERROT =500;
	public final static String MESSAGE_SYSTEM_ERROT ="系统错误";
	/**请求正常*/
	public final static Integer CODE_SYSTEM_NORMAL =200;
	public final static String MESSAGE_SYSTEM_NORMAL ="";
	/**查询的收益详情数目*/
	public final static Integer FLOWGOLD_DETAIL_NUMBER =50;
	
	public final static Integer STATUS_NORMAL=1;//状态   正常或者加入
	public final static Integer STATUS_DISABLED=0;//状态   废弃或者断开
	
	public final static Integer REDIS_TIMEOUT=600;//状态   正常或者加入
	
	public final static String LOGIN_RESOULT_TRUE="{'login':'true'}";
	public final static String LOGIN_RESOULT_NOEXIST="{'login':'noexist'}";
	public final static String LOGIN_RESOULT_RELOGIN="{'login':'relogin'}";
	public final static String LOGIN_RESOULT_OVERTIME="{'login':'overtime'}";
	public final static String LOGIN_RESOULT_FALSE="{'login':'false'}";
	
	// 流量兑换状态map
	public final static Map<Integer, String> exchageStatus = new HashMap<Integer, String>(){{
		put(0, "等待审核");
		put(1, "审核通过");
		put(2, "审核未通过");
		put(3, "兑换成功");
	}};
	
	public static Map<String, Object> MIFI_SERVICES=new ConcurrentHashMap<String, Object>();//service 对象
	
	public static String getProperty(String key) {
		return propertiesUtil.getProperty(key);
	}
}
