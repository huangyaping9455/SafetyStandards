package org.springblade.platform.qiyeshouye;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.platform.qiyeshouye.entity.Notice;

/**
 * 通知公告视图类
 *
 * @author hyp
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NoticeVO extends Notice {

	@ApiModelProperty(value = "通知类型名")
	private String categoryName;

}
