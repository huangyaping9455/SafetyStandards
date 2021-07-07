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
@ApiModel(value = "TrajectoryAnomalies对象", description = "TrajectoryAnomalies对象")
public class TrajectoryAnomalies implements Serializable {

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

	@ApiModelProperty(value = "漂移开始时间")
	private String startTime;

	@ApiModelProperty(value = "漂移结束时间")
	private String endTime;

	@ApiModelProperty(value = "异常漂移报警时间")
	private String alarmTime;

	@ApiModelProperty(value = "漂移报警时长")
	private Integer driftLong;

	@ApiModelProperty(value = "报警开始位置")
	private String startLocation;

	@ApiModelProperty(value = "报警结束位置")
	private String endLocatin;


}
