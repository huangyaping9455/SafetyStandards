/**
 * Copyright (C), 2015-2020,
 * FileName: XinXiJiaoHuZhuServiceImpl
 * Author:   呵呵哒
 * Date:     2020/6/20 17:18
 * Description:
 */
package org.springblade.platform.zhengfu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.platform.zhengfu.entity.ZhengFuShouYeNew;
import org.springblade.platform.zhengfu.mapper.ZhengFuShouYeNewMapper;
import org.springblade.platform.zhengfu.service.IZhengFuShouYeNewService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 呵呵哒
 * @创建人 hyp
 * @创建时间 2020/7/4
 * @描述
 */
@Service
@AllArgsConstructor
public class ZhengFuShouYeNewServiceImpl extends ServiceImpl<ZhengFuShouYeNewMapper, ZhengFuShouYeNew> implements IZhengFuShouYeNewService {

	private ZhengFuShouYeNewMapper zhengFuShouYeNewMapper;

	@Override
	public List<ZhengFuShouYeNew>  selectGetOne(String deptId, String xiaJiDeptId, String date) {
		return zhengFuShouYeNewMapper.selectGetOne(deptId, xiaJiDeptId, date);
	}

	@Override
	public ZhengFuShouYeNew selectGetTwo(String deptId, String xiaJiDeptId, Integer year, int month, String areaName) {
		return zhengFuShouYeNewMapper.selectGetTwo(deptId, xiaJiDeptId, year, month, areaName);
	}

	@Override
	public List<ZhengFuShouYeNew> selectGetTwoXJ(String deptId, String xiaJiDeptId, Integer year, int month, String areaName) {
		return zhengFuShouYeNewMapper.selectGetTwoXJ(deptId, xiaJiDeptId, year, month, areaName);
	}

	@Override
	public List<ZhengFuShouYeNew> selectGetThree(String deptId, String xiaJiDeptId, int year, String firstDate, String endDate, String areaName) {
		return zhengFuShouYeNewMapper.selectGetThree(deptId, xiaJiDeptId, year, firstDate, endDate, areaName);
	}

	@Override
	public List<ZhengFuShouYeNew> selectGetFour(String deptId, String xiaJiDeptId, int year, int month, String date) {
		return zhengFuShouYeNewMapper.selectGetFour(deptId, xiaJiDeptId, year, month, date);
	}

}
