package org.springblade.platform.guanlijigouherenyuan.feign;

import org.springblade.platform.guanlijigouherenyuan.entity.Personnel;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

/**
 * @program: SafetyStandards
 * @description: IPersonnelClientlBack
 **/
@Component
public class IPersonnelClientBack implements IPersonnelClient {
	@Override
	public Boolean saveOrUpdate(@Valid Personnel personnel) {
		return null;
	}

	@Override
	public Boolean updateDelByUserId(String userId) {
		return null;
	}

	@Override
	public Personnel selectByUserIdAdnByDeptId(String userId, String deptId) {
		return null;
	}

	@Override
	public Boolean insertPersonnelSelective(@Valid Personnel personnel) {
		return null;
	}
}
