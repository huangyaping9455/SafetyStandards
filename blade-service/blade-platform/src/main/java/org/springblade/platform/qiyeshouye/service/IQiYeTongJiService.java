/**
 * Copyright (C), 2015-2020,
 * FileName: IXinXiJiaoHuZhuService
 * Author:   呵呵哒
 * Date:     2020/6/20 17:18
 * Description:
 */
package org.springblade.platform.qiyeshouye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.platform.qiyeshouye.entity.*;
import org.springblade.platform.qiyeshouye.page.QiYeInOutAreaPage;
import org.springblade.platform.qiyeshouye.page.QiYeOffLineTongJiPage;
import org.springblade.platform.qiyeshouye.page.QiYeTongJiPage;
import org.springblade.platform.qiyeshouye.page.QiYeTpvehdataPage;

import java.util.List;

/**
 * @author 呵呵哒
 * @创建人 hyp
 * @创建时间 2020/7/4
 * @描述
 */
public interface IQiYeTongJiService extends IService<QiYeTongJi> {

	/**
	 * 报警统计汇总
	 * @param qiYeTongJiPage
	 * @return
	 */
	QiYeTongJiPage selectGetBJTJ(QiYeTongJiPage qiYeTongJiPage);

	/**
	 * 车辆报警排名
	 * @param qiYeTongJiPage
	 * @return
	 */
	QiYeTongJiPage selectBJPMTJ(QiYeTongJiPage qiYeTongJiPage);

	/**
	 * 日运行情况统计
	 * @param qiYeTongJiPage
	 * @return
	 */
	QiYeTongJiPage selectGetRYXBJTJ(QiYeTongJiPage qiYeTongJiPage);

	/**
	 * 24小时不在线统计
	 * @param qiYeOffLineTongJiPage
	 * @return
	 */
	QiYeOffLineTongJiPage<QiYeOffLineTongJi> selectGet24HoursOffLineTJ(QiYeOffLineTongJiPage qiYeOffLineTongJiPage);

	/**
	 * 进出区域统计
	 * @param qiYeInOutAreaPage
	 * @return
	 */
	QiYeInOutAreaPage<QiYeInOutAreaTongJi> selectGetInOutAreaTJ(QiYeInOutAreaPage qiYeInOutAreaPage);


	/**
	 *查询里程统计报表
	 * @param qiYeInOutAreaPage
	 * @return
	 */
	QiYeInOutAreaPage<StatementTongJi> selectGetMileageTJ (QiYeInOutAreaPage qiYeInOutAreaPage);

	/**
	 *车辆轨迹完整率报表
	 * @param qiYeInOutAreaPage
	 * @return
	 */
	QiYeInOutAreaPage<StatementTongJi> selectGetTrajectoryIntegrityTJ(QiYeInOutAreaPage qiYeInOutAreaPage);

	/**
	 * 轨迹漂移报表
	 * @param qiYeInOutAreaPage
	 * @return
	 */
	QiYeInOutAreaPage<TrajectoryAnomalies> selectGuiJiYiChangTJ(QiYeInOutAreaPage qiYeInOutAreaPage);

	/**
	 * 停车明细报表
	 * @param qiYeInOutAreaPage
	 * @return
	 */
	QiYeInOutAreaPage<StopVehicle> selectTingCheMingXiTJ(QiYeInOutAreaPage qiYeInOutAreaPage);

	/**
	 * 停车汇总报表
	 * @param qiYeInOutAreaPage
	 * @return
	 */
	QiYeInOutAreaPage<StopVehicle> selectTingCheHuiZongTJ(QiYeInOutAreaPage qiYeInOutAreaPage);

	/**
	 * 企业在线车辆详情
	 * @param qiYeTpvehdataPage
	 * @return
	 */
	QiYeTpvehdataPage<QiYeTpvehdataTongJi> selecttpvehdataTJ(QiYeTpvehdataPage qiYeTpvehdataPage);


}
