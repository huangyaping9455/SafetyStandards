/**
 * Copyright (C), 2015-2020
 * FileName: FileUPLoad
 * Author:   呵呵哒
 * Date:     2020/11/16 14:50
 * Description:
 */
package org.springblade.common.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;
/**
 * @创建人 hyp
 * @创建时间 2020/11/16
 * @描述
 */

public class FileUPLoad extends XpError {
	public FileUPLoad() {
	}

	public List<String[]> ImportExcel(MultipartFile var1, int var2, ServletWebRequest var3) {
		List var4 = null;

		try {
			InputStream var5 = var1.getInputStream();
			var4 = this.readExcel(var5, var2);
			return this.isError() ? null : var4;
		} catch (IOException var6) {
			this.errHandler(var6.getMessage(), (long)var6.hashCode());
			return null;
		}
	}

	public List<String[]> readExcel(InputStream var1, int var2) {
		ArrayList var3 = new ArrayList();
		Workbook var4 = null;
		Cell var5 = null;

		try {
			var4 = Workbook.getWorkbook(var1);
			Sheet var7 = var4.getSheet(0);

			for(int var8 = var2 - 1; var8 < var7.getRows(); ++var8) {
				String[] var9 = new String[var7.getColumns()];

				for(int var10 = 0; var10 < var7.getColumns(); ++var10) {
					var5 = var7.getCell(var10, var8);
					var9[var10] = var5.getContents();
				}

				var3.add(var9);
			}

			return var3;
		} catch (IOException var11) {
			this.errHandler(var11.getMessage(), (long)var11.hashCode());
			return null;
		} catch (BiffException var12) {
			this.errHandler(var12.getMessage(), (long)var12.hashCode());
			return null;
		} catch (Exception var13) {
			var13.printStackTrace();
			if (var13.getMessage() == null) {
				this.errHandler("未知异常错误:可能文件格式不能解析!", (long)var13.hashCode());
			} else {
				this.errHandler(var13.getMessage(), (long)var13.hashCode());
			}

			return null;
		}
	}

	public String SaveFile(MultipartFile var1, ServletWebRequest var2) {
		String var3 = String.valueOf(System.currentTimeMillis());
		String var4 = "data/upload/default/";
		return this.SaveFile(var1, var3, var4, var2);
	}

	public String SaveFile(MultipartFile var1, String var2, String var3, ServletWebRequest var4) {
		String var5 = "";
		HttpServletRequest var6 = var4.getRequest();
		String var7 = var6.getServletContext().getRealPath("/");
		if (var1 != null) {
			InputStream var8 = null;

			try {
				var8 = var1.getInputStream();
			} catch (IOException var16) {
				var16.printStackTrace();
			}

			String var9 = var1.getOriginalFilename();
			int var10 = var9.lastIndexOf(".");
			String var11 = var9.substring(var10);
			var5 = var3 + var2 + var11;
			File var12 = new File(var7 + var5);

			try {
				FileOutputStream var13 = new FileOutputStream(var12);
				byte[] var14 = new byte[10240];
				boolean var15 = false;

				int var19;
				while((var19 = var8.read(var14)) != -1) {
					var13.write(var14, 0, var19);
				}

				var13.flush();
				var13.close();
				var8.close();
			} catch (FileNotFoundException var17) {
				var17.printStackTrace();
			} catch (IOException var18) {
				var18.printStackTrace();
			}
		}

		return var5;
	}

	public String SaveFile(MultipartFile var1, String var2) {
		if (var1 != null) {
			InputStream var3 = null;

			try {
				var3 = var1.getInputStream();
			} catch (IOException var8) {
				var8.printStackTrace();
			}

			File var4 = new File(var2);

			try {
				FileOutputStream var5 = new FileOutputStream(var4);
				byte[] var6 = new byte[10240];
				boolean var7 = false;

				int var11;
				while((var11 = var3.read(var6)) != -1) {
					var5.write(var6, 0, var11);
				}

				var5.flush();
				var5.close();
				var3.close();
			} catch (FileNotFoundException var9) {
				var9.printStackTrace();
			} catch (IOException var10) {
				var10.printStackTrace();
			}
		}

		return var2;
	}

	public String SaveFile(InputStream var1, String var2) {
		File var3 = new File(var2);

		try {
			FileOutputStream var4 = new FileOutputStream(var3);
			byte[] var5 = new byte[10240];
			boolean var6 = false;

			int var9;
			while((var9 = var1.read(var5)) != -1) {
				var4.write(var5, 0, var9);
			}

			var4.flush();
			var4.close();
			var1.close();
		} catch (FileNotFoundException var7) {
			var7.printStackTrace();
		} catch (IOException var8) {
			var8.printStackTrace();
		}

		return var2;
	}

	public String DownFile(String var1, InputStream var2, HttpServletRequest var3, HttpServletResponse var4) {
		String var5 = var3.getServletContext().getRealPath("/");

		try {
			ServletOutputStream var6 = var4.getOutputStream();
			if (var3.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				var1 = new String(var1.getBytes("UTF-8"), "ISO8859-1");
			} else if (var3.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				var1 = URLEncoder.encode(var1, "UTF-8");
			} else {
				var1 = new String(var1.getBytes("UTF-8"), "ISO8859-1");
			}

			var4.setContentType(this._$1(var1));
			var4.setHeader("Content-disposition", this._$2(var1) + ";filename=" + var1);
			byte[] var7 = new byte[1024];
			boolean var8 = false;

			int var10;
			while(0 <= (var10 = var2.read(var7))) {
				var6.write(var7, 0, var10);
			}

			var6.flush();
			var6.close();
			var2.close();
			return "ok";
		} catch (Exception var9) {
			var9.printStackTrace();
			this.errHandler(var9.getMessage().replace(var5, ""), (long)var9.hashCode());
			return null;
		}
	}

	private String _$2(String var1) {
		String var2 = var1.substring(var1.lastIndexOf(".") + 1);
		String var3 = "attachment";
		var2 = var2.toLowerCase();
		if (var2 == ".jpg") {
			var3 = "inline";
		} else if (var2 == ".png") {
			var3 = "inline";
		} else if (var2 == ".bmp") {
			var3 = "inline";
		} else if (var2 == ".gif") {
			var3 = "inline";
		} else {
			var3 = "attachment";
		}

		return var3;
	}

	private String _$1(String var1) {
		String var2 = var1.substring(var1.lastIndexOf(".") + 1);
		String var3 = "application/x-download";
		var2 = var2.toLowerCase();
		if (var2 == ".jpg") {
			var3 = "image/x-jpeg";
		} else if (var2 == ".png") {
			var3 = "image/x-png";
		} else if (var2 == ".bmp") {
			var3 = "image/x-bmp";
		} else if (var2 == ".gif") {
			var3 = "image/gif";
		} else {
			var3 = "application/x-download";
		}

		return var3;
	}
}
