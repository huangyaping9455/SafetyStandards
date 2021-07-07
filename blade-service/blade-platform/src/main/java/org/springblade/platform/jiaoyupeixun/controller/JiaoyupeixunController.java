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
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.platform.configure.entity.Configure;
import org.springblade.platform.configure.service.IConfigureService;
import org.springblade.platform.jiaoyupeixun.entity.Jiaoyupeixun;
import org.springblade.platform.jiaoyupeixun.page.JiaoyupeixunPage;
import org.springblade.platform.jiaoyupeixun.service.IJiaoyupeixunService;
import org.springblade.platform.jiaoyupeixun.vo.JiaoyupeixunVO;
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
@RequestMapping("/platform/jiaoyupeixun")
@Api(value = "教育培训", tags = "教育培训")
public class JiaoyupeixunController extends BladeController {

	private IJiaoyupeixunService jiaoyupeixunService;

	private IConfigureService mapService;
	/**
	* 详情
	*/
	@GetMapping("/detail")
	@ApiLog("详情-教育培训")
	@ApiOperation(value = "详情-教育培训", notes = "传入id", position = 1)
	public R detail(String id) {
		return R.data(jiaoyupeixunService.selectByIds(id));
	}

	/**
	 * 分页
	 */
	@PostMapping("/list")
	@ApiLog("分页-教育培训")
	@ApiOperation(value = "分页-教育培训", notes = "传入JiaoyupeixunPage", position = 2)
	public R<JiaoyupeixunPage<JiaoyupeixunVO>> list(@RequestBody JiaoyupeixunPage jiaoyupeixunPage) {
		JiaoyupeixunPage<JiaoyupeixunVO> pages = jiaoyupeixunService.selectPageList(jiaoyupeixunPage);
		return R.data(pages);
	}

	/**
	* 新增
	*/
	@PostMapping("/insert")
	@ApiLog("新增-教育培训")
	@ApiOperation(value = "新增-教育培训", notes = "传入jiaoyupeixun", position = 3)
	public R insert(@RequestBody Jiaoyupeixun jiaoyupeixun,BladeUser user) {
		jiaoyupeixun.setCaozuoren(user.getUserName());
		jiaoyupeixun.setCaozuorenid(user.getUserId());
		jiaoyupeixun.setCaozuoshijian(DateUtil.now());
		jiaoyupeixun.setCreatetime(DateUtil.now());
		return R.status(jiaoyupeixunService.save(jiaoyupeixun));
	}

	/**
	* 修改
	*/
	@PostMapping("/update")
	@ApiLog("修改-教育培训")
	@ApiOperation(value = "修改-教育培训", notes = "传入jiaoyupeixun", position = 4)
	public R update(@RequestBody Jiaoyupeixun jiaoyupeixun,BladeUser user) {
		jiaoyupeixun.setCaozuoren(user.getUserName());
		jiaoyupeixun.setCaozuorenid(user.getUserId());
		jiaoyupeixun.setCaozuoshijian(DateUtil.now());
		if("".equals(jiaoyupeixun.getCreatetime())){
			jiaoyupeixun.setCreatetime(DateUtil.now());
		}
		return R.status(jiaoyupeixunService.updateById(jiaoyupeixun));
	}

	/**
	* 删除
	*/
	@PostMapping("/del")
	@ApiLog("删除-教育培训")
	@ApiOperation(value = "删除-教育培训", notes = "传入id", position = 5)
	public R del(@ApiParam(value = "id", required = true) @RequestParam String id) {
		return R.status(jiaoyupeixunService.updateDel(id));
	}
    /********************************** 配置表 ***********************/

	/**
	 * 配置表新增
	 */
	@PostMapping("/insertMap")
	@ApiLog("配置表新增-教育培训")
	@ApiOperation(value = "配置表新增-教育培训", notes = "传入biaodancanshu与deptId", position = 6)
	public R insertMap(String biaodancanshu,String deptId) {
		Configure configure=new Configure();
		JSONObject jsonObject = JSONUtil.parseObj(biaodancanshu);
		configure.setLabel(jsonObject.getStr("label"));
		configure.setShujubiaoziduan(jsonObject.getStr("prop"));
		configure.setDeptId(Integer.parseInt(deptId));
		configure.setTableName("anbiao_jiaoyupeixun_map");
		configure.setBiaodancanshu(biaodancanshu);
		return R.status(mapService.insertMap(configure));
	}
	/**
	 * 配置表编辑
	 */
	@PostMapping("/updateMap")
	@ApiLog("配置表编辑-教育培训")
	@ApiOperation(value = "配置表编辑-教育培训", notes = "传入biaodancanshu与id", position = 9)
	public R updateMap(String biaodancanshu,String id) {
		Configure configure=new Configure();
		JSONObject jsonObject = JSONUtil.parseObj(biaodancanshu);
		configure.setId(id);
		configure.setLabel(jsonObject.getStr("label"));
		configure.setShujubiaoziduan(jsonObject.getStr("prop"));
		configure.setTableName("anbiao_jiaoyupeixun_map");
		configure.setBiaodancanshu(biaodancanshu);
		return R.status(mapService.updateMap(configure));
	}

	/**
	 * 配置表删除
	 */
	@PostMapping("/delMap")
	@ApiLog("配置表删除-教育培训")
	@ApiOperation(value = "配置表删除-教育培训", notes = "传入id", position = 7)
	public R delMap(@ApiParam(value = "主键id", required = true) @RequestParam String id) {
		return R.status(mapService.delMap("anbiao_jiaoyupeixun_map",id));
	}

	/**
	 * 根据单位id获取配置模块数据
	 */
	@GetMapping("/listMap")
	@ApiLog("获取配置-教育培训")
	@ApiOperation(value = "获取配置-教育培训", notes = "传入deptId", position = 8)
	public R<JSONArray> listMap(Integer deptId) {
		List<Configure>  list1=mapService.selectMapList("anbiao_jiaoyupeixun_map",deptId);
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
}
