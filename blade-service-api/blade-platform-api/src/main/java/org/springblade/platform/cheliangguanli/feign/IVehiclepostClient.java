package org.springblade.platform.cheliangguanli.feign;

import org.springblade.platform.cheliangguanli.entity.Vehicle;
import org.springframework.stereotype.Component;

/**
 * @author Lh
 * @description: TODO
 * @projectName SafetyStandards
 * @date 2019/11/1311:23
 */
@Component
public class IVehiclepostClient implements IVehiclepostClientBack {
	@Override
	public Vehicle vehileOne(String cheliangpaizhao, String chepaiyanse, Integer deptId) {
		return null;
	}

	@Override
	public Vehicle getvehileYYS(String id) {
		return null;
	}
}
