package org.springblade.platform.cheliangziping.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Lh
 * @description: TODO
 * @projectName SafetyStandards
 * @date 2019/9/2510:56
 */
@Data
@ApiModel(value = "ZipinZhouQI", description = "ZipinZhouQI")
public class ZipinZhouQI {
	private Integer number;
	private String  zijianzhouqi;
}
