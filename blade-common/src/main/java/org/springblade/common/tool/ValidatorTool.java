/**
 * Copyright (C), 2015-2020
 * FileName: ValidatorTool
 * Author:   呵呵哒
 * Date:     2020/11/16 14:42
 * Description:
 */
package org.springblade.common.tool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
/**
 * @创建人 hyp
 * @创建时间 2020/11/16
 * @描述
 */
public class ValidatorTool {
	public ValidatorTool() {
	}

	public static boolean validateRequired(String var0) {
		return StringUtils.isNotBlank(var0);
	}

	public static boolean validateChepaiBlank(String var0) {
		if (StringUtils.isBlank(var0)) {
			return true;
		} else {
			try {
				Pattern var1 = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$");
				Matcher var2 = var1.matcher(var0);
				return var2.matches();
			} catch (Exception var3) {
				return false;
			}
		}
	}

	public static boolean validateChepai(String var0) {
		if (StringUtils.isBlank(var0)) {
			return false;
		} else {
			try {
				Pattern var1 = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$");
				Matcher var2 = var1.matcher(var0);
				return var2.matches();
			} catch (Exception var3) {
				return false;
			}
		}
	}

	public static boolean validateIntegerBlank(String var0) {
		if (StringUtils.isBlank(var0)) {
			return true;
		} else {
			try {
				return validateInteger(var0, (Integer)null, (Integer)null);
			} catch (Exception var2) {
				return false;
			}
		}
	}

	public static boolean validateInteger(String var0) {
		if (StringUtils.isBlank(var0)) {
			return false;
		} else {
			try {
				return validateInteger(var0, (Integer)null, (Integer)null);
			} catch (Exception var2) {
				return false;
			}
		}
	}

	public static boolean validateInteger(String var0, Integer var1, Integer var2) {
		if (StringUtils.isBlank(var0)) {
			return false;
		} else {
			try {
				int var3 = Integer.parseInt(var0.trim());
				if (var1 != null && var3 < var1) {
					return false;
				} else {
					return var2 == null || var3 <= var2;
				}
			} catch (Exception var4) {
				return false;
			}
		}
	}

	public static boolean validateLongBlank(String var0) {
		if (StringUtils.isBlank(var0)) {
			return true;
		} else {
			try {
				return validateLong(var0, (Long)null, (Long)null);
			} catch (Exception var2) {
				return false;
			}
		}
	}

	public static boolean validateLong(String var0) {
		if (StringUtils.isBlank(var0)) {
			return false;
		} else {
			try {
				return validateLong(var0, (Long)null, (Long)null);
			} catch (Exception var2) {
				return false;
			}
		}
	}

	public static boolean validateLong(String var0, Long var1, Long var2) {
		if (StringUtils.isBlank(var0)) {
			return false;
		} else {
			try {
				long var3 = Long.parseLong(var0.trim());
				if (var1 != null && var3 < var1) {
					return false;
				} else {
					return var2 == null || var3 <= var2;
				}
			} catch (Exception var5) {
				return false;
			}
		}
	}

	public static boolean validateDoubleBlank(String var0) {
		if (StringUtils.isBlank(var0)) {
			return true;
		} else {
			try {
				return validateDouble(var0, (Double)null, (Double)null);
			} catch (Exception var2) {
				return false;
			}
		}
	}

	public static boolean validateDouble(String var0) {
		if (StringUtils.isBlank(var0)) {
			return false;
		} else {
			try {
				return validateDouble(var0, (Double)null, (Double)null);
			} catch (Exception var2) {
				return false;
			}
		}
	}

	public static boolean validateDouble(String var0, Double var1, Double var2) {
		if (StringUtils.isBlank(var0)) {
			return false;
		} else {
			try {
				double var3 = Double.parseDouble(var0.trim());
				if (var1 != null && var3 < var1) {
					return false;
				} else {
					return var2 == null || var3 <= var2;
				}
			} catch (Exception var5) {
				return false;
			}
		}
	}

	public static boolean validateDateBlank(String var0) {
		if (StringUtils.isBlank(var0)) {
			return true;
		} else {
			try {
				String var1 = "yyyy-MM-dd";
				return validateDate(var0, (Date)null, (Date)null, var1);
			} catch (Exception var2) {
				return false;
			}
		}
	}

	public static boolean validateDateBlank(String var0, String var1) {
		if (StringUtils.isBlank(var0)) {
			return true;
		} else {
			try {
				if (StringUtils.isBlank(var1)) {
					var1 = "yyyy-MM-dd";
				}

				return validateDate(var0, (Date)null, (Date)null, var1);
			} catch (Exception var3) {
				return false;
			}
		}
	}

	public static boolean validateDateBlank(String var0, Date var1, Date var2, String var3) {
		if (StringUtils.isBlank(var0)) {
			return true;
		} else {
			try {
				if (StringUtils.isBlank(var3)) {
					var3 = "yyyy-MM-dd";
				}

				SimpleDateFormat var4 = new SimpleDateFormat(var3);
				Date var5 = var4.parse(var0.trim());
				String var6 = var4.format(var5);
				if (!var0.equals(var6)) {
					return false;
				} else if (var1 != null && var5.before(var1)) {
					return false;
				} else {
					return var2 == null || !var5.after(var2);
				}
			} catch (Exception var7) {
				return false;
			}
		}
	}

	public static boolean validateDate(String var0) {
		if (StringUtils.isBlank(var0)) {
			return false;
		} else {
			try {
				String var1 = "yyyy-MM-dd";
				return validateDate(var0, (Date)null, (Date)null, var1);
			} catch (Exception var2) {
				return false;
			}
		}
	}

	public static boolean validateDate(String var0, String var1) {
		if (StringUtils.isBlank(var0)) {
			return false;
		} else {
			try {
				if (StringUtils.isBlank(var1)) {
					var1 = "yyyy-MM-dd";
				}

				return validateDate(var0, (Date)null, (Date)null, var1);
			} catch (Exception var3) {
				return false;
			}
		}
	}

	public static boolean validateDate(String var0, Date var1, Date var2, String var3) {
		if (StringUtils.isBlank(var0)) {
			return false;
		} else {
			try {
				if (StringUtils.isBlank(var3)) {
					var3 = "yyyy-MM-dd";
				}

				SimpleDateFormat var4 = new SimpleDateFormat(var3);
				Date var5 = var4.parse(var0.trim());
				String var6 = var4.format(var5);
				if (!var0.equals(var6)) {
					return false;
				} else if (var1 != null && var5.before(var1)) {
					return false;
				} else {
					return var2 == null || !var5.after(var2);
				}
			} catch (Exception var7) {
				return false;
			}
		}
	}

	public static boolean validateEqualField(String var0, String var1) {
		if (!StringUtils.isBlank(var0) && !StringUtils.isBlank(var1)) {
			return var0.equals(var1);
		} else {
			return false;
		}
	}

	public static boolean validateEqualInteger(Integer var0, Integer var1) {
		if (var0 != null && var1 != null) {
			return var0 == var1;
		} else {
			return false;
		}
	}

	public static boolean validateEmailBlank(String var0) {
		return StringUtils.isBlank(var0) ? true : validateEmail(var0);
	}

	public static boolean validateEmail(String var0) {
		return validateRegex(var0, "\\b(^['_A-Za-z0-9-]+(\\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b");
	}

	public static boolean validateUrlBlank(String var0) {
		return StringUtils.isBlank(var0) ? true : validateUrl(var0);
	}

	public static boolean validateUrl(String var0) {
		return validateRegex(var0, "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
	}

	public static boolean validateMobileBlank(String var0) {
		return StringUtils.isBlank(var0) ? true : validateMobile(var0);
	}

	public static boolean validateMobile(String var0) {
		return validateRegex(var0, "^0?[1][34578]\\d{9}$");
	}

	public static boolean validateQqBlank(String var0) {
		return StringUtils.isBlank(var0) ? true : validateQq(var0);
	}

	public static boolean validateQq(String var0) {
		return validateRegex(var0, "^[1-9]\\d{4,10}$");
	}

	public static boolean validateChineseBlank(String var0) {
		return StringUtils.isBlank(var0) ? true : validateChinese(var0);
	}

	public static boolean validateChinese(String var0) {
		return validateRegex(var0, "^[Α-￥]+$");
	}

	public static boolean validateTelephoneBlank(String var0) {
		return StringUtils.isBlank(var0) ? true : validateTelephone(var0);
	}

	public static boolean validateTelephone(String var0) {
		return validateRegex(var0, "^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$");
	}

	public static boolean validatePhoneBlank(String var0) {
		return StringUtils.isBlank(var0) ? true : validatePhone(var0);
	}

	public static boolean validatePhone(String var0) {
		return validateMobile(var0) || validateTelephone(var0);
	}

	public static boolean validateIDNumberBlank(String var0) {
		return StringUtils.isBlank(var0) ? true : validateIDNumber(var0);
	}

	public static boolean validateIDNumber(String var0) {
		return validateRegex(var0, "^(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])$");
	}

	public static boolean validateRegex(String var0, String var1) {
		if (!StringUtils.isBlank(var0) && !StringUtils.isBlank(var1)) {
			Pattern var2 = Pattern.compile(var1);
			Matcher var3 = var2.matcher(var0);
			return var3.matches();
		} else {
			return false;
		}
	}

	public static boolean validateBooleanValue(String var0) {
		if (StringUtils.isBlank(var0)) {
			return false;
		} else {
			var0 = var0.trim().toLowerCase();
			return "1".equals(var0) || "true".equals(var0) || "0".equals(var0) || "false".equals(var0);
		}
	}

	public static boolean validateBrowser(String var0) {
		if (StringUtils.isBlank(var0)) {
			return false;
		} else if (var0.toLowerCase().indexOf("firefox") > 0) {
			return true;
		} else {
			return var0.toUpperCase().indexOf("MSIE") > 0 || var0.toUpperCase().indexOf("RV:11") > 0;
		}
	}
}

