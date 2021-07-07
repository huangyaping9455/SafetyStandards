/**
 * Copyright (C), 2015-2020
 * FileName: XpError
 * Author:   呵呵哒
 * Date:     2020/11/16 14:32
 * Description:
 */
package org.springblade.common.tool;

/**
 * @创建人 hyp
 * @创建时间 2020/11/16
 * @描述
 */
public class XpError {
	private boolean _$4 = false;
	private long _$3 = 0L;
	private String _$2 = "";
	private String _$1 = "";

	protected XpError() {
	}

	public boolean isError() {
		return this._$4;
	}

	public String errMsg() {
		return this._$2;
	}

	public long errCode() {
		return this._$3;
	}

	public String hintMsg() {
		return this._$1;
	}

	protected void hintHandler(String var1) {
		this._$1 = var1;
	}

	protected void errHandler(String var1, long var2) {
		if (this._$2 == null) {
			this._$2 = var1;
		} else {
			this._$2 = this._$2 + " " + var1;
		}

		this._$4 = true;
		this._$3 = var2;
	}

	protected void clearError() {
		this._$3 = 0L;
		this._$2 = "";
		this._$4 = false;
	}
}
