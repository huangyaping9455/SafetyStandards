/**
 * Copyright (C), 2015-2020,
 * FileName: 北斗Vehicle
 * Author:   呵呵哒
 * Date:     2020/7/3 10:29
 * Description:
 */
package org.springblade.platform.qiyeshouye.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 呵呵哒
 * @创建人 hyp
 * @创建时间 2020/8/31
 * @描述
 */
@Data
@ApiModel(value = "StopVehicle对象", description = "StopVehicle对象")
public class StopVehicle implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private String id;

	@ApiModelProperty(value = "企业ID")
	private String deptId;

	@ApiModelProperty(value = "企业名称")
	private String deptName;

	@ApiModelProperty(value = "车辆ID")
	private String vehId;

	@ApiModelProperty(value = "车牌号")
	private String cheliangpaizhao;

	@ApiModelProperty(value = "车牌颜色")
	private String chepaiyanse;

	@ApiModelProperty(value = "使用性质")
	private String shiyongxingzhi;

	@ApiModelProperty(value = "停车开始时间")
	private String startTime;

	@ApiModelProperty(value = "停车结束时间")
	private String endTime;

	@ApiModelProperty(value = "停车时长 单位秒")
	private String stopLong;

	@ApiModelProperty(value = "停车时的里程")
	private String stopMileage;

	@ApiModelProperty(value = "停车类型 1 熄火停车 2 未熄火停车")
	private Integer stopType;

	@ApiModelProperty(value = "停车类型")
	private String stopTypeShow;

	@ApiModelProperty(value = "停车位置")
	private String stopLocatin;

	@ApiModelProperty(value = "停车次数")
	private Integer stopTimes;

	@ApiModelProperty(value = "停车未熄火次数")
	private Integer stopAccOnTimes;

	@ApiModelProperty(value = "停车未熄火时长")
	private String stopAccOnLong;

}
