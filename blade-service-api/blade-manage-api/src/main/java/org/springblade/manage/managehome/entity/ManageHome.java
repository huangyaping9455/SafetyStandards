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
package org.springblade.manage.managehome.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 */
@Data
@ApiModel(value = "ManageHome对象", description = "ManageHome对象")
public class ManageHome implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * nianchuku
     */
    @ApiModelProperty(value = "平台年出库量")
    private Double  nianchuku;
	/**
	 * nianruku
	 */
	@ApiModelProperty(value = "平台年入库量")
	private Double  nianruku;

	/**
	 * 年完成率
	 */
	@ApiModelProperty(value = "年完成率")
	private Double  nianwancheng;
	/**
	 * nianruku
	 */
	@ApiModelProperty(value = "年在线率")
	private Double  nianzaixian;
	/**
	 * 月完成率
	 */
	@ApiModelProperty(value = "月完成率")
	private Double  yuewancheng;
	/**
	 * nianruku
	 */
	@ApiModelProperty(value = "月在线率")
	private Double  yuezaixian;
	/**
	 * yuechuku
	 */
	@ApiModelProperty(value = "月出库")
	private Double  yuechuku;
	/**
	 * yueruku
	 */
	@ApiModelProperty(value = "月入库")
	private Double  yueruku;
	/**
	 * ri
	 */
	@ApiModelProperty(value = "日")
	private String  ri;
	/**
	 * yue
	 */
	@ApiModelProperty(value = "月")
	private String yue;
	/**
	 * zaiyun
	 */
	@ApiModelProperty(value = "在运")
	private String zaiyun;
	/**
	 * xianzhi
	 */
	@ApiModelProperty(value = "闲置")
	private String xianzhi;
	/**
	 * richuku
	 */
	@ApiModelProperty(value = "日出库")
	private Double richuku;
	/**
	 * riruku
	 */
	@ApiModelProperty(value = "日入库")
	private Double riruku;
	/**
	 * huoyunliang
	 */
	@ApiModelProperty(value = "货运量")
	private Double huoyunliang;
	/**
	 * chepaihao
	 */
	@ApiModelProperty(value = "车牌号")
	private String chepaihao;
	/**
	 * chukuliang
	 */
	@ApiModelProperty(value = "出库量")
	private Double chukuliang;
	/**
	 * chukushijian
	 */
	@ApiModelProperty(value = "出库时间")
	private String chukushijian;
	/**
	 * fahuo
	 */
	@ApiModelProperty(value = "发货公司")
	private String fahuo;
	/**
	 * shouhuo
	 */
	@ApiModelProperty(value = "收货公司")
	private String shouhuo;
}
