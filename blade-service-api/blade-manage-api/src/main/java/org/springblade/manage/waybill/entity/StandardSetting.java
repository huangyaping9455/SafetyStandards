/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.manage.waybill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 * @author 呵呵哒
 */
@Data
@TableName("manage_standard_setting")
@ApiModel(value = "StandardSetting对象", description = "StandardSetting对象")
public class StandardSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
	private Integer id;
    /**
     * 车辆id
     */
    @ApiModelProperty(value = "车辆id")
    private String vehicleId;
	/**
	 * 数据
	 */
	@ApiModelProperty(value = "数据")
	private String shuju;
	/**
	 * 月份
	 */
	@ApiModelProperty(value = "月份")
	private String yue;

	/**
	 * 一月
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "一月")
	private String yi;
	/**
	 * 二月
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "二月")
	private String er;
	/**
	 * 三月
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "三月")
	private String san;
	/**
	 * 四月
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "四月")
	private String si;
	/**
	 * 五月
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "五月")
	private String wu;
	/**
	 * 六月
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "六月")
	private String liu;
	/**
	 * 七月
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "七月")
	private String qi;
	/**
	 * 八月
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "八月")
	private String ba;
	/**
	 * 九月
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "九月")
	private String jiu;
	/**
	 * 十月
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "十月")
	private String shi;
	/**
	 * 十一月
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "十一月")
	private String shiyi;
	/**
	 * 十二月
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "十二月")
	private String shier;


}
