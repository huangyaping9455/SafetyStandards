package org.springblade.baojing.feign;

import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
	//定义Feign指向的service-id
	value = "blade-baojing",
	fallback = IVehicleStopClient.class
)
public interface IVehicleStopClientBack {
		String API_PREFIX = "/baojing/dataCenter";

		@GetMapping(API_PREFIX+"getdatastopveh")
	    R  dataFormStopVehicle();

}
