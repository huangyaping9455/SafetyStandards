/**
 * Copyright (C), 2015-2020,
 * FileName: GpsVehicleController
 * Author:   呵呵哒
 * Date:     2020/7/3 9:42
 * Description:
 */
package org.springblade.platform.qiyeshouye.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.platform.qiyeshouye.entity.*;
import org.springblade.platform.qiyeshouye.page.QiYeInOutAreaPage;
import org.springblade.platform.qiyeshouye.page.QiYeOffLineTongJiPage;
import org.springblade.platform.qiyeshouye.page.QiYeTongJiPage;
import org.springblade.platform.qiyeshouye.page.QiYeTpvehdataPage;
import org.springblade.platform.qiyeshouye.service.IQiYeTongJiService;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 呵呵哒
 * @创建人 hyp
 * @创建时间 2020/8/30
 * @描述
 */
@RestController
@AllArgsConstructor
@RequestMapping("/platform/qiYeTongJi")
@Api(value = "企业平台-报警统计", tags = "企业平台-报警统计")
public class QiYeTongJiController {

	private IQiYeTongJiService iQiYeTongJiService;

	@PostMapping(value = "/getBJTJJS")
	@ApiLog("企业报警统计-报警统计汇总")
	@ApiOperation(value = "企业报警统计-报警统计汇总", notes = "传入qiYeTongJiPage",position = 1)
	public R<QiYeTongJiPage<QiYeTongJi>> getBJTJJS(@RequestBody QiYeTongJiPage qiYeTongJiPage) {
		//排序条件
		////默认报警总数降序
		if(qiYeTongJiPage.getOrderColumns()==null){
			qiYeTongJiPage.setOrderColumn("baojingcishu");
		}else{
			qiYeTongJiPage.setOrderColumn(qiYeTongJiPage.getOrderColumns());
		}
		QiYeTongJiPage<QiYeTongJi> pages = iQiYeTongJiService.selectGetBJTJ(qiYeTongJiPage);
		return R.data(pages);
	}

	@PostMapping(value = "/getBJPMTJ")
	@ApiLog("企业报警统计-车辆报警排名")
	@ApiOperation(value = "企业报警统计-车辆报警排名", notes = "传入qiYeTongJiPage",position = 2)
	public R<QiYeTongJiPage<QiYeTongJi>> getBJPMTJ(@RequestBody QiYeTongJiPage qiYeTongJiPage) {
		//排序条件
		////默认报警总数降序
		if(qiYeTongJiPage.getOrderColumns()==null){
			qiYeTongJiPage.setOrderColumn("baojingcishu");
		}else{
			qiYeTongJiPage.setOrderColumn(qiYeTongJiPage.getOrderColumns());
		}
		QiYeTongJiPage<QiYeTongJi> pages = iQiYeTongJiService.selectBJPMTJ(qiYeTongJiPage);
		return R.data(pages);
	}

	@PostMapping(value = "/getBJRYXTJ")
	@ApiLog("企业报警统计-日运行情况统计")
	@ApiOperation(value = "企业报警统计-日运行情况统计", notes = "传入qiYeTongJiPage",position = 2)
	public R<QiYeTongJiPage<QiYeRiYunXingTongJi>> getBJRYXTJ(@RequestBody QiYeTongJiPage qiYeTongJiPage) {
		//排序条件
		////默认报警总数降序
		if(qiYeTongJiPage.getOrderColumns()==null){
			qiYeTongJiPage.setOrderColumn("");
		}else{
			qiYeTongJiPage.setOrderColumn(qiYeTongJiPage.getOrderColumns());
		}
		QiYeTongJiPage<QiYeRiYunXingTongJi> pages = iQiYeTongJiService.selectGetRYXBJTJ(qiYeTongJiPage);
		return R.data(pages);
	}

	@PostMapping(value = "/getQYOffLineTJ")
	@ApiLog("企业-24小时不在线车辆统计")
	@ApiOperation(value = "企业-24小时不在线车辆统计", notes = "传入qiYeOffLineTongJiPage",position = 5)
	public R<QiYeOffLineTongJiPage<QiYeOffLineTongJi>> getQYOffLineTJ(@RequestBody QiYeOffLineTongJiPage qiYeOffLineTongJiPage) {
		//排序条件
		////默认车辆牌照降序
		if(qiYeOffLineTongJiPage.getOrderColumns()==null){
			qiYeOffLineTongJiPage.setOrderColumn("cheliangpaizhao");
		}else{
			qiYeOffLineTongJiPage.setOrderColumn(qiYeOffLineTongJiPage.getOrderColumns());
		}
		QiYeOffLineTongJiPage<QiYeOffLineTongJi> pages = iQiYeTongJiService.selectGet24HoursOffLineTJ(qiYeOffLineTongJiPage);
		return R.data(pages);
	}

	@PostMapping(value = "/getQYInOutAreaTJ")
	@ApiLog("企业-进出区域统计")
	@ApiOperation(value = "企业-进出区域统计", notes = "传入qiYeInOutAreaPage",position = 6)
	public R<QiYeInOutAreaPage<QiYeInOutAreaTongJi>> getQYInOutAreaTJ(@RequestBody QiYeInOutAreaPage qiYeInOutAreaPage) {
		//排序条件
		////默认车辆牌照降序
		if(qiYeInOutAreaPage.getOrderColumns()==null){
			qiYeInOutAreaPage.setOrderColumn("KeepTime");
		}else{
			qiYeInOutAreaPage.setOrderColumn(qiYeInOutAreaPage.getOrderColumns());
		}
		QiYeInOutAreaPage<QiYeInOutAreaTongJi> pages = iQiYeTongJiService.selectGetInOutAreaTJ(qiYeInOutAreaPage);
		return R.data(pages);
	}

	@PostMapping(value = "/getMileageTJ")
	@ApiLog("企业-查询里程统计报表")
	@ApiOperation(value = "企业-查询里程统计报表", notes = "传入qiYeInOutAreaPage",position = 7)
	public R<QiYeInOutAreaPage<StatementTongJi>> getMileageTJ(QiYeInOutAreaPage qiYeInOutAreaPage) {
		if (qiYeInOutAreaPage.getOrderColumns() == null) {
			qiYeInOutAreaPage.setOrderColumn("statisticsDate");
		} else {
			qiYeInOutAreaPage.setOrderColumn(qiYeInOutAreaPage.getOrderColumns());
		}
		QiYeInOutAreaPage<StatementTongJi> pages = iQiYeTongJiService.selectGetMileageTJ(qiYeInOutAreaPage);
		return R.data(pages);
	}

	@PostMapping(value = "/getTrajectoryIntegrityTJ")
	@ApiLog("企业-车辆轨迹完整率报表")
	@ApiOperation(value = "企业-车辆轨迹完整率报表", notes = "传入qiYeInOutAreaPage",position = 8)
	public R<QiYeInOutAreaPage<StatementTongJi>> getTrajectoryIntegrityTJ(QiYeInOutAreaPage qiYeInOutAreaPage) {
		if (qiYeInOutAreaPage.getOrderColumns() == null) {
			qiYeInOutAreaPage.setOrderColumn("statisticsDate");
		} else {
			qiYeInOutAreaPage.setOrderColumn(qiYeInOutAreaPage.getOrderColumns());
		}
		QiYeInOutAreaPage pages = iQiYeTongJiService.selectGetTrajectoryIntegrityTJ(qiYeInOutAreaPage);
		return R.data(pages);
	}

	@PostMapping(value = "/getGuiJiYiChangTJ")
	@ApiLog("企业-轨迹漂移报表")
	@ApiOperation(value = "企业-轨迹漂移报表", notes = "传入qiYeInOutAreaPage",position = 9)
	public R<QiYeInOutAreaPage<TrajectoryAnomalies>> getGuiJiYiChangTJ(QiYeInOutAreaPage qiYeInOutAreaPage) {
		if (qiYeInOutAreaPage.getOrderColumns() == null) {
			qiYeInOutAreaPage.setOrderColumn("cheliangpaizhao");
		} else {
			qiYeInOutAreaPage.setOrderColumn(qiYeInOutAreaPage.getOrderColumns());
		}
		QiYeInOutAreaPage pages = iQiYeTongJiService.selectGuiJiYiChangTJ(qiYeInOutAreaPage);
		return R.data(pages);
	}

	@PostMapping(value = "/getTingCheMingXiTJ")
	@ApiLog("企业-停车明细报表")
	@ApiOperation(value = "企业-停车明细报表", notes = "传入qiYeInOutAreaPage",position = 10)
	public R<QiYeInOutAreaPage<StopVehicle>> getTingCheMingXiTJ(QiYeInOutAreaPage qiYeInOutAreaPage) {
		if (qiYeInOutAreaPage.getOrderColumns() == null) {
			qiYeInOutAreaPage.setOrderColumn("cheliangpaizhao");
		} else {
			qiYeInOutAreaPage.setOrderColumn(qiYeInOutAreaPage.getOrderColumns());
		}
		QiYeInOutAreaPage pages = iQiYeTongJiService.selectTingCheMingXiTJ(qiYeInOutAreaPage);
		return R.data(pages);
	}

	@PostMapping(value = "/getTingCheHuiZongTJ")
	@ApiLog("企业-停车汇总报表")
	@ApiOperation(value = "企业-停车汇总报表", notes = "传入qiYeInOutAreaPage",position = 11)
	public R<QiYeInOutAreaPage<StopVehicle>> getTingCheHuiZongTJ(QiYeInOutAreaPage qiYeInOutAreaPage) {
		if (qiYeInOutAreaPage.getOrderColumns() == null) {
			qiYeInOutAreaPage.setOrderColumn("cheliangpaizhao");
		} else {
			qiYeInOutAreaPage.setOrderColumn(qiYeInOutAreaPage.getOrderColumns());
		}
		QiYeInOutAreaPage pages = iQiYeTongJiService.selectTingCheHuiZongTJ(qiYeInOutAreaPage);
		return R.data(pages);
	}

	@PostMapping(value = "/getTpvehdataTJ")
	@ApiLog("企业-在线车辆详情")
	@ApiOperation(value = "企业-在线车辆详情", notes = "传入qiYeTpvehdataPage",position = 12)
	public R<QiYeTpvehdataPage<QiYeTpvehdataTongJi>> getTpvehdataTJ(@RequestBody QiYeTpvehdataPage qiYeTpvehdataPage) {

		//排序条件
		////默认定位时间降序
		if(qiYeTpvehdataPage.getOrderColumns()==null){
			qiYeTpvehdataPage.setOrderColumn("Systime");
		}else{
			qiYeTpvehdataPage.setOrderColumn(qiYeTpvehdataPage.getOrderColumns());
		}
		QiYeTpvehdataPage<QiYeTpvehdataTongJi> pages = iQiYeTongJiService.selecttpvehdataTJ(qiYeTpvehdataPage);
		return R.data(pages);
	}

}

