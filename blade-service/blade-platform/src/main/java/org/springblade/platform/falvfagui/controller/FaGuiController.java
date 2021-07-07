package org.springblade.platform.falvfagui.controller;

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
import org.springblade.platform.falvfagui.entity.FaGui;
import org.springblade.platform.falvfagui.page.FaGuiPage;
import org.springblade.platform.falvfagui.service.IFaGuiService;
import org.springblade.platform.falvfagui.vo.FaGuiVo;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: SafetyStandards
 * @description: 法律法规-法规控制层
 * @author: 呵呵哒
 **/
@RestController
@RequestMapping("/platform/fagui")
@AllArgsConstructor
@Api(value = "安标-法规", tags = "法规")
public class FaGuiController {
	/**
	 * 法规
	 */
	private IFaGuiService service;

	private IConfigureService mapService;

	/**
	 * @Description: 新增
	 * @Param: [blog]
	 * @return: org.springblade.core.tool.api.R
	 * @Author: 呵呵哒
	 */
	@PostMapping("/insert")
	@ApiLog("新增-法规")
	@ApiOperation(value = "新增-法规", notes = "传入fagui", position = 1)
	public R insert(@RequestBody FaGui fagui, BladeUser user) {
		fagui.setCaozuoren(user.getUserName());
		fagui.setCaozuorenid(user.getUserId());
		fagui.setCaozuoshijian(DateUtil.now());
		fagui.setCreatetime(DateUtil.now());
		return R.status(service.saveOrUpdate(fagui));
	}

	/**
	 * @Description: 修改
	 * @return: org.springblade.core.tool.api.R
	 * @Author: 呵呵哒
	 */
	@PostMapping("/update")
	@ApiLog("修改-法规")
	@ApiOperation(value = "修改-法规", notes = "传入blog", position = 2)
	public R update(@RequestBody FaGui fagui, BladeUser user) {
		fagui.setCaozuoren(user.getUserName());
		fagui.setCaozuorenid(user.getUserId());
		fagui.setCaozuoshijian(DateUtil.now());
		fagui.setIs_deleted(0);
		return R.status(service.saveOrUpdate(fagui));
	}
	/**
	 * @Description: 删除
	 * @return: org.springblade.core.tool.api.R
	 * @Author: 呵呵哒
	 */
	@PostMapping("/del")
	@ApiLog("删除-法规")
	@ApiOperation(value = "删除-法规", notes = "传入id", position = 3)
	public R del(@RequestParam String id) {
		return R.status(service.deleteFagui(id));
	}
	/**
	 * @Description: 详情
	 * @Param: [id]
	 * @return: org.springblade.core.tool.api.R<org.springblade.deadline.entity.Blog>
	 * @Author: 呵呵哒
	 */
	@GetMapping("/detail")
	@ApiLog("详情-法规")
	@ApiOperation(value = "详情-法规", notes = "传入id", position = 4)
	public R<FaGui> detail(Integer id) {
		FaGui detail = service.getById(id);
		return R.data(detail);
	}

	@PostMapping("/list")
	@ApiLog("分页-法规")
	@ApiOperation(value = "分页-法规", notes = "传入fagui与query", position = 5)
	public R<FaGuiPage<FaGuiVo>> list(@RequestBody FaGuiPage faGuiPage) {
		FaGuiPage<FaGuiVo> pages = service.selectFaGuiPage(faGuiPage);
		return R.data(pages);
	}
	//-----------------配置表---------------------
	/**
	 * 配置表新增
	 */
	@PostMapping("/insertMap")
	@ApiLog("配置表新增-法规")
	@ApiOperation(value = "配置表新增-法规", notes = "传入biaodancanshu与deptId", position = 6)
	public R insertMap(String biaodancanshu,String deptId) {
		Configure configure=new Configure();
		JSONObject jsonObject = JSONUtil.parseObj(biaodancanshu);
		configure.setLabel(jsonObject.getStr("label"));
		configure.setShujubiaoziduan(jsonObject.getStr("prop"));
		configure.setDeptId(Integer.parseInt(deptId));
		configure.setTableName("anbiao_fagui_map");
		configure.setBiaodancanshu(biaodancanshu);
		return R.status(mapService.insertMap(configure));
	}
	/**
	 * 配置表编辑
	 */
	@PostMapping("/updateMap")
	@ApiLog("配置表编辑-法规")
	@ApiOperation(value = "配置表编辑-法规", notes = "传入biaodancanshu与id", position = 6)
	public R updateMap(String biaodancanshu,String id) {
		Configure configure=new Configure();
		JSONObject jsonObject = JSONUtil.parseObj(biaodancanshu);
		configure.setId(id);
		configure.setLabel(jsonObject.getStr("label"));
		configure.setShujubiaoziduan(jsonObject.getStr("prop"));
		configure.setTableName("anbiao_fagui_map");
		configure.setBiaodancanshu(biaodancanshu);
		return R.status(mapService.updateMap(configure));
	}

	/**
	 * 配置表删除
	 */
	@PostMapping("/delMap")
	@ApiLog("配置表删除-法规")
	@ApiOperation(value = "配置表删除-法规", notes = "传入id", position = 7)
	public R delMap(@ApiParam(value = "主键id", required = true) @RequestParam String id) {
		return R.status(mapService.delMap("anbiao_fagui_map",id));
	}

	/**
	 * @Description: 根据单位id获取配置模块数据
	 * @Param: [postId]
	 * @return: org.springblade.core.tool.api.R<java.util.List<org.springblade.anbiao.vo.FaguiMapVO>>
	 * @Author: 呵呵哒
	 */
	@GetMapping("/listMap")
	@ApiLog("获取配置-法规")
	@ApiOperation(value = "获取配置-法规", notes = "传入deptId", position = 8)
	public R<JSONArray> listMap(Integer deptId) {
		List<Configure>  list1=mapService.selectMapList("anbiao_fagui_map",deptId);
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
