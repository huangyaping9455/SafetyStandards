package org.springblade.platform.falvfagui.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.platform.falvfagui.entity.FaGui;

/**
 * @description: 法规视图层
 * @author: 呵呵哒
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "FaGuiVo对象", description = "FaGuiVo对象")
public class FaGuiVo extends FaGui {

	private static final long serialVersionUID = 1L;

}
