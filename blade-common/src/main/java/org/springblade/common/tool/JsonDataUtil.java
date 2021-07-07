/**
 * Copyright (C), 2015-2020
 * FileName: JsonDataUtil
 * Author:   呵呵哒
 * Date:     2020/11/16 14:41
 * Description:
 */
package org.springblade.common.tool;

/**
 * @创建人 hyp
 * @创建时间 2020/11/16
 * @描述
 */
public class JsonDataUtil {
	private String _$10 = "success";
	private String _$9 = "4";
	private String _$8 = "";
	private String _$7 = "";
	private Object _$6 = null;
	private String _$5 = "0";
	private String _$4 = "";
	private String _$3 = "";
	private String _$2 = "";
	private BasePage _$1 = new BasePage();

	public BasePage getPageData() {
		return this._$1;
	}

	public void setPageData(BasePage var1) {
		this._$1 = var1;
	}

	public String getListTemplateName() {
		return this._$2;
	}

	public void setListTemplateName(String var1) {
		this._$2 = var1;
	}

	public String getColumnNames() {
		return this._$3;
	}

	public void setColumnNames(String var1) {
		this._$3 = var1;
	}

	public JsonDataUtil() {
	}

	public String getOT() {
		return this._$9;
	}

	public void setOT(String var1) {
		this._$9 = var1;
	}

	public String getFocus() {
		return this._$8;
	}

	public void setFocus(String var1) {
		this._$8 = var1;
	}

	public String getMsg() {
		return this._$7;
	}

	public void setMsg(String var1) {
		this._$7 = var1;
	}

	public String getErrCode() {
		return this._$5;
	}

	public void setErrCode(String var1) {
		this._$5 = var1;
	}

	public String getErrMsg() {
		return this._$4;
	}

	public void setErrMsg(String var1) {
		this._$4 = var1;
	}

	public void setErr(String var1, String var2) {
		this._$4 = var2;
		this._$5 = var1;
		this._$10 = "fail";
	}

	public String getStatus() {
		return this._$10;
	}

	public void setStatus(String var1) {
		this._$10 = var1;
	}

	public Object getObjData() {
		return this._$6;
	}

	public void setObjData(Object var1) {
		this._$6 = var1;
	}
}
