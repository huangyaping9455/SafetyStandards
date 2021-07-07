package org.springblade.platform.muban.feign;

import org.springblade.core.tool.api.R;
import org.springframework.stereotype.Component;

/**
 * @program: SpringBlade
 * @author: 呵呵哒
 **/
@Component
public class MubanClientFallback implements IMubanClient {


	@Override
	public R initConf(Integer deptId,String [] tables) {
		return R.fail("同步失败");
	}
}
