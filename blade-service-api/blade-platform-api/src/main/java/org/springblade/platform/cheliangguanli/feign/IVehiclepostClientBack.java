package org.springblade.platform.cheliangguanli.feign;


import org.springblade.platform.cheliangguanli.entity.Vehicle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Lh
 * @description: TODO
 * @projectName SafetyStandards
 * @date 2019/11/1311:22
 */
@FeignClient(
	//定义Feign指向的service-id
	value = "blade-platform",
	fallback = IVehiclepostClient.class
)
public interface IVehiclepostClientBack {
	String API_PREFIX = "/platform/vehicle";

	@PostMapping(API_PREFIX + "/vehileOne")
	Vehicle vehileOne(@RequestParam("cheliangpaizhao")String cheliangpaizhao, @RequestParam("chepaiyanse")String chepaiyanse, @RequestParam("deptId")Integer deptId);

	@PostMapping(API_PREFIX + "/getvehileYYS")
	Vehicle getvehileYYS(@RequestParam("id") String id);

}
