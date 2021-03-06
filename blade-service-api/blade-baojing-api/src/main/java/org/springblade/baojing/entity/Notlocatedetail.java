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
package org.springblade.baojing.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 24小时不定位 实体类
 */
@Data
@TableName("baobiao_notlocatedetail")
@ApiModel(value = "Notlocatedetail对象", description = "Notlocatedetail对象")
public class Notlocatedetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Integer noid;
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Integer cid;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String company;
    /**
     * 车辆id
     */
    @ApiModelProperty(value = "车辆id")
    private Integer vid;
    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plate;
    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    private String color;
    /**
     * 车队
     */
    @ApiModelProperty(value = "车队")
    private String lineid;
    /**
     * 营运类型
     */
    @ApiModelProperty(value = "营运类型")
    @TableField("operatType")
    private String operatType;
    /**
     * 最后数据时间
     */
    @ApiModelProperty(value = "最后回传时间")
    @TableField("lastTime")
    private LocalDateTime lastTime;
    /**
     * 系统时间
     */
    @ApiModelProperty(value = "最后数据时间")
    private LocalDateTime systime;
    /**
     * 最后定位时间
     */
    @ApiModelProperty(value = "最后定位时间")
    @TableField("lastlocateTime")
    private LocalDateTime lastlocateTime;
    /**
     * 处理结果
     */
    @ApiModelProperty(value = "处理结果")
    @TableField("handleResult")
    private String handleResult;
    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期")
    @TableField("createDate")
    private LocalDate createDate;


}
