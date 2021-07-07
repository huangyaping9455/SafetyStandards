/**
 * Copyright (C), 2015-2020
 * FileName: DownWord
 * Author:   呵呵哒
 * Date:     2020/11/16 14:32
 * Description:
 */
package org.springblade.common.tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 * @创建人 hyp
 * @创建时间 2020/11/16
 * @描述
 */
public class DownWord extends XpError {
	public DownWord() {
	}

	public InputStream getWordReplace(String var1, Map<String, String> var2, String var3) {
		String var4 = var3 + var1;

		try {
			File var5 = new File(var4);
			if (!var5.exists()) {
				this.errHandler(var1 + "(系统找不到指定的文件。)", 400411L);
				return null;
			} else {
				FileInputStream var6 = new FileInputStream(var4);
				InputStream var7 = this.getWordReplace((InputStream)var6, var2, "docx");
				return var7;
			}
		} catch (Exception var8) {
			var8.printStackTrace();
			this.errHandler(var8.getMessage().replace(var3, ""), (long)var8.hashCode());
			return null;
		}
	}

	public InputStream getWordXReplace(InputStream var1, Map<String, String> var2) {
		return this.getWordReplace(var1, var2, "docx");
	}

	public InputStream getWordReplace(InputStream var1, Map<String, String> var2, String var3) {
		XWPFDocument var4 = null;
		HWPFDocument var5 = null;
		ByteArrayOutputStream var6 = new ByteArrayOutputStream();

		try {
			Iterator var28;
			if (var3.equalsIgnoreCase("docx")) {
				try {
					var4 = new XWPFDocument(var1);
					Iterator var7 = var4.getParagraphsIterator();

					label186:
					while(true) {
						List var9;
						String var10;
						int var11;
						do {
							do {
								do {
									do {
										if (!var7.hasNext()) {
											var28 = var4.getTablesIterator();

											while(var28.hasNext()) {
												XWPFTable var29 = (XWPFTable)var28.next();
												int var31 = var29.getNumberOfRows();

												for(var11 = 0; var11 < var31; ++var11) {
													XWPFTableRow var32 = var29.getRow(var11);
													List var13 = var32.getTableCells();
													Iterator var14 = var13.iterator();

													while(var14.hasNext()) {
														XWPFTableCell var15 = (XWPFTableCell)var14.next();
														List var16 = var15.getParagraphs();

														for(int var17 = 0; var17 < var16.size(); ++var17) {
															List var18 = ((XWPFParagraph)var16.get(var17)).getRuns();
															String var19 = "";

															int var20;
															for(var20 = 0; var20 < var18.size(); ++var20) {
																if (((XWPFRun)var18.get(var20)).getText(((XWPFRun)var18.get(var20)).getTextPosition()) != null) {
																	var19 = var19 + ((XWPFRun)var18.get(var20)).getText(((XWPFRun)var18.get(var20)).getTextPosition()).trim();
																}
															}

															if (var19 != null && var19.contains("${") && var19.contains("}")) {
																for(var20 = 0; var20 < var18.size(); ++var20) {
																	((XWPFRun)var18.get(var20)).setText("", 0);
																}

																Map.Entry var21;
																for(Iterator var34 = var2.entrySet().iterator(); var34.hasNext(); var19 = var19.replace((CharSequence)var21.getKey(), (CharSequence)var21.getValue())) {
																	var21 = (Map.Entry)var34.next();
																}

																if (var18.size() > 0) {
																	((XWPFRun)var18.get(0)).setText(var19, 0);
																}
															}
														}
													}
												}
											}
											break label186;
										}

										XWPFParagraph var8 = (XWPFParagraph)var7.next();
										var9 = var8.getRuns();
										var10 = "";
									} while(var9 == null);

									for(var11 = 0; var11 < var9.size(); ++var11) {
										if (((XWPFRun)var9.get(var11)).getText(((XWPFRun)var9.get(var11)).getTextPosition()) != null) {
											var10 = var10 + ((XWPFRun)var9.get(var11)).getText(((XWPFRun)var9.get(var11)).getTextPosition()).trim();
										}
									}
								} while(var10 == null);
							} while(!var10.contains("${"));
						} while(!var10.contains("}"));

						for(var11 = 0; var11 < var9.size(); ++var11) {
							((XWPFRun)var9.get(var11)).setText("", 0);
						}

						Map.Entry var12;
						for(Iterator var33 = var2.entrySet().iterator(); var33.hasNext(); var10 = var10.replace((CharSequence)var12.getKey(), (CharSequence)var12.getValue())) {
							var12 = (Map.Entry)var33.next();
						}

						if (var9.size() > 0) {
							((XWPFRun)var9.get(0)).setText(var10, 0);
						}
					}
				} catch (Exception var24) {
					this.errHandler(var24.getMessage(), (long)var24.hashCode());
					return null;
				}
			} else {
				if (!var3.equalsIgnoreCase("doc")) {
					return null;
				}

				try {
					var5 = new HWPFDocument(var1);
					Range var26 = var5.getRange();
					var28 = var2.entrySet().iterator();

					while(var28.hasNext()) {
						Map.Entry var30 = (Map.Entry)var28.next();
						var26.replaceText((String)var30.getKey(), (String)var30.getValue());
					}
				} catch (FileNotFoundException var22) {
					this.errHandler(var22.getMessage(), (long)var22.hashCode());
					return null;
				} catch (IOException var23) {
					this.errHandler(var23.getMessage(), (long)var23.hashCode());
					return null;
				}
			}

			if (var5 != null) {
				var5.write(var6);
			}

			if (var4 != null) {
				var4.write(var6);
			}

			ByteArrayInputStream var27 = new ByteArrayInputStream(var6.toByteArray());
			return var27;
		} catch (Exception var25) {
			var25.printStackTrace();
			this.errHandler(var25.getMessage(), (long)var25.hashCode());
			return null;
		}
	}

	public String wordReplaceDown(String var1, String var2, Map<String, String> var3, HttpServletRequest var4, HttpServletResponse var5) {
		String var6 = var4.getServletContext().getRealPath("/");

		try {
			ServletOutputStream var7 = var5.getOutputStream();
			String var9 = var4.getHeader("User-Agent");
			String var8 = this._$2(var2, var9);
			var5.setContentType("application/x-download");
			var5.setHeader("Content-disposition", "attachment;filename=" + var8);
			InputStream var10 = this.getWordReplace(var1, var3, var6);
			byte[] var11 = new byte[1024];
			boolean var12 = false;

			int var14;
			while(0 <= (var14 = var10.read(var11))) {
				var7.write(var11, 0, var14);
			}

			var7.flush();
			var7.close();
			var10.close();
			return "ok";
		} catch (Exception var13) {
			var13.printStackTrace();
			this.errHandler(var13.getMessage().replace(var6, ""), (long)var13.hashCode());
			return null;
		}
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
			} catch (FileNotFoundException var5) {
				this.errHandler(var1 + var5.getMessage(), 400412L);
				return null;
			}
		}
	}

	private String _$2(String var1, String var2) {
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

	private void _$1(OutputStream var1) {
		if (var1 != null) {
			try {
				var1.close();
			} catch (IOException var3) {
				var3.printStackTrace();
			}
		}

	}

	private static void _$1(String var0, String var1) {
		BufferedInputStream var2 = null;
		BufferedOutputStream var3 = null;

		try {
			try {
				FileInputStream var4 = new FileInputStream(var0);
				FileOutputStream var5 = new FileOutputStream(var1);
				var2 = new BufferedInputStream(var4);
				var3 = new BufferedOutputStream(var5);
				byte[] var6 = new byte[4096];

				int var7;
				while((var7 = var2.read(var6)) != -1) {
					var3.write(var6, 0, var7);
				}

				var2.close();
				var3.close();
				var4.close();
				var5.close();
			} catch (Exception var11) {
				var11.printStackTrace();
			}

		} finally {
			;
		}
	}
}

