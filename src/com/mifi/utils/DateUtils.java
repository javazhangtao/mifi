package com.mifi.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/**
	 * string to date
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String dateStr) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dateFormat.parse(dateStr);
		return date;
	}

	/**
	 * timestamp to date
	 * 
	 * @param ts
	 * @return
	 */
	public static Date timestampToDate(Timestamp timestamp) {
		Date date = new Date(timestamp.getTime());
		return date;
	}

	/**
	 * calendar to date
	 * 
	 * @param calendar
	 * @return
	 */
	public static Date calendarToDate(Calendar calendar) {
		return calendar.getTime();
	}

	/**
	 * date to string
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = dateFormat.format(date);
		return dateStr;
	}
	
	/**
	 * date to string
	 * 
	 * @param date
	 * @return
	 */
	public static String simpleDateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = dateFormat.format(date);
		return dateStr;
	}

	/**
	 * timestamp to string
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String timstampToString(Timestamp timestamp) {
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = dateFormat.format(timestamp);
		return dateStr;
	}

	/**
	 * calendar to string
	 * 
	 * @param calendar
	 * @return
	 */
	public static String calendarToString(Calendar calendar) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = dateFormat.format(calendar.getTime());
		return dateStr;
	}

	/**
	 * date to timestamp
	 * 
	 * @param date
	 * @return
	 */
	public static Timestamp dateToTimestamp(Date date) {
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}

	/**
	 * string to timestamp
	 * 
	 * @param string
	 *            Timestamp format must be yyyy-mm-dd hh:mm:ss.fffffffff
	 * @return
	 */
	public static Timestamp stringToTimestamp(String string) {
		Timestamp timestamp = Timestamp.valueOf(string);
		return timestamp;
	}
	
	/**
	 * calendar to timestamp
	 * @param calendar
	 * @return
	 */
	public static Timestamp calendarToTimestamp(Calendar calendar){
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
		return timestamp;
	}
	
	/**
	 * date to calendar
	 * @param date
	 * @return
	 */
	public static Calendar dateToCalendar(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/**
	 * string to calendar
	 * @param string
	 * @return
	 * @throws ParseException
	 */
	public static Calendar stringToCalendar(String string) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dateFormat.parse(string);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/**
	 * timestamp to calendar
	 * @param timestamp
	 * @return
	 */
	public static Calendar timestampToCalendar(Timestamp timestamp){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timestamp);
		return calendar;
	}
	
	//�����ǻ���������ת�������¾���Date���ͱ�д��������Ҫת���ɵ������Ϸ�����
	
	/**
	 * get the begin of one date
	 * @param date
	 * @return
	 */
	public static Date getDayBegin(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date dateBegin = calendar.getTime();
		return dateBegin;
	}
	
	/**
	 * get the end of one date
	 * @param date
	 * @return
	 */
	public static Date getDayEnd(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date dateEnd = calendar.getTime();
		return dateEnd;
	}
	
	/**
	 * get n days before date
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getNDaysBefore(Date date, int n){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -n);
		Date nDaysBefore = calendar.getTime();
		return nDaysBefore;
	}
	
	/**
	 * get n days later date
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getNDaysLater(Date date, int n){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, n);
		Date nDaysLater = calendar.getTime();
		return nDaysLater;
	}
	
	/**
	 * get n months before date
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getNMonthsBefore(Date date, int n){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -n);
		Date nMonthsBefore = calendar.getTime();
		return nMonthsBefore;
	}
	
	/**
	 * get n months later date
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getNMonthsLater(Date date, int n){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, n);
		Date nMonthsLater = calendar.getTime();
		return nMonthsLater;
	}
	
	/**
	 * get n years before date
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getNYearsBefore(Date date, int n){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, -n);
		Date nYearsBefore = calendar.getTime();
		return nYearsBefore;
	}
	
	/**
	 * get n years later date
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getNYearsLater(Date date, int n){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, n);
		Date nYearsLater = calendar.getTime();
		return nYearsLater;
	}
	
	/**
	 * �ж�һ������Ƿ�������(leap year)
	 * @param year
	 * @return
	 */
	public static Boolean isLeapYear(int year){
		if (year % 100 == 0) {
			if (year % 400 == 0) {
				return true;
			}
		} else {
			if (year % 4 == 0) {
				return true;
			}
		}
		return false;
	}
	
	public static Date getNdayBegin(Date date,int count){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, dayOfYear-count);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date getNdayEnd(Date date,int count){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, dayOfYear-count);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	public static Date getYesterdayBegin(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, dayOfYear-1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date getYesterdayEnd(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, dayOfYear-1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	
	public static Date curWeekBegin(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date lastWeekBegin(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		calendar.set(Calendar.WEEK_OF_YEAR, weekOfYear-1);
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date lastWeekEnd(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	
	public static Date curMonthBegin(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date lastMonthBegin(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int monthOfYear = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, monthOfYear-1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date lastMonthEnd(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	
	
	/**
	 * some values of calendar
	 * for getting some arguments of today
	 * @param date
	 * @return
	 */
	public static int detailOfDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		int year = calendar.get(Calendar.YEAR);   //���
		int month = calendar.get(Calendar.MONTH) + 1;  //�·�
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);   //����ڼ���
		int dayOfMonth = calendar.get(Calendar.DATE);   //��
		//int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);   //���µڼ��죬ֵ������
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);   //���ܵڼ��죬����Ϊ��һ��
		int dayOfWeekInMonth = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);   //��һ��Ϊ��һ�ܿ�ʼ�㣬�ڼ���
		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);   //����ڼ���
		int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);   //���µڼ���
		int hour_12 = calendar.get(Calendar.HOUR);    //12����
		int hour_24 = calendar.get(Calendar.HOUR_OF_DAY);   //24����
		int mimute = calendar.get(Calendar.MINUTE);  //��
		int second = calendar.get(Calendar.SECOND);  //��
		int millisecond = calendar.get(Calendar.MILLISECOND);  //����
		long timeInMillis = calendar.getTimeInMillis();  //��ǰʱ��ĺ�����
		
		return 0;
	}
}
