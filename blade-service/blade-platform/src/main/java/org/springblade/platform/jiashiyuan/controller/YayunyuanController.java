package org.springblade.platform.jiashiyuan.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.http.util.TextUtils;
import org.springblade.common.tool.IdCardUtil;
import org.springblade.common.tool.RegexUtils;
import org.springblade.core.tool.utils.DigestUtil;
import org.springblade.platform.cheliangguanli.entity.CheliangJiashiyuan;
import org.springblade.platform.cheliangguanli.entity.Vehicle;
import org.springblade.platform.cheliangguanli.service.IVehicleService;
import org.springblade.platform.cheliangguanli.vo.VehicleVO;
import org.springblade.platform.configure.entity.Configure;
import org.springblade.platform.configure.service.IConfigureService;
import org.springblade.platform.jiashiyuan.entity.JiaShiYuan;
import org.springblade.platform.jiashiyuan.page.JiaShiYuanPage;
import org.springblade.platform.jiashiyuan.service.IJiaShiYuanService;
import org.springblade.platform.jiashiyuan.vo.JiaShiYuanVO;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.Dept;
import org.springblade.system.feign.ISysClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by you on 2019/5/6.
 */
@RestController
@RequestMapping("/platform/yayunyuan")
@AllArgsConstructor
@Api(value = "押运员资料管理", tags = "押运员资料管理")
public class YayunyuanController {
	private IJiaShiYuanService iJiaShiYuanService;
	private IConfigureService mapService;
	private ISysClient iSysClient;
	private IVehicleService vehicleService;

	/**
	 * 新增
	 */
	@PostMapping("/insert")
	@ApiLog("新增-押运员资料管理")
	@ApiOperation(value = "新增-押运员资料管理", notes = "传入jiaShiYuan", position = 1)
	public R insert(@RequestBody JiaShiYuan jiaShiYuan, BladeUser user) {
		if("男".equals(jiaShiYuan.getXingbie())){
			jiaShiYuan.setXingbie("1");
		}else{
			jiaShiYuan.setXingbie("2");
		}
		jiaShiYuan.setJiashiyuanleixing("押运员");
		jiaShiYuan.setCaozuoren(user.getUserName());
		jiaShiYuan.setCaozuorenid(user.getUserId());
		jiaShiYuan.setCaozuoshijian(DateUtil.now());
		jiaShiYuan.setCreatetime(DateUtil.now());
		if("".equals(jiaShiYuan.getChushengshijian())){
			jiaShiYuan.setChushengshijian(null);
		}
		if("".equals(jiaShiYuan.getShenfenzhengyouxiaoqi())){
			jiaShiYuan.setShenfenzhengyouxiaoqi(null);
		}
		if("".equals(jiaShiYuan.getPingyongriqi())){
			jiaShiYuan.setPingyongriqi(null);
		}
		if("".equals(jiaShiYuan.getJiashizhengchulingriqi())){
			jiaShiYuan.setJiashizhengchulingriqi(null);
		}
		if("".equals(jiaShiYuan.getJiashizhengyouxiaoqi())){
			jiaShiYuan.setJiashizhengyouxiaoqi(null);
		}
		if("".equals(jiaShiYuan.getTijianyouxiaoqi())){
			jiaShiYuan.setTijianyouxiaoqi(null);
		}
		if("".equals(jiaShiYuan.getCongyezhengyouxiaoqi())){
			jiaShiYuan.setCongyezhengyouxiaoqi(null);
		}
		if("".equals(jiaShiYuan.getCongyezhengchulingri())){
			jiaShiYuan.setCongyezhengchulingri(null);
		}
		if("".equals(jiaShiYuan.getXiacichengxinkaoheshijian())){
			jiaShiYuan.setXiacichengxinkaoheshijian(null);
		}
		if("".equals(jiaShiYuan.getXiacijixujiaoyushijian())){
			jiaShiYuan.setXiacijixujiaoyushijian(null);
		}
		if("".equals(jiaShiYuan.getHuzhaoyouxiaoqi())){
			jiaShiYuan.setHuzhaoyouxiaoqi(null);
		}
		if("".equals(jiaShiYuan.getZhunjiazhengyouxiaoqi())){
			jiaShiYuan.setZhunjiazhengyouxiaoqi(null);
		}
		if("".equals(jiaShiYuan.getLizhishijian())){
			jiaShiYuan.setLizhishijian(null);
		}
		if("".equals(jiaShiYuan.getChengxinkaoheshijian())){
			jiaShiYuan.setChengxinkaoheshijian(null);
		}
		if("".equals(jiaShiYuan.getJixujiaoyushijian())){
			jiaShiYuan.setJixujiaoyushijian(null);
		}
		if("".equals(jiaShiYuan.getTijianriqi())){
			jiaShiYuan.setTijianriqi(null);
		}
		return R.status(iJiaShiYuanService.save(jiaShiYuan));
	}

	/**
	 * 编辑
	 */
	@PostMapping("/update")
	@ApiLog("编辑-押运员资料管理")
	@ApiOperation(value = "编辑-押运员资料管理", notes = "编辑jiaShiYuan", position = 2)
	public R update(@RequestBody JiaShiYuan jiaShiYuan,BladeUser user) {
		if("男".equals(jiaShiYuan.getXingbie())){
			jiaShiYuan.setXingbie("1");
		}else{
			jiaShiYuan.setXingbie("2");
		}
		jiaShiYuan.setCaozuoren(user.getUserName());
		jiaShiYuan.setCaozuorenid(user.getUserId());
		jiaShiYuan.setCaozuoshijian(DateUtil.now());
		if("".equals(jiaShiYuan.getCreatetime())){
			jiaShiYuan.setCreatetime(DateUtil.now());
		}
		if("".equals(jiaShiYuan.getChushengshijian())){
			jiaShiYuan.setChushengshijian(null);
		}
		if("".equals(jiaShiYuan.getShenfenzhengyouxiaoqi())){
			jiaShiYuan.setShenfenzhengyouxiaoqi(null);
		}
		if("".equals(jiaShiYuan.getPingyongriqi())){
			jiaShiYuan.setPingyongriqi(null);
		}
		if("".equals(jiaShiYuan.getJiashizhengchulingriqi())){
			jiaShiYuan.setJiashizhengchulingriqi(null);
		}
		if("".equals(jiaShiYuan.getJiashizhengyouxiaoqi())){
			jiaShiYuan.setJiashizhengyouxiaoqi(null);
		}
		if("".equals(jiaShiYuan.getTijianyouxiaoqi())){
			jiaShiYuan.setTijianyouxiaoqi(null);
		}
		if("".equals(jiaShiYuan.getCongyezhengyouxiaoqi())){
			jiaShiYuan.setCongyezhengyouxiaoqi(null);
		}
		if("".equals(jiaShiYuan.getCongyezhengchulingri())){
			jiaShiYuan.setCongyezhengchulingri(null);
		}
		if("".equals(jiaShiYuan.getXiacichengxinkaoheshijian())){
			jiaShiYuan.setXiacichengxinkaoheshijian(null);
		}
		if("".equals(jiaShiYuan.getXiacijixujiaoyushijian())){
			jiaShiYuan.setXiacijixujiaoyushijian(null);
		}
		if("".equals(jiaShiYuan.getHuzhaoyouxiaoqi())){
			jiaShiYuan.setHuzhaoyouxiaoqi(null);
		}
		if("".equals(jiaShiYuan.getZhunjiazhengyouxiaoqi())){
			jiaShiYuan.setZhunjiazhengyouxiaoqi(null);
		}
		if("".equals(jiaShiYuan.getLizhishijian())){
			jiaShiYuan.setLizhishijian(null);
		}
		if("".equals(jiaShiYuan.getChengxinkaoheshijian())){
			jiaShiYuan.setChengxinkaoheshijian(null);
		}
		if("".equals(jiaShiYuan.getJixujiaoyushijian())){
			jiaShiYuan.setJixujiaoyushijian(null);
		}
		if("".equals(jiaShiYuan.getTijianriqi())){
			jiaShiYuan.setTijianriqi(null);
		}
		return R.status(iJiaShiYuanService.updateById(jiaShiYuan));
	}

	/**
	 *  删除
	 */
	@PostMapping("/del")
	@ApiLog("删除-押运员资料管理")
	@ApiOperation(value = "删除-押运员资料管理", notes = "传入id", position = 3)
	public R del(String id) {
		return R.status(iJiaShiYuanService.updateDel(id));
	}

	/**
	 *  查询详情
	 */
	@GetMapping("/detail")
	@ApiLog("详情-押运员资料管理")
	@ApiOperation(value = "详情-押运员资料管理", notes = "传入id", position = 4)
	public R detail(String id) {
		return R.data(iJiaShiYuanService.selectByIds(id));
	}

	/**
	 * 分页
	 */
	@PostMapping("/list")
	@ApiLog("分页-押运员资料管理")
	@ApiOperation(value = "分页-押运员资料管理", notes = "传入JiaShiYuanPage", position = 5)
	public R<JiaShiYuanPage<JiaShiYuanVO>> list(@RequestBody JiaShiYuanPage jiaShiYuanPage) {
		jiaShiYuanPage.setJiashiyuanleixing("押运员");
		JiaShiYuanPage<JiaShiYuanVO> pages = iJiaShiYuanService.selectPageList(jiaShiYuanPage);
		return R.data(pages);
	}

	public static boolean isCarnumberNO(String carnumber) {
		String carnumRegex = "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1})";

		if (TextUtils.isEmpty(carnumber)) {
			return false;
		}else {
			boolean ss = carnumber.matches(carnumRegex);
			return ss;
		}
	}

	/**
	 * 押运员信息--验证
	 * @update: 黄亚平 添加验证
	 * @param
	 */
	@PostMapping("driverImport")
	@ApiLog("押运员信息-验证")
	@ApiOperation(value = "押运员信息-验证", notes = "file", position = 10)
	public R driverImport(@RequestParam(value = "file") MultipartFile file,BladeUser user){
		R rs = new R();
		ExcelReader reader = null;
		try {
			reader = ExcelUtil.getReader(file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//时间默认格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//验证数据成功条数
		int aa=0;
		//验证数据错误条数
		int bb=0;
		//全局变量，只要一条数据不对，就为false
		boolean isDataValidity=true;
		//错误信息
		String errorStr="";

		List<Map<String,Object>> readAll = reader.readAll();
		if(readAll.size()>5000){
			errorStr+="导入数据超过5000条，无法导入！";
			rs.setMsg(errorStr);
			rs.setCode(400);
			return rs;
		}

		List<JiaShiYuan> drivers= new ArrayList<>();
		for(Map<String,Object> a:readAll){
			aa++;
			JiaShiYuan driver=new JiaShiYuan();
			SimpleDateFormat dateFormat2=new SimpleDateFormat("yyyy-MM-dd");
			Dept dept;
			JiaShiYuanVO driverVO = null;
			String deptname =  String.valueOf(a.get("所属单位")).trim();
			if(StringUtils.isBlank(deptname)){
				driver.setMsg("所属单位不能为空;");
				driver.setImportUrl("icon_cha.png");
				errorStr+="所属单位不能为空;";
				bb++;
			}
			dept = iSysClient.getDeptByName(deptname.trim());
			if (dept != null){
				driver.setDeptId(Integer.valueOf(dept.getId()));
				driver.setDeptName(deptname.trim());
				driver.setImportUrl("icon_gou.png");
			}else{
				driver.setMsg(deptname+"所属单位不存在;");
				driver.setImportUrl("icon_cha.png");
				errorStr+=deptname+"所属单位不存在;";
				bb++;
			}
			String driverName = String.valueOf(a.get("押运员姓名")).trim();
			if(StringUtils.isBlank(driverName)){
				driver.setMsg("押运员姓名不能为空;");
				driver.setImportUrl("icon_cha.png");
				errorStr+="押运员姓名不能为空;";
				bb++;
			}else{
				driver.setJiashiyuanxingming(driverName);
				driver.setImportUrl("icon_gou.png");
			}
			String tmp = String.valueOf(a.get("身份证号")).trim();
			if(StringUtils.isNotBlank(tmp)){
				//校验身份证是否合法
				if(IdCardUtil.isValidCard(tmp) == true){
					driver.setShenfenzhenghao(tmp);
					driverVO = iJiaShiYuanService.selectByCardNo(driver.getDeptId().toString(),tmp,null);
					if(driverVO!=null){
						driver.setMsg(driverVO.getShenfenzhenghao()+"该押运员身份证号已存在;");
						driver.setImportUrl("icon_cha.png");
						errorStr+=driverVO.getShenfenzhenghao()+"该押运员身份证号已存在;";
						bb++;
					}else{
						driver.setShenfenzhenghao(tmp);
						driver.setImportUrl("icon_gou.png");
					}
				}else{
					driver.setMsg("该押运员身份证号不合法;");
					driver.setImportUrl("icon_cha.png");
					errorStr+="该押运员身份证号不合法;";
					bb++;
				}
			}
			//验证手机号码是否合法
			String shoujihaoma = String.valueOf(a.get("手机号码")).trim();
			if(StringUtils.isBlank(shoujihaoma)){
				driver.setMsg("手机号码不能为空;");
				driver.setImportUrl("icon_cha.png");
				errorStr+="手机号码不能为空;";
				bb++;
			}else{
				if(RegexUtils.checkMobile(shoujihaoma)){
					driver.setShoujihaoma(shoujihaoma);
					driver.setImportUrl("icon_gou.png");
				}else{
					driver.setMsg(shoujihaoma+"该手机号码不合法;");
					errorStr+=shoujihaoma+"该手机号码不合法;";
					driver.setImportUrl("icon_cha.png");
					bb++;
				}
			}

			//验证驾驶证是否合法
//			String jiashizheng = String.valueOf(a.get("驾驶证号")).trim();
//			if(StringUtils.isBlank(jiashizheng)){
//				driver.setMsg("驾驶证号不能为空;");
//				driver.setImportUrl("icon_cha.png");
//				errorStr+="驾驶证号不能为空;";
//				bb++;
//			}else{
//				if(IdCardUtil.isValidCard(jiashizheng) == true){
//					driver.setJiashizhenghao(jiashizheng);
//					driverVO = iJiaShiYuanService.selectByCardNo(null,jiashizheng);
//					if(driverVO!=null){
//						driver.setMsg(driverVO.getJiashizhenghao()+"该驾驶证号已存在;");
//						driver.setImportUrl("icon_cha.png");
//						errorStr+=driverVO.getJiashizhenghao()+"该驾驶证号已存在;";
//						bb++;
//					}else{
//						driver.setShenfenzhenghao(tmp);
//						driver.setImportUrl("icon_gou.png");
//					}
//				}else{
//					driver.setMsg(jiashizheng+"该驾驶证号不合法;");
//					errorStr+=jiashizheng+"该驾驶证号不合法;";
//					driver.setImportUrl("icon_cha.png");
//					bb++;
//				}
//			}

			//验证从业资格证号
			String congyezigezheng = String.valueOf(a.get("从业资格证号")).trim();
			if(StringUtils.isNotBlank(congyezigezheng)){
				driver.setCongyezigezheng(congyezigezheng);
				driver.setImportUrl("icon_gou.png");
			}

			//验证从业证有效期
			String congyezigezhengyouxiaoqi = String.valueOf(a.get("从业证有效期")).trim();
			if(StringUtils.isNotBlank(congyezigezheng)){
				driver.setCongyezhengyouxiaoqi(congyezigezhengyouxiaoqi);
				driver.setImportUrl("icon_gou.png");
			}

			String cheliangpaiz=String.valueOf(a.get("车辆牌照")).trim();
			if(isCarnumberNO(cheliangpaiz) == false){
				driver.setMsg("辆牌照格式不正确;");
				errorStr+=cheliangpaiz+"辆牌照格式不正确;";
				driver.setImportUrl("icon_cha.png");
				bb++;
			}else{
				driver.setImportUrl("icon_gou.png");
			}

			String chepaiyanse=String.valueOf(a.get("车牌颜色")).trim();
			if(StringUtils.isNotBlank(chepaiyanse)){
				VehicleVO vehicleVO = vehicleService.selectVehicleColor(chepaiyanse);
				if (vehicleVO == null || vehicleVO.getChepaiyanse() == null) {
					driver.setMsg("车牌颜色输入错误;");
					errorStr+="车牌颜色输入错误;";
					driver.setImportUrl("icon_cha.png");
					bb++;
				}else{
					driver.setImportUrl("icon_gou.png");
				}
			}

			Vehicle vehicleVO = vehicleService.vehileOne(cheliangpaiz,chepaiyanse,Integer.valueOf(dept.getId()));
			if(vehicleVO!=null){
				driver.setImportUrl("icon_gou.png");
			}else{
				driver.setImportUrl("icon_cha.png");
				errorStr+=cheliangpaiz+"该车不存在;";
				driver.setMsg(cheliangpaiz+"该车不存在;");
				bb++;
			}
			driver.setCheliangpaizhao(cheliangpaiz);
			driver.setChepaiyanse(chepaiyanse);

			drivers.add(driver);
		}
		if(bb>0){
			rs.setMsg(errorStr);
			rs.setCode(500);
			rs.setSuccess(false);
			rs.setData(drivers);
			return rs;
		}else{
			rs.setCode(200);
			rs.setMsg("数据验证成功");
			rs.setData(drivers);
			rs.setSuccess(true);
			return rs;
		}
	}

	/**
	 * 押运员信息--确认导入
	 * @author: elvis
	 * @date: 2020/10/19 10:23
	 * @param
	 * @return: org.springblade.core.tool.api.R
	 */
	@PostMapping("driverImportOk")
	@ApiLog("押运员信息-确认导入")
	@ApiOperation(value = "押运员信息-确认导入", notes = "drivers", position = 10)
	public R driverImportOk(@RequestParam(value = "drivers") String drivers,BladeUser user,@RequestParam Integer userId,@RequestParam String userName){
		JSONArray json = JSONUtil.parseArray(drivers);
		List<Map<String,Object>> lists = (List)json;
		R rs = new R();
		//时间默认格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//验证数据成功条数
		int aa=0;
		//验证数据错误条数
		int bb=0;
		//全局变量，只要一条数据不对，就为false
		boolean isDataValidity=true;
		//错误信息
		String errorStr="";

		if(lists.size()>5000){
			errorStr+="导入数据超过5000条，无法导入！";
			rs.setMsg(errorStr);
			rs.setCode(400);
			return rs;
		}

		for(Map<String,Object> a:lists){
			aa++;
			JiaShiYuan driver=new JiaShiYuan();
			SimpleDateFormat dateFormat2=new SimpleDateFormat("yyyy-MM-dd");
			Dept dept;
			String id=IdUtil.simpleUUID();
			driver.setId(id);
			driver.setDeptId(Integer.valueOf(String.valueOf(a.get("deptId")).trim()));
			driver.setJiashiyuanxingming(String.valueOf(a.get("jiashiyuanxingming")).trim());
			String tmp = String.valueOf(a.get("shenfenzhenghao")).trim();
			driver.setShenfenzhenghao(tmp);
			//通过身份证获取性别
			Integer sex = IdCardUtil.getGender(tmp);
//			if(1 == sex){
//				driver.setXingbie("男");
//			}else if(2 == sex){
//				driver.setXingbie("女");
//			}
			if(1 == sex){
				driver.setXingbie("1");
			}else if(2 == sex){
				driver.setXingbie("2");
			}
			//通过身份证获取年龄
			Integer age = IdCardUtil.getAgeByCard(tmp);
			driver.setNianling(age.toString());
			//通过身份证获取生日日期
			Date chushengshijian = IdCardUtil.getBirthDate(tmp);
			driver.setChushengshijian(dateFormat2.format(chushengshijian));
			//验证手机号码是否合法
			String shoujihaoma = String.valueOf(a.get("shoujihaoma")).trim();
			driver.setShoujihaoma(shoujihaoma);
			driver.setJiashiyuanleixing("押运员");
			driver.setCongyerenyuanleixing("押运员");
			driver.setCongyezigezheng(String.valueOf(a.get("congyezigezheng")).trim());
			driver.setCongyezhengyouxiaoqi(String.valueOf(a.get("congyezhengyouxiaoqi")).trim());
			driver.setDenglumima(DigestUtil.encrypt("123456"));
			driver.setIsdelete(0);
			driver.setCreatetime(DateUtil.now());
			driver.setCaozuoshijian(DateUtil.now());
			if(user != null){
				driver.setCaozuoren(user.getUserName());
				driver.setCaozuorenid(user.getUserId());
			}else{
				driver.setCaozuoren(userName);
				driver.setCaozuorenid(userId);
			}
			driver.setCheliangpaizhao(String.valueOf(a.get("cheliangpaizhao")));
			driver.setChepaiyanse(String.valueOf(a.get("chepaiyanse")));
			isDataValidity = iJiaShiYuanService.insertSelective(driver);
			//向车辆驾驶员绑定关系表中添加记录
			Vehicle vehicleVO = vehicleService.vehileOne(driver.getCheliangpaizhao(),driver.getChepaiyanse(),Integer.valueOf(driver.getDeptId()));
			CheliangJiashiyuan cheliangJiashiyuan = vehicleService.getVehidAndJiashiyuanId(vehicleVO.getId(),driver.getId());
			if(cheliangJiashiyuan == null){
				CheliangJiashiyuan cheliangJiashiyuan1 = new CheliangJiashiyuan();
				cheliangJiashiyuan1.setDeptId(vehicleVO.getDeptId());
				cheliangJiashiyuan1.setCreatetime(DateUtil.now());
				cheliangJiashiyuan1.setJiashiyuanid(driver.getId());
				cheliangJiashiyuan1.setVehid(vehicleVO.getId());
				cheliangJiashiyuan1.setType("1");
				boolean vaj = vehicleService.insertVehidAndJiashiyuan(cheliangJiashiyuan1);
			}
			//更新车辆表相关数据信息
			Vehicle vehicle = new Vehicle();
			vehicle.setYayunyuanxingming(driver.getJiashiyuanxingming());
			vehicle.setYayunyuandianhua(driver.getShoujihaoma());
			vehicle.setDeptId(driver.getDeptId());
			vehicle.setYayunyuanid(driver.getId());
			vehicle.setChepaiyanse(driver.getChepaiyanse());
			vehicle.setCheliangpaizhao(driver.getCheliangpaizhao());
			boolean v = vehicleService.updateSelective(vehicle);
		}
		if(isDataValidity == true){
			rs.setCode(200);
			rs.setMsg("数据导入成功");
			rs.setData(drivers);
			return rs;
		}else{
			rs.setCode(500);
			rs.setMsg("数据导入失败");
			rs.setData(drivers);
			return rs;
		}
	}

	/********************************** 配置表 ***********************/

	/**
	 * 根据单位id获取配置模块数据
	 */
	@GetMapping("/listMap")
	@ApiLog("获取配置-押运员资料管理")
	@ApiOperation(value = "获取配置-押运员资料管理", notes = "传入deptId", position = 6)
	public R<JSONArray> listMap(Integer deptId) {
		List<Configure> list1=mapService.selectMapList("anbiao_yayunyuan_map",deptId);
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
	@ApiLog("配置表新增-押运员资料管理")
	@ApiOperation(value = "配置表新增-押运员资料管理", notes = "传入biaodancanshu与deptId", position = 7)
	public R insertMap(String biaodancanshu,String deptId) {
		Configure configure=new Configure();
		JSONObject jsonObject = JSONUtil.parseObj(biaodancanshu);
		configure.setLabel(jsonObject.getStr("label"));
		configure.setShujubiaoziduan(jsonObject.getStr("prop"));
		configure.setDeptId(Integer.parseInt(deptId));
		configure.setTableName("anbiao_yayunyuan_map");
		configure.setBiaodancanshu(biaodancanshu);
		return R.status(mapService.insertMap(configure));
	}
	/**
	 * 配置表编辑
	 */
	@PostMapping("/updateMap")
	@ApiLog("配置表编辑-押运员资料管理")
	@ApiOperation(value = "配置表编辑-押运员资料管理", notes = "传入biaodancanshu与id", position = 9)
	public R updateMap(String biaodancanshu,String id) {
		Configure configure=new Configure();
		JSONObject jsonObject = JSONUtil.parseObj(biaodancanshu);
		configure.setId(id);
		configure.setLabel(jsonObject.getStr("label"));
		configure.setShujubiaoziduan(jsonObject.getStr("prop"));
		configure.setTableName("anbiao_yayunyuan_map");
		configure.setBiaodancanshu(biaodancanshu);
		return R.status(mapService.updateMap(configure));
	}

	/**
	 * 配置表删除
	 */
	@PostMapping("/delMap")
	@ApiLog("配置表删除-押运员资料管理")
	@ApiOperation(value = "配置表删除-押运员资料管理", notes = "传入id", position = 8)
	public R delMap(@ApiParam(value = "主键id", required = true) @RequestParam String id) {
		return R.status(mapService.delMap("anbiao_yayunyuan_map",id));
	}
}
