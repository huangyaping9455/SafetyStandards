package org.springblade.manage.waybill.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.common.BasePage;

/**
 * @program: SafetyStandards
 * @description: OperationsPage
 * @author: hyp
 * @create: 2019-11-28 10:14
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OperationsPage对象", description = "OperationsPage对象")
public class OperationsPage<T> extends BasePage<T> {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "企业 id", required = true)
	private Integer deptId;
	@ApiModelProperty(value = "车辆牌照")
	private String cheliangpaizhao;
	@ApiModelProperty(value = "类型 1燃油费、2交通费、3维修费、4保险费、5车辆年审费、6驾驶员年审费、7其他费用")
	private String type;
	@ApiModelProperty(value = "开始时间（yyyy-MM-dd）", required = true)
	private String beginTime;
	@ApiModelProperty(value = "结束时间（yyyy-MM-dd）", required = true)
	private String endTime;


}
