/**
 * Copyright (C), 2015-2020
 * FileName: ExportExcel
 * Author:   呵呵哒
 * Date:     2020/11/16 14:46
 * Description:
 */
package org.springblade.common.tool;

import jxl.report.ReportEnginer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * @创建人 hyp
 * @创建时间 2020/11/16
 * @描述
 */

public class ExportExcel extends XpError {
	public ExportExcel() {
	}

	public String exportExcel(String var1, String var2, Map<String, Object> var3, ServletWebRequest var4) {
		ReportEnginer var5 = new ReportEnginer();
		HttpServletRequest var6 = var4.getRequest();
		HttpServletResponse var7 = var4.getResponse();
		FileInputStream var8 = null;
		XpSysLog var9 = new XpSysLog(var6);

		try {
			var9.setLog("开始生成EXCEL:" + var2);
			String var10 = var6.getServletContext().getRealPath("/");
			String var11 = var10 + var1;
			File var12 = new File(var11);
			if (!var12.exists()) {
				this.errHandler(var1 + "(系统找不到指定的文件。)", 400411L);
				return "";
			}

			var8 = new FileInputStream(var11);
			ServletOutputStream var13 = var7.getOutputStream();
			String var15 = var6.getHeader("User-Agent");
			String var14 = this._$1(var2, var15);
			var7.setContentType("application/x-download");
			var7.setHeader("Content-disposition", "attachment;filename=" + var14);
			var5.excute(var8, var3, var13);
			var8.close();
			var9.setLog("下载EXCEL:" + var2 + " 成功!");
		} catch (IOException var16) {
			this._$1((InputStream)var8);
			var16.printStackTrace();
			var9.setErrLog("生成EXCEL:" + var1 + "失败!", 4001001L);
		} catch (InvalidFormatException var17) {
			this._$1((InputStream)var8);
			var17.printStackTrace();
			var9.setErrLog("生成EXCEL:" + var1 + "失败!", 4001001L);
		} catch (Exception var18) {
			this._$1((InputStream)var8);
			var18.printStackTrace();
			var9.setErrLog("生成EXCEL:" + var1 + "失败!", 4001001L);
		}

		return "";
	}

	public String exportExcel2(String var1, String var2, List<String> var3, List<String[]> var4, ServletWebRequest var5) {
		HttpServletRequest var6 = var5.getRequest();
		HttpServletResponse var7 = var5.getResponse();
		FileInputStream var8 = null;
		XpSysLog var9 = new XpSysLog(var6);

		try {
			var9.setLog("开始生成EXCEL:" + var2);
			String var10 = var6.getServletContext().getRealPath("/");
			String var11 = var10 + var1;
			File var12 = new File(var11);
			if (!var12.exists()) {
				this.errHandler(var1 + "(系统找不到指定的文件。)", 400411L);
				return "";
			}

			var8 = new FileInputStream(var11);
			ServletOutputStream var13 = var7.getOutputStream();
			String var15 = var6.getHeader("User-Agent");
			String var14 = this._$1(var2, var15);
			var7.setContentType("application/x-download");
			var7.setHeader("Content-disposition", "attachment;filename=" + var14);
			_$1(var3, var4, (OutputStream)var13);
			var8.close();
			var9.setLog("下载EXCEL:" + var2 + " 成功!");
		} catch (Exception var16) {
			this._$1((InputStream)var8);
			var16.printStackTrace();
			var9.setErrLog("生成EXCEL:" + var1 + "失败!", 4001001L);
		}

		return "";
	}

	public <T> String exportExcel3(String var1, List<String[]> var2, List<T> var3, ServletWebRequest var4) {
		return this.exportExcel3(var1, var2, var3, var4, false);
	}

	public <T> String exportExcel3(String var1, List<String[]> var2, List<T> var3, ServletWebRequest var4, boolean var5) {
		HttpServletRequest var6 = var4.getRequest();
		HttpServletResponse var7 = var4.getResponse();
		XpSysLog var8 = new XpSysLog(var6);

		try {
			var8.setLog("开始生成EXCEL:" + var1);
			ServletOutputStream var9 = var7.getOutputStream();
			String var11 = var6.getHeader("User-Agent");
			XSSFWorkbook var12 = new XSSFWorkbook();
			XSSFSheet var13 = var12.createSheet();
			SXSSFWorkbook var14 = new SXSSFWorkbook(var12, 1000);
			SXSSFSheet var15 = var14.getSheetAt(0);
			_$1(var2, var3, var15);
			var7.setContentType("application/x-download");
			String var10;
			if (var5) {
				ByteArrayOutputStream var16 = new ByteArrayOutputStream();
				var14.write(var16);
				ByteArrayInputStream var17 = new ByteArrayInputStream(var16.toByteArray());
				XSSFWorkbook var18 = new XSSFWorkbook(var17);
				StringBuffer var19 = ExcelToTxt(var18);
				String var20 = Md5.MD5(var19.toString(), 8);
				var1 = var1.substring(0, var1.length() - 5) + "_" + var20 + ".xlsx";
				var10 = this._$1(var1, var11);
				var7.setHeader("Content-disposition", "attachment;filename=" + var10);
				var9.write(var16.toByteArray());
			} else {
				var1 = var1.substring(0, var1.length() - 5) + ".xlsx";
				var10 = this._$1(var1, var11);
				var7.setHeader("Content-disposition", "attachment;filename=" + var10);
				var14.write(var9);
			}

			var9.close();
			var8.setLog("下载EXCEL:" + var1 + " 成功!");
		} catch (Exception var21) {
			var21.printStackTrace();
			var8.setErrLog("生成EXCEL:失败!", 4001001L);
		}

		return "";
	}

	public <T> String exportExcel4(String var1, String var2, List<ExcelSheet> var3, ServletWebRequest var4) {
		return this.exportExcel4(var1, var2, var3, var4, false);
	}

	public <T> String exportExcel4(String var1, String var2, List<ExcelSheet> var3, ServletWebRequest var4, boolean var5) {
		HttpServletRequest var6 = var4.getRequest();
		HttpServletResponse var7 = var4.getResponse();
		FileInputStream var8 = null;
		XpSysLog var9 = new XpSysLog(var6);

		try {
			var9.setLog("开始生成EXCEL:" + var2);
			String var10 = var6.getServletContext().getRealPath("/");
			String var11 = var10 + var1;
			File var12 = new File(var11);
			if (!var12.exists()) {
				this.errHandler(var1 + "(系统找不到指定的文件。)", 400411L);
				return "";
			}

			var8 = new FileInputStream(var11);
			ServletOutputStream var13 = var7.getOutputStream();
			String var15 = var6.getHeader("User-Agent");
			XSSFWorkbook var16 = new XSSFWorkbook(var8);
			XSSFWorkbook var17 = new XSSFWorkbook();
			SXSSFWorkbook var18 = new SXSSFWorkbook(var17, 1000);

			for(int var19 = 0; var19 < var3.size(); ++var19) {
				ExcelSheet var20 = (ExcelSheet)var3.get(var19);
				Map var21 = var20.getTitle();
				List var22 = var20.getHeadList();
				List var23 = var20.getDataList();
				int var24 = var20.getSheetID();
				XSSFSheet var25 = var16.getSheetAt(var24);
				SXSSFSheet var26 = var18.createSheet(var25.getSheetName());

				try {
					_$1((Sheet)var25, (Sheet)var26);
				} catch (Exception var28) {
					var28.printStackTrace();
				}

				SXSSFSheet var27 = var18.getSheetAt(var24);
				_$2(var24, var21, var22, var23, var27);
			}

			var7.setContentType("application/x-download");
			String var14;
			if (var5) {
				ByteArrayOutputStream var31 = new ByteArrayOutputStream();
				var18.write(var31);
				ByteArrayInputStream var32 = new ByteArrayInputStream(var31.toByteArray());
				XSSFWorkbook var33 = new XSSFWorkbook(var32);
				StringBuffer var34 = ExcelToTxt(var33);
				String var35 = Md5.MD5(var34.toString(), 8);
				var2 = var2.substring(0, var2.length() - 5) + "_" + var35 + ".xlsx";
				var14 = this._$1(var2, var15);
				var7.setHeader("Content-disposition", "attachment;filename=" + var14);
				var13.write(var31.toByteArray());
			} else {
				var2 = var2.substring(0, var2.length() - 5) + ".xlsx";
				var14 = this._$1(var2, var15);
				var7.setHeader("Content-disposition", "attachment;filename=" + var14);
				var18.write(var13);
			}

			var13.close();
			var8.close();
			var9.setLog("下载EXCEL:" + var2 + " 成功!");
		} catch (IOException var29) {
			this._$1((InputStream)var8);
			var29.printStackTrace();
			var9.setErrLog("生成EXCEL:" + var1 + "失败!", 4001001L);
		} catch (Exception var30) {
			this._$1((InputStream)var8);
			var30.printStackTrace();
			var9.setErrLog("生成EXCEL:" + var1 + "失败!", 4001001L);
		}

		return "";
	}

	public InputStream exportExcel5(String var1, List<ExcelSheet> var2, String var3) {
		FileInputStream var4 = null;
		XpBaseSysLog var5 = new XpBaseSysLog(var3);

		try {
			new ByteArrayOutputStream();
			File var7 = new File(var1);
			if (!var7.exists()) {
				this.errHandler(var1 + "(系统找不到指定的文件。)", 400411L);
				var5.setErrLog("生成EXCEL:" + var1 + "失败! 系统找不到指定的文件", 4001001L);
				return null;
			}

			var4 = new FileInputStream(var1);
			return this.exportExcel5((InputStream)var4, var2, var3);
		} catch (IOException var8) {
			this._$1((InputStream)var4);
			var8.printStackTrace();
			var5.setErrLog("生成EXCEL:" + var1 + "失败!", 4001001L);
		} catch (Exception var9) {
			this._$1((InputStream)var4);
			var9.printStackTrace();
			var5.setErrLog("生成EXCEL:" + var1 + "失败!", 4001001L);
		}

		return null;
	}

	public <T> InputStream exportExcel5(InputStream var1, List<ExcelSheet> var2, String var3) {
		XpBaseSysLog var4 = new XpBaseSysLog(var3);

		try {
			ByteArrayOutputStream var5 = new ByteArrayOutputStream();
			XSSFWorkbook var6 = new XSSFWorkbook(var1);
			SXSSFWorkbook var7 = new SXSSFWorkbook(var6, 1000);

			for(int var8 = 0; var8 < var2.size(); ++var8) {
				ExcelSheet var9 = (ExcelSheet)var2.get(var8);
				Map var10 = var9.getTitle();
				List var11 = var9.getHeadList();
				List var12 = var9.getDataList();
				int var13 = var9.getSheetID();
				XSSFSheet var14 = var6.getSheetAt(var13);
				_$1(var13, var10, var11, var12, var14);
			}

			var7.write(var5);
			ByteArrayInputStream var17 = new ByteArrayInputStream(var5.toByteArray());
			var5.close();
			return var17;
		} catch (IOException var15) {
			var15.printStackTrace();
			var4.setErrLog("生成EXCEL失败!", 4001011L);
		} catch (Exception var16) {
			var16.printStackTrace();
			var4.setErrLog("生成EXCEL失败!", 4001012L);
		}

		return null;
	}

	public <T> InputStream exportExcel6(List<String[]> var1, List<T> var2, String var3) {
		XpBaseSysLog var4 = new XpBaseSysLog(var3);

		try {
			ByteArrayOutputStream var5 = new ByteArrayOutputStream();
			XSSFWorkbook var6 = new XSSFWorkbook();
			XSSFSheet var7 = var6.createSheet();
			SXSSFWorkbook var8 = new SXSSFWorkbook(var6, 1000);
			SXSSFSheet var9 = var8.getSheetAt(0);
			_$1(var1, var2, var9);
			var8.write(var5);
			ByteArrayInputStream var10 = new ByteArrayInputStream(var5.toByteArray());
			var5.close();
			return var10;
		} catch (Exception var11) {
			var11.printStackTrace();
			var4.setErrLog("生成EXCEL:失败!", 4001001L);
			return null;
		}
	}

	private static <T> void _$2(int var0, Map<String, String> var1, List<String[]> var2, List<T> var3, Sheet var4) throws IOException {
		int var5 = var4.getLastRowNum();
		if (var5 > 0) {
			Cell var8 = null;
			String var9 = "";
			CellStyle var10 = var4.getWorkbook().createCellStyle();
			var10.setVerticalAlignment(VerticalAlignment.CENTER);
			var10.setAlignment(HorizontalAlignment.CENTER);
			int var14;
			if (var1.size() > 0) {
				for(int var11 = 0; var11 <= var5 - 2; ++var11) {
					Row var12 = var4.getRow(var11);
					if (var12 != null) {
						short var13 = var12.getLastCellNum();

						for(var14 = 0; var14 < var13; ++var14) {
							var8 = var12.getCell(var14);
							if (var8 != null) {
								var9 = var8.toString();
								if (!var9.equals("") && var9.indexOf("${") >= 0) {
									String var6;
									String var7;
									for(Iterator var15 = var1.entrySet().iterator(); var15.hasNext(); var9 = var9.toString().replace("${" + var6 + "}", var7)) {
										Entry var16 = (Entry)var15.next();
										var6 = (String)var16.getKey();
										var7 = (String)var16.getValue();
									}

									var8.setCellValue(var9);
									var8.setCellStyle(var10);
								}
							}
						}
					}
				}
			}

			CellStyle var25 = var4.getRow(var5 - 1).getCell(0).getCellStyle();
			CellStyle var26 = var4.getRow(var5).getCell(0).getCellStyle();
			Row var27 = var4.getRow(var5 - 1);
			if (var2.size() > 0) {
				for(var14 = 0; var14 < var2.size(); ++var14) {
					String var28 = ((String[])var2.get(var14))[1];
					CellUtil.createCell(var27, var14, var28);
					var27.getCell(var14).setCellStyle(var25);
				}
			}

			var14 = var5;
			if (var3.size() > 0) {
				List var29 = method(var3, var2);

				for(int var30 = 0; var30 < var3.size(); ++var30) {
					Row var17 = var4.createRow(var14);
					++var14;

					for(int var18 = 0; var18 < var2.size(); ++var18) {
						if (var29.get(var18) != null) {
							Object var19 = null;

							try {
								var19 = ((Method)var29.get(var18)).invoke(var3.get(var30));
								if (var19 != null && !"null".equalsIgnoreCase(var19.toString())) {
									CellUtil.createCell(var17, var18, var19.toString());
								} else {
									CellUtil.createCell(var17, var18, "");
								}

								var17.getCell(var18).setCellStyle(var26);
							} catch (IllegalAccessException var21) {
								CellUtil.createCell(var17, var18, "");
								var21.printStackTrace();
							} catch (IllegalArgumentException var22) {
								CellUtil.createCell(var17, var18, "");
								var22.printStackTrace();
							} catch (InvocationTargetException var23) {
								CellUtil.createCell(var17, var18, "");
								var23.printStackTrace();
							} catch (Exception var24) {
								CellUtil.createCell(var17, var18, "");
								var24.printStackTrace();
							}
						}
					}
				}
			}

		}
	}

	private static <T> void _$1(int var0, Map<String, String> var1, List<String[]> var2, List<T> var3, Sheet var4) throws IOException {
		int var5 = var4.getLastRowNum();
		if (var5 > 0) {
			Cell var8 = null;
			String var9 = "";
			int var13;
			if (var1.size() > 0) {
				for(int var10 = 0; var10 <= var5 - 2; ++var10) {
					Row var11 = var4.getRow(var10);
					if (var11 != null) {
						short var12 = var11.getLastCellNum();

						for(var13 = 0; var13 < var12; ++var13) {
							var8 = var11.getCell(var13);
							if (var8 != null) {
								var9 = var8.toString();
								if (!var9.equals("") && var9.indexOf("${") >= 0) {
									String var6;
									String var7;
									for(Iterator var14 = var1.entrySet().iterator(); var14.hasNext(); var9 = var9.toString().replace("${" + var6 + "}", var7)) {
										Entry var15 = (Entry)var14.next();
										var6 = (String)var15.getKey();
										var7 = (String)var15.getValue();
									}

									var8.setCellValue(var9);
								}
							}
						}
					}
				}
			}

			CellStyle var26 = var4.getRow(var5 - 1).getCell(0).getCellStyle();
			CellStyle var27 = var4.getRow(var5).getCell(0).getCellStyle();
			Row var28 = var4.getRow(var5 - 1);
			String var29;
			if (var2.size() > 0) {
				for(var13 = 0; var13 < var2.size(); ++var13) {
					var29 = ((String[])var2.get(var13))[1];
					CellUtil.createCell(var28, var13, var29);
					var28.getCell(var13).setCellStyle(var26);
				}
			}

			var13 = var5;
			if (var3.size() > 0) {
				Row var16;
				int var31;
				if (var3.get(0).getClass().getName().equals("[Ljava.lang.String;")) {
					var29 = "";

					for(var31 = 0; var31 < var3.size(); ++var31) {
						var16 = var4.createRow(var13);
						++var13;
						String[] var17 = (String[])((String[])var3.get(var31));

						for(int var18 = 0; var18 < var17.length; ++var18) {
							try {
								var29 = var17[var18];
								if (var29 != null && !"null".equalsIgnoreCase(var29.toString())) {
									CellUtil.createCell(var16, var18, var29.toString());
								} else {
									CellUtil.createCell(var16, var18, "");
								}

								var16.getCell(var18).setCellStyle(var27);
							} catch (IllegalArgumentException var24) {
								CellUtil.createCell(var16, var18, "");
								var24.printStackTrace();
							} catch (Exception var25) {
								CellUtil.createCell(var16, var18, "");
								var25.printStackTrace();
							}
						}
					}
				} else {
					List var30 = method(var3, var2);

					for(var31 = 0; var31 < var3.size(); ++var31) {
						var16 = var4.createRow(var13);
						++var13;

						for(int var32 = 0; var32 < var2.size(); ++var32) {
							if (var30.get(var32) != null) {
								Object var33 = null;

								try {
									var33 = ((Method)var30.get(var32)).invoke(var3.get(var31));
									if (var33 != null && !"null".equalsIgnoreCase(var33.toString())) {
										CellUtil.createCell(var16, var32, var33.toString());
									} else {
										CellUtil.createCell(var16, var32, "");
									}

									var16.getCell(var32).setCellStyle(var27);
								} catch (IllegalAccessException var20) {
									CellUtil.createCell(var16, var32, "");
									var20.printStackTrace();
								} catch (IllegalArgumentException var21) {
									CellUtil.createCell(var16, var32, "");
									var21.printStackTrace();
								} catch (InvocationTargetException var22) {
									CellUtil.createCell(var16, var32, "");
									var22.printStackTrace();
								} catch (Exception var23) {
									CellUtil.createCell(var16, var32, "");
									var23.printStackTrace();
								}
							}
						}
					}
				}
			}

		}
	}

	private static <T> void _$1(List<String[]> var0, List<T> var1, Sheet var2) throws IOException {
		int var3 = var2.getLastRowNum();
		Row var4 = var2.getRow(var3);
		int var5 = var3;
		CellStyle var6 = var2.getRow(var3 - 1).getCell(0).getCellStyle();
		CellStyle var7 = var2.getRow(var3).getCell(0).getCellStyle();
		if (var1.size() > 0) {
			int var9;
			Row var10;
			if (var1.get(0).getClass().getName().equals("[Ljava.lang.String;")) {
				String var8 = "";

				for(var9 = 0; var9 < var1.size(); ++var9) {
					var10 = var2.createRow(var5);
					var10.setRowStyle(var4.getRowStyle());
					++var5;
					String[] var11 = (String[])((String[])var1.get(var9));

					for(int var12 = 0; var12 < var11.length; ++var12) {
						try {
							var8 = var11[var12];
							if (var8 != null && !"null".equalsIgnoreCase(var8.toString())) {
								CellUtil.createCell(var10, var12, var8.toString());
							} else {
								CellUtil.createCell(var10, var12, "");
							}
						} catch (IllegalArgumentException var18) {
							CellUtil.createCell(var10, var12, "");
							var18.printStackTrace();
						} catch (Exception var19) {
							CellUtil.createCell(var10, var12, "");
							var19.printStackTrace();
						}
					}
				}
			} else {
				List var20 = method(var1, var0);

				for(var9 = 0; var9 < var1.size(); ++var9) {
					var10 = var2.createRow(var5);
					++var5;

					for(int var21 = 0; var21 < var0.size(); ++var21) {
						if (var20.get(var21) != null) {
							Object var22 = null;

							try {
								var22 = ((Method)var20.get(var21)).invoke(var1.get(var9));
								if (var22 != null && !"null".equalsIgnoreCase(var22.toString())) {
									CellUtil.createCell(var10, var21, var22.toString());
								} else {
									CellUtil.createCell(var10, var21, "");
								}

								var10.getCell(var21).setCellStyle(var7);
							} catch (IllegalAccessException var14) {
								CellUtil.createCell(var10, var21, "");
								var14.printStackTrace();
							} catch (IllegalArgumentException var15) {
								CellUtil.createCell(var10, var21, "");
								var15.printStackTrace();
							} catch (InvocationTargetException var16) {
								CellUtil.createCell(var10, var21, "");
								var16.printStackTrace();
							} catch (Exception var17) {
								CellUtil.createCell(var10, var21, "");
								var17.printStackTrace();
							}
						}
					}
				}
			}
		}

	}

	private static <T> void _$1(List<String[]> var0, List<T> var1, SXSSFSheet var2) throws IOException {
		SXSSFRow var3 = var2.createRow(0);

		for(int var4 = 0; var4 < var0.size(); ++var4) {
			String var5 = ((String[])var0.get(var4))[1];
			CellUtil.createCell(var3, var4, var5);
		}

		if (var1.size() > 0) {
			List var14 = method(var1, var0);

			for(int var15 = 0; var15 < var1.size(); ++var15) {
				SXSSFRow var6 = var2.createRow(var15 + 1);

				for(int var7 = 0; var7 < var0.size(); ++var7) {
					if (var14.get(var7) != null) {
						Object var8 = null;

						try {
							var8 = ((Method)var14.get(var7)).invoke(var1.get(var15));
							if (var8 != null && !"null".equalsIgnoreCase(var8.toString())) {
								CellUtil.createCell(var6, var7, var8.toString());
							} else {
								CellUtil.createCell(var6, var7, "");
							}
						} catch (IllegalAccessException var10) {
							CellUtil.createCell(var6, var7, "");
							var10.printStackTrace();
						} catch (IllegalArgumentException var11) {
							CellUtil.createCell(var6, var7, "");
							var11.printStackTrace();
						} catch (InvocationTargetException var12) {
							CellUtil.createCell(var6, var7, "");
							var12.printStackTrace();
						} catch (Exception var13) {
							CellUtil.createCell(var6, var7, "");
							var13.printStackTrace();
						}
					}
				}
			}
		}

	}

	private static void _$1(List<String> var0, List<String[]> var1, OutputStream var2) throws IOException {
		XSSFWorkbook var3 = new XSSFWorkbook();
		XSSFSheet var4 = var3.createSheet();
		SXSSFWorkbook var5 = new SXSSFWorkbook(var3, 100);
		SXSSFSheet var6 = var5.getSheetAt(0);
		Row var7 = var6.createRow(0);

		int var8;
		for(var8 = 0; var8 < var0.size(); ++var8) {
			String var9 = (String)var0.get(var8);
			CellUtil.createCell(var7, var8, var9);
		}

		for(var8 = 0; var8 < var1.size(); ++var8) {
			Row var12 = var6.createRow(var8 + 1);
			int var10 = ((String[])var1.get(var8)).length;

			for(int var11 = 0; var11 < var10; ++var11) {
				CellUtil.createCell(var12, var11, ((String[])var1.get(var8))[var11]);
			}
		}

		var5.write(var2);
		var2.close();
	}

	private String _$1(String var1, String var2) {
		String var3;
		try {
			if (var2.toLowerCase().indexOf("firefox") > 0) {
				var3 = new String(var1.getBytes("UTF-8"), "ISO8859-1");
			} else if (var2.toUpperCase().indexOf("MSIE") <= 0 && var2.toUpperCase().indexOf("RV:11") <= 0) {
				var3 = new String(var1.getBytes("UTF-8"), "ISO8859-1");
			} else {
				var3 = URLEncoder.encode(var1, "UTF-8");
			}
		} catch (Exception var5) {
			var3 = "";
		}

		return var3;
	}

	public static <T> List<Method> method(List<T> var0, List<String[]> var1) {
		ArrayList var2 = new ArrayList();

		try {
			Class var3 = var0.get(0).getClass();

			for(int var4 = 0; var4 < var1.size(); ++var4) {
				String var5 = ((String[])var1.get(var4))[0];
				PropertyDescriptor var6 = new PropertyDescriptor(var5, var3);
				Method var7 = var6.getReadMethod();
				var2.add(var7);
			}
		} catch (SecurityException var8) {
			var8.printStackTrace();
		} catch (IllegalArgumentException var9) {
			var9.printStackTrace();
		} catch (IntrospectionException var10) {
			var10.printStackTrace();
		}

		return var2;
	}

	private void _$1(InputStream var1) {
		if (var1 != null) {
			try {
				var1.close();
			} catch (IOException var3) {
				var3.printStackTrace();
			}
		}

	}

	private void _$1(OutputStream var1) {
		if (var1 != null) {
			try {
				var1.close();
			} catch (IOException var3) {
				var3.printStackTrace();
			}
		}

	}

	public static StringBuffer ExcelToTxt(Workbook var0) {
		String var1 = "\t";
		String var2 = "\n";
		int var3 = var0.getNumberOfSheets();
		StringBuffer var4 = new StringBuffer();
		Sheet var5 = null;
		String var6 = "";

		for(int var7 = 0; var7 < var3; ++var7) {
			var5 = var0.getSheetAt(var7);
			int var8 = var5.getLastRowNum();
			Row var9 = null;

			for(int var10 = 0; var10 <= var8; ++var10) {
				var9 = var5.getRow(var10);
				if (var9 != null) {
					short var11 = var9.getLastCellNum();

					for(int var12 = 0; var12 < var11; ++var12) {
						if (var9.getCell(var12) != null) {
							var4.append(var9.getCell(var12).toString());
						}
					}

					var4.append(var2);
				}
			}

			var4.append(var2 + var2);
		}

		return var4;
	}

	public FileInputStream getFileIS(String var1) {
		File var2 = new File(var1);
		if (!var2.exists()) {
			this.errHandler(var1 + "(系统找不到指定的文件。)", 400411L);
			return null;
		} else {
			try {
				FileInputStream var3 = new FileInputStream(var1);
				return var3;
			} catch (Exception var5) {
				this.errHandler(var1 + var5.getMessage(), 400412L);
				return null;
			}
		}
	}

	public static String ExcelToTxtMd5(Workbook var0) {
		String var1 = "\t";
		String var2 = "\n";
		int var3 = var0.getNumberOfSheets();
		StringBuffer var4 = new StringBuffer();
		StringBuffer var5 = new StringBuffer();
		Sheet var6 = null;
		String var7 = "";
		String var8 = "";
		int var9 = 0;

		for(int var10 = 0; var10 < var3; ++var10) {
			var6 = var0.getSheetAt(var10);
			int var11 = var6.getLastRowNum();
			Row var12 = null;

			for(int var13 = 0; var13 <= var11; ++var13) {
				var12 = var6.getRow(var13);
				if (var12 != null) {
					short var14 = var12.getLastCellNum();

					for(int var15 = 0; var15 < var14; ++var15) {
						if (var12.getCell(var15) != null) {
							var4.append(var12.getCell(var15).toString());
							++var9;
						}
					}

					var4.append(var2);
				}

				if (var9 >= 100000) {
					var8 = Md5.MD5(var4.toString(), 8);
					var5.append(var8);
					var4.setLength(0);
					var9 = 0;
				}
			}

			var4.append(var2 + var2);
		}

		if (var4 != null && !var4.toString().equals("")) {
			var8 = Md5.MD5(var4.toString(), 8);
			var5.append(var8);
		}

		var8 = Md5.MD5(var5.toString(), 8);
		return var8;
	}

	private static void _$1(Sheet var0, Sheet var1) throws Exception {
		Row var2 = null;
		Row var3 = null;
		Cell var4 = null;
		Cell var5 = null;

		int var7;
		for(int var6 = 0; var6 <= var0.getLastRowNum(); ++var6) {
			var2 = var0.getRow(var6);
			if (null != var2) {
				var3 = var1.createRow(var6);
				var3.setHeight(var2.getHeight());

				for(var7 = 0; var7 < var2.getLastCellNum(); ++var7) {
					var4 = var2.getCell(var7);
					if (null != var4) {
						var5 = var3.createCell(var7);
						if (var6 == 0) {
							var1.setColumnWidth(var7, var0.getColumnWidth(var7));
							if (null != var0.getColumnStyle(var7)) {
								var1.setDefaultColumnStyle(var7, var0.getColumnStyle(var7));
							}
						}

						var5.setCellValue(var4.toString());
						CellStyle var8 = var4.getCellStyle();
						CellStyle var9 = var5.getCellStyle();
						var4.getCellStyle().cloneStyleFrom(var9);
						var9.setFillForegroundColor(var8.getFillForegroundColor());
						var9.setFillBackgroundColor(var8.getFillBackgroundColor());
						var5.setCellStyle(var9);
					}
				}
			}
		}

		CellRangeAddress var10 = null;

		for(var7 = 0; var7 < var0.getNumMergedRegions(); ++var7) {
			var10 = var0.getMergedRegion(var7);
			if (var10.getFirstColumn() >= var0.getFirstRowNum() && var10.getLastRow() <= var0.getLastRowNum()) {
				var1.addMergedRegion(var10);
			}
		}

		var1.setDisplayGridlines(true);
	}
}
