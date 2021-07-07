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
package org.springblade.manage.waybill.mapper;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.manage.waybill.entity.StandardSetting;
import org.springblade.manage.waybill.page.StandardSettingPage;
import org.springblade.manage.waybill.vo.StandardSettingVO;

import java.util.List;

/**
 *  Mapper 接口
 * @author 呵呵哒
 */
public interface StandardSettingMapper extends BaseMapper<StandardSetting> {
	/**
	 * 分页列表
	 * @param page
	 * @return
	 */
	List<StandardSettingVO> selectPageList(StandardSettingPage page);

	/**
	 * 分页统计
	 * @param page
	 * @return
	 */
	int selectTotal(StandardSettingPage page);

	/**
	 * 分页列表
	 * @param page
	 * @return
	 */
	List<StandardSettingVO> selectdefaultPageList(StandardSettingPage page);

	/**
	 * 分页统计
	 * @param page
	 * @return
	 */
	int selectdefaultTotal(StandardSettingPage page);

	/**
	 * 监控达标-右侧
	 * @param page
	 * @return
	 */
	List<StandardSettingVO> selecJiankong(StandardSettingPage page);

	/**
	 * 监控达标-右侧
	 * @param page
	 * @return
	 */
	int selectJiankongTotal(StandardSettingPage page);

	/**
	 * 本月监控数据-左侧
	 * @param page
	 * @return
	 */
	List<StandardSettingVO> selecBenYueJiankong(StandardSettingPage page);

	/**
	 * 根据车辆牌照获取车辆本年运营情况
	 * @param page
	 * @return
	 */
	List<StandardSettingVO> selectByYunYing(StandardSettingPage page);

	/**
	 * 根据车辆id和月份获取唯一值
	 * @param vehicleId
	 * @param yue
	 * @return
	 */
	StandardSettingVO selectByPlate(String vehicleId,String yue);

	/**
	 * 根据单位id获取车辆在线率  每个月数据
	 * @param page
	 * @return
	 */
	List<StandardSettingVO> selectByZaiXian(StandardSettingPage page);

	/**
	 * 年度完成率
	 * @param page
	 * @return
	 */
	@SqlParser(filter=true)
	List<StandardSettingVO> selectByWanCheng(StandardSettingPage page);
	//************************************左侧数据
	/**
	 * 月完成率
	 * @param page
	 * @return
	 */
	StandardSettingVO selectByYueDaBiao(StandardSettingPage page);

}
