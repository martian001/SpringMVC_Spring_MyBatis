package com.et.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author liangyanjun
 *
 */
public class DateUtils {
	// 转换[Date,Calendar,String,long]

	private DateUtils() {

	}

	/**
	 * Date类型的日期转换成long类型的日期
	 * 
	 * @param date
	 * @return
	 */
	public static long dateToLong(Date date) {
		long time = date.getTime();
		return time;
	}

	/**
	 * 根据传入的long型日期转成date
	 * @param l
	 * @return
	 */
	public static Date longToDate(long l) {
		return new Date(l);
	}

	/**
	 * 根据传入的Calendar转成date
	 * @param c
	 * @return
	 */
	public static Date calendarToDate(Calendar c) {
		return c.getTime();
	}

	/**
	 * 根据传入的date转成calendar
	 * @param date
	 * @return
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar c = getCalendar();
		c.setTime(date);
		return c;
	}

	/**
	 * xxxx-xx-xx xx:xx:xx
	 * 根据传入的date获取完整日期
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		String fullDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		return fullDate;
	}

	/**
	 * 
	 * 根据传入的相应格式的字符串日期，转为date
	 * @param str
	 * 		传过来的日期格式必须是：xxxx-xx-xx xx:xx:xx 例如：2000-02-12 14:25:25
	 * @return
	 */
	public static Date stringToDate(String str) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
		} catch (ParseException e) {
			System.out.println("传过来的日期格式错误");
		}
		return date;
	}
	
	public static Date stringToDate(String str,String format) {
	   Date date = null;
	   try {
	      date = new SimpleDateFormat(format).parse(str);
	   } catch (ParseException e) {
	      System.out.println("传过来的日期格式错误");
	   }
	   return date;
	}

	// 得到当前时间

	/**
	 * 得到一个Calendar
	 * @return
	 */
	public static Calendar getCalendar() {
		Calendar c = Calendar.getInstance();
		return c;
	}

	/**
	 * 得到当前时间：例如2104-04-04
	 * @return
	 * 	    返回当前日期:xxxx-xx-xx 例如：2012-12-25
	 */
	public static String getCurrentDate() {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return date;
	}

	/**
	 * 得到当前时分秒时间：例如12:34:21
	 * @return
	 */
	public static String getCurrentTime() {
		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
		return time;
	}

	/**
	 * 得到日期和时间：例如2104-04-04 12:34:21
	 * @return
	 */
	public static String getCurrentDateTime() {
		String date = getCurrentTime();
		return getCurrentDate() + " " + date;
	}

	/**
	 * 得到当前长整型年份：例如2014
	 * @return
	 */
	public static String getCurrentLongYear() {
		String year = new SimpleDateFormat("yyyy").format(new Date());
		return year;
	}

	/**
	 * 得到当前短整型年份：例如14
	 * @return
	 */
	public static String getCurrentShortYear() {
		String year = new SimpleDateFormat("yy").format(new Date());
		return year;
	}

	/**
	 * 得到当前月份
	 * @return
	 */
	public static String getCurrentMonth() {
		String month = new SimpleDateFormat("MM").format(new Date());
		return month;
	}

	/**
	 * 得到当前星期
	 * @return
	 */
	public static String getCurrentWeek() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("E");
		String week = dateFormat.format(new Date());
		return week;
	}

	/**
	 * 得到当前日
	 * @return
	 */
	public static String getCurrentDay() {
		String day = new SimpleDateFormat("dd").format(new Date());
		return day;
	}

	// 得到一个日期里的日期时间信息

	/**
	 * 根据date返回年
	 * @param date
	 * @return
	 */
	public static int getYearByDate(Date date) {
		Calendar c = dateToCalendar(date);
		int year = c.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 
	 * 根据date返货月
	 * @param date
	 * @return
	 */
	public static int getMonthByDate(Date date) {
		Calendar c = dateToCalendar(date);
		int month = c.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 *根据date得到 一年中的第几天
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfYearByDate(Date date) {
		Calendar c = dateToCalendar(date);
		int day = c.get(Calendar.DAY_OF_YEAR);
		return day;
	}

	/**
	 * 根据date返回月的第几周
	 * @param date
	 * @return
	 */
	public static int getWeekByDate(Date date) {
		Calendar c = dateToCalendar(date);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		return week;
	}

	/**
	 * 根据date返回月的第几天
	 * @param date
	 * @return
	 */
	public static int getDayOfMonthByDate(Date date) {
		Calendar c = dateToCalendar(date);
		int day = c.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 *根据date获取该天是星期几
	 * @param date
	 * @return
	 */
	public static String getDayOfWeekByDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("E");
		String week = dateFormat.format(date);
		return week;
	}

	// 时间格式之间的转换

	/**
	 * 根据传入短整形日期，返回长整型日期。例如：传入010323，返回2001-03-23
	 * 注意：在世纪方面，如果输入的年份比当前年份小，则世纪为20世纪，如果传入的年份比当前年份大，则表明不科学，所以世纪则理所当然是19世纪
	 *     如果传入参数长度超过6位数，则返回的是以前六位为准的长整型日期
	 * 例如：
	 * 1.传入参数为010323，返回：2001-03-23
	 * 2.传入参数为：890823，返回则是：1989-08-23
	 * 3.传入参数为：82031312  返回则是：1982-03-13
	 * @param shortDate
	 * @return
	 */
	public static String shortDateToLongDate(String shortDate) {
		int year = Integer.parseInt(shortDate.substring(0, 2));// 截取传入年份
		String longDate = "";
		int currentCentury = Integer.parseInt(getCurrentShortYear());// 获取当前年份
		if (year > currentCentury) {
			longDate = "19" + shortDate;
		} else {
			longDate = "20" + shortDate;
		}
		String fullDate = longDateToShortDate(longDate);
		return fullDate;
	}

	/**
	 * 根据传入长整型日期转为短整型日期：例如传入20130212，返回为2013-02-12
	 * @param longDate
	 * @return
	 */
	public static String longDateToShortDate(String longDate) {
		Calendar calendar = getCalendar();
		String shortDate = "";
		try {
			int year = Integer.parseInt(longDate.substring(0, 4));
			int month = Integer.parseInt(longDate.substring(4, 6));
			int day = Integer.parseInt(longDate.substring(6, 8));
			calendar.set(year, month - 1, day, 0, 0);
			shortDate = dateFormatByPattern(calendar.getTime(), "yyyy-MM-dd");
		} catch (Exception e) {
			System.out.println("日期格式错误,正确格式如：20130212");
		}
		return shortDate;
	}

	/**
	 * 根据传入长整型时分秒转为正常格式显示，例如：传入13:43:12，转为  134312(注意：分隔符是可以是任意的非数字字符)
	 * @param longTime
	 * @return
	 */
	public static String longTimeToShortTime(String longTime) {
		String shortTime = "";
		try {
			shortTime = longTime.replaceAll("\\D", "");
		} catch (Exception e) {
			System.out.println("日期格式错误");
		}
		return shortTime;
	}

	/**
	 * 根据传入短整型时分秒转为正常格式显示，例如：传入134312，转为13:43:12
	 * @param shortTime
	 * @return
	 */
	public static String shortTimeToLongTime(String shortTime) {
		Calendar calendar = getCalendar();
		String longTime = "";
		try {
			int huor = Integer.parseInt(shortTime.substring(0, 2));
			int minute = Integer.parseInt(shortTime.substring(2, 4));
			int second = Integer.parseInt(shortTime.substring(4, 6));
			calendar.set(0, 0, 0, huor, minute, second);
			longTime = dateFormatByPattern(calendar.getTime(), "kk:mm:ss");
		} catch (Exception e) {
			System.out.println("日期格式错误,正确格式如：134312");
		}
		return longTime;
	}

	// other
	/**
	 * 得到年有多少天
	 * @param year
	 * @return
	 */
	public static int getDaysOfYear(Date year) {
		Calendar calendar = dateToCalendar(year);
		int day = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
		return day;
	}

	/**
	 * 得到一年又多少星期
	 * @param year
	 * @return
	 */
	public static int getWeeksOfYear(Date year) {
		Calendar calendar = dateToCalendar(year);
		int week = calendar.getActualMaximum(Calendar.WEEK_OF_YEAR);
		return week;
	}

	/**
	 * 得到月有多少天
	 * @param month
	 * @return
	 */
	public static int getDaysOfMonth(Date month) {
		Calendar calendar = dateToCalendar(month);
		int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 得到月有几周
	 * @param month
	 * @return
	 */
	public static int getWeeksOfMonth(Date month) {
		Calendar calendar = dateToCalendar(month);
		int week = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
		return week;
	}

	/**
	 * 判断是否闰年
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(Date date) {
		int year = getYearByDate(date);
		boolean isleapyear = (((((year % 4) == 0) && (((year % 100) != 0))) || ((year % 400) != 0)));
		return isleapyear;
	}

	/**
	 * 根据格式模式获得日期字符串
	 * @param pattern
	 * @return
	 */
	public static String dateFormatByPattern(Date date, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String formatDate = dateFormat.format(date);
		return formatDate;
	}

	/**
	 * 获取月的最后一天
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int month) {
		Calendar calendar = getCalendar();
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, 1);
		String lastDay = String.valueOf(calendar.getActualMaximum(Calendar.DATE));
		return lastDay;
	}
	/**
	 * 获取月的第一天
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth() {
	    Calendar cal = Calendar.getInstance();   
	    cal.setTime(new Date());   
	    cal.set(Calendar.DAY_OF_MONTH, 1);   
        return cal.getTime();
	}

    /** 
     * 返回该月最后一天
     * @author:liangyanjun
     * @time:2015-11-25下午6:45:51
     * @param date
     * @return */
    public static Date getLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        final int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date lastDate = c.getTime();
        lastDate.setDate(lastDay);
        return lastDate;
    }

	/**
	 * 根据传入的两个date，计算它们的时间差（以“秒”为单位）
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int secondsDifference(Date beginDate, Date endDate) {
		long begin = beginDate.getTime();
		long end = endDate.getTime();
		long millisecond = 1000;// 一分钟的毫秒数
		int seconds = Integer.parseInt(String.valueOf((end - begin) / millisecond));
		return seconds;
	}
	/**
	 * 根据传入的两个date，计算它们的时间差（以“分钟”为单位）
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int minutesDifference(Date beginDate, Date endDate) {
		long begin = beginDate.getTime();
		long end = endDate.getTime();
		long millisecond = 60000;// 一分钟的毫秒数
		int minutes = Integer.parseInt(String.valueOf((end - begin) / millisecond));
		return minutes;
	}
	/**
	 * 根据传入的两个date，计算它们的时间差（以“天”为单位）
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int dayDifference(Date beginDate, Date endDate) {
		long begin = beginDate.getTime();
		long end = endDate.getTime();
		long millisecond = 86400000;// 一天的毫秒数
		int day = Integer.parseInt(String.valueOf((end - begin) / millisecond));
		return day;
	}

	/**
	 * 天数相加
	 * 
	 * @param date
	 * @param number
	 * @return
	 */
	public static Date addDay(Date date, Integer number) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, number);

		Date result = cal.getTime();

		return result;
	}
	
	public static void main(String[] args) throws InterruptedException {
		Date begin = new Date();
//		Thread.sleep(3000);
		System.out.println(minutesDifference(stringToDate("2016-04-02 08:00:00"), stringToDate("2016-04-02 20:07:52")));
		// System.out.println(longDateToShortDate("20141102"));
		// System.out.println(longTimeToShortTime("061102"));
		// System.out.println(getDayOfWeekByDate(new Date()));
		// System.out.println(longTimeToShortTime("2014.02.13"));
		// System.out.println(shortDateToLongDate("011108"));
		// System.out.println(getYearByDate(new Date()));
		//		Date date = new Date(114, 3, 2);
		//		System.out.println(timeDifference(date, new Date()));
//		System.out.println(getDaysOfYear(new Date()));
//		System.out.println(getWeeksOfYear(new Date()));
//		System.out.println(getDaysOfMonth(new Date()));
//		System.out.println(getWeeksOfMonth(new Date()));
//		System.out.println(getFirstDayOfMonth());
//		System.out.println(getLastDayOfMonth(new Date()));
//		System.out.println(dateFormatByPattern(addDay(new Date(),-1), "yyyy-MM-dd"));

	}
}
