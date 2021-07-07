package org.springblade.platform.guanlijigouherenyuan.feign;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.platform.guanlijigouherenyuan.entity.Organizations;
import org.springblade.platform.guanlijigouherenyuan.service.IOrganizationsService;
import org.springblade.platform.zhengfu.entity.Organization;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @program: SafetyStandards
 **/
@ApiIgnore
@RestController
@AllArgsConstructor
public class OrganizationsClient implements IOrganizationsClient {

	private IOrganizationsService orrganizationsService;

	@Override
	@ApiOperation(value = "根据单位id获取信息(feign使用)", notes = "传入deptId", position = 3)
	public Organizations selectByDeptId(String deptId) {
		return orrganizationsService.selectByDeptId(deptId);
	}

	@Override
	@ApiOperation(value = "根据单位id清除数据(feign使用)", notes = "传入deptId", position = 4)
	public Boolean delByDeptId(String deptId) {
		return orrganizationsService.delByDeptId(deptId);
	}

	@Override
	@ApiOperation(value = "根据人员id查询政府岗位(feign使用)", notes = "传入Id", position = 4)
	public Organization selectZFRenyuan(String id) {
		return orrganizationsService.selectZFRenyuan(id);
	}
}
