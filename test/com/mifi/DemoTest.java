package com.mifi;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.mifi.common.RedisClient;
import com.mifi.po.mifi.User;
import com.mifi.utils.DateUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class DemoTest {

	@Test
	public void dateTest(){
		Calendar calendar = Calendar.getInstance();
		int weekNum = calendar.get(Calendar.WEEK_OF_YEAR );
		int monthNum = calendar.get(Calendar.MONTH );
		int dayOfWeekNum = calendar.get(Calendar.DAY_OF_WEEK );
		
		//上一周（不需要判断一年的开始，第一周是从周几开始）
		Calendar weekStart = Calendar.getInstance();
		weekStart.set(Calendar.WEEK_OF_YEAR, weekNum-1);
		weekStart.set(Calendar.DAY_OF_WEEK, 2);
		weekStart.set(Calendar.HOUR_OF_DAY, 0);
		weekStart.set(Calendar.MINUTE, 0);
		weekStart.set(Calendar.SECOND, 0);
		weekStart.set(Calendar.MILLISECOND, 0);
		Calendar weekEnd = Calendar.getInstance();
		weekEnd.set(Calendar.WEEK_OF_YEAR, weekNum);
		weekEnd.set(Calendar.DAY_OF_WEEK, 1);
		weekEnd.set(Calendar.HOUR_OF_DAY, 23);
		weekEnd.set(Calendar.MINUTE, 59);
		weekEnd.set(Calendar.SECOND, 59);
		weekEnd.set(Calendar.MILLISECOND, 999);
		
		//上一月
		Calendar monthStart = Calendar.getInstance();
		monthStart.set(Calendar.MONTH, monthNum-1);
		monthStart.set(Calendar.DAY_OF_MONTH, 1);
		monthStart.set(Calendar.HOUR_OF_DAY, 0);
		monthStart.set(Calendar.MINUTE, 0);
		monthStart.set(Calendar.SECOND, 0);
		monthStart.set(Calendar.MILLISECOND, 0);
		Calendar monthEnd = Calendar.getInstance();
		monthEnd.set(Calendar.MONTH, monthNum);
		monthEnd.set(Calendar.DAY_OF_MONTH, 0);
		monthEnd.set(Calendar.HOUR_OF_DAY, 23);
		monthEnd.set(Calendar.MINUTE, 59);
		monthEnd.set(Calendar.SECOND, 59);
		monthEnd.set(Calendar.MILLISECOND, 999);
		
		System.out.println("一年中的第几周："+weekNum);
		System.out.println("一周中的第几天："+dayOfWeekNum);
		System.out.println("日历输出calendar："+calendar);
		System.out.println("上周的日期开始："+new Date(weekStart.getTimeInMillis()));
		System.out.println("上周的日期结束："+new Date(weekEnd.getTimeInMillis()));
		System.out.println("上月的日期开始："+new Date(monthStart.getTimeInMillis()));
		System.out.println("上月的日期结束："+monthEnd.getTime());
	}
//	statistics.setToday(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.getDayBegin(date), date));
//	statistics.setYesterday(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.getNDaysBefore(date, 1), DateUtils.getYesterdayEnd(date)));
//	statistics.setWeek(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.curWeekBegin(date), date));
//	statistics.setLastweek(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.lastWeekBegin(date), DateUtils.lastWeekEnd(date)));
//	statistics.setMonth(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.curMonthBegin(date), date));
//	statistics.setLastmonth(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.lastMonthBegin(date), DateUtils.lastMonthEnd(date)));
//	
	public static void main(String[] args) {
		Date date = new Date();
//		System.out.println("当天开始："+DateUtils.getDayBegin(date));
//		System.out.println("昨天开始："+DateUtils.getYesterdayBegin(date));
//		System.out.println("昨天结束："+DateUtils.getYesterdayEnd(date));
//		System.out.println("本周开始："+DateUtils.curWeekBegin(date));
//		System.out.println("上周开始："+DateUtils.lastWeekBegin(date));
//		System.out.println("上周结束："+DateUtils.lastWeekEnd(date));
//		System.out.println("本月开始："+DateUtils.curMonthBegin(date));
//		System.out.println("上月开始："+DateUtils.lastMonthBegin(date));
//		System.out.println("上月结束："+DateUtils.lastMonthEnd(date));
		String m = "q=KX4h7osiw18ZtJCycVfHAQkMourGNXkdIQSfi8B9s9OY35H%20yd9AYgYbnhTly/0QwcBvl7HmcEXxOoEbNSF%20g8YIqGd7aT2zLCGVHvvU3%20zv4IjkjOyEclrIVuSIWFY6";
		m = m.replaceAll("%20", "+");
		System.out.println(m);
	}
	
	@Test
	public void testRedis(){
		
		RedisClient redisClient = new RedisClient();
		redisClient.getJedis().set("001", "aaa");
		redisClient.getJedis().expire("001", 10);
		redisClient.getJedis().set("002", "aba");
		System.out.println(redisClient.getJedis().get("001"));
		System.out.println(redisClient.getJedis().ttl("001"));
		System.out.println(redisClient.getJedis().persist("001"));
		
	}
	
	public void testCreateUser(){
		String m = "q=KX4h7osiw18ZtJCycVfHAQkMourGNXkdIQSfi8B9s9OY35H%20yd9AYgYbnhTly/0QwcBvl7HmcEXxOoEbNSF%20g8YIqGd7aT2zLCGVHvvU3%20zv4IjkjOyEclrIVuSIWFY6";
		m = m.replace("%20", "\\+");
		System.out.println(m);
	}
}
