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
package org.springblade.platform.guanlijigouherenyuan.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.platform.configure.entity.Configure;
import org.springblade.platform.configure.service.IConfigureService;
import org.springblade.platform.guanlijigouherenyuan.entity.Huiyirenyuan;
import org.springblade.platform.guanlijigouherenyuan.page.HuiyirenyuanPage;
import org.springblade.platform.guanlijigouherenyuan.service.IHuiyirenyuanService;
import org.springblade.platform.guanlijigouherenyuan.vo.HuiyirenyuanVO;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *  控制器
 */
@RestController
@AllArgsConstructor
@RequestMapping("/platform/huiyirenyuan")
@Api(value = "安全会议-会议人员", tags = "会议人员")
public class HuiyirenyuanController extends BladeController {

	private IHuiyirenyuanService huiyirenyuanService;

	private IConfigureService mapService;

	@PostMapping("/list")
	@ApiLog("分页-会议人员")
	@ApiOperation(value = "分页-会议人员", notes = "传入anquanhuiyiPage", position = 1)
	public R<HuiyirenyuanPage<HuiyirenyuanVO>> list(@RequestBody HuiyirenyuanPage huiyirenyuanPage) {
		HuiyirenyuanPage<HuiyirenyuanVO> pages = huiyirenyuanService.selectPageQuery(huiyirenyuanPage);
		return R.data(pages);
	}

	/**
	* 新增
	*/
	@PostMapping("/insert")
	@ApiLog("新增-会议人员")
	@ApiOperation(value = "新增-会议人员", notes = "传入huiyirenyuan", position = 4)
	public R insert(@Valid @RequestBody Huiyirenyuan huiyirenyuan, BladeUser user) {
//		huiyirenyuan.setCaozuoren(user.getUserName());
//		huiyirenyuan.setCaozuorenid(user.getUserId());
//		huiyirenyuan.setCaozuoshijian(DateUtil.now());
		return R.status(huiyirenyuanService.save(huiyirenyuan));
	}

	/**
	* 修改
	*/
	@PostMapping("/update")
	@ApiLog("修改-会议人员")
	@ApiOperation(value = "修改-会议人员", notes = "传入huiyirenyuan", position = 5)
	public R update(@Valid @RequestBody Huiyirenyuan huiyirenyuan) {
		return R.status(huiyirenyuanService.updateById(huiyirenyuan));
	}


	/**
	* 删除
	*/
	@PostMapping("/del")
	@ApiLog("删除-会议人员")
	@ApiOperation(value = "删除-会议人员", notes = "传入id", position = 7)
	public R del(@ApiParam(value = "主键id", required = true) @RequestParam String id) {
		return R.status(huiyirenyuanService.deleteHuiYi(id));
	}

	@GetMapping("/detail")
	@ApiLog("详情-会议人员")
	@ApiOperation(value = "详情-会议人员", notes = "传入id", position = 4)
	public R<Huiyirenyuan> detail(String id) {
		Huiyirenyuan detail = huiyirenyuanService.getById(id);
		return R.data(detail);
	}
	//-----------------配置表---------------------
	/**
	 * 配置表新增
	 */
	@PostMapping("/insertMap")
	@ApiLog("配置表新增-会议人员")
	@ApiOperation(value = "配置表新增-会议人员", notes = "传入biaodancanshu与deptId", position = 6)
	public R insertMap(String biaodancanshu,String deptId) {
		Configure configure=new Configure();
		JSONObject jsonObject = JSONUtil.parseObj(biaodancanshu);
		configure.setLabel(jsonObject.getStr("label"));
		configure.setShujubiaoziduan(jsonObject.getStr("prop"));
		configure.setDeptId(Integer.parseInt(deptId));
		configure.setTableName("anbiao_huiyirenyuan_map");
		configure.setBiaodancanshu(biaodancanshu);
		return R.status(mapService.insertMap(configure));
	}
	/**
	 * 配置表编辑
	 */
	@PostMapping("/updateMap")
	@ApiLog("配置表编辑-会议人员")
	@ApiOperation(value = "配置表编辑-会议人员", notes = "传入biaodancanshu与id", position = 6)
	public R updateMap(String biaodancanshu,String id) {
		Configure configure=new Configure();
		JSONObject jsonObject = JSONUtil.parseObj(biaodancanshu);
		configure.setId(id);
		configure.setLabel(jsonObject.getStr("label"));
		configure.setShujubiaoziduan(jsonObject.getStr("prop"));
		configure.setTableName("anbiao_huiyirenyuan_map");
		configure.setBiaodancanshu(biaodancanshu);
		return R.status(mapService.updateMap(configure));
	}

	/**
	 * 配置表删除
	 */
	@PostMapping("/delMap")
	@ApiLog("配置表删除-会议人员")
	@ApiOperation(value = "配置表删除-会议人员", notes = "传入id", position = 6)
	public R delMap(@ApiParam(value = "主键id", required = true) @RequestParam String id) {
		return R.status(mapService.delMap("anbiao_huiyirenyuan_map",id));
	}

	/**
	 * @Description: 根据单位id获取配置模块数据
	 * @Param: [postId]
	 */
	@GetMapping("/listMap")
	@ApiLog("获取配置-会议人员")
	@ApiOperation(value = "获取配置-会议人员", notes = "传入deptId", position = 7)
	public R<JSONArray> listMap(Integer deptId) {
		List<Configure> list=mapService.selectMapList("anbiao_huiyirenyuan_map",deptId);
		String str="";
		for (int i = 0; i <list.size() ; i++) {
			//转换成json数据并put id
			JSONObject jsonObject = JSONUtil.parseObj(list.get(i).getBiaodancanshu());
			jsonObject.put("id",list.get(i).getId());
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
