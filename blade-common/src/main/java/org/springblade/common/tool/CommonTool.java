/**
 * Copyright (C), 2015-2020
 * FileName: CommonTool
 * Author:   呵呵哒
 * Date:     2020/11/16 14:20
 * Description:
 */
package org.springblade.common.tool;

/**
 * @创建人 hyp
 * @创建时间 2020/11/16
 * @描述
 */
public class CommonTool {

	public CommonTool() {
	}

	public static String HtmlToText(String var0) {
		String var1 = var0.replaceAll("</?[^>]+>", "");
		var1 = var1.replaceAll("<a>\\s*|\t|\r|\n</a>", "");
		return var1;
	}

}

