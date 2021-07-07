package org.springblade.platform.guanlijigouherenyuan.feign;

import org.springblade.platform.guanlijigouherenyuan.entity.Personnel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/** upload Feign接口类
* @author 呵呵哒
*/
@FeignClient(
	//定义Feign指向的service-id
	value = "blade-platform",
	fallback = IPersonnelClientBack.class
)
public interface IPersonnelClient {

	String API_PREFIX = "/platform/perback";

	@PostMapping(API_PREFIX + "/saveOrUpdate")
	Boolean saveOrUpdate(@Valid @RequestBody  Personnel personnel);

	@PostMapping(API_PREFIX + "/updateDelByUserId")
	Boolean updateDelByUserId(@RequestParam("userId") String userId);

	@GetMapping(API_PREFIX + "/selectByUserIdAdnByDeptId")
	Personnel selectByUserIdAdnByDeptId(@RequestParam("userId") String userId, @RequestParam("deptId") String deptId);

	@PostMapping(API_PREFIX + "/insertPersonnelSelective")
	Boolean insertPersonnelSelective(@Valid @RequestBody Personnel personnel);


}
