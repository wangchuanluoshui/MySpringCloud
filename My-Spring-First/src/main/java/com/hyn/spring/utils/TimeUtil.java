package com.hyn.spring.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Title:：TimeUtil.java
 * @Package ：com.summit.homs.util @Description： TODO @author： hyn @date：
 *          2018年9月13日 上午9:20:51
 * @version ： 1.0
 */
public class TimeUtil {

	private static final Logger log = LoggerFactory.getLogger(TimeUtil.class);

	static SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	private static final String TIME_PATTERN = "HH:mm";

	private TimeUtil() {

	}

	/**
	 * 获取日期格式化字符串
	 * 
	 * @return 返回默认格式datePattern(MM/dd/yyyy)
	 */
	public static String getDatePattern() {
		return "yyyy-dd-MM";
	}

	/**
	 * 获取时间格式化字符串
	 * 
	 * @return
	 * @see
	 */
	public static String getDateTimePattern() {
		return TimeUtil.getDatePattern() + " HH:mm:ss.S";
	}

	/**
	 * 将aDate格式化为字符串
	 * 
	 * @param aDate
	 * @return 返回aDate按照默认DatePattern格式化后的字符串
	 */
	public static String getDate(Date aDate) {
		SimpleDateFormat df;
		String returnValue = "";
		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * 按照aMask将strDate转换为Date对象
	 * 
	 * @param aMask   转换格式
	 * @param strDate 日期字符串
	 * @return 返回转换后的日期对象
	 * @throws 转换出错抛出 ParseException
	 * @see {@link SimpleDateFormat#parse(String)}
	 */
	public static Date convertStringToDate(String aMask, String strDate) throws ParseException {
		return convertStringToDate(aMask, strDate, false);
	}

	/**
	 * 按照aMask将strDate转换为Date对象，如果check==true则验证将Date再转换成string时必须和strDate相等
	 * 
	 * @param aMask   转换格式
	 * @param strDate 日期字符串
	 * @param check   是否验证转换后的日期对象再转换成String和strDate相等
	 * @return 返回转换后的日期对象
	 * @throws 转换出错抛出 ParseException
	 */
	public static Date convertStringToDate(String aMask, String strDate, boolean check) throws ParseException {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat(aMask);
		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}
		try {
			date = df.parse(strDate);
			if (check && !strDate.equals(TimeUtil.getDateTime(aMask, date))) {
				log.error("converting '" + strDate + "' to date with mask '" + aMask + "'");
				throw new ParseException("日期格式有误", 0);
			}
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return (date);
	}

	/**
	 * 将theTime格式化为字符串
	 * 
	 * @param theTime 给格式化的时间
	 * @return 返回格式化后的字符串
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(TIME_PATTERN, theTime);
	}

	/**
	 * 获取当前日期的Calendar
	 * 
	 * @return 返回当前日期的Calendar
	 * @throws ParseException
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));
		return cal;
	}

	/**
	 * 按照aMask将aDate格式化为字符串
	 * 
	 * @param aMask 格式化规则
	 * @param aDate 被格式化的时间对象
	 * @return 返回格式化后的字符串
	 */
	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * 将aDate转换为String
	 * 
	 * @param aDate 时间对象
	 * @return 返回转换后的String
	 */
	public static String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * 将strDate按照默认转换规则转换为String
	 * 
	 * @param strDate
	 * @return 返回转换后的时间对象
	 * @throws 转换出错抛出ParseException
	 */
	public static Date convertStringToDate(String strDate) throws ParseException {
		Date aDate = null;
		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + getDatePattern());
			}
			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate + "' to a date, throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return aDate;
	}

	/**
	 * 给date加days天 <br/>
	 * 创建者：马飞虎 <br/>
	 * 创建时间：2011-4-20 上午11:54:23 <br/>
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDay(Date date, int days) {
		if (date == null) {
			return date;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	/**
	 * 日期内添加月份 @Title: addMonth @Description: TODO @param @param date @param @param
	 * months @param @return @return Date @throws
	 */
	public static Date addMonth(Date date, int months) {
		if (date == null) {
			return date;
		}
		;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, months);
		return c.getTime();
	}

	public static Date addHour(Date date, int hours) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, hours);
		return c.getTime();
	}

	/**
	 * 计算两个时间之差
	 * 
	 * @param time1 开始时间 格式为（yyyy-mm-dd hh24:mi）
	 * @param time2 结束时间 格式为（yyyy-mm-dd hh24:mi）
	 * @return 年数时间差
	 */
	public static Double getTimesDiffOfYears(Date time1, Date time2) {
		// 如2016-08-10 20:40
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		long from = 0;
		long to = 0;
		from = time1.getTime();
		to = time2.getTime();

		Double days = ((double) (Math.abs((to - from)) / (1000 * 60 * 60 * 24))) / 365;
		return days;
	}

	/**
	 * 计算两个时间之差
	 * 
	 * @param time1 开始时间 格式为（yyyy-mm-dd hh24:mi）
	 * @param time2 结束时间 格式为（yyyy-mm-dd hh24:mi）
	 * @return 天数时间差
	 */
	public static int getTimesDiffOfDays(Date time1, Date time2) {
		// 如2016-08-10 20:40
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		long from = 0;
		long to = 0;
		from = time1.getTime();
		to = time2.getTime();
		int days = (int) (Math.abs((to - from)) / (1000 * 60 * 60 * 24));
		return days;
	}

	/**
	 * 计算两个时间之差
	 * 
	 * @param time1 开始时间 格式为（yyyy-mm-dd hh24:mi）
	 * @param time2 结束时间 格式为（yyyy-mm-dd hh24:mi）
	 * @return 天数时间差
	 */
	public static int getTimesDiffOfDays(String time1, String time2) {
		// 如2016-08-10 20:40
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		long from = 0;
		long to = 0;
		try {
			from = simpleFormat.parse(time1).getTime();
			to = simpleFormat.parse(time2).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int days = (int) (Math.abs((to - from)) / (1000 * 60 * 60 * 24));
		return days;
	}

	/**
	 * 计算两个时间之差
	 * 
	 * @param time1 开始时间 格式为（yyyy-mm-dd hh24:mi）
	 * @param time2 结束时间 格式为（yyyy-mm-dd hh24:mi）
	 * @return 小时时间差
	 */
	public static int getTimesDiffOfHours(String time1, String time2) {
		// 如2016-08-10 20:40
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		long from = 0;
		long to = 0;
		try {
			from = simpleFormat.parse(time1).getTime();
			to = simpleFormat.parse(time2).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int hours = (int) ((to - from) / (1000 * 60 * 60));

		return hours;
	}

	/**
	 * 计算两个时间之差
	 * 
	 * @param time1 开始时间 格式为（yyyy-mm-dd hh24:mi）
	 * @param time2 结束时间 格式为（yyyy-mm-dd hh24:mi）
	 * @return 分钟差
	 */
	public static int getTimesDiffOfMinutes(String time1, String time2) {
		// 如2016-08-10 20:40
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

		long from = 0;
		long to = 0;
		try {
			from = simpleFormat.parse(time1).getTime();
			to = simpleFormat.parse(time2).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int minutes = (int) ((to - from) / (1000 * 60));

		return minutes;
	}

	/**
	 * 获取时间差
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static String getTimesToNow(String time1, String time2) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String now = time2;
		String returnText = null;
		try {
			long from = format.parse(time1).getTime();
			long to = format.parse(now).getTime();
			int days = (int) ((to - from) / (1000 * 60 * 60 * 24));
			if (days == 0) {
				// 一天以内，以分钟或者小时显示
				int hours = (int) ((to - from) / (1000 * 60 * 60));
				if (hours == 0) {
					int minutes = (int) ((to - from) / (1000 * 60));
					if (minutes == 0) {
						returnText = "刚刚";
					} else {
						returnText = minutes + "分钟前";
					}
				} else {
					returnText = hours + "小时前";
				}
			} else if (days == 1) {
				returnText = "昨天";
			} else {
				returnText = days + "天前";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnText;
	}

	/**
	 * dayNum 为天数 可以为正数，也可以为负数
	 * 
	 * @param nowDate
	 * @param dayNum
	 * @return
	 */
	public static Date getTimeAddOrSubByDay(Date nowDate, int dayNum) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dayNum);
		date = calendar.getTime();
		return date;
	}

	/**
	 * hourNum 为小时数 可以为正数，也可以为负数
	 * 
	 * @param nowDate
	 * @param dayNum
	 * @return
	 */
	public static Date getTimeAddOrSubByHour(Date nowDate, int hourNum) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hourNum);
		date = calendar.getTime();
		return date;
	}

	/**
	 * minuteNum 为分钟数 可以为正数，也可以为负数
	 * 
	 * @param nowDate
	 * @param dayNum
	 * @return
	 */
	public static Date getTimeAddOrSubByMinute(Date nowDate, int minuteNum) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minuteNum);
		date = calendar.getTime();
		return date;
	}

	public static void main(String[] args) {
		/*
		 * System.out.println(getTimesDiffOfDays("2018-07-25 18:25","2018-07-16 10:15"))
		 * ;
		 * System.out.println(getTimesDiffOfHours("2018-07-15 18:25","2018-07-26 10:15")
		 * ); System.out.println(getTimesDiffOfMinutes("2018-07-27 18:25"
		 * ,"2018-07-26 10:15"));
		 */
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Double double1 = getTimesDiffOfYears(new Date(), dateFormat.parse("2018-04-17 18:25:00"));
			System.out.println(double1);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
