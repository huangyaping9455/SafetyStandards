package org.springblade.platform.cheliangguanli.feign;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.platform.cheliangguanli.entity.Vehicle;
import org.springblade.platform.cheliangguanli.service.IVehicleService;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Lh
 * @description: TODO
 * @projectName SafetyStandards
 * @date 2019/11/1311:33
 */
@ApiIgnore
@RestController
@AllArgsConstructor
public class VehiclepostClient implements  IVehiclepostClientBack {

	private IVehicleService vehicleService;
	@Override
	@ApiOperation(value = "根据车牌 车牌颜色 deptId获取车辆信息信息(feign使用)", notes = "传入deptId cheliangpaizhao chepaiyanse", position = 3)
	public Vehicle vehileOne(String cheliangpaizhao, String chepaiyanse, Integer deptId) {
		return vehicleService.vehileOne(cheliangpaizhao,chepaiyanse,deptId);
	}

	@Override
	public Vehicle getvehileYYS(String id) {
		return vehicleService.selectByYYS(id);
	}
}
