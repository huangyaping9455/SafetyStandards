package org.springblade.platform.cheliangguanli.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.common.BasePage;

/**
 * Created by you on 2019/5/1.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "BaoYangWeiXiuPage对象", description = "BaoYangWeiXiuPage对象")
public class BaoYangWeiXiuPage<T> extends BasePage<T> {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "企业 id", required = true)
	private Integer deptId;

	@ApiModelProperty(value = "企业名称")
	private String deptName;

	@ApiModelProperty(value = "车辆牌照")
	private String cheliangpaizhao;

}
