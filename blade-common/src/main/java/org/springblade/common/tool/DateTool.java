/**
 * Copyright (C), 2015-2020
 * FileName: DateTool
 * Author:   呵呵哒
 * Date:     2020/11/16 14:28
 * Description:
 */
package org.springblade.common.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 * @创建人 hyp
 * @创建时间 2020/11/16
 * @描述
 */

public class DateTool {
	public DateTool() {
	}

	public static String getNow() {
		return getNow("yyyy-MM-dd HH:mm:ss");
	}

	public static String getNow(String var0) {
		String var1 = "";

		try {
			if (StringUtils.isBlank(var0)) {
				var0 = "yyyy-MM-dd HH:mm:ss";
			}

			var1 = (new SimpleDateFormat(var0)).format(new Date());
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		return var1;
	}

	public static String getMonth(String var0, String var1) {
		SimpleDateFormat var2 = new SimpleDateFormat(var1);

		try {
			Date var3 = var2.parse(var0);
			Calendar var4 = Calendar.getInstance();
			var4.setTime(var3);
			int var5 = var4.get(2) + 1;
			return Integer.toString(var5);
		} catch (ParseException var6) {
			var6.printStackTrace();
			return null;
		}
	}

	public static String getNowLastMonth(String var0) {
		String var1 = "";

		try {
			if (StringUtils.isBlank(var0)) {
				var0 = "yyyy-MM-dd HH:mm:ss";
			}

			Calendar var2 = Calendar.getInstance();
			var2.add(2, -1);
			Date var3 = var2.getTime();
			var1 = (new SimpleDateFormat(var0)).format(var3);
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		return var1;
	}

	public static String formatDate(String var0, String var1, String var2) {
		String var3 = "";

		try {
			if (StringUtils.isBlank(var0)) {
				return "";
			}

			if (StringUtils.isBlank(var1)) {
				var1 = "yyyy-MM-dd HH:mm:ss";
			}

			if (StringUtils.isBlank(var2)) {
				var2 = "yyyy-MM-dd";
			}

			Date var4 = (new SimpleDateFormat(var1)).parse(var0);
			var3 = (new SimpleDateFormat(var2)).format(var4);
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		return var3;
	}

	public static String getLastDayofMonth(Integer var0, Integer var1, String var2) {
		String var3 = "";

		try {
			if (StringUtils.isBlank(var2)) {
				var2 = "yyyy-MM-dd";
			}

			Calendar var4 = Calendar.getInstance();
			if (var0 == null || var0 == 0) {
				var0 = var4.get(1);
			}

			if (var1 == null || var1 == 0) {
				var1 = var4.get(2) + 1;
			}

			var4.set(1, var0);
			var4.set(2, var1);
			var4.set(5, 1);
			var4.add(5, -1);
			Date var5 = var4.getTime();
			var3 = (new SimpleDateFormat(var2)).format(var5);
		} catch (Exception var6) {
			var6.printStackTrace();
		}

		return var3;
	}

	public static int getLastDayofMonth(Integer var0, Integer var1) {
		int var2 = -1;

		try {
			Calendar var3 = Calendar.getInstance();
			if (var0 == null || var0 == 0) {
				var0 = var3.get(1);
			}

			if (var1 == null || var1 == 0) {
				var1 = var3.get(2) + 1;
			}

			var3.set(1, var0);
			var3.set(2, var1);
			var3.set(5, 1);
			var3.add(5, -1);
			var2 = var3.get(5);
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		return var2;
	}

	public static String getFirstDayofMonth(Integer var0, Integer var1, String var2) {
		String var3 = "";

		try {
			if (StringUtils.isBlank(var2)) {
				var2 = "yyyy-MM-dd";
			}

			Calendar var4 = Calendar.getInstance();
			if (var0 == null || var0 == 0) {
				var0 = var4.get(1);
			}

			if (var1 == null || var1 == 0) {
				var1 = var4.get(2) + 1;
			}

			var4.set(1, var0);
			var4.set(2, var1 - 1);
			var4.set(5, 1);
			Date var5 = var4.getTime();
			var3 = (new SimpleDateFormat(var2)).format(var5);
		} catch (Exception var6) {
			var6.printStackTrace();
		}

		return var3;
	}

	public static int getDayofWeek(String var0, String var1) {
		int var2 = -1;

		try {
			if (StringUtils.isBlank(var1)) {
				var1 = "yyyy-MM-dd";
			}

			Date var3 = new Date();
			if (StringUtils.isNotBlank(var0)) {
				var3 = (new SimpleDateFormat(var1)).parse(var0);
			}

			Calendar var4 = Calendar.getInstance();
			var4.setTime(var3);
			var2 = var4.get(7) - 1;
			if (var2 == 0) {
				var2 = 7;
			}
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		return var2;
	}

	public static int GetYear(String var0, String var1) {
		int var2 = -1;

		try {
			if (StringUtils.isBlank(var1)) {
				var1 = "yyyy-MM-dd";
			}

			Date var3 = new Date();
			if (StringUtils.isNotBlank(var0)) {
				var3 = (new SimpleDateFormat(var1)).parse(var0);
			}

			Calendar var4 = Calendar.getInstance();
			var4.setTime(var3);
			var2 = var4.get(1);
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		return var2;
	}

	public static int GetYear(Date var0) {
		int var1 = -1;

		try {
			Calendar var2 = Calendar.getInstance();
			var2.setTime(var0);
			var1 = var2.get(1);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		return var1;
	}

	public static int GetYear() {
		int var0 = -1;

		try {
			Calendar var1 = Calendar.getInstance();
			var1.setTime(new Date());
			var0 = var1.get(1);
		} catch (Exception var2) {
			var2.printStackTrace();
		}

		return var0;
	}

	public static int GetMonth(String var0, String var1) {
		int var2 = -1;

		try {
			if (StringUtils.isBlank(var1)) {
				var1 = "yyyy-MM-dd";
			}

			Date var3 = new Date();
			if (StringUtils.isNotBlank(var0)) {
				var3 = (new SimpleDateFormat(var1)).parse(var0);
			}

			Calendar var4 = Calendar.getInstance();
			var4.setTime(var3);
			var2 = var4.get(2) + 1;
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		return var2;
	}

	public static int GetDay(String var0, String var1) {
		int var2 = -1;

		try {
			if (StringUtils.isBlank(var1)) {
				var1 = "yyyy-MM-dd";
			}

			Date var3 = new Date();
			if (StringUtils.isNotBlank(var0)) {
				var3 = (new SimpleDateFormat(var1)).parse(var0);
			}

			Calendar var4 = Calendar.getInstance();
			var4.setTime(var3);
			var2 = var4.get(5);
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		return var2;
	}

	public static int getYearDay(int var0) {
		return var0 % 4 != 0 || var0 % 100 == 0 && var0 % 400 != 0 ? 365 : 366;
	}

	public static int getMonthDay(Integer var0, Integer var1) {
		int var2 = -1;

		try {
			Calendar var3 = Calendar.getInstance();
			if (var0 == null || var0 == 0) {
				var0 = var3.get(1);
			}

			if (var1 == null || var1 == 0) {
				var1 = var3.get(2) + 1;
			}

			var3.set(1, var0);
			var3.set(2, var1);
			var3.set(5, 1);
			var3.add(5, -1);
			var2 = var3.get(5);
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		return var2;
	}

	public static int getDays(String var0, String var1) {
		int var2 = -1;

		try {
			Calendar var3 = Calendar.getInstance();
			if (StringUtils.isBlank(var1)) {
				var1 = "yyyy-MM-dd";
			}

			Date var4 = new Date();
			if (StringUtils.isNotBlank(var0)) {
				var4 = (new SimpleDateFormat(var1)).parse(var0);
			}

			var3.setTime(var4);
			var3.set(2, var3.get(2) + 1);
			var3.set(5, 1);
			var3.add(5, -1);
			var2 = var3.get(5);
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		return var2;
	}

	public static boolean isSameDateDay(String var0, String var1, String var2, String var3) {
		boolean var4 = false;

		try {
			if (StringUtils.isBlank(var0) || StringUtils.isBlank(var2)) {
				return false;
			}

			if (StringUtils.isBlank(var1)) {
				var1 = "yyyy-MM-dd";
			}

			if (StringUtils.isBlank(var3)) {
				var3 = "yyyy-MM-dd";
			}

			Date var5 = (new SimpleDateFormat(var1)).parse(var0);
			Date var6 = (new SimpleDateFormat(var3)).parse(var2);
			if (var5.getTime() == var6.getTime()) {
				var4 = true;
			}
		} catch (Exception var7) {
			var7.printStackTrace();
		}

		return var4;
	}

	public static Date StrToDate(String var0, String var1) {
		Date var2 = null;

		try {
			if (StringUtils.isBlank(var0)) {
				return null;
			}

			if (StringUtils.isBlank(var1)) {
				var1 = "yyyy-MM-dd";
			}

			var2 = (new SimpleDateFormat(var1)).parse(var0);
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		return var2;
	}

	public static String DateToStr(Date var0, String var1) {
		String var2 = "";

		try {
			if (var0 == null) {
				return var2;
			}

			if (StringUtils.isBlank(var1)) {
				var1 = "yyyy-MM-dd";
			}

			var2 = (new SimpleDateFormat(var1)).format(var0);
		} catch (Exception var4) {
			var4.printStackTrace();
		}

		return var2;
	}

	public static String addDate(String var0, String var1, Integer var2, Integer var3, Integer var4) {
		String var5 = "";

		try {
			if (StringUtils.isBlank(var0)) {
				return "";
			}

			if (StringUtils.isBlank(var1)) {
				var1 = "yyyy-MM-dd";
			}

			if (var2 == null) {
				var2 = 0;
			}

			if (var3 == null) {
				var3 = 0;
			}

			if (var4 == null) {
				var4 = 0;
			}

			SimpleDateFormat var6 = new SimpleDateFormat(var1);
			Date var7 = var6.parse(var0);
			Calendar var8 = Calendar.getInstance();
			var8.setTime(var7);
			var8.add(1, var2);
			var8.add(2, var3);
			var8.add(5, var4);
			var5 = var6.format(var8.getTime());
		} catch (Exception var9) {
			var9.printStackTrace();
		}

		return var5;
	}

	public static String addTime(String var0, String var1, Integer var2, Integer var3, Integer var4) {
		String var5 = "";

		try {
			if (StringUtils.isBlank(var0)) {
				return "";
			}

			if (StringUtils.isBlank(var1)) {
				var1 = "yyyy-MM-ss HH:mm:ss";
			}

			if (var2 == null) {
				var2 = 0;
			}

			if (var3 == null) {
				var3 = 0;
			}

			if (var4 == null) {
				var4 = 0;
			}

			SimpleDateFormat var6 = new SimpleDateFormat(var1);
			Date var7 = var6.parse(var0);
			Calendar var8 = Calendar.getInstance();
			var8.setTime(var7);
			var8.add(10, var2);
			var8.add(12, var3);
			var8.add(13, var4);
			var5 = var6.format(var8.getTime());
		} catch (Exception var9) {
			var9.printStackTrace();
		}

		return var5;
	}

	public static boolean isDateBefore(String var0, String var1, String var2) {
		boolean var3 = false;

		try {
			if (StringUtils.isBlank(var0) || StringUtils.isBlank(var1)) {
				return false;
			}

			if (StringUtils.isBlank(var2)) {
				var2 = "yyyy-MM-dd";
			}

			SimpleDateFormat var4 = new SimpleDateFormat(var2);
			Date var5 = var4.parse(var0);
			Date var6 = var4.parse(var1);
			var3 = var5.before(var6);
		} catch (Exception var7) {
			var7.printStackTrace();
		}

		return var3;
	}

	public static boolean isDateBeforeBlank(String var0, String var1, String var2) {
		boolean var3 = false;

		try {
			if (StringUtils.isBlank(var0) || StringUtils.isBlank(var1)) {
				return true;
			}

			if (StringUtils.isBlank(var2)) {
				var2 = "yyyy-MM-dd";
			}

			SimpleDateFormat var4 = new SimpleDateFormat(var2);
			Date var5 = var4.parse(var0);
			Date var6 = var4.parse(var1);
			var3 = var5.before(var6);
		} catch (Exception var7) {
			var7.printStackTrace();
		}

		return var3;
	}

	public static boolean DateBetween(String var0, String var1, String var2, String var3) {
		boolean var4 = true;

		try {
			if (StringUtils.isBlank(var0)) {
				return false;
			}

			if (StringUtils.isBlank(var3)) {
				var3 = "yyyy-MM-dd";
			}

			SimpleDateFormat var5 = new SimpleDateFormat(var3);
			Date var6 = var5.parse(var0);
			Date var7;
			if (StringUtils.isNotBlank(var1)) {
				var7 = var5.parse(var1);
				if (var6.compareTo(var7) < 0) {
					var4 = false;
				}
			}

			if (StringUtils.isNotBlank(var2)) {
				var7 = var5.parse(var2);
				if (var7.compareTo(var6) < 0) {
					var4 = false;
				}
			}
		} catch (Exception var8) {
			var8.printStackTrace();
		}

		return var4;
	}

	public static boolean IsDate(String var0, String var1) {
		boolean var2 = false;

		try {
			if (StringUtils.isBlank(var1)) {
				var1 = "yyyy-MM-dd";
			}

			SimpleDateFormat var3 = new SimpleDateFormat(var1);
			Date var4 = var3.parse(var0);
			if (var4 != null) {
				var2 = true;
			}
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		return var2;
	}

	public static boolean IsDateTime(String var0) {
		boolean var1 = false;
		String var2 = "yyyy-MM-dd HH:mm:ss";

		try {
			SimpleDateFormat var3 = new SimpleDateFormat(var2);
			Date var4 = var3.parse(var0);
			if (var4 != null) {
				var1 = true;
			}
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		return var1;
	}

	public static String getFirstDayOfWeek(String var0, String var1) {
		String var2 = "";

		try {
			if (StringUtils.isBlank(var1)) {
				var1 = "yyyy-MM-dd";
			}

			SimpleDateFormat var3 = new SimpleDateFormat(var1);
			Date var4 = var3.parse(var0);
			Calendar var5 = Calendar.getInstance();
			var5.setTime(var4);
			var5.set(7, 2);
			var2 = var3.format(var5.getTime());
		} catch (Exception var6) {
			var6.printStackTrace();
		}

		return var2;
	}

	public static String getLastDayOfWeek(String var0, String var1) {
		String var2 = "";

		try {
			if (StringUtils.isBlank(var1)) {
				var1 = "yyyy-MM-dd";
			}

			SimpleDateFormat var3 = new SimpleDateFormat(var1);
			Date var4 = var3.parse(var0);
			Calendar var5 = Calendar.getInstance();
			var5.setTime(var4);
			var5.set(7, 1);
			var5.add(5, 7);
			var2 = var3.format(var5.getTime());
		} catch (Exception var6) {
			var6.printStackTrace();
		}

		return var2;
	}

	public static String[] DateArySort(String[] var0, String var1) {
		try {
			if (StringUtils.isBlank(var1)) {
				var1 = "yyyy-MM-dd";
			}

			IlIIIIIIllIlIllI var2 = new IlIIIIIIllIlIllI(var1);
			Arrays.sort(var0, var2);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		return var0;
	}
}

