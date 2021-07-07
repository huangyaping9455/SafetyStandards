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
package org.springblade.manage.waybill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.manage.waybill.entity.Waybill;
import org.springblade.manage.waybill.page.WaybillPage;
import org.springblade.manage.waybill.vo.WaybillVO;

/**
 *  服务类
 *
 * @author jx
 * @since 2019-08-07
 */
public interface IWaybillService extends IService<Waybill> {


	WaybillPage<WaybillVO> selectWaybillPageList(WaybillPage page);

	WaybillVO selectDetail(String id);

	/**
	 * @Description: 自定义新增
	 * @Param: [waybill]
	 */
	boolean insertWay(Waybill waybill);

}
