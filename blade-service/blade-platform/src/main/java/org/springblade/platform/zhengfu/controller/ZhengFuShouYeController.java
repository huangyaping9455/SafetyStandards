/**
 * Copyright (C), 2015-2020,
 * FileName: GpsVehicleController
 * Description:
 */
package org.springblade.platform.zhengfu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springblade.platform.zhengfu.entity.Organization;
import org.springblade.platform.zhengfu.entity.ZhengFuShouYe;
import org.springblade.platform.zhengfu.service.IOrganizationService;
import org.springblade.platform.zhengfu.service.IZhengFuShouYeService;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @创建人 呵呵哒
 * @描述
 */
@RestController
@AllArgsConstructor
@RequestMapping("/platform/zhengFuShouYe")
@Api(value = "政府-首页数据统计", tags = "政府-首页数据统计")
public class ZhengFuShouYeController {

	private IZhengFuShouYeService iZhengFuShouYeService;

	private IOrganizationService iOrganizationService;

	@GetMapping(value = "/getZCJKQiYeNum")
	@ApiLog("政府-注册、监控企业数据")
	@ApiOperation(value = "政府-注册、监控企业数据", notes = "传入deptId或者xiaJiDeptId",position = 1)
	public R getZCJKQiYeList(String deptId,String xiaJiDeptId) throws IOException {
		R rs = new R();
		if(StringUtils.isBlank(deptId) && StringUtils.isBlank(xiaJiDeptId)){
			rs.setCode(500);
			rs.setMsg("请传人参数deptId或者xiaJiDeptId");
			return rs;
		}

		if(!StringUtils.isBlank(deptId)){
			ZhengFuShouYe zhengFuShouYes = iZhengFuShouYeService.selectGetJianKongQiYe(deptId);
			if(zhengFuShouYes != null){
				rs.setCode(200);
				rs.setData(zhengFuShouYes);
				rs.setMsg("获取成功");
			}else{
				rs.setCode(500);
				rs.setMsg("获取失败");
				return rs;
			}
		}
		if(!StringUtils.isBlank(xiaJiDeptId)){
			List<ZhengFuShouYe> zhengFuShouYes = iZhengFuShouYeService.selectGetJianKongQiYe_XiaJi(xiaJiDeptId);
			if(zhengFuShouYes != null){
				rs.setCode(200);
				rs.setData(zhengFuShouYes);
				rs.setMsg("获取成功");
			}else{
				rs.setCode(500);
				rs.setMsg("获取失败");
				return rs;
			}
		}
		return rs;
	}

	@GetMapping(value = "/getBaoJingNum")
	@ApiLog("政府-主动安全报警数报、GPS报警数据")
	@ApiOperation(value = "政府-主动安全报警数报、GPS报警数据", notes = "传入deptId或者xiaJiDeptId",position = 2)
	public R getBaoJingNum(String deptId,String xiaJiDeptId) throws IOException {
		R rs = new R();
		if(StringUtils.isBlank(deptId) && StringUtils.isBlank(xiaJiDeptId)){
			rs.setCode(500);
			rs.setMsg("请传人参数deptId或者xiaJiDeptId");
			return rs;
		}
		if(!StringUtils.isBlank(deptId)) {
			ZhengFuShouYe zhengFuShouYes = iZhengFuShouYeService.selectGetBaoJing(deptId);
			if (zhengFuShouYes != null) {
				rs.setCode(200);
				rs.setData(zhengFuShouYes);
				rs.setMsg("获取成功");
			} else {
				rs.setCode(500);
				rs.setMsg("获取失败");
				return rs;
			}
		}
		if(!StringUtils.isBlank(xiaJiDeptId)){
			List<ZhengFuShouYe> zhengFuShouYes = iZhengFuShouYeService.selectGetBaoJing_XiaJi(xiaJiDeptId);
			if (zhengFuShouYes != null) {
				rs.setCode(200);
				rs.setData(zhengFuShouYes);
				rs.setMsg("获取成功");
			} else {
				rs.setCode(500);
				rs.setMsg("获取失败");
				return rs;
			}
		}
		return rs;
	}

	@GetMapping(value = "/getZCJKVehicleList")
	@ApiLog("政府-本月注册车辆、监控车辆、停运车辆、上线车辆数据")
	@ApiOperation(value = "政府-本月注册车辆、监控车辆、停运车辆、上线车辆数据", notes = "传入deptId或者xiaJiDeptId",position = 3)
	public R<ZhengFuShouYe> getZCJKVehicleList(String deptId) {
		R rs = new R();
		String xiaJiDeptId = null;
		Organization jb = iOrganizationService.selectGetZFJB(deptId);
		if(!StringUtils.isBlank(jb.getProvince()) && StringUtils.isBlank(jb.getCity())){
			List<ZhengFuShouYe> zhengFuShouYes = iZhengFuShouYeService.selectGetVehicleStatus(deptId,null);
			if (zhengFuShouYes != null) {
				rs.setCode(200);
				rs.setData(zhengFuShouYes);
				rs.setMsg("获取成功");
			} else {
				rs.setCode(500);
				rs.setMsg("获取失败");
			}
		}

		if(!StringUtils.isBlank(jb.getCity()) && StringUtils.isBlank(jb.getCountry())){
			List<ZhengFuShouYe> zhengFuShouYes = iZhengFuShouYeService.selectGetVehicleStatus(deptId,null);
			if (zhengFuShouYes != null) {
				rs.setCode(200);
				rs.setData(zhengFuShouYes);
				rs.setMsg("获取成功");
			} else {
				rs.setCode(500);
				rs.setMsg("获取失败");
			}
		}

		if(!StringUtils.isBlank(jb.getCountry())){
			xiaJiDeptId = deptId;
			List<ZhengFuShouYe> zhengFuShouYes = iZhengFuShouYeService.selectGetVehicleStatus(null,xiaJiDeptId);
			if (zhengFuShouYes != null) {
				rs.setCode(200);
				rs.setData(zhengFuShouYes);
				rs.setMsg("获取成功");
			} else {
				rs.setCode(500);
				rs.setMsg("获取失败");
			}
		}

//		if(!StringUtils.isBlank(xiaJiDeptId)){
//			List<ZhengFuShouYe> zhengFuShouYes = iZhengFuShouYeService.selectGetVehicleStatus_XiaJi(xiaJiDeptId);
//			if (zhengFuShouYes != null) {
//				rs.setCode(200);
//				rs.setData(zhengFuShouYes);
//				rs.setMsg("获取成功");
//			} else {
//				rs.setCode(500);
//				rs.setMsg("获取失败");
//				return rs;
//			}
//		}
		return rs;
	}

	@GetMapping(value = "/getSevenBaoJingList")
	@ApiLog("政府-获取最近7天GPS报警数、主动设备报警数；\n" +
		"\t * 获取最近7天GPS未处理报警数、主动设备未处理报警数；\n" +
		"\t * 获取最近7天处理率数据")
	@ApiOperation(value = "政府-获取最近7天GPS报警数、主动设备报警数；\n" +
		"\t * 获取最近7天GPS未处理报警数、主动设备未处理报警数；\n" +
		"\t * 获取最近7天处理率", notes = "传入deptId或者xiaJiDeptId",position = 4)
	public R<ZhengFuShouYe> getSevenBaoJingList(String deptId,String xiaJiDeptId) {
		R rs = new R();
		if(StringUtils.isBlank(deptId) && StringUtils.isBlank(xiaJiDeptId)){
			rs.setCode(500);
			rs.setMsg("请传人参数deptId或者xiaJiDeptId");
			return rs;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		// 设置为当前时间
		calendar.setTime(date);
		String benyue = new SimpleDateFormat("yyyy-MM").format(date);
		// 设置为上一个月
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);

		date = calendar.getTime();
		String shangyue = format.format(date);

		List<ZhengFuShouYe> zhengFuShouYes = iZhengFuShouYeService.selectGetSevenBaoJing(deptId,shangyue,benyue);
		if(zhengFuShouYes != null){
			rs.setCode(200);
			rs.setData(zhengFuShouYes);
			rs.setMsg("获取成功");
		}else{
			rs.setCode(500);
			rs.setMsg("获取失败");
			return rs;
		}
		return rs;
	}


}

