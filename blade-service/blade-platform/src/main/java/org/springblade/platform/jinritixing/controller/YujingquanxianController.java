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
package org.springblade.platform.jinritixing.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.platform.jinritixing.entity.Yujingquanxian;
import org.springblade.platform.jinritixing.page.YujingquanxianPage;
import org.springblade.platform.jinritixing.service.IYujingquanxianService;
import org.springblade.platform.jinritixing.vo.YujingquanxianVO;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.Dept;
import org.springblade.system.feign.ISysClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *  控制器
 */
@RestController
@AllArgsConstructor
@RequestMapping("platform/yujingquanxian")
@Api(value = "预警权限", tags = "预警权限接口")
public class YujingquanxianController extends BladeController {

	private IYujingquanxianService yujingquanxianService;

	private ISysClient iSysClient;

	/**
	* 查询所有预警
	*/
	@GetMapping("/allyujing")
	@ApiLog("查询所有-预警权限")
	@ApiOperation(value = "查询所有-预警权限", notes = "查询所有预警", position = 1)
	public R<List<YujingquanxianVO>> allyujing() {
		List<YujingquanxianVO> lists = yujingquanxianService.selectAllYuJing();
		return R.data(lists);
	}

	/**
	 * 获取当前岗位所拥有权限
	 */
	@GetMapping("/yujingTreeKeys")
	@ApiLog("获取当前岗位-预警权限")
	@ApiOperation(value = "获取当前岗位-预警权限", notes = "传入岗位id", position = 2)
	@ApiImplicitParams({@ApiImplicitParam(name = "postId", value = "岗位id", required = true)})
	public R<List<String>> yujingTreeKeys(String postId) {
		YujingquanxianPage page=new YujingquanxianPage();
		page.setPostId(postId);
		List<YujingquanxianVO> list=yujingquanxianService.selectYuJingList(page);
		List<String> lists=new ArrayList<>();
		for (int i = 0; i <list.size() ; i++) {
			lists.add(list.get(i).getYujingxiangid());
		}
		return R.data(lists);
	}

	/**
	* 查询单位预警
	*/
	@PostMapping("/yujinglist")
	@ApiLog("查询单位-预警权限")
	@ApiOperation(value = "查询单位-预警权限", notes = "传入岗位id", position = 3)
	public R<List<YujingquanxianVO>> yujinglist(String postId) {
		YujingquanxianPage page=new YujingquanxianPage();
		page.setPostId(postId);
		return R.data(yujingquanxianService.selectYuJingList(page));
	}
	/**
	* 保存
	*/
	@GetMapping("/submit")
	@ApiLog("保存-预警权限")
	@ApiOperation(value = "保存-预警权限", notes = "传入menuIds,postIds,user", position = 4)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "postIds", value = "岗位id", required = true),
		@ApiImplicitParam(name = "menuIds", value = "选中id值", required = true)
	})
	public R submit(String menuIds,String postIds,BladeUser user) {
		YujingquanxianPage page = new YujingquanxianPage();
		//直属上级id
		Dept dept=iSysClient.selectByJGBM("机构",postIds);
		page.setDeptId(dept.getId());
		page.setPostId(postIds);
		String[] idss = menuIds.split(",");
		boolean falg;
		if(menuIds.length()>0){
			List<Yujingquanxian> list = new ArrayList<>();
			yujingquanxianService.delYuJing(page);
			for (int i = 0;i<idss.length;i++){
				Yujingquanxian yujingquanxian = new Yujingquanxian();
				yujingquanxian.setYujingxiangid(idss[i]);
				yujingquanxian.setDeptId(dept.getId());
				yujingquanxian.setCaozuoren(user.getUserName());
				yujingquanxian.setCaozuorenid(user.getUserId());
				yujingquanxian.setCaozuoshijian(DateUtil.now());
				yujingquanxian.setPostid(postIds);
				list.add(yujingquanxian);
				//yujingquanxianService.save(yujingquanxian);
			}
			falg=yujingquanxianService.saveBatch(list);
		}else{
			falg=false;
		}
		return R.status(falg);
	}

	/**
	 * 预警结算
	 */
	@PostMapping("/yujingjiesuan")
	@ApiLog("预警结算-预警权限")
	@ApiOperation(value = "预警结算-预警权限", notes = "传入YujingquanxianPage", position = 5)
	public R yujingjiesuan(@RequestBody YujingquanxianPage page) {
		List<YujingquanxianVO> list = yujingquanxianService.selectYuJingList(page);
		if(list.size()>0){
			for (int i = 0;i<list.size();i++){
				YujingquanxianPage pages = new YujingquanxianPage();
				pages.setDeptId(page.getDeptId());
				pages.setYujingxiangid(list.get(i).getYujingxiangid());
				yujingquanxianService.yujingjiesuan(pages);
			}
		}
		return R.status(true);
	}
}
