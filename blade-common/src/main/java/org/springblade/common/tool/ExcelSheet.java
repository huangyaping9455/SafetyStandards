/**
 * Copyright (C), 2015-2020
 * FileName: ExcelSheet
 * Author:   呵呵哒
 * Date:     2020/11/16 14:45
 * Description:
 */
package org.springblade.common.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 hyp
 * @创建时间 2020/11/16
 * @描述
 */

public class ExcelSheet<T> {
	private Integer SheetID;
	private Map<String, String> Title = new HashMap();
	private List<String[]> headList = new ArrayList();
	private List<T> dataList = new ArrayList();

	public ExcelSheet() {
	}

	public Integer getSheetID() {
		return this.SheetID;
	}

	public void setSheetID(Integer var1) {
		this.SheetID = var1;
	}

	public Map<String, String> getTitle() {
		return this.Title;
	}

	public void setTitle(Map<String, String> var1) {
		this.Title = var1;
	}

	public List<String[]> getHeadList() {
		return this.headList;
	}

	public void setHeadList(List<String[]> var1) {
		this.headList = var1;
	}

	public List<T> getDataList() {
		return this.dataList;
	}

	public void setDataList(List<T> var1) {
		this.dataList = var1;
	}
}
