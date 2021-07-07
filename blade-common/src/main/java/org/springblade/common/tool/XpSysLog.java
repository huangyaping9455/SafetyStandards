/**
 * Copyright (C), 2015-2020
 * FileName: XpSysLog
 * Author:   呵呵哒
 * Date:     2020/11/16 14:47
 * Description:
 */
package org.springblade.common.tool;

/**
 * @创建人 hyp
 * @创建时间 2020/11/16
 * @描述
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

public class XpSysLog extends XpError {
	public static int LogType_Day = 1;
	public static int LogType_Week = 2;
	public static int LogType_Month = 3;
	public static int LogType_Season = 4;
	public static int LogType_Year = 5;
	private String _$2 = "";
	private HttpServletRequest _$1 = null;

	public XpSysLog(HttpServletRequest var1) {
		this._$1 = var1;
		this._$2 = this._$1.getServletContext().getRealPath("/");
	}

	public void setLog(String var1) {
		SimpleDateFormat var2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String var3 = this._$1.getRequestURI();
		String var4 = var2.format(new Date());
		String var5 = this._$1.getRemoteHost();
		String var6 = var4 + ", " + var3 + "," + var5;
		this._$1(LogType_Day, "Msg_", var6 + var1);
		if (this.isError()) {
			this.errHandler(this.errMsg(), this.errCode());
		}
	}

	public void setOKLog(String var1) {
		SimpleDateFormat var2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String var3 = this._$1.getRequestURI();
		String var4 = var2.format(new Date());
		String var5 = this._$1.getRemoteHost();
		String var6 = var4 + ", " + var3 + "," + var5;
		this._$1(LogType_Day, "SUC_", var6 + var1);
		if (this.isError()) {
			this.errHandler(this.errMsg(), this.errCode());
		}
	}

	public void setErrLog(String var1, long var2) {
		SimpleDateFormat var4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String var5 = this._$1.getRequestURI();
		String var6 = var4.format(new Date());
		String var7 = this._$1.getRemoteHost();
		String var8 = var6 + ", " + var5 + ", " + var7 + ", " + var2 + ", " + var1;
		this._$1(LogType_Day, "ERR_", var8 + var1);
		if (this.isError()) {
			this.errHandler(this.errMsg(), this.errCode());
		}
	}

	private void _$1(int var1, String var2, String var3) {
		String var4 = this._$2 + "/data/logfiles/";
		String var5 = var4 + this._$1(var1, var2);

		try {
			File var6 = new File(var4);
			if (!var6.exists()) {
				var6.mkdirs();
			}

			var6 = new File(var5);
			if (!var6.exists()) {
				var6.createNewFile();
			}

			PrintWriter var7 = new PrintWriter(new FileOutputStream(var6, true));
			var7.println(var3);
			var7.close();
		} catch (IOException var8) {
			this.errHandler("写日志出错" + var8.getMessage(), 87610L);
		}
	}

	private String _$1(int var1, String var2) {
		Calendar var3 = Calendar.getInstance();
		var3.setTime(new Date());
		String var4 = "";
		int var5;
		int var7;
		int var9;
		switch(var1) {
			case 1:
				var5 = var3.get(5);
				var9 = var3.get(2) + 1;
				var7 = var3.get(1);
				var4 = var2 + "Day" + var7 + "_" + var9 + "_" + var5;
				break;
			case 2:
				var5 = var3.get(3);
				var4 = var2 + "Week" + var5;
				break;
			case 3:
				var5 = var3.get(2) + 1;
				var4 = var2 + "Month" + var5;
				break;
			case 4:
				var5 = var3.get(2) + 1;
				boolean var6 = true;
				byte var8;
				if (var5 <= 3) {
					var8 = 1;
				} else if (var5 <= 6) {
					var8 = 2;
				} else if (var5 <= 9) {
					var8 = 3;
				} else if (var5 <= 12) {
					var8 = 4;
				} else {
					var8 = 1;
				}

				var4 = var2 + "Season" + var8;
				break;
			case 5:
				var5 = var3.get(1) + 1;
				var4 = var2 + "Year" + var5;
				break;
			default:
				var5 = var3.get(5);
				var9 = var3.get(2) + 1;
				var7 = var3.get(1);
				var4 = var2 + "Day" + var7 + "_" + var9 + "_" + var5;
		}

		var4 = var4 + ".log";
		return var4;
	}
}
