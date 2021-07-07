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
package org.springblade.platform.jiaoyupeixun.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springblade.platform.jiaoyupeixun.entity.JiaoyupeixunRenyuan;
import org.springblade.platform.jiaoyupeixun.page.JiaoyupeixunRenyuanPage;
import org.springblade.platform.jiaoyupeixun.service.IJiaoyupeixunRenyuanService;
import org.springblade.platform.jiaoyupeixun.vo.JiaoyupeixunRenyuanVO;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Map;

/**
 *  控制器
 */
@RestController
@AllArgsConstructor
@RequestMapping("/platform/jiaoyupeixunrenyuan")
@Api(value = "教育培训人员从表", tags = "教育培训人员从表")
public class JiaoyupeixunRenyuanController extends BladeController {

	private IJiaoyupeixunRenyuanService jiaoyupeixunRenyuanService;

	/**
	* 详情
	*/
	@GetMapping("/detail")
	@ApiLog("详情-教育培训人员从表")
	@ApiOperation(value = "详情-教育培训人员从表", notes = "传入id", position = 1)
	public R detail(String id) {
		return R.data(jiaoyupeixunRenyuanService.getById(id));
	}

	/**
	* 自定义分页
	*/
	@PostMapping("/list")
	@ApiLog("分页-教育培训人员从表")
	@ApiOperation(value = "分页-教育培训人员从表", notes = "传入Anquanshengchan与query", position = 2)
	public R<JiaoyupeixunRenyuanPage<JiaoyupeixunRenyuanVO>> list(@RequestBody JiaoyupeixunRenyuanPage jiaoyupeixunRenyuanPage) {
		JiaoyupeixunRenyuanPage<JiaoyupeixunRenyuanVO> pages = jiaoyupeixunRenyuanService.selectPageList(jiaoyupeixunRenyuanPage);
		return R.data(pages);
	}

	/**
	* 新增
	*/
	@PostMapping("/insert")
	@ApiLog("新增-教育培训人员从表")
	@ApiOperation(value = "新增-教育培训人员从表", notes = "传入jiaoyupeixunRenyuan", position = 3)
	public R insert(@RequestBody JiaoyupeixunRenyuan jiaoyupeixunRenyuan,BladeUser user) {
		jiaoyupeixunRenyuan.setCaozuoren(user.getUserName());
		jiaoyupeixunRenyuan.setCaozuorenid(user.getUserId());
		jiaoyupeixunRenyuan.setCaozuoshijian(DateUtil.now());
		jiaoyupeixunRenyuan.setCreatetime(DateUtil.now());
		return R.status(jiaoyupeixunRenyuanService.save(jiaoyupeixunRenyuan));
	}

	/**
	* 修改
	*/
	@PostMapping("/update")
	@ApiLog("修改-教育培训人员从表")
	@ApiOperation(value = "修改-教育培训人员从表", notes = "传入jiaoyupeixunRenyuan", position = 4)
	public R update(@RequestBody JiaoyupeixunRenyuan jiaoyupeixunRenyuan,BladeUser user) {
		jiaoyupeixunRenyuan.setCaozuoren(user.getUserName());
		jiaoyupeixunRenyuan.setCaozuorenid(user.getUserId());
		jiaoyupeixunRenyuan.setCaozuoshijian(DateUtil.now());
		if("".equals(jiaoyupeixunRenyuan.getCreatetime())){
			jiaoyupeixunRenyuan.setCreatetime(DateUtil.now());
		}
		return R.status(jiaoyupeixunRenyuanService.updateById(jiaoyupeixunRenyuan));
	}

	/**
	* 删除
	*/
	@PostMapping("/del")
	@ApiLog("删除-教育培训人员从表")
	@ApiOperation(value = "删除-教育培训人员从表", notes = "传入id", position = 5)
	public R del(@ApiParam(value = "id", required = true) @RequestParam String id) {
		return R.status(jiaoyupeixunRenyuanService.updateDel(id));
	}


}
