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
package org.springblade.platform.cheliangguanli.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.platform.cheliangguanli.entity.Xincheyanshoumingxi;
import org.springblade.platform.cheliangguanli.page.XincheyanshoumingxiPage;
import org.springblade.platform.cheliangguanli.service.IXincheyanshoumingxiService;
import org.springblade.platform.cheliangguanli.vo.XincheyanshoumingxiVO;
import org.springblade.platform.configure.entity.Configure;
import org.springblade.platform.configure.service.IConfigureService;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  控制器
 */
@RestController
@AllArgsConstructor
@RequestMapping("/platform/xincheyanshoumingxi")
@Api(value = "新车验收明细", tags = "新车验收明细")
public class XincheyanshoumingxiController extends BladeController {

	private IXincheyanshoumingxiService xincheyanshoumingxiService;
	private IConfigureService mapService;
	/**
	* 详情
	*/
	@GetMapping("/detail")
	@ApiLog("详情-新车验收明细")
	@ApiOperation(value = "详情-新车验收明细", notes = "传入id", position = 1)
	public R detail(String id) {
		return R.data(xincheyanshoumingxiService.selectByIds(id));
	}

	/**
	* 自定义分页
	*/
	@PostMapping("/list")
	@ApiLog("分页-新车验收明细")
	@ApiOperation(value = "分页-新车验收明细", notes = "传入XincheyanshoumingxiPage", position = 2)
	public R<XincheyanshoumingxiPage<XincheyanshoumingxiVO>> list(@RequestBody XincheyanshoumingxiPage Page) {
		XincheyanshoumingxiPage<XincheyanshoumingxiVO> pages = xincheyanshoumingxiService.selectPageList(Page);
		return R.data(pages);
	}

	/**
	* 新增
	*/
	@PostMapping("/insert")
	@ApiLog("新增-新车验收明细")
	@ApiOperation(value = "新增-新车验收明细", notes = "传入xincheyanshoumingxi", position = 3)
	public R insert(@RequestBody Xincheyanshoumingxi xincheyanshoumingxi,BladeUser user) {
		xincheyanshoumingxi.setCaozuoren(user.getUserName());
		xincheyanshoumingxi.setCaozuorenid(user.getUserId());
		xincheyanshoumingxi.setCaozuoshijian(DateUtil.now());
		xincheyanshoumingxi.setCreatetime(DateUtil.now());
		return R.status(xincheyanshoumingxiService.save(xincheyanshoumingxi));
	}

	/**
	* 修改
	*/
	@PostMapping("/update")
	@ApiLog("修改-新车验收明细")
	@ApiOperation(value = "修改-新车验收明细", notes = "传入xincheyanshoumingxi", position = 4)
	public R update(@RequestBody Xincheyanshoumingxi xincheyanshoumingxi,BladeUser user) {
		xincheyanshoumingxi.setCaozuoren(user.getUserName());
		xincheyanshoumingxi.setCaozuorenid(user.getUserId());
		xincheyanshoumingxi.setCaozuoshijian(DateUtil.now());
		if("".equals(xincheyanshoumingxi.getCreatetime())){
			xincheyanshoumingxi.setCreatetime(DateUtil.now());
		}
		return R.status(xincheyanshoumingxiService.updateById(xincheyanshoumingxi));
	}

	/**
	* 删除
	*/
	@PostMapping("/del")
	@ApiLog("删除-新车验收明细")
	@ApiOperation(value = "删除-新车验收明细", notes = "传入id", position = 5)
	public R del(@ApiParam(value = "id", required = true) @RequestParam String id) {
		return R.status(xincheyanshoumingxiService.updateDel(id));
	}

	/**
	 * 查询检查项目
	 */
	@PostMapping("/jianchaxiang")
	@ApiLog("新车验收明细-删除-查询检查项目")
	@ApiOperation(value = "删除-查询检查项目", notes = "传入id", position = 5)
	public R jianchaxiang() {
		return R.data(xincheyanshoumingxiService.selectJianChaXiang());
	}

	/********************************** 配置表 ***********************/

	/**
	 * 根据单位id获取配置模块数据
	 */
	@GetMapping("/listMap")
	@ApiLog("获取配置-查询检查项目")
	@ApiOperation(value = "获取配置-查询检查项目", notes = "传入deptId", position = 6)
	public R<JSONArray> listMap(Integer deptId) {
		List<Configure> list1=mapService.selectMapList("anbiao_xincheyanshoumingxi_map",deptId);
		String str="";
		for (int i = 0; i <list1.size() ; i++) {
			//转换成json数据并put id
			JSONObject jsonObject = JSONUtil.parseObj(list1.get(i).getBiaodancanshu());
			jsonObject.put("id",list1.get(i).getId());
			if(!str.equals("")){
				str=str+","+jsonObject.toString();
			}else{
				str=jsonObject.toString();
			}
		}
		str="["+str+"]";
		JSONArray json= JSONUtil.parseArray(str);
		return R.data(json);
	}

	/**
	 * 配置表新增
	 */
	@PostMapping("/insertMap")
	@ApiLog("配置表新增-查询检查项目")
	@ApiOperation(value = "配置表新增-查询检查项目", notes = "传入biaodancanshu与deptId", position = 7)
	public R insertMap(String biaodancanshu,String deptId) {
		Configure configure=new Configure();
		JSONObject jsonObject = JSONUtil.parseObj(biaodancanshu);
		configure.setLabel(jsonObject.getStr("label"));
		configure.setShujubiaoziduan(jsonObject.getStr("prop"));
		configure.setDeptId(Integer.parseInt(deptId));
		configure.setTableName("anbiao_xincheyanshoumingxi_map");
		configure.setBiaodancanshu(biaodancanshu);
		return R.status(mapService.insertMap(configure));
	}
	/**
	 * 配置表编辑
	 */
	@PostMapping("/updateMap")
	@ApiLog("配置表编辑-查询检查项目")
	@ApiOperation(value = "配置表编辑-查询检查项目", notes = "传入biaodancanshu与id", position = 9)
	public R updateMap(String biaodancanshu,String id) {
		Configure configure=new Configure();
		JSONObject jsonObject = JSONUtil.parseObj(biaodancanshu);
		configure.setId(id);
		configure.setLabel(jsonObject.getStr("label"));
		configure.setShujubiaoziduan(jsonObject.getStr("prop"));
		configure.setTableName("anbiao_xincheyanshoumingxi_map");
		configure.setBiaodancanshu(biaodancanshu);
		return R.status(mapService.updateMap(configure));
	}

	/**
	 * 配置表删除
	 */
	@PostMapping("/delMap")
	@ApiLog("配置表删除-查询检查项目")
	@ApiOperation(value = "配置表删除-查询检查项目", notes = "传入id", position = 8)
	public R delMap(@ApiParam(value = "主键id", required = true) @RequestParam String id) {
		return R.status(mapService.delMap("anbiao_xincheyanshoumingxi_map",id));
	}
}
