/**
 * Copyright (C), 2015-2020
 * FileName: IlIIIIIIllIlIllI
 * Author:   呵呵哒
 * Date:     2020/11/16 14:30
 * Description:
 */
package org.springblade.common.tool;

import java.util.Comparator;
import java.util.Date;

/**
 * @创建人 hyp
 * @创建时间 2020/11/16
 * @描述
 */

class IlIIIIIIllIlIllI implements Comparator<String> {
	private String _$1;

	public IlIIIIIIllIlIllI() {
	}

	public IlIIIIIIllIlIllI(String var1) {
		this._$1 = var1;
	}

	@Override
	public int compare(String var1, String var2) {
		byte var3 = 0;
		Date var4 = DateTool.StrToDate(var1, this._$1);
		Date var5 = DateTool.StrToDate(var2, this._$1);
		if (var4 != null && var5 != null) {
			long var6 = var4.getTime() - var5.getTime();
			if (var6 > 0L) {
				var3 = 1;
			} else if (var6 < 0L) {
				var3 = -1;
			}
		}

		return var3;
	}
}
