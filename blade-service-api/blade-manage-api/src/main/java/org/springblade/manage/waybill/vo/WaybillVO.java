/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.manage.waybill.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.manage.waybill.entity.Waybill;

/**
 * 视图实体类
 *
 * @author jx
 * @since 2019-08-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "WaybillVO对象", description = "WaybillVO对象")
public class WaybillVO extends Waybill {
	private static final long serialVersionUID = 1L;

	/**
	 * 审核状态
	 */
	@ApiModelProperty(value="审核状态")
	private String examine;
	/**
	 * 车辆牌照
	 */
	@ApiModelProperty(value ="车辆牌照")
	private String cheliangpaizhao;



}
