package org.springblade.manage.waybill.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: SafetyStandards
 * @description: statistics
 **/
@Data
@ApiModel(value = "Statistics对象", description = "Statistics对象")
public class Statistics implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * cheliangpaizhao
	 */
	@ApiModelProperty(value = "车辆牌照")
	private String  cheliangpaizhao;
	/**
	 * shijian
	 */
	@ApiModelProperty(value = "时间段")
	private String  shijian;
	/**
	 * shouru
	 */
	@ApiModelProperty(value = "收入")
	private String  shouru;
	/**
	 * zhichu
	 */
	@ApiModelProperty(value = "支出")
	private String  zhichu;
	/**
	 * ranyou
	 */
	@ApiModelProperty(value = "燃油")
	private String  ranyou;
	/**
	 * jiaotong
	 */
	@ApiModelProperty(value = "交通费")
	private String  jiaotong;
	/**
	 * weixiu
	 */
	@ApiModelProperty(value = "维修")
	private String  weixiu;
	/**
	 * baoxian
	 */
	@ApiModelProperty(value = "保险")
	private String  baoxian;
	/**
	 * vehiclenianshen
	 */
	@ApiModelProperty(value = "车辆年审")
	private String  vehiclenianshen;
	/**
	 * drivenianshen
	 */
	@ApiModelProperty(value = "驾驶员年审")
	private String  drivenianshen;
	/**
	 * qita
	 */
	@ApiModelProperty(value = "其他")
	private String  qita;
	/**
	 * franyou
	 */
	@ApiModelProperty(value = "非运单燃油")
	private String  franyou;
	/**
	 * fjiaotong
	 */
	@ApiModelProperty(value = "非运单交通费")
	private String  fjiaotong;
	/**
	 * fqita
	 */
	@ApiModelProperty(value = "非运单其他费用")
	private String  fqita;
}
