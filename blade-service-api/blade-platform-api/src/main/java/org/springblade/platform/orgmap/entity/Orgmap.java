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
package org.springblade.platform.orgmap.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 * @author 呵呵哒
 */
@Data
@TableName("blade_orgmap")
@ApiModel(value = "Orgmap对象", description = "Orgmap对象")
public class Orgmap implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
  	private Integer id;
    /**
     * 父主键
     */
    @ApiModelProperty(value = "父主键")
    private Integer parentId;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String deptName;
    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人")
    private String fuzeren;
	/**
	 * 单位id
	 */
	@ApiModelProperty(value = "单位id")
  	private Integer deptId;
    /**
     * 照片
     */
    @ApiModelProperty(value = "照片")
    private String photo;
    /**
     * 岗位职责
     */
    @ApiModelProperty(value = "岗位职责")
    private String gangweizhize;
    /**
     * 安全职责
     */
    @ApiModelProperty(value = "安全职责")
    private String anquanzhize;
    /**
     * 是否已删除
     */
    @ApiModelProperty(value = "是否已删除")
    private Integer isDeleted;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createtime;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createuser;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private String updatetime;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updateuser;


}
