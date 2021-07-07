/**
 * Copyright (C), 2015-2021
 * FileName: CheliangJiashiyuan
 * Author:   呵呵哒
 * Date:     2021/6/28 14:27
 * Description:
 */
package org.springblade.platform.cheliangguanli.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @创建人 hyp
 * @创建时间 2021/6/28
 * @描述
 */
@Data
@TableName("anbiao_cheliang_jiashiyuan")
@ApiModel(value = "CheliangJiashiyuan对象", description = "CheliangJiashiyuan对象")
public class CheliangJiashiyuan implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	private String id;

	@TableField("企业ID")
	private Integer deptId;

	@TableField("车辆ID")
	private String vehid;

	@TableField("驾驶员ID")
	private String jiashiyuanid;

	@TableField("创建时间")
	private String createtime;

	@TableField("类型（1为ios 2 为安卓）")
	private String type;
}
