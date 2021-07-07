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
import org.springblade.manage.waybill.entity.StandardSetting;
import org.springblade.manage.waybill.page.StandardSettingPage;
import org.springblade.manage.waybill.vo.StandardSettingVO;

import java.util.List;

/**
 *  服务类
 * @author 呵呵哒
 */
public interface IStandardSettingService extends IService<StandardSetting> {
	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param page
	 * @return
	 */
	StandardSettingPage<StandardSettingVO> selectPageList(StandardSettingPage page);
	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param page
	 * @return
	 */
	StandardSettingPage<StandardSettingVO> selectdefaultPageList(StandardSettingPage page);

	/**
	 * 监控分页
	 * @param page
	 * @return
	 */
	StandardSettingPage<StandardSettingVO> selecJiankong(StandardSettingPage page);
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
	 * 根据单位id获取车辆在线率
	 * @param page
	 * @return
	 */
	List<StandardSettingVO> selectByZaiXian(StandardSettingPage page);

	/**
	 * 月达标率
	 * @param page
	 * @return
	 */
	StandardSettingVO selectByYueDaBiao(StandardSettingPage page);
	/**
	 * 年度完成率
	 * @param page
	 * @return
	 */
	List<StandardSettingVO> selectByWanCheng(StandardSettingPage page);

}
