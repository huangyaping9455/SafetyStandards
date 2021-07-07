package org.springblade.platform.cheliangguanli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.platform.cheliangguanli.entity.VehiclesMoveInfo;
import org.springblade.platform.cheliangguanli.page.VehiclesMoveInfoPage;

/**
 * 车辆自定义 接口
 * */
public interface IVehiclesMoveInfoService extends IService<VehiclesMoveInfo> {

	/**
	 * 自定义分页
	 * @param vehiclesMoveInfoPage
	 * @return
	 */
	VehiclesMoveInfoPage<VehiclesMoveInfo> selectVehiclePage(VehiclesMoveInfoPage vehiclesMoveInfoPage);

	/**
	 * 车辆异动
	 * @return
	 */
	boolean insertSelective(VehiclesMoveInfo vehiclesMoveInfo);

}
