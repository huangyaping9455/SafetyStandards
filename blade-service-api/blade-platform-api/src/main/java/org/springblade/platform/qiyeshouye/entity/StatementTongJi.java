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
@ApiModel(value = "StatementTongJi对象", description = "StatementTongJi对象")
public class StatementTongJi implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private String id;

	@ApiModelProperty(value = "企业ID")
	private String deptId;

	@ApiModelProperty(value = "企业名称")
	private String deptName;

	@ApiModelProperty(value = "车牌号")
	private String cheliangpaizhao;

	@ApiModelProperty(value = "车牌颜色")
	private String chepaiyanse;

	@ApiModelProperty(value = "营运类型")
	private String operatType;

	@ApiModelProperty(value = "统计时间")
	private String statisticsDate;

	@ApiModelProperty(value = "行驶里程")
	private double TravelMileage;

	@ApiModelProperty(value = "行驶时间")
	private String TravelTimes;

	@ApiModelProperty(value = "平均速度")
	private double averageSpeed;

	@ApiModelProperty(value = "开始里程")
	private double StartMileage;

	@ApiModelProperty(value = "结束里程")
	private double EndMileage;

	@ApiModelProperty(value = "开始位置")
	private String TravelStartPosition;

	@ApiModelProperty(value = "结束位置")
	private String TravelStopPosition;

	@ApiModelProperty(value = "丢失次数")
	private Integer lostCount;

	@ApiModelProperty(value = "位置总数")
	private Integer PositionCount;

	@ApiModelProperty(value = "丢失里程")
	private double lostMileage;

	@ApiModelProperty(value = "总里程")
	private double totalDistance;

	@ApiModelProperty(value = "轨迹完整率")
	private String IntactPositionRate;


}
