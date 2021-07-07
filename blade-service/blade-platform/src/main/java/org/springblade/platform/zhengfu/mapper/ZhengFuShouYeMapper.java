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
package org.springblade.platform.zhengfu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.platform.zhengfu.entity.ZhengFuShouYe;

import java.util.List;

/**
 * @author 呵呵哒
 */
public interface ZhengFuShouYeMapper extends BaseMapper<ZhengFuShouYe> {

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
