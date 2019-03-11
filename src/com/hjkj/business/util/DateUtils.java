package com.hjkj.business.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static final long ONE_MINUTE = 60;
	private static final long ONE_HOUR = 3600;
	private static final long ONE_DAY = 86400;
	private static final long ONE_MONTH = 2592000;

	public static Calendar calendar = Calendar.getInstance();

	/**
	 * @param format
	 * @return 
	 * yyyy年MM月dd HH:mm 
	 * MM-dd HH:mm 2012-12-25
	 * 
	 */
	public static String getDate(String format) {
		SimpleDateFormat simple = new SimpleDateFormat(format);
		return simple.format(calendar.getTime());
	}

	/**
	 * 
	 * @return yyyy-MM-dd HH:mm 
	 * 2012-12-29 23:47
	 */
	public static String getDateAndMinute() {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return simple.format(calendar.getTime());
	}

	/**
	 * 
	 * @return
	 *  yyyy-MM-dd HH:mm:ss 
	 *  2012-12-29 23:47:36
	 */
	public static String getFullDate() {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simple.format(calendar.getTime());
	}

	/**
	 * 距离今天多久
	 * @param date
	 * @return 
	 * 
	 */
	public static String fromToday(String dateStr) {
		SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf= new SimpleDateFormat("MM-dd HH:mm");
		Date date=new Date();
		try {
			date =sdf1.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		long time = date.getTime() / 1000;
		long now = new Date().getTime() / 1000;
		long ago = now - time;
		if(ago<=ONE_MINUTE)
			return "刚刚";
		else if (ago <= ONE_HOUR)
			return ago / ONE_MINUTE + "分钟�?";
		else if (ago <= ONE_DAY)
			return ago / ONE_HOUR + "小时�?";
		else if (ago <= ONE_MONTH)
			return ago / ONE_DAY +"天前";
		else
			return sdf.format(date);
	}

	/**
	 * 距离截止日期还有多长时间
	 * 
	 * @param date
	 * @return
	 */
	public static String fromDeadline(Date date) {
		long deadline = date.getTime() / 1000;
		long now = (new Date().getTime()) / 1000;
		long remain = deadline - now;
		if (remain <= ONE_HOUR)
			return "只剩�?" + remain / ONE_MINUTE + "分钟";
		else if (remain <= ONE_DAY)
			return "只剩�?" + remain / ONE_HOUR + "小时"
					+ (remain % ONE_HOUR / ONE_MINUTE) + "分钟";
		else {
			long day = remain / ONE_DAY;
			long hour = remain % ONE_DAY / ONE_HOUR;
			long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;
			return "只剩�?" + day + "�?" + hour + "小时" + minute + "分钟";
		}

	}
	public static String getNow(){
		return new Date().getTime()+"";
	}
	public static void main(String[] args) throws ParseException {
//		String deadline = "2012-12-30 12:45:59";
		Date date = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		date = simple.parse(deadline);
//		System.out.println(DateUtils.fromDeadline(date));

		String before = "2015-8-12 18:45:00";
		date = simple.parse(before);
		System.out.println(DateUtils.fromToday(before));
		System.out.println(getNow());
//		System.out.println(DateUtils.getFullDate());
//		System.out.println(DateUtils.getDate());
	}

}
