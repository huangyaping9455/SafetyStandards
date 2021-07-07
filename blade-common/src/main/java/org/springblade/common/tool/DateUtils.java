/**
 * Copyright (C), 2015-2021
 * FileName: aaa
 * Author:   呵呵哒
 * Date:     2021/1/29 15:51
 * Description:
 */
package org.springblade.common.tool;

/**
 * @创建人 hyp
 * @创建时间 2021/1/29
 * @描述
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		Date now = new Date();
		System.out.println("当前日期：" + DATE_FORMAT.format(now));
		Date newDate = stepMonth(now, -13);
		System.out.println("当前时间前13个月的日期：" + DATE_FORMAT.format(newDate));
	}

	/**
	 * 在给定的日期加上或减去指定月份后的日期
	 *
	 * @param sourceDate 原始时间
	 * @param month      要调整的月份，向前为负数，向后为正数
	 * @return
	 */
	public static Date stepMonth(Date sourceDate, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(sourceDate);
		c.add(Calendar.MONTH, month);

		return c.getTime();
	}

	/**
	 * 获取过去或者未来 任意天内的日期数组
	 * @param intervals      intervals天内
	 * @return              日期数组
	 */
	public static ArrayList<String> test(int intervals ) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		ArrayList<String> fetureDaysList = new ArrayList<>();
		for (int i = 0; i <intervals; i++) {
			pastDaysList.add(getPastDate(i));
			fetureDaysList.add(getFetureDate(i));
		}
		return pastDaysList;
	}

	/**
	 * 获取过去第几天的日期
	 *
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	/**
	 * 获取未来 第 past 天的日期
	 * @param past
	 * @return
	 */
	public static String getFetureDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}
}
