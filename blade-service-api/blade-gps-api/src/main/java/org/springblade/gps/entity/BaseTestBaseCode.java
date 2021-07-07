/**
 * Copyright (C), 2015-2021
 * FileName: BaseTestBaseCode
 * Author:   呵呵哒
 * Date:     2021/4/9 19:30
 * Description:
 */
package org.springblade.gps.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 呵呵哒
 * @创建人 hyp
 * @创建时间 2021/4/9
 * @描述
 */
@Data
@ApiModel(value = "BaseTestBaseCode", description = "BaseTestBaseCode")
public class BaseTestBaseCode implements Serializable {

	@ApiModelProperty(value = "dpId")
	private String dpId;

	@ApiModelProperty(value = "dpName")
	private String dpName;

	@ApiModelProperty(value = "bcTreeId")
	private String bcTreeId;

	@ApiModelProperty(value = "bcName")
	private String bcName;

}
