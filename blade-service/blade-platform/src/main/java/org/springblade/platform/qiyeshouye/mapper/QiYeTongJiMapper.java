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
package org.springblade.platform.qiyeshouye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.platform.qiyeshouye.entity.*;
import org.springblade.platform.qiyeshouye.page.QiYeInOutAreaPage;
import org.springblade.platform.qiyeshouye.page.QiYeOffLineTongJiPage;
import org.springblade.platform.qiyeshouye.page.QiYeTongJiPage;
import org.springblade.platform.qiyeshouye.page.QiYeTpvehdataPage;

import java.util.List;

/**
 * @author 呵呵哒
 * @创建人 hyp
 * @创建时间 2020/6/18
 * @描述
 */
public interface QiYeTongJiMapper extends BaseMapper<QiYeTongJi> {

	/**
	 * 报警统计汇总
	 * @param qiYeTongJiPage
	 * @return
	 */
	List<QiYeTongJi> selectGetBJTJ(QiYeTongJiPage qiYeTongJiPage);
	int selectGetBJTJTotal(QiYeTongJiPage qiYeTongJiPage);

	/**
	 * 车辆报警排名
	 * @param qiYeTongJiPage
	 * @return
	 */
	List<QiYeTongJi> selectBJPMTJ(QiYeTongJiPage qiYeTongJiPage);
	int selectBJTJPMTotal(QiYeTongJiPage qiYeTongJiPage);

	/**
	 * 日运行情况统计
	 * @param qiYeTongJiPage
	 * @return
	 */
	List<QiYeRiYunXingTongJi> selectGetRYXBJTJ(QiYeTongJiPage qiYeTongJiPage);
	int selectGetRYXBJTJTotal(QiYeTongJiPage qiYeTongJiPage);

	/**
	 * 24小时不在线统计
	 * @param qiYeOffLineTongJiPage
	 * @return
	 */
	List<QiYeOffLineTongJi> selectGet24HoursOffLineTJ(QiYeOffLineTongJiPage qiYeOffLineTongJiPage);
	int selectGet24HoursOffLineTJTotal(QiYeOffLineTongJiPage qiYeOffLineTongJiPage);

	/**
	 * 进出区域统计
	 * @param qiYeInOutAreaPage
	 * @return
	 */
	List<QiYeInOutAreaTongJi> selectGetInOutAreaTJ(QiYeInOutAreaPage qiYeInOutAreaPage);
	int selectGetInOutAreaTJTotal(QiYeInOutAreaPage qiYeInOutAreaPage);

	/**
	 *
	 *查询里程统计报表
	 * @param qiYeInOutAreaPage
	 * @return
	 */
	List<StatementTongJi> selectGetMileageTJ (QiYeInOutAreaPage qiYeInOutAreaPage);
	int selectGetMileageTotal(QiYeInOutAreaPage qiYeInOutAreaPage);

	/**
	 *车辆轨迹完整率报表
	 * @param qiYeInOutAreaPage
	 * @return
	 */
	List<StatementTongJi> selectGetTrajectoryIntegrityTJ(QiYeInOutAreaPage qiYeInOutAreaPage);
	int selectGetTrajectoryIntegrityTotal(QiYeInOutAreaPage qiYeInOutAreaPage);

	/**
	 * 轨迹漂移报表
	 * @param qiYeInOutAreaPage
	 * @return
	 */
	List<TrajectoryAnomalies> selectGuiJiYiChangTJ(QiYeInOutAreaPage qiYeInOutAreaPage);
	int selectGuiJiYiChangTotal(QiYeInOutAreaPage qiYeInOutAreaPage);

	/**
	 * 停车明细报表
	 * @param qiYeInOutAreaPage
	 * @return
	 */
	List<StopVehicle> selectTingCheMingXiTJ(QiYeInOutAreaPage qiYeInOutAreaPage);
	int selectTingCheMingXiTotal(QiYeInOutAreaPage qiYeInOutAreaPage);


	/**
	 * 停车汇总报表
	 * @param qiYeInOutAreaPage
	 * @return
	 */
	List<StopVehicle> selectTingCheHuiZongTJ(QiYeInOutAreaPage qiYeInOutAreaPage);
	int selectTingCheHuiZongTotal(QiYeInOutAreaPage qiYeInOutAreaPage);

	/**
	 * 企业在线车辆详情
	 * @param qiYeTpvehdataPage
	 * @return
	 */
	List<QiYeTpvehdataTongJi> selecttpvehdataTJ(QiYeTpvehdataPage qiYeTpvehdataPage);
	int	selectGettpvehdataTJTotal(QiYeTpvehdataPage qiYeTpvehdataPage);

}
