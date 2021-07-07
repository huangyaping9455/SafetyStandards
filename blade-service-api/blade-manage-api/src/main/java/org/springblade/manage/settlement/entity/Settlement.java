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
package org.springblade.manage.settlement.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体类
 */
@Data
@TableName("manage_settlement")
@ApiModel(value = "Settlement对象", description = "Settlement对象")
public class Settlement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;
	/**
	 * 单位id
	 */
	@ApiModelProperty(value = "单位id")
	private Integer deptId;
    /**
     * 运单id
     */
    @ApiModelProperty(value = "运单id")
    private String waybillId;
    /**
     * 装货时间
     */
    @ApiModelProperty(value = "装货时间")
    private String zhuanghuoshijian;
    /**
     * 卸货时间
     */
    @ApiModelProperty(value = "卸货时间")
    private String xiehuoshijian;
  private String vehicleId;
    /**
     * 装货地
     */
    @ApiModelProperty(value = "装货地")
    private String zhuanghuodi;
    /**
     * 卸货地
     */
    @ApiModelProperty(value = "卸货地")
    private String xiehuodi;
    /**
     * 货物名称
     */
    @ApiModelProperty(value = "货物名称")
    private String huowumingcheng;
    /**
     * 装货量
     */
    @ApiModelProperty(value = "装货量")
    private String zhuanghuoliang;
    /**
     * 入库序号
     */
    @ApiModelProperty(value = "入库序号")
    private String rukuxuhao;
	/**
	 * 出库序号
	 */
	@ApiModelProperty(value = "出库序号")
	private String chukuxuhao;
    /**
     * 卸货量
     */
    @ApiModelProperty(value = "卸货量")
    private String xiehuoliang;
    /**
     * 磅差
     */
    @ApiModelProperty(value = "磅差")
    private String bangcha;
    /**
     * 结算量
     */
    @ApiModelProperty(value = "结算量")
    private String jiesuanliang;
    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private String danjia;
    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private String jine;
    /**
     * 折损吨位
     */
    @ApiModelProperty(value = "折损吨位")
    private String zhesundunwei;
    /**
     * 折损金额
     */
    @ApiModelProperty(value = "折损金额")
    private String zhesunjine;
    /**
     * 折损单价
     */
    @ApiModelProperty(value = "折损单价")
    private String zhesundanjia;
    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    private LocalDateTime caozuoshijian;
    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    private String caozuoren;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createtime;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createuser;
    /**
     * 是否删除(1代表已删除)
     */
    @ApiModelProperty(value = "是否删除(1代表已删除)")
    private Integer isDelete;

	/**
	 * 企业名称
	 */
	@ApiModelProperty(value = "企业名称")
	 private String deptName;

	/**
	 * 车牌号
	 */
	@ApiModelProperty(value = "车牌号")
	private String chepaihao;

	/**
	 * 利润
	 */
	@ApiModelProperty(value = "利润")
	private String lirun;

	/**
	 * 燃油费
	 */
	@ApiModelProperty(value = "燃油费")
	private String ranyoufei;

	/**
	 * 过路费
	 */
	@ApiModelProperty(value = "过路费")
	private String guolufei;

	/**
	 * 其他费用
	 */
	@ApiModelProperty(value = "其他费用")
	private String qitafei;

	/**
	 * 税率
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "税率")
	private String shuilv;

	/**
	 * 支出
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "支出")
	private String zhichu;

	/**
	 * 出库时间
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "出库时间")
	private String outTime;
	/**
	 * 入库时间
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "入库时间")
	private String intTime;
	/**
	 * 入库净重
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "入库净重")
	private String onetWeight;
	/**
	 * 入库净重
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "入库净重")
	private String inetWeight;
	/**
	 * 序号
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "序号")
	private Integer xuhao;

}
