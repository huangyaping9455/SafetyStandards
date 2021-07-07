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
package org.springblade.platform.anquanmubiao.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.platform.anquanmubiao.entity.Mubiaokaohe;
import org.springblade.platform.anquanmubiao.page.MubiaokaohePage;
import org.springblade.platform.anquanmubiao.service.IMubiaokaoheService;
import org.springblade.platform.anquanmubiao.vo.MubiaokaoheVO;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

/**
 *  控制器
 * @author 呵呵哒
 */
@RestController
@AllArgsConstructor
@RequestMapping("/platform/mubiaokaohe")
@Api(value = "目标考核", tags = "目标考核")
public class MubiaokaoheController extends BladeController {

	private IMubiaokaoheService mubiaokaoheService;

	/**
	* 详情
	*/
	@GetMapping("/detail")
	@ApiLog("详情-目标考核")
	@ApiOperation(value = "详情-目标考核", notes = "传入id", position = 1)
	public R detail(String id) {
		return R.data(mubiaokaoheService.selectByIds(id));
	}

	/**
	 * 分页
	 */
	@PostMapping("/list")
	@ApiLog("分页-目标考核")
	@ApiOperation(value = "分页-目标考核", notes = "传入MubiaokaohePage", position = 2)
	public R<MubiaokaohePage<MubiaokaoheVO>> list(@RequestBody MubiaokaohePage mubiaokaohePage) {
		MubiaokaohePage<MubiaokaoheVO> pages = mubiaokaoheService.selectPageList(mubiaokaohePage);
		return R.data(pages);
	}

	/**
	* 新增
	*/
	@PostMapping("/insert")
	@ApiLog("新增-目标考核")
	@ApiOperation(value = "新增-目标考核", notes = "传入mubiaokaohe", position = 3)
	public R insert(@RequestBody Mubiaokaohe mubiaokaohe,BladeUser user) {
		mubiaokaohe.setCaozuoren(user.getUserName());
		mubiaokaohe.setCaozuorenid(user.getUserId());
		mubiaokaohe.setCaozuoshijian(DateUtil.now());
		mubiaokaohe.setCreatetime(DateUtil.now());
		return R.status(mubiaokaoheService.save(mubiaokaohe));
	}

	/**
	* 修改
	*/
	@PostMapping("/update")
	@ApiLog("修改-目标考核")
	@ApiOperation(value = "修改-目标考核", notes = "传入mubiaokaohe", position = 4)
	public R update(@RequestBody Mubiaokaohe mubiaokaohe,BladeUser user) {
		mubiaokaohe.setCaozuoren(user.getUserName());
		mubiaokaohe.setCaozuorenid(user.getUserId());
		mubiaokaohe.setCaozuoshijian(DateUtil.now());
		if("".equals(mubiaokaohe.getCreatetime())){
			mubiaokaohe.setCreatetime(DateUtil.now());
		}
		return R.status(mubiaokaoheService.updateById(mubiaokaohe));
	}

	/**
	* 删除
	*/
	@PostMapping("/del")
	@ApiLog("删除-目标考核")
	@ApiOperation(value = "删除-目标考核", notes = "传入id", position = 5)
	public R del(@RequestParam String id) {
		return R.status(mubiaokaoheService.updateDel(id));
	}


}
