package org.springblade.gps.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.common.BasePage;

import java.util.List;

/**
 * 监控车辆 分页实体类
 * Program: SafetyStandards
 *
 * @description: VehiclePTPage
 * @author: hyp
 * @create: 2020-06-12
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "VehiclePTPage对象", description = "VehiclePTPage对象")
public class VehiclePTPage<T> extends BasePage<T> {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "企业 id")
    private Integer deptId;

    @ApiModelProperty(value = "企业名称", required = true)
    private String company;

    @ApiModelProperty(value = "车辆状态")
    private String vehicleStatus;

    @ApiModelProperty(value = "车辆牌照")
    private String cpn;

	@ApiModelProperty(value = "第几页(从1开始)")
	private Integer page;

	@ApiModelProperty(value = "每页条数")
	private Integer pagesize;

	@ApiModelProperty(value = "总条数")
	private Integer totalss;

	@ApiModelProperty(value = "数据list")
	private List<VehiclePTPage> list;

	@ApiModelProperty(value = "线路id")
	private String LineId;
}
