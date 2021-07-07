/**
 * Copyright (C), 2015-2020,
 * FileName: XinXiJiaoHuZhuServiceImpl
 * Author:   呵呵哒
 * Date:     2020/6/20 17:18
 * Description:
 */
package org.springblade.platform.qiyeshouye.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.common.tool.GpsToBaiduUtil;
import org.springblade.common.tool.LatLotForLocation;
import org.springblade.platform.qiyeshouye.entity.*;
import org.springblade.platform.qiyeshouye.mapper.QiYeTongJiMapper;
import org.springblade.platform.qiyeshouye.page.QiYeInOutAreaPage;
import org.springblade.platform.qiyeshouye.page.QiYeOffLineTongJiPage;
import org.springblade.platform.qiyeshouye.page.QiYeTongJiPage;
import org.springblade.platform.qiyeshouye.page.QiYeTpvehdataPage;
import org.springblade.platform.qiyeshouye.service.IQiYeTongJiService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 呵呵哒
 * @创建人 hyp
 * @创建时间 2020/7/4
 * @描述
 */
@Service
@AllArgsConstructor
public class QiYeTongJiServiceImpl extends ServiceImpl<QiYeTongJiMapper, QiYeTongJi> implements IQiYeTongJiService {

	private QiYeTongJiMapper qiYeTongJiMapper;

	@Override
	public QiYeTongJiPage selectGetBJTJ(QiYeTongJiPage qiYeTongJiPage) {
		Integer total = qiYeTongJiMapper.selectGetBJTJTotal(qiYeTongJiPage);
		if(qiYeTongJiPage.getSize()==0){
			if(qiYeTongJiPage.getTotal()==0){
				qiYeTongJiPage.setTotal(total);
			}
			if(qiYeTongJiPage.getTotal()==0){
				return qiYeTongJiPage;
			}else{
				List<QiYeTongJi> zhengFuBaoJingTongJiList = qiYeTongJiMapper.selectGetBJTJ(qiYeTongJiPage);
				qiYeTongJiPage.setRecords(zhengFuBaoJingTongJiList);
				return qiYeTongJiPage;
			}
		}
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%qiYeTongJiPage.getSize()==0){
				pagetotal = total / qiYeTongJiPage.getSize();
			}else {
				pagetotal = total / qiYeTongJiPage.getSize() + 1;
			}
		}
		if (pagetotal >= qiYeTongJiPage.getCurrent()) {
			qiYeTongJiPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (qiYeTongJiPage.getCurrent() > 1) {
				offsetNo = qiYeTongJiPage.getSize() * (qiYeTongJiPage.getCurrent() - 1);
			}
			qiYeTongJiPage.setTotal(total);
			qiYeTongJiPage.setOffsetNo(offsetNo);
			List<QiYeTongJi> zhengFuBaoJingTongJiList = qiYeTongJiMapper.selectGetBJTJ(qiYeTongJiPage);
			qiYeTongJiPage.setRecords(zhengFuBaoJingTongJiList);
		}
		return qiYeTongJiPage;
	}

	@Override
	public QiYeTongJiPage selectBJPMTJ(QiYeTongJiPage qiYeTongJiPage) {
		Integer total = qiYeTongJiMapper.selectBJTJPMTotal(qiYeTongJiPage);
		if(qiYeTongJiPage.getSize()==0){
			if(qiYeTongJiPage.getTotal()==0){
				qiYeTongJiPage.setTotal(total);
			}
			if(qiYeTongJiPage.getTotal()==0){
				return qiYeTongJiPage;
			}else{
				List<QiYeTongJi> zhengFuBaoJingTongJiList = qiYeTongJiMapper.selectBJPMTJ(qiYeTongJiPage);
				qiYeTongJiPage.setRecords(zhengFuBaoJingTongJiList);
				return qiYeTongJiPage;
			}
		}
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%qiYeTongJiPage.getSize()==0){
				pagetotal = total / qiYeTongJiPage.getSize();
			}else {
				pagetotal = total / qiYeTongJiPage.getSize() + 1;
			}
		}
		if (pagetotal >= qiYeTongJiPage.getCurrent()) {
			qiYeTongJiPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (qiYeTongJiPage.getCurrent() > 1) {
				offsetNo = qiYeTongJiPage.getSize() * (qiYeTongJiPage.getCurrent() - 1);
			}
			qiYeTongJiPage.setTotal(total);
			qiYeTongJiPage.setOffsetNo(offsetNo);
			List<QiYeTongJi> zhengFuBaoJingTongJiList = qiYeTongJiMapper.selectBJPMTJ(qiYeTongJiPage);
			qiYeTongJiPage.setRecords(zhengFuBaoJingTongJiList);
		}
		return qiYeTongJiPage;
	}

	@Override
	public QiYeTongJiPage selectGetRYXBJTJ(QiYeTongJiPage qiYeTongJiPage) {
		Integer total = qiYeTongJiMapper.selectGetRYXBJTJTotal(qiYeTongJiPage);
		if(qiYeTongJiPage.getSize()==0){
			if(qiYeTongJiPage.getTotal()==0){
				qiYeTongJiPage.setTotal(total);
			}
			if(qiYeTongJiPage.getTotal()==0){
				return qiYeTongJiPage;
			}else{
				List<QiYeRiYunXingTongJi> zhengFuBaoJingTongJiList = qiYeTongJiMapper.selectGetRYXBJTJ(qiYeTongJiPage);
				qiYeTongJiPage.setRecords(zhengFuBaoJingTongJiList);
				return qiYeTongJiPage;
			}
		}
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%qiYeTongJiPage.getSize()==0){
				pagetotal = total / qiYeTongJiPage.getSize();
			}else {
				pagetotal = total / qiYeTongJiPage.getSize() + 1;
			}
		}
		if (pagetotal >= qiYeTongJiPage.getCurrent()) {
			qiYeTongJiPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (qiYeTongJiPage.getCurrent() > 1) {
				offsetNo = qiYeTongJiPage.getSize() * (qiYeTongJiPage.getCurrent() - 1);
			}
			qiYeTongJiPage.setTotal(total);
			qiYeTongJiPage.setOffsetNo(offsetNo);
			List<QiYeRiYunXingTongJi> zhengFuBaoJingTongJiList = qiYeTongJiMapper.selectGetRYXBJTJ(qiYeTongJiPage);
			qiYeTongJiPage.setRecords(zhengFuBaoJingTongJiList);
		}
		return qiYeTongJiPage;
	}

	@Override
	public QiYeOffLineTongJiPage<QiYeOffLineTongJi> selectGet24HoursOffLineTJ(QiYeOffLineTongJiPage qiYeOffLineTongJiPage) {
		Integer total = qiYeTongJiMapper.selectGet24HoursOffLineTJTotal(qiYeOffLineTongJiPage);
		if(qiYeOffLineTongJiPage.getSize()==0){
			if(qiYeOffLineTongJiPage.getTotal()==0){
				qiYeOffLineTongJiPage.setTotal(total);
			}
			if(qiYeOffLineTongJiPage.getTotal()==0){
				return qiYeOffLineTongJiPage;
			}else{
				List<QiYeOffLineTongJi> zhengFuBaoJingTongJiList = qiYeTongJiMapper.selectGet24HoursOffLineTJ(qiYeOffLineTongJiPage);
				qiYeOffLineTongJiPage.setRecords(zhengFuBaoJingTongJiList);
				return qiYeOffLineTongJiPage;
			}
		}
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%qiYeOffLineTongJiPage.getSize()==0){
				pagetotal = total / qiYeOffLineTongJiPage.getSize();
			}else {
				pagetotal = total / qiYeOffLineTongJiPage.getSize() + 1;
			}
		}
		if (pagetotal >= qiYeOffLineTongJiPage.getCurrent()) {
			qiYeOffLineTongJiPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (qiYeOffLineTongJiPage.getCurrent() > 1) {
				offsetNo = qiYeOffLineTongJiPage.getSize() * (qiYeOffLineTongJiPage.getCurrent() - 1);
			}
			qiYeOffLineTongJiPage.setTotal(total);
			qiYeOffLineTongJiPage.setOffsetNo(offsetNo);
			List<QiYeOffLineTongJi> zhengFuBaoJingTongJiList = qiYeTongJiMapper.selectGet24HoursOffLineTJ(qiYeOffLineTongJiPage);
			qiYeOffLineTongJiPage.setRecords(zhengFuBaoJingTongJiList);
		}
		return qiYeOffLineTongJiPage;
	}

	@Override
	public QiYeInOutAreaPage<QiYeInOutAreaTongJi> selectGetInOutAreaTJ(QiYeInOutAreaPage qiYeInOutAreaPage) {
		Integer total = qiYeTongJiMapper.selectGetInOutAreaTJTotal(qiYeInOutAreaPage);
		if(qiYeInOutAreaPage.getSize()==0){
			if(qiYeInOutAreaPage.getTotal()==0){
				qiYeInOutAreaPage.setTotal(total);
			}
			if(qiYeInOutAreaPage.getTotal()==0){
				return qiYeInOutAreaPage;
			}else{
				List<QiYeInOutAreaTongJi> qiYeInOutAreaTongJiList = qiYeTongJiMapper.selectGetInOutAreaTJ(qiYeInOutAreaPage);
				qiYeInOutAreaPage.setRecords(qiYeInOutAreaTongJiList);
			}
		}
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%qiYeInOutAreaPage.getSize()==0){
				pagetotal = total / qiYeInOutAreaPage.getSize();
			}else {
				pagetotal = total / qiYeInOutAreaPage.getSize() + 1;
			}
		}
		if (pagetotal >= qiYeInOutAreaPage.getCurrent()) {
			qiYeInOutAreaPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (qiYeInOutAreaPage.getCurrent() > 1) {
				offsetNo = qiYeInOutAreaPage.getSize() * (qiYeInOutAreaPage.getCurrent() - 1);
			}
			qiYeInOutAreaPage.setTotal(total);
			qiYeInOutAreaPage.setOffsetNo(offsetNo);
			List<QiYeInOutAreaTongJi> qiYeInOutAreaTongJiList = qiYeTongJiMapper.selectGetInOutAreaTJ(qiYeInOutAreaPage);
			qiYeInOutAreaPage.setRecords(qiYeInOutAreaTongJiList);
		}
		return qiYeInOutAreaPage;
	}

	@Override
	public QiYeInOutAreaPage<StatementTongJi> selectGetMileageTJ(QiYeInOutAreaPage qiYeInOutAreaPage) {
		Integer total = qiYeTongJiMapper.selectGetMileageTotal(qiYeInOutAreaPage);
		if(qiYeInOutAreaPage.getSize()==0){
			if(qiYeInOutAreaPage.getTotal()==0){
				qiYeInOutAreaPage.setTotal(total);
			}
			if(qiYeInOutAreaPage.getTotal()==0){
				return qiYeInOutAreaPage;
			}else{
				List<StatementTongJi> statementTongJiList = qiYeTongJiMapper.selectGetMileageTJ(qiYeInOutAreaPage);
				qiYeInOutAreaPage.setRecords(statementTongJiList);
				return qiYeInOutAreaPage;
			}
		}
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%qiYeInOutAreaPage.getSize()==0){
				pagetotal = total / qiYeInOutAreaPage.getSize();
			}else {
				pagetotal = total / qiYeInOutAreaPage.getSize() + 1;
			}
		}
		if (pagetotal >= qiYeInOutAreaPage.getCurrent()) {
			qiYeInOutAreaPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (qiYeInOutAreaPage.getCurrent() > 1) {
				offsetNo = qiYeInOutAreaPage.getSize() * (qiYeInOutAreaPage.getCurrent() - 1);
			}
			qiYeInOutAreaPage.setTotal(total);
			qiYeInOutAreaPage.setOffsetNo(offsetNo);
			List<StatementTongJi> statementTongJiList = qiYeTongJiMapper.selectGetMileageTJ(qiYeInOutAreaPage);
			qiYeInOutAreaPage.setRecords(statementTongJiList);
		}
		return qiYeInOutAreaPage;
	}

	@Override
	public QiYeInOutAreaPage<StatementTongJi> selectGetTrajectoryIntegrityTJ(QiYeInOutAreaPage qiYeInOutAreaPage) {
		Integer total = qiYeTongJiMapper.selectGetTrajectoryIntegrityTotal(qiYeInOutAreaPage);
		if(qiYeInOutAreaPage.getSize()==0){
			if(qiYeInOutAreaPage.getTotal()==0){
				qiYeInOutAreaPage.setTotal(total);
			}
			if(qiYeInOutAreaPage.getTotal()==0){
				return qiYeInOutAreaPage;
			}else{
				List<StatementTongJi> statementTongJiList = qiYeTongJiMapper.selectGetTrajectoryIntegrityTJ(qiYeInOutAreaPage);
				qiYeInOutAreaPage.setRecords(statementTongJiList);
				return qiYeInOutAreaPage;
			}
		}
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%qiYeInOutAreaPage.getSize()==0){
				pagetotal = total / qiYeInOutAreaPage.getSize();
			}else {
				pagetotal = total / qiYeInOutAreaPage.getSize() + 1;
			}
		}
		if (pagetotal >= qiYeInOutAreaPage.getCurrent()) {
			qiYeInOutAreaPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (qiYeInOutAreaPage.getCurrent() > 1) {
				offsetNo = qiYeInOutAreaPage.getSize() * (qiYeInOutAreaPage.getCurrent() - 1);
			}
			qiYeInOutAreaPage.setTotal(total);
			qiYeInOutAreaPage.setOffsetNo(offsetNo);
			List<StatementTongJi> statementTongJiList = qiYeTongJiMapper.selectGetTrajectoryIntegrityTJ(qiYeInOutAreaPage);
			qiYeInOutAreaPage.setRecords(statementTongJiList);
		}
		return qiYeInOutAreaPage;
	}

	@Override
	public QiYeInOutAreaPage<TrajectoryAnomalies> selectGuiJiYiChangTJ(QiYeInOutAreaPage qiYeInOutAreaPage) {
		Integer total = qiYeTongJiMapper.selectGuiJiYiChangTotal(qiYeInOutAreaPage);
		if(qiYeInOutAreaPage.getSize()==0){
			if(qiYeInOutAreaPage.getTotal()==0){
				qiYeInOutAreaPage.setTotal(total);
			}
			if(qiYeInOutAreaPage.getTotal()==0){
				return qiYeInOutAreaPage;
			}else{
				List<TrajectoryAnomalies> trajectoryAnomalies = qiYeTongJiMapper.selectGuiJiYiChangTJ(qiYeInOutAreaPage);
				qiYeInOutAreaPage.setRecords(trajectoryAnomalies);
				return qiYeInOutAreaPage;
			}
		}
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%qiYeInOutAreaPage.getSize()==0){
				pagetotal = total / qiYeInOutAreaPage.getSize();
			}else {
				pagetotal = total / qiYeInOutAreaPage.getSize() + 1;
			}
		}
		if (pagetotal >= qiYeInOutAreaPage.getCurrent()) {
			qiYeInOutAreaPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (qiYeInOutAreaPage.getCurrent() > 1) {
				offsetNo = qiYeInOutAreaPage.getSize() * (qiYeInOutAreaPage.getCurrent() - 1);
			}
			qiYeInOutAreaPage.setTotal(total);
			qiYeInOutAreaPage.setOffsetNo(offsetNo);
			List<TrajectoryAnomalies> trajectoryAnomalies = qiYeTongJiMapper.selectGuiJiYiChangTJ(qiYeInOutAreaPage);
			qiYeInOutAreaPage.setRecords(trajectoryAnomalies);
		}
		return qiYeInOutAreaPage;
	}

	@Override
	public QiYeInOutAreaPage<StopVehicle> selectTingCheMingXiTJ(QiYeInOutAreaPage qiYeInOutAreaPage) {
		Integer total = qiYeTongJiMapper.selectTingCheMingXiTotal(qiYeInOutAreaPage);
		if(qiYeInOutAreaPage.getSize()==0){
			if(qiYeInOutAreaPage.getTotal()==0){
				qiYeInOutAreaPage.setTotal(total);
			}
			if(qiYeInOutAreaPage.getTotal()==0){
				return qiYeInOutAreaPage;
			}else{
				List<StopVehicle> stopVehicleList = qiYeTongJiMapper.selectTingCheMingXiTJ(qiYeInOutAreaPage);
				qiYeInOutAreaPage.setRecords(stopVehicleList);
				return qiYeInOutAreaPage;
			}
		}
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%qiYeInOutAreaPage.getSize()==0){
				pagetotal = total / qiYeInOutAreaPage.getSize();
			}else {
				pagetotal = total / qiYeInOutAreaPage.getSize() + 1;
			}
		}
		if (pagetotal >= qiYeInOutAreaPage.getCurrent()) {
			qiYeInOutAreaPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (qiYeInOutAreaPage.getCurrent() > 1) {
				offsetNo = qiYeInOutAreaPage.getSize() * (qiYeInOutAreaPage.getCurrent() - 1);
			}
			qiYeInOutAreaPage.setTotal(total);
			qiYeInOutAreaPage.setOffsetNo(offsetNo);
			List<StopVehicle> stopVehicleList = qiYeTongJiMapper.selectTingCheMingXiTJ(qiYeInOutAreaPage);
			qiYeInOutAreaPage.setRecords(stopVehicleList);
		}
		return qiYeInOutAreaPage;
	}

	@Override
	public QiYeInOutAreaPage<StopVehicle> selectTingCheHuiZongTJ(QiYeInOutAreaPage qiYeInOutAreaPage) {
		Integer total = qiYeTongJiMapper.selectTingCheHuiZongTotal(qiYeInOutAreaPage);
		if(qiYeInOutAreaPage.getSize()==0){
			if(qiYeInOutAreaPage.getTotal()==0){
				qiYeInOutAreaPage.setTotal(total);
			}
			if(qiYeInOutAreaPage.getTotal()==0){
				return qiYeInOutAreaPage;
			}else{
				List<StopVehicle> stopVehicleList = qiYeTongJiMapper.selectTingCheHuiZongTJ(qiYeInOutAreaPage);
				qiYeInOutAreaPage.setRecords(stopVehicleList);
				return qiYeInOutAreaPage;
			}
		}
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%qiYeInOutAreaPage.getSize()==0){
				pagetotal = total / qiYeInOutAreaPage.getSize();
			}else {
				pagetotal = total / qiYeInOutAreaPage.getSize() + 1;
			}
		}
		if (pagetotal >= qiYeInOutAreaPage.getCurrent()) {
			qiYeInOutAreaPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (qiYeInOutAreaPage.getCurrent() > 1) {
				offsetNo = qiYeInOutAreaPage.getSize() * (qiYeInOutAreaPage.getCurrent() - 1);
			}
			qiYeInOutAreaPage.setTotal(total);
			qiYeInOutAreaPage.setOffsetNo(offsetNo);
			List<StopVehicle> stopVehicleList = qiYeTongJiMapper.selectTingCheHuiZongTJ(qiYeInOutAreaPage);
			qiYeInOutAreaPage.setRecords(stopVehicleList);
		}
		return qiYeInOutAreaPage;
	}

	@Override
	public QiYeTpvehdataPage<QiYeTpvehdataTongJi> selecttpvehdataTJ(QiYeTpvehdataPage qiYeTpvehdataPage) {
		Integer total = qiYeTongJiMapper.selectGettpvehdataTJTotal(qiYeTpvehdataPage);
		if(qiYeTpvehdataPage.getSize()==0){
			if(qiYeTpvehdataPage.getTotal()==0){
				qiYeTpvehdataPage.setTotal(total);
			}

			if(qiYeTpvehdataPage.getTotal()==0){
				return qiYeTpvehdataPage;
			}else{
				List<QiYeTpvehdataTongJi> zhengFuBaoJingTongJiList = qiYeTongJiMapper.selecttpvehdataTJ(qiYeTpvehdataPage);
				zhengFuBaoJingTongJiList.forEach(item->{
					if("1".equals(item.getAlarm())){
						item.setAlarm("报警");
					}else{
						item.setAlarm("未报警");
					}

					if("1".equals(item.getLocate())){
						item.setLocate("定位");
					}else{
						item.setLocate("不定位");
					}
					double lat = Double.parseDouble(item.getLatitude().toString());
					double lon = Double.parseDouble(item.getLongitude().toString());
					double[] zuobiao = GpsToBaiduUtil.wgs2bd(lat,lon);
					item.setLatitude(new BigDecimal(zuobiao[0]).setScale(6,BigDecimal.ROUND_HALF_UP));
					item.setLongitude(new BigDecimal(zuobiao[1]).setScale(6,BigDecimal.ROUND_HALF_UP));
					String LocalName= LatLotForLocation.getProvince(item.getLatitude().toString(),item.getLongitude().toString());
					item.setLocalName(LocalName);
				});
				qiYeTpvehdataPage.setRecords(zhengFuBaoJingTongJiList);
				return qiYeTpvehdataPage;
			}
		}
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%qiYeTpvehdataPage.getSize()==0){
				pagetotal = total / qiYeTpvehdataPage.getSize();
			}else {
				pagetotal = total / qiYeTpvehdataPage.getSize() + 1;
			}
		}
		if (pagetotal >= qiYeTpvehdataPage.getCurrent()) {
			qiYeTpvehdataPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (qiYeTpvehdataPage.getCurrent() > 1) {
				offsetNo = qiYeTpvehdataPage.getSize() * (qiYeTpvehdataPage.getCurrent() - 1);
			}
			qiYeTpvehdataPage.setTotal(total);
			qiYeTpvehdataPage.setOffsetNo(offsetNo);
			List<QiYeTpvehdataTongJi> zhengFuBaoJingTongJiList = qiYeTongJiMapper.selecttpvehdataTJ(qiYeTpvehdataPage);
			zhengFuBaoJingTongJiList.forEach(item->{
				if("1".equals(item.getAlarm())){
					item.setAlarm("报警");
				}else{
					item.setAlarm("未报警");
				}

				if("1".equals(item.getLocate())){
					item.setLocate("定位");
				}else{
					item.setLocate("不定位");
				}
				double lat = Double.parseDouble(item.getLatitude().toString());
				double lon = Double.parseDouble(item.getLongitude().toString());
				double[] zuobiao = GpsToBaiduUtil.wgs2bd(lat,lon);
				item.setLatitude(new BigDecimal(zuobiao[0]).setScale(6,BigDecimal.ROUND_HALF_UP));
				item.setLongitude(new BigDecimal(zuobiao[1]).setScale(6,BigDecimal.ROUND_HALF_UP));
				String LocalName= LatLotForLocation.getProvince(item.getLatitude().toString(),item.getLongitude().toString());
				item.setLocalName(LocalName);
			});
			qiYeTpvehdataPage.setRecords(zhengFuBaoJingTongJiList);
		}
		return qiYeTpvehdataPage;
	}

}
