/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.platform.qiyeshouye.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.platform.qiyeshouye.entity.UpdateTableMap;
import org.springblade.platform.qiyeshouye.service.IUpdateTableMapService;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器
 * @author hyp
 */
@RestController
@AllArgsConstructor
@RequestMapping("/platform/updateTableMap")
@Api(value = "运维端-配置平台基础资料显示权限", tags = "运维端-配置平台基础资料显示权限")
public class UpdateTableMapController {

	private IUpdateTableMapService updateTableMapService;

	/**
	 * 新增
	 */
	@PostMapping("/submit")
	@ApiOperation(value = "新增", notes = "传入tableName", position = 1)
	public R submit(@RequestBody UpdateTableMap updateTableMap) {
		R rs = new R();
		boolean i = updateTableMapService.insertAnBiaoDepartmentPostMap(updateTableMap);
		if(i == true){
			rs.setCode(200);
			rs.setSuccess(true);
			rs.setMsg("新增成功");
		}else{
			rs.setCode(500);
			rs.setSuccess(false);
			rs.setMsg("新增失败");
		}
		return rs;
	}


}
