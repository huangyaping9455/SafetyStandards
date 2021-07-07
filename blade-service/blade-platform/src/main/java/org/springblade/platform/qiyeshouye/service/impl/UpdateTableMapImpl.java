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
package org.springblade.platform.qiyeshouye.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springblade.platform.qiyeshouye.entity.UpdateTableMap;
import org.springblade.platform.qiyeshouye.mapper.UpdateTableMapMapper;
import org.springblade.platform.qiyeshouye.service.IUpdateTableMapService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务实现类
 *
 * @author hyp
 * @since 2020-12-29
 */
@Service
@AllArgsConstructor
public class UpdateTableMapImpl extends ServiceImpl<UpdateTableMapMapper, UpdateTableMap> implements IUpdateTableMapService {

	private UpdateTableMapMapper updateTableMapMapper;


	@Override
	public boolean insertAnBiaoDepartmentPostMap(UpdateTableMap updateTableMap) {
		boolean i = false;
		if (StringUtils.isBlank(updateTableMap.getTableName())) {
			String tableName =
				" anbiao_anquanhuiyi_map," +
					" anbiao_baoxianxinxi_map," +
					" anbiao_baoxiuxiangmumingxi_map," +
					" anbiao_chelianganquanshebei_map," +
					" anbiao_cheliangbaofei_map," +
					" anbiao_cheliangbaoxian_map," +
					" anbiao_cheliangdengjipingding_map," +
					" anbiao_cheliangguanchejiancha_map," +
					" anbiao_cheliangjingying_map," +
					" anbiao_cheliangrenyuanbangding_map," +
					" anbiao_cheliangweihu_map," +
					" anbiao_cheliangyuejian_map," +
					" anbiao_departmentpost_map," +
					" anbiao_fagui_map," +
					" anbiao_fangzhenmubiao_map," +
					" anbiao_gangweianquanshengchancaozuoliucheng_map," +
					" anbiao_guacheziliao_map," +
					" anbiao_guanchejianchaxinxi_map," +
					" anbiao_guanlizhidu_map," +
					" anbiao_huiyirenyuan_map," +
					" anbiao_jiaoyupeixun_map," +
					" anbiao_jiashiyuan_map," +
					" anbiao_jiashiyuanheimingdan_map," +
					" anbiao_niandujihua_map," +
					" anbiao_organization_map," +
					" anbiao_personnel_map," +
					" anbiao_shigubaogao_map," +
					" anbiao_shiguchuli_map," +
					" anbiao_vehicle_map," +
					" anbiao_weixiucailiaomingxi_map," +
					" anbiao_xincheyanshou_map," +
					" anbiao_xincheyanshoumingxi_map," +
					" anbiao_yayunyuan_map," +
					" anbiao_yingjiduiwu_map," +
					" anbiao_yingjiyanlian_map," +
					" anbiao_yingjiyanlianjihua_map," +
					" anbiao_yingjiyuan_map," +
					" anbiao_yingjizhuangbei_map," +
					" anbiao_zhengjianshenyan_map";
			String[] tables = tableName.split(",");
			//去除素组中重复的数组
			List<String> tableslist = new ArrayList<String>();
			for (int j = 0; j < tables.length; j++) {
				if (!tableslist.contains(tables[j])) {
					tableslist.add(tables[j]);
				}
			}
			//返回一个包含所有对象的指定类型的数组
			String[] idss = tableslist.toArray(new String[1]);
			for (int p = 0; p < idss.length; p++) {
				System.out.println(idss[p]);
				updateTableMap.setTableName(idss[p]);
				i = updateTableMapMapper.insertAnBiaoDepartmentPostMap(updateTableMap);
			}
		} else {
			i = updateTableMapMapper.insertAnBiaoDepartmentPostMap(updateTableMap);
		}
		return i;
	}

}
