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
package org.springblade.manage.waybill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.manage.waybill.entity.WaybillFujian;
import org.springblade.manage.waybill.mapper.WaybillFujianMapper;
import org.springblade.manage.waybill.service.IWaybillFujianService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 */
@Service
@AllArgsConstructor
public class WaybillFujianServiceImpl extends ServiceImpl<WaybillFujianMapper, WaybillFujian> implements IWaybillFujianService {

	private WaybillFujianMapper waybillFujianMapper;

	@Override
	public List<WaybillFujian> selectbyWayId(String waybillId) {
		return waybillFujianMapper.selectbyWayId(waybillId);
	}

	@Override
	public WaybillFujian selectbyWayIdandLeixing(String waybillId, String leixing) {
		return waybillFujianMapper.selectbyWayIdandLeixing(waybillId,leixing);
	}

	@Override
	public boolean delBywaybillId(String waybillId) {
		return waybillFujianMapper.delBywaybillId(waybillId);
	}

	@Override
	public boolean insertWay(WaybillFujian waybillFujian) {
		return waybillFujianMapper.insertWay(waybillFujian);
	}

	@Override
	public boolean del(Integer id) {
		return waybillFujianMapper.del(id);
	}
}
