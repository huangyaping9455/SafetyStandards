/**
 * Copyright (C), 2015-2020
 * FileName: ErrUtil
 * Author:   呵呵哒
 * Date:     2020/11/16 14:40
 * Description:
 */
package org.springblade.common.tool;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 hyp
 * @创建时间 2020/11/16
 * @描述
 */

public class ErrUtil extends JsonDataUtil {
	private List<ErrInfo> _$12 = new ArrayList();
	private boolean _$11 = false;

	public ErrUtil() {
	}

	public boolean isError() {
		return this._$11;
	}

	public List<ErrInfo> getErrInfoList() {
		return this._$12;
	}

	public void setError(boolean var1) {
		this._$11 = var1;
	}

	public void AddErr(String var1, String var2) {
		ErrInfo var3 = new ErrInfo();
		var3.setErrFieldName(var1);
		var3.setErrMSG(var2);
		this._$12.add(var3);
		this.setOT("1");
		this._$11 = true;
	}

	public class ErrInfo {
		private String _$2;
		private String _$1;

		public ErrInfo() {
		}

		public String getErrMSG() {
			return this._$1;
		}

		public void setErrMSG(String var1) {
			this._$1 = var1;
		}

		public String getErrFieldName() {
			return this._$2;
		}

		public void setErrFieldName(String var1) {
			this._$2 = var1;
		}
	}
}
