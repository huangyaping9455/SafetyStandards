/**
 * Copyright (C), 2015-2020,
 * FileName: IXinXiJiaoHuZhuService
 * Description:
 */
package org.springblade.platform.zhengfu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springblade.platform.zhengfu.entity.ZhengFuShouYe;

import java.util.List;

/**
 * @author 呵呵哒
 * @描述
 */
public interface IZhengFuShouYeService extends IService<ZhengFuShouYe> {

	/**
	 * 获取报警数
	 *
	 * @return
	 */
	ZhengFuShouYe selectGetBaoJing(@Param("deptId") String deptId);

	List<ZhengFuShouYe> selectGetBaoJing_XiaJi(@Param("xiaJiDeptId") String xiaJiDeptId);
	/**
	 * 获取监控企业
	 */
	ZhengFuShouYe selectGetJianKongQiYe(@Param("deptId") String deptId);

	List<ZhengFuShouYe> selectGetJianKongQiYe_XiaJi(@Param("xiaJiDeptId") String xiaJiDeptId);
	/**
	 * 获取本月注册车辆、监控车辆、停运车辆、上线车辆数
	 */
	List<ZhengFuShouYe> selectGetVehicleStatus(@Param("deptId") String deptId,@Param("xiaJiDeptId") String xiaJiDeptId);

	List<ZhengFuShouYe> selectGetVehicleStatus_XiaJi(@Param("xiaJiDeptId") String xiaJiDeptId);
	/**
	 * 获取最近7天GPS报警数、主动设备报警数；
	 * 获取最近7天GPS未处理报警数、主动设备未处理报警数；
	 * 获取最近7天处理率；
	 */
	List<ZhengFuShouYe> selectGetSevenBaoJing(@Param("deptId") String deptId,@Param("shangyue") String shangyue,@Param("benyue") String benyue);

	List<ZhengFuShouYe> selectGetSevenBaoJing_XiaJi(@Param("xiaJiDeptId") String xiaJiDeptId,@Param("shangyue") String shangyue,@Param("benyue") String benyue);

}
