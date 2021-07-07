/**
 * Copyright (C), 2015-2021
 * FileName: UpdateTableMap
 * Author:   呵呵哒
 * Date:     2021/5/28 21:35
 * Description:
 */
package org.springblade.platform.qiyeshouye.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @创建人 hyp
 * @创建时间 2021/5/28
 * @描述
 */
@Data
@ApiModel(value = "UpdateTableMap对象", description = "UpdateTableMap对象")
public class UpdateTableMap implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "tableName")
	private String tableName;

}
