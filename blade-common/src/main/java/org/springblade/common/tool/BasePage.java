/**
 * Copyright (C), 2015-2020
 * FileName: BasePage
 * Author:   呵呵哒
 * Date:     2020/11/16 14:42
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

public class BasePage<T> {
	private int _$9 = 1;
	private int _$8 = 100;
	private int _$7;
	private int _$6;
	private List<T> _$5 = new ArrayList();
	private String _$4;
	private String _$3;
	private String _$2;
	private int _$1;

	public String getListColumnNames() {
		return this._$3;
	}

	public void setListColumnNames(String var1) {
		this._$3 = var1;
	}

	public String getListId() {
		return this._$4;
	}

	public void setListId(String var1) {
		this._$4 = var1;
	}

	public BasePage() {
	}

	public int getPage() {
		if (this._$9 < 1) {
			this._$9 = 1;
		} else if (this._$9 >= this.getTotalPage()) {
			this._$9 = this.getTotalPage();
		}

		return this._$9;
	}

	public void setPage(int var1) {
		this._$9 = var1;
	}

	public int getPageSize() {
		return this._$8;
	}

	public void setPageSize(int var1) {
		this._$8 = var1;
	}

	public int getTotal() {
		return this._$7;
	}

	public void setTotal(int var1) {
		this._$7 = var1;
	}

	public int getTotalPage() {
		if (this.getTotal() != 0) {
			if (this.getPageSize() > 0) {
				return this.getTotal() % this.getPageSize() == 0 ? this.getTotal() / this.getPageSize() : this.getTotal() / this.getPageSize() + 1;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}

	public List<T> getRows() {
		return this._$5;
	}

	public void setRows(List<T> var1) {
		this._$5 = var1;
	}

	public String getOrderColumn() {
		return this._$2;
	}

	public void setOrderColumn(String var1) {
		if (ValidatorTool.validateRegex(var1, "\\w+")) {
			this._$2 = var1;
		} else {
			this._$2 = null;
		}

	}

	public int getOrder() {
		return this._$1;
	}

	public void setOrder(int var1) {
		this._$1 = var1;
	}
}
