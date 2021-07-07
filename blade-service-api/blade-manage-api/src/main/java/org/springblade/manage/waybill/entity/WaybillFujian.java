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
package org.springblade.manage.waybill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 */
@Data
@TableName("manage_waybill_fujian")
@ApiModel(value = "WaybillFujian对象", description = "WaybillFujian对象")
public class WaybillFujian implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
  	private Integer id;
    /**
     * 货运单id
     */
    @ApiModelProperty(value = "货运单id")
    private String waybillId;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String leixing;
    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private Double jine;
    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    private String shijian;
    /**
     * 附件
     */
    @ApiModelProperty(value = "附件")
    private String fujian;


}
