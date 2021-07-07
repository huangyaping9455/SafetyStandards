package org.springblade.platform.guanlijigouherenyuan.feign;

import org.springblade.platform.guanlijigouherenyuan.entity.Organizations;
import org.springblade.platform.zhengfu.entity.Organization;
import org.springframework.stereotype.Component;

/**
 * @program: SafetyStandards
 * @description:
 **/
@Component
public class IOrganizationsClientBack implements IOrganizationsClient {
	@Override
	public Organizations selectByDeptId(String deptId) {
		return null;
	}

	@Override
	public Boolean delByDeptId(String deptId) {
		return null;
	}

	@Override
	public Organization selectZFRenyuan(String id) {
		return null;
	}
}
