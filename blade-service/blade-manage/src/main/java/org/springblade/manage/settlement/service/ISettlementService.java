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
package org.springblade.manage.settlement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.manage.settlement.entity.Settlement;
import org.springblade.manage.settlement.page.SettlementPage;
import org.springblade.manage.settlement.vo.SettlementVO;

/**
 *  服务类
 */
public interface ISettlementService extends IService<Settlement> {

	/**
	 * 自定义 分页
	 * @param contractPage
	 * @return
	 */
	SettlementPage<SettlementVO> selectPageList(SettlementPage contractPage);
	/**
	 * 自定义 分页(对内利润)
	 * @param contractPage
	 * @return
	 */
	SettlementPage<SettlementVO> selectLirunList(SettlementPage contractPage);
	/**
	 * 自定义 分页(支出)
	 * @param contractPage
	 * @return
	 */
	SettlementPage<SettlementVO> selectZhiChuList(SettlementPage contractPage);

	/**
	 * @Description: 根据运单id清楚数据
	 * @Param: [waybillId]
	 */
	boolean delBywaybillId(String waybillId);
}
