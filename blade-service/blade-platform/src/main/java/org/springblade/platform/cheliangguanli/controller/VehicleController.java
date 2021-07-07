package org.springblade.platform.cheliangguanli.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.util.TextUtils;
import org.springblade.common.tool.RegexUtils;
import org.springblade.core.tool.utils.DigestUtil;
import org.springblade.platform.cheliangguanli.entity.*;
import org.springblade.platform.cheliangguanli.page.VehiclePage;
import org.springblade.platform.cheliangguanli.service.ICheliangrenyuanbangdingService;
import org.springblade.platform.cheliangguanli.service.IVehicleService;
import org.springblade.platform.cheliangguanli.vo.VehicleVO;
import org.springblade.platform.configure.entity.Configure;
import org.springblade.platform.configure.service.IConfigureService;
import org.springblade.platform.jiashiyuan.entity.JiaShiYuan;
import org.springblade.platform.jiashiyuan.service.IJiaShiYuanService;
import org.springblade.common.tool.CheckPhoneUtil;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.gps.entity.VehiclePT;
import org.springblade.gps.feign.IGpsPointDataClient;
import org.springblade.system.entity.Dept;
import org.springblade.system.feign.ISysClient;
import org.springblade.upload.upload.feign.IFileUploadClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :hyp
 * @program : SafetyStandards
 * @description: VehicleController
 * @create 2021-04-22 17:50
 */
@RestController
@RequestMapping("/platform/vehicle")
@AllArgsConstructor
@Api(value = "车辆资料管理", tags = "车辆资料管理")
public class VehicleController {
    private IVehicleService vehicleService;
    private IConfigureService mapService;
	private IFileUploadClient fileUploadClient;
	private ICheliangrenyuanbangdingService cheliangrenyuanbangdingService;
	private IJiaShiYuanService iJiaShiYuanService;
	private ISysClient iSysClient;
	private IGpsPointDataClient iGpsPointDataClient;

    @PostMapping("/list")
	@ApiLog("分页-车辆资料管理")
    @ApiOperation(value = "分页-车辆资料管理", notes = "传入VehiclealarmweichuliPage", position = 1)
    public R<VehiclePage<VehicleVO>> list(@RequestBody VehiclePage vehiclepage) {
		vehiclepage.setCheliangleixing("2");
        VehiclePage<VehicleVO> pages = vehicleService.selectVehiclePage(vehiclepage);
		List<VehicleVO>  list=pages.getRecords();
		for (int i = 0; i <list.size() ; i++) {
			//车辆照片
			if(StrUtil.isNotEmpty(list.get(i).getCheliangzhaopian())){
				list.get(i).setCheliangzhaopian(fileUploadClient.getUrl(list.get(i).getCheliangzhaopian()));
			}

			//燃料消耗附件
			if(StrUtil.isNotEmpty(list.get(i).getRanliaoxiaohaofujian())){
				list.get(i).setRanliaoxiaohaofujian(fileUploadClient.getUrl(list.get(i).getRanliaoxiaohaofujian()));
			}
			//行驶证附件
			if(StrUtil.isNotEmpty(list.get(i).getXingshifujian())){
				list.get(i).setXingshifujian(fileUploadClient.getUrl(list.get(i).getXingshifujian()));
			}
		}
        return R.data(pages);
    }

    @GetMapping("/detail")
	@ApiLog("详情-车辆资料管理")
    @ApiOperation(value = "详情-车辆资料管理", notes = "传入id", position = 2)
    public R<VehicleVO> detail(String id) {
        VehicleVO detail = vehicleService.selectByKey(id);
		//车辆照片
//		if(StrUtil.isNotEmpty(detail.getCheliangzhaopian())){
//			detail.setCheliangzhaopian(fileUploadClient.getUrl(detail.getCheliangzhaopian()));
//		}
//		//燃料消耗附件
//		if(StrUtil.isNotEmpty(detail.getRanliaoxiaohaofujian())){
//			detail.setRanliaoxiaohaofujian(fileUploadClient.getUrl(detail.getRanliaoxiaohaofujian()));
//		}
//		//行驶证附件
//		if(StrUtil.isNotEmpty(detail.getXingshifujian())){
//			detail.setXingshifujian(fileUploadClient.getUrl(detail.getXingshifujian()));
//		}
        return R.data(detail);
    }

	@GetMapping("/selectByCL")
	@ApiLog("车牌搜索")
	@ApiOperation(value = "车牌搜索", notes = "传入postId,cheliangpaizhao", position = 2)
	@ApiImplicitParams({ @ApiImplicitParam(name = "postId", value = "岗位id", required = true),
		@ApiImplicitParam(name = "cheliangpaizhao", value = "车辆牌照", required = false)
	})
	public R<List<VehicleCP>> selectByCL(String postId, String cheliangpaizhao) {
		Dept dept=iSysClient.selectByJGBM("机构",postId);
		List<VehicleCP> detail = vehicleService.selectCL(dept.getId().toString(),cheliangpaizhao);
		return R.data(detail);
	}

	@GetMapping("/selectByCPYS")
	@ApiLog("牌照和颜色-获取数据")
	@ApiOperation(value = "牌照和颜色-获取数据", notes = "传入cheliangpaizhao和chepaiyanse", position = 2)
	public R<Map<String,Object>> selectByCPYS(String cheliangpaizhao,String chepaiyanse) {
		Map<String,Object> map = new HashMap<String,Object>();
		VehicleVO vehicleVO = vehicleService.selectByCPYS(cheliangpaizhao,chepaiyanse);
		//车辆照片
		if(StrUtil.isNotEmpty(vehicleVO.getCheliangzhaopian())){
			vehicleVO.setCheliangzhaopian(fileUploadClient.getUrl(vehicleVO.getCheliangzhaopian()));
		}
		//燃料消耗附件
		if(StrUtil.isNotEmpty(vehicleVO.getRanliaoxiaohaofujian())){
			vehicleVO.setRanliaoxiaohaofujian(fileUploadClient.getUrl(vehicleVO.getRanliaoxiaohaofujian()));
		}
		//行驶证附件
		if(StrUtil.isNotEmpty(vehicleVO.getXingshifujian())){
			vehicleVO.setXingshifujian(fileUploadClient.getUrl(vehicleVO.getXingshifujian()));
		}

		map.put("cheliang",vehicleVO);

//		//根据当前车辆id获取当班驾驶员id
//		CheliangrenyuanbangdingPage Page=new CheliangrenyuanbangdingPage();
//		Page.setDeptId(vehicleVO.getDeptId());
//		Page.setCheliangid(vehicleVO.getId());
//		Page.setShifoudangban("0");
//		CheliangrenyuanbangdingPage<CheliangrenyuanbangdingVO> pages = cheliangrenyuanbangdingService.selectPageList(Page);

//		JiaShiYuan detal=new JiaShiYuan();
//		if(pages!=null){
//			//获取驾驶员信息
//			List<CheliangrenyuanbangdingVO> records = pages.getRecords();
//			if(records != null && records.size()>0){
//				detal=iJiaShiYuanService.selectByIds(records.get(0).getJiashiyuanid());
//			}
//			//照片
//			if(StrUtil.isNotEmpty(detal.getZhaopian())){
//				detal.setZhaopian(fileUploadClient.getUrl(detal.getZhaopian()));
//			}
//			//身份证附件
//			if(StrUtil.isNotEmpty(detal.getShenfenzhengfujian())){
//				detal.setShenfenzhengfujian(fileUploadClient.getUrl(detal.getShenfenzhengfujian()));
//			}
//			//从业证附件
//			if(StrUtil.isNotEmpty(detal.getCongyezhengfujian())){
//				detal.setCongyezhengfujian(fileUploadClient.getUrl(detal.getCongyezhengfujian()));
//			}
//			//驾驶证附件
//			if(StrUtil.isNotEmpty(detal.getJiashizhengfujian())){
//				detal.setJiashizhengfujian(fileUploadClient.getUrl(detal.getJiashizhengfujian()));
//			}
//			//复印件
//			if(StrUtil.isNotEmpty(detal.getFuyinjian())){
//				detal.setFuyinjian(fileUploadClient.getUrl(detal.getFuyinjian()));
//			}
//		}
//
//		map.put("jiashiyuan",detal);
		return R.data(map);
	}

    @PostMapping("/insert")
	@ApiLog("新增-车辆资料管理")
    @ApiOperation(value = "新增-车辆资料管理", notes = "传入Vehicle", position = 3)
    public R insert(@RequestBody Vehicle vehicle, BladeUser user) {
		R r = new R();
    	VehicleVO vehicleVO = vehicleService.selectCPYS(vehicle.getCheliangpaizhao(),vehicle.getChepaiyanse());
		if(vehicleVO!=null){
			r.setMsg(vehicleVO.getCheliangpaizhao()+"该车已存在");
			r.setCode(500);
			return r;
		}
		VehicleVO vehicleVO1 = vehicleService.selectByZongDuan(vehicle.getZongduanid());
		if(vehicleVO1 !=null ){
			r.setMsg(vehicleVO1.getZongduanid()+"该终端ID已存在");
			r.setCode(500);
			return r;
		}

		vehicle.setCaozuoren(user.getUserName());
		vehicle.setCaozuorenid(user.getUserId());
		vehicle.setCaozuoshijian(LocalDateTime.now());
		vehicle.setCreatetime(LocalDateTime.now());
		if("".equals(vehicle.getRuhushijian())){
			vehicle.setRuhushijian(null);
		}
		if("".equals(vehicle.getZhucedengjishijian())){
			vehicle.setZhucedengjishijian(null);
		}
		if("".equals(vehicle.getGuohushijian())){
			vehicle.setGuohushijian(null);
		}
		if("".equals(vehicle.getTuishishijian())){
			vehicle.setTuishishijian(null);
		}
		if("".equals(vehicle.getQiangzhibaofeishijian())){
			vehicle.setQiangzhibaofeishijian(null);
		}
		if("".equals(vehicle.getChuchangriqi())){
			vehicle.setChuchangriqi(null);
		}
		if("".equals(vehicle.getGpsanzhuangshijian())){
			vehicle.setGpsanzhuangshijian(null);
		}

		if(!"".equals(vehicle.getYunyingshang())){
			String yys = StringEscapeUtils.unescapeHtml(StringEscapeUtils.unescapeHtml(vehicle.getYunyingshang()));
			vehicle.setYunyingshang(yys);
		}
		String str="1";
		//登录页
		if(StringUtil.isNotBlank(vehicle.getCheliangzhaopian())){
			fileUploadClient.updateCorrelation(vehicle.getCheliangzhaopian(),str);
		}
		boolean i = vehicleService.save(vehicle);
		if(i==true){
			r.setMsg("新增成功");
			r.setCode(200);
		}else{
			r.setMsg("新增失败");
			r.setCode(500);
		}
        return r;
    }

    @PostMapping("/update")
	@ApiLog("修改-车辆资料管理")
    @ApiOperation(value = "修改-车辆资料管理", notes = "传入Vehicle", position = 4)
    public R update(@RequestBody Vehicle vehicle,BladeUser user) {
		R r = new R();
		VehicleVO vehicleVO = vehicleService.selectCPYS(vehicle.getCheliangpaizhao(),vehicle.getChepaiyanse());
//		if(vehicleVO!=null){
//			r.setMsg(vehicleVO.getCheliangpaizhao()+"该车已存在");
//			r.setCode(500);
//			return r;
//		}
//		VehicleVO vehicleVO1 = vehicleService.selectByZongDuan(vehicle.getZongduanid());
//		if(vehicleVO1 !=null ){
//			r.setMsg(vehicleVO1.getZongduanid()+"该终端ID已存在");
//			r.setCode(500);
//			return r;
//		}

		vehicle.setCaozuoren(user.getUserName());
		vehicle.setCaozuorenid(user.getUserId());
		vehicle.setCaozuoshijian(LocalDateTime.now());
		if("".equals(vehicle.getCreatetime())){
			vehicle.setCreatetime(LocalDateTime.now());
		}
		if("".equals(vehicle.getRuhushijian())){
			vehicle.setRuhushijian("");
		}
		if("".equals(vehicle.getZhucedengjishijian())){
			vehicle.setZhucedengjishijian("");
		}
		if("".equals(vehicle.getGuohushijian())){
			vehicle.setGuohushijian("");
		}
		if("".equals(vehicle.getTuishishijian())){
			vehicle.setTuishishijian("");
		}
		if("".equals(vehicle.getQiangzhibaofeishijian())){
			vehicle.setQiangzhibaofeishijian("");
		}
		if("".equals(vehicle.getChuchangriqi())){
			vehicle.setChuchangriqi("");
		}
		if("".equals(vehicle.getGpsanzhuangshijian())){
			vehicle.setGpsanzhuangshijian("");
		}

		if(!"".equals(vehicle.getYunyingshang())){
			String yys = StringEscapeUtils.unescapeHtml(StringEscapeUtils.unescapeHtml(vehicle.getYunyingshang()));
			vehicle.setYunyingshang(yys);
		}
		boolean i = vehicleService.updateById(vehicle);
		if(i==true){
			r.setMsg("编辑成功");
			r.setCode(200);
		}else{
			r.setMsg("编辑失败");
			r.setCode(500);
		}
		return r;
    }

	@PostMapping("/del")
	@ApiLog("删除-车辆资料管理")
	@ApiOperation(value = "删除-车辆资料管理", notes = "传入车辆id", position = 5)
	public R del(@RequestParam String id) {
		R r = new R();
		String[] idsss = id.split(",");
		//去除素组中重复的数组
		List<String> listid = new ArrayList<String>();
		for (int i=0; i<idsss.length; i++) {
			if(!listid.contains(idsss[i])) {
				listid.add(idsss[i]);
			}
		}
		//返回一个包含所有对象的指定类型的数组
		String[]  idss= listid.toArray(new String[1]);
		for(int i = 0;i< idss.length;i++){
			boolean ss = vehicleService.deleleVehicle(idsss[i]);
			if (ss){
				r.setMsg("删除成功");
				r.setCode(200);
				r.setData(ss);
			}else{
				r.setMsg("删除失败");
				r.setCode(500);
				r.setData(null);
			}
		}
		return r;
	}

	@PostMapping("/updateVehicleOutStatus")
	@ApiLog("停用-车辆资料管理")
	@ApiOperation(value = "停用-车辆资料管理", notes = "传入车辆id", position = 15)
	public R updateVehicleOutStatus(@RequestParam String id) {
		R r = new R();
		String[] idsss = id.split(",");
		//去除素组中重复的数组
		List<String> listid = new ArrayList<String>();
		for (int i=0; i<idsss.length; i++) {
			if(!listid.contains(idsss[i])) {
				listid.add(idsss[i]);
			}
		}
		//返回一个包含所有对象的指定类型的数组
		String[]  idss= listid.toArray(new String[1]);
		for(int i = 0;i< idss.length;i++){
			boolean ss = vehicleService.updateVehicleOutStatus(idsss[i]);
			if (ss){
				r.setMsg("停用成功");
				r.setCode(200);
				r.setData(ss);
			}else{
				r.setMsg("停用失败");
				r.setCode(500);
				r.setData(null);
			}
		}
		return r;
	}

	@PostMapping("/updateVehicleSignStatus")
	@ApiLog("启用-车辆资料管理")
	@ApiOperation(value = "启用-车辆资料管理", notes = "传入车辆id", position = 15)
	public R updateVehicleSignStatus(@RequestParam String id) {
		R r = new R();
		String[] idsss = id.split(",");
		//去除素组中重复的数组
		List<String> listid = new ArrayList<String>();
		for (int i=0; i<idsss.length; i++) {
			if(!listid.contains(idsss[i])) {
				listid.add(idsss[i]);
			}
		}
		//返回一个包含所有对象的指定类型的数组
		String[]  idss= listid.toArray(new String[1]);
		for(int i = 0;i< idss.length;i++){
			boolean ss = vehicleService.updateVehicleSignStatus(idsss[i]);
			if (ss){
				r.setMsg("启用成功");
				r.setCode(200);
				r.setData(ss);
			}else{
				r.setMsg("启用失败");
				r.setCode(500);
				r.setData(null);
			}
		}
		return r;
	}

	@PostMapping("/updateVehicleScrapStatus")
	@ApiLog("报废-车辆资料管理")
	@ApiOperation(value = "报废-车辆资料管理", notes = "传入车辆id", position = 16)
	public R updateVehicleScrapStatus(@RequestParam String id) {
		R r = new R();
		String[] idsss = id.split(",");
		//去除素组中重复的数组
		List<String> listid = new ArrayList<String>();
		for (int i=0; i<idsss.length; i++) {
			if(!listid.contains(idsss[i])) {
				listid.add(idsss[i]);
			}
		}
		//返回一个包含所有对象的指定类型的数组
		String[]  idss= listid.toArray(new String[1]);
		for(int i = 0;i< idss.length;i++){
			boolean ss = vehicleService.updateVehicleScrapStatus(idsss[i]);
			if (ss){
				r.setMsg("启用成功");
				r.setCode(200);
				r.setData(ss);
			}else{
				r.setMsg("启用失败");
				r.setCode(500);
				r.setData(null);
			}
		}
		return r;
	}

    /********************************** 配置表 ***********************/
    /**
     * 配置表新增
     */
    @PostMapping("/insertMap")
	@ApiLog("配置表新增-车辆资料管理")
    @ApiOperation(value = "配置表新增-车辆资料管理", notes = "传入biaodancanshu与deptId", position = 6)
    public R insertMap(String biaodancanshu,String deptId) {
		Configure configure=new Configure();
		JSONObject jsonObject = JSONUtil.parseObj(biaodancanshu);
		configure.setLabel(jsonObject.getStr("label"));
		configure.setShujubiaoziduan(jsonObject.getStr("prop"));
		configure.setDeptId(Integer.parseInt(deptId));
		configure.setTableName("anbiao_vehicle_map");
		configure.setBiaodancanshu(biaodancanshu);
		return R.status(mapService.insertMap(configure));
    }

    /**
     * 配置表编辑
     */
    @PostMapping("/updateMap")
	@ApiLog("配置表编辑-车辆资料管理")
    @ApiOperation(value = "配置表编辑-车辆资料管理", notes = "传入biaodancanshu与id", position = 7)
    public R updateMap(String biaodancanshu, String id) {
        Configure configure = new Configure();
        JSONObject jsonObject = JSONUtil.parseObj(biaodancanshu);
		configure.setId(id);
		configure.setLabel(jsonObject.getStr("label"));
		configure.setShujubiaoziduan(jsonObject.getStr("prop"));
		configure.setTableName("anbiao_vehicle_map");
		configure.setBiaodancanshu(biaodancanshu);
        return R.status(mapService.updateMap(configure));
    }

    /**
     * 配置表删除
     */
    @PostMapping("/delMap")
	@ApiLog("配置表删除-车辆资料管理")
    @ApiOperation(value = "配置表删除-车辆资料管理", notes = "传入id", position = 8)
    public R delMap(@ApiParam(value = "主键id", required = true) @RequestParam String id) {
        return R.status(mapService.delMap("anbiao_vehicle_map", id));
    }

    /**
     * @Description: 根据岗位id获取车辆配置模块数据
     * @Param: [deptId]
     * @return: org.springblade.core.tool.api.R<java.util.List>
     * @Author: hyp
     * @date 2021-04-28
     */
    @GetMapping("/listMap")
	@ApiLog("获取车辆配置-车辆资料管理")
    @ApiOperation(value = "获取车辆配置-车辆资料管理", notes = "传入deptId", position = 9)
    public R<JSONArray> listMap(Integer deptId) {
        List<Configure> list1 = mapService.selectMapList("anbiao_vehicle_map", deptId);
        String str = "";
        for (int i = 0; i < list1.size(); i++) {
            //转换成json数据并put id
            JSONObject jsonObject = JSONUtil.parseObj(list1.get(i).getBiaodancanshu());
            jsonObject.put("id", list1.get(i).getId());
            if (!str.equals("")) {
                str = str + "," + jsonObject.toString();
            } else {
                str = jsonObject.toString();
            }
        }
        str = "[" + str + "]";
        JSONArray json = JSONUtil.parseArray(str);
        return R.data(json);
    }
	/**
	 * 车辆资料导入
	 * @author: LH
	 * @date: 2019/8/19 16:23
	 * @param
	 * @return: org.springblade.core.tool.api.R
	 */
	@PostMapping("vehicleImportOne")
	@ApiLog("车辆资料-导入(本企业)")
	@ApiOperation(value = "车辆资料-导入(本企业)", notes = "file", position = 10)
	public  R vehicleImportOne(@RequestParam(value = "file") MultipartFile file,BladeUser user,String DeptId,String DeptName){

		ExcelReader reader = null;
		try {
			reader = ExcelUtil.getReader(file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Map<String,Object>> readAll = reader.readAll();
		int index=readAll.size();
		if(index>5000){

			return  R.fail("导入条目不能超过5000条");

		}
		//导入数据成功条数
		int aa=0;
		int bb=0;
		List<Vehicle> vehicles=new ArrayList<Vehicle>();
		boolean b=true;
		for(Map<String,Object> a:readAll){
			aa++;
			Vehicle vehicle=new Vehicle();
			String id=IdUtil.simpleUUID();
			vehicle.setId(id);
			vehicle.setDeptId(Integer.valueOf(DeptId));
			vehicle.setDeptName(DeptName);

			String cheliangpaiz=String.valueOf(a.get("车辆牌照"));
			String chepaiyanse=String.valueOf(a.get("车牌颜色"));
			vehicle.setCheliangpaizhao(cheliangpaiz);
			vehicle.setChepaiyanse(chepaiyanse);
			VehicleVO vehicleVO = vehicleService.selectCPYS(cheliangpaiz,chepaiyanse);
			if(vehicleVO!=null){
				vehicle.setMsg("该车已存在");
				vehicle.setMsg2(false);
				bb++;
			}else{
				vehicle.setMsg2(true);
			}
			vehicle.setShiyongxingzhi(String.valueOf(a.get("使用性质")));
			vehicle.setJiashiyuanid(null);
			vehicle.setChangpai(String.valueOf(a.get("厂牌")));
			vehicle.setXinghao(String.valueOf(a.get("型号")));
			vehicle.setChejiahao(String.valueOf(a.get("车架号")));
			vehicle.setLuntaiguige(String.valueOf(a.get("轮胎规格")));
			vehicle.setCheshenyanse(String.valueOf(a.get("车身颜色")));
			vehicle.setHedingzaike(String.valueOf(a.get("核定载客")));
			vehicle.setYingyunnianxian(null);
			vehicle.setDengjizhengshubianhao(String.valueOf(a.get("车辆登记证书编号")));
			vehicle.setChelianglaiyuan(String.valueOf(a.get("车辆来源")));
			vehicle.setZhucedengjishijian(String.valueOf(a.get("注册登记日期")));
			vehicle.setRuhushijian(null);
			vehicle.setGuohushijian(null);
			vehicle.setTuishishijian(null);
			vehicle.setQiangzhibaofeishijian(String.valueOf(a.get("强制报废日期")));
			vehicle.setJieboyunshuzhenghao(String.valueOf(a.get("接驳运输证号")));
			vehicle.setYuancheliangpaizhao(String.valueOf(a.get("原车辆牌照")));
			vehicle.setCheliangzhuangtai("0");
			vehicle.setCheliangtingfangdiqu(null);
			vehicle.setDanganhao(String.valueOf(a.get("档案号")));
			vehicle.setBeiyongcheliang(String.valueOf(a.get("备用车辆")));
			vehicle.setYunyingshang(null);
			vehicle.setSuoshuchedui(null);
			vehicle.setXingshifujian(null);
			vehicle.setFujian(null);
			vehicle.setFadongjixinghao(String.valueOf(a.get("发动机型号")));
			vehicle.setFadongjihao(String.valueOf(a.get("发动机号")));
			vehicle.setFadongjipailianggonglv(String.valueOf(a.get("排量功率")));
			vehicle.setRanliaoleibie(String.valueOf(a.get("燃料类别")));
			vehicle.setRanyouxiaohao(null);
			vehicle.setPaifangbiaozhun(null);
			vehicle.setZhuanxiangfangshi(String.valueOf(a.get("转向方式")));
			vehicle.setChemenshezhi(String.valueOf(a.get("车门设置")));
			vehicle.setZhouju(String.valueOf(a.get("轴距")));
			vehicle.setChechang(String.valueOf(a.get("车长")));
			vehicle.setChekuan(String.valueOf(a.get("车宽")));
			vehicle.setChegao(String.valueOf(a.get("车高")));
			vehicle.setLuntaishu(null);
			vehicle.setChezhoushu(String.valueOf(a.get("车轴数")));
			vehicle.setGangbantanhuangpianshu(null);
			vehicle.setDipanxinghao(null);
			vehicle.setDonglileixing(null);
			vehicle.setZongzhiliang(String.valueOf(a.get("总质量")));
			vehicle.setZhengbeizhiliang(String.valueOf(a.get("整备质量")));
			vehicle.setLuntaizonglei(null);
			vehicle.setXuanguaxingshi(String.valueOf(a.get("悬挂形式")));
			vehicle.setXingchezhidongfangshi(null);
			vehicle.setZhidongqiqianlun(null);
			vehicle.setZhidongqihoulun(null);
			vehicle.setAbs(String.valueOf(a.get("ABS")));
			vehicle.setKongtiaoxitong(String.valueOf(a.get("空调系统")));
			vehicle.setHuanshuqi(String.valueOf(a.get("缓速器")));
			vehicle.setBiansuxiangxingshi(null);
			vehicle.setZhizhaochangshang(String.valueOf(a.get("制造厂商名称")));
			vehicle.setGouzhishuizhenghao(null);
			vehicle.setChuchangriqi(null);
			vehicle.setLeijilicheng(null);
			vehicle.setZhongduanfuwuqi(null);
//			vehicle.setCheliangdengji(String.valueOf(a.get("车辆等级")));
			vehicle.setWeishengjian(null);
			vehicle.setFadongjipailiang(null);
			vehicle.setCheliangwaikuochicun(null);
			vehicle.setRanliaoxiaohaofujian(null);
			vehicle.setBeizhu(null);
			vehicle.setGpsanzhuangshijian(null);
			vehicle.setZhinenghuaxitong(String.valueOf(a.get("智能化系统")));
			vehicle.setGps(null);
			vehicle.setXingshijiluyi(null);
			vehicle.setZongduanid(null);
			vehicle.setZongduanxinghao(null);
			vehicle.setCheliangzhaopian(null);
//			vehicle.setYunshujiezhi(String.valueOf(a.get("运输介质")));
			vehicle.setCreatetime(LocalDateTime.now());
			if(user!=null){
				vehicle.setCaozuoren(user.getUserName());
				vehicle.setCaozuorenid(user.getUserId());
			}
			vehicle.setCaozuoshijian(LocalDateTime.now());

			vehicles.add(vehicle);

		}
		if(bb>0){
			return  R.data(400, vehicles,"导入失败");
		}else{
			b=vehicleService.saveBatch(vehicles);
			if(b){
				return  R.success("成功导入:"+aa+"条");
			}else{
				return  R.fail("导入失败");
			}
		}
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
	 * 车辆信息--验证
	 * @author: elvis
	 * @date: 2020/06/19 10:23
	 * @update: 黄亚平 添加验证
	 * @param
	 * @return: org.springblade.core.tool.api.R
	 */
	@PostMapping("vehicleImport")
	@ApiLog("车辆信息-验证")
	@ApiOperation(value = "车辆信息-验证", notes = "file", position = 10)
	public R vehicleImport(@RequestParam(value = "file") MultipartFile file,BladeUser user,@RequestParam Integer userId,@RequestParam String userName,@RequestParam int type){

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

		List<Vehicle> vehicles=new ArrayList<Vehicle>();
		if (type == 1){
			for(Map<String,Object> a:readAll){
				aa++;
				Vehicle vehicle=new Vehicle();
				Dept dept;
				String id=IdUtil.simpleUUID();
				vehicle.setId(id);
				String deptname =  String.valueOf(a.get("机构名称"));
				dept = iSysClient.getDeptByName(deptname);
				vehicle.setDeptId(Integer.valueOf(dept.getId()));
				vehicle.setCheliangpaizhao(String.valueOf(a.get("车辆牌照")));
				vehicle.setChepaiyanse(String.valueOf(a.get("车牌颜色")));
				if(StringUtils.isBlank((String) a.get("使用性质"))){
					vehicle.setShiyongxingzhi("");
				}else{
					vehicle.setShiyongxingzhi(String.valueOf(a.get("使用性质")).trim());
				}

				if(StringUtils.isBlank(String.valueOf(a.get("车辆类型")))){
					vehicle.setXinghao("");
				}else{
					vehicle.setXinghao(String.valueOf(a.get("车辆类型")).trim());
				}

				if(StringUtils.isBlank(String.valueOf(a.get("厂牌")))){
					vehicle.setChangpai("");
				}else{
					vehicle.setChangpai(String.valueOf(a.get("厂牌")).trim());
				}

				if(StringUtils.isBlank(String.valueOf(a.get("车架号")))){
					vehicle.setChejiahao("");
				}else{
					vehicle.setChejiahao(String.valueOf(a.get("车架号")).trim());
				}

				if(StringUtils.isBlank(String.valueOf(a.get("4G视频地址")))){
					vehicle.setYunyingshang("");
				}else{
					String yys = StringEscapeUtils.unescapeHtml(StringEscapeUtils.unescapeHtml(String.valueOf(a.get("4G视频地址")).trim()));
					vehicle.setYunyingshang(yys);
				}

				if(StringUtils.isBlank(String.valueOf(a.get("运营商名称")))){
					vehicle.setYunyingshangmingcheng("");
				}else{
					vehicle.setYunyingshangmingcheng(String.valueOf(a.get("运营商名称")).trim());
				}

				if(StringUtils.isBlank(String.valueOf(a.get("终端编号")))){
					vehicle.setZongduanid("");
				}else{
					vehicle.setZongduanid(String.valueOf(a.get("终端编号")).trim());
				}

				if(StringUtils.isBlank(String.valueOf(a.get("驾驶员")))){
					vehicle.setJiashiyuanxingming("");
				}else{
					vehicle.setJiashiyuanxingming(String.valueOf(a.get("驾驶员")).trim());
				}
				if(StringUtils.isBlank(String.valueOf(a.get("驾驶员电话")))){
					vehicle.setJiashiyuandianhua("");
				}else{
					vehicle.setJiashiyuandianhua(String.valueOf(a.get("驾驶员电话")).trim());
				}
				if(StringUtils.isBlank(String.valueOf(a.get("押运员")))){
					vehicle.setYayunyuanxingming("");
				}else{
					vehicle.setYayunyuanxingming(String.valueOf(a.get("押运员")).trim());
				}
				if(StringUtils.isBlank(String.valueOf(a.get("押运员电话")))){
					vehicle.setYayunyuandianhua("");
				}else{
					vehicle.setYayunyuandianhua(String.valueOf(a.get("押运员电话")).trim());
				}
				if(StringUtils.isBlank(String.valueOf(a.get("车主")))){
					vehicle.setChezhu("");
				}else{
					vehicle.setChezhu(String.valueOf(a.get("车主")).trim());
				}
				if(StringUtils.isBlank(String.valueOf(a.get("车主电话")))) {
					vehicle.setChezhudianhua("");
				}else{
					vehicle.setChezhudianhua(String.valueOf(a.get("车主电话")).trim());
				}

				vehicle.setCreatetime(LocalDateTime.now());
				vehicle.setCaozuoshijian(LocalDateTime.now());
				if(user != null){
					vehicle.setCaozuoren(user.getUserName());
					vehicle.setCaozuorenid(user.getUserId());
				}else{
					vehicle.setCaozuoren(userName);
					vehicle.setCaozuorenid(userId);
				}
				vehicle.setCheliangzhuangtai("0");
				vehicles.add(vehicle);
				isDataValidity = vehicleService.insertSelective(vehicle);
			}
			if(isDataValidity == true){
				rs.setCode(200);
				rs.setMsg("数据导入成功");
				rs.setData(vehicles);
				return rs;
			}else{
				rs.setCode(500);
				rs.setMsg("数据导入失败");
				rs.setData(vehicles);
				return rs;
			}
		}else{
			for(Map<String,Object> a:readAll){
				aa++;
				Vehicle vehicle=new Vehicle();
				Dept dept;
				String deptname =  String.valueOf(a.get("所属单位")).trim();
				if(StringUtils.isBlank(deptname)){
					vehicle.setMsg("所属单位不能为空;");
					vehicle.setImportUrl("icon_cha.png");
					bb++;
				}
				dept = iSysClient.getDeptByName(deptname.trim());
				if (dept != null){
					vehicle.setDeptId(Integer.valueOf(dept.getId()));
					vehicle.setDeptName(deptname);
					vehicle.setImportUrl("icon_gou.png");

					String cheliangpaiz=String.valueOf(a.get("车辆牌照")).trim();
					if(StringUtils.isBlank(cheliangpaiz)){
						vehicle.setMsg("车辆牌照不能为空;");
						vehicle.setImportUrl("icon_cha.png");
						bb++;
					}else{
						if(isCarnumberNO(cheliangpaiz) == false){
							vehicle.setMsg("辆牌照格式不正确;");
							errorStr+=cheliangpaiz+"辆牌照格式不正确;";
							vehicle.setImportUrl("icon_cha.png");
							bb++;
						}else{
							vehicle.setImportUrl("icon_gou.png");
						}
					}
					String chepaiyanse=String.valueOf(a.get("车牌颜色")).trim();
					if(StringUtils.isBlank(chepaiyanse)){
						vehicle.setMsg("车牌颜色不能为空;");
						errorStr+="车牌颜色不能为空;";
						vehicle.setImportUrl("icon_cha.png");
						bb++;
					}else{
						VehicleVO vehicleVO = vehicleService.selectVehicleColor(chepaiyanse);
						if (vehicleVO == null || vehicleVO.getChepaiyanse() == null) {
							vehicle.setMsg("车牌颜色输入错误;");
							errorStr+="车牌颜色输入错误;";
							vehicle.setImportUrl("icon_cha.png");
							bb++;
						}else{
							vehicle.setImportUrl("icon_gou.png");
						}
					}
					vehicle.setCheliangpaizhao(cheliangpaiz);
					vehicle.setChepaiyanse(chepaiyanse);
					VehicleVO vehicleVO = vehicleService.selectCPYS(cheliangpaiz,chepaiyanse);
					if(vehicleVO!=null){
						vehicle.setImportUrl("icon_cha.png");
						errorStr+=vehicleVO.getCheliangpaizhao()+"该车已存在;";
						vehicle.setMsg(vehicleVO.getCheliangpaizhao()+"该车已存在;");
						bb++;
					}else{
						vehicle.setImportUrl("icon_gou.png");
					}
					for(Vehicle item : vehicles){
						if(item.getCheliangpaizhao().equals(cheliangpaiz) && item.getChepaiyanse().equals(chepaiyanse)){
							vehicle.setImportUrl("icon_cha.png");
							errorStr+=cheliangpaiz+"车牌号重复；";
							vehicle.setMsg(cheliangpaiz+"车牌号重复；");
							bb++;
						}
					}
//					if(bb>0){
//						vehicle.setImportUrl("icon_cha.png");
//						errorStr+=cheliangpaiz+"车牌号重复；";
//						vehicle.setMsg(cheliangpaiz+"车牌号重复；");
//						bb++;
//					}

					vehicle.setShiyongxingzhi(String.valueOf(a.get("使用性质")).trim());
					vehicle.setXinghao(String.valueOf(a.get("车辆类型")).trim());
					vehicle.setChangpai(String.valueOf(a.get("厂牌")).trim());
					vehicle.setChejiahao(String.valueOf(a.get("车架号")).trim());
					vehicle.setYunyingshangmingcheng(String.valueOf(a.get("运营商名称")).trim());
					String yys = StringEscapeUtils.unescapeHtml(StringEscapeUtils.unescapeHtml(String.valueOf(a.get("4G视频地址")).trim()));
					vehicle.setYunyingshang(yys);
					vehicle.setChezhu(String.valueOf(a.get("车主")).trim());

					String zongduanid = String.valueOf(a.get("终端编号")).trim();
					if(zongduanid.length() != 12){
						vehicle.setImportUrl("icon_cha.png");
						errorStr += zongduanid + "该终端号不是12位;";
						vehicle.setZongduanid(zongduanid);
						vehicle.setMsg(zongduanid + "该终端号不是12位;");
						bb++;
					}else {
						VehicleVO vehicleVO1 = vehicleService.selectByZongDuan(zongduanid);
						if (vehicleVO1 != null) {
							vehicle.setImportUrl("icon_cha.png");
							errorStr += zongduanid + "该终端ID已存在;";
							vehicle.setZongduanid(zongduanid);
							vehicle.setMsg(zongduanid + "该终端ID已存在;");
							bb++;
						} else {
							vehicle.setImportUrl("icon_gou.png");
							vehicle.setZongduanid(zongduanid);
						}
					}

					if(StringUtils.isBlank(String.valueOf(a.get("驾驶员"))) || String.valueOf(a.get("驾驶员")).equals("null")){
						vehicle.setJiashiyuanxingming("");
					}else{
						vehicle.setJiashiyuanxingming(String.valueOf(a.get("驾驶员")).trim());
					}

					if(StringUtils.isBlank(String.valueOf(a.get("押运员"))) || String.valueOf(a.get("押运员")).equals("null")){
						vehicle.setYayunyuanxingming("");
					}else{
						vehicle.setYayunyuanxingming(String.valueOf(a.get("押运员")).trim());
					}

					if(StringUtils.isBlank(String.valueOf(a.get("车主"))) || String.valueOf(a.get("车主")).equals("null")){
						vehicle.setChezhu("");
					}else{
						vehicle.setChezhu(String.valueOf(a.get("车主")).trim());
					}

					String phone = String.valueOf(a.get("驾驶员电话"));
					if(!StringUtils.isBlank(phone) && !phone.equals("null")){
						if(CheckPhoneUtil.isPhoneOrTel(phone) == false){
							vehicle.setMsg("驾驶员电话格式不正确;");
							errorStr+=phone+"驾驶员电话格式不正确;";
							vehicle.setImportUrl("icon_cha.png");
							vehicle.setJiashiyuandianhua(String.valueOf(a.get("驾驶员电话")).trim());
							bb++;
						}else{
							vehicle.setImportUrl("icon_gou.png");
							vehicle.setJiashiyuandianhua(String.valueOf(a.get("驾驶员电话")).trim());
						}
					}else{
						vehicle.setJiashiyuandianhua(String.valueOf(a.get("驾驶员电话")).trim());
					}

					String yyphone = String.valueOf(a.get("押运员电话"));
					if(!StringUtils.isBlank(yyphone) && !yyphone.equals("null")){
						if(CheckPhoneUtil.isPhoneOrTel(yyphone) == false){
							vehicle.setMsg("押运员电话格式不正确;");
							errorStr+=yyphone+"押运员电话格式不正确;";
							vehicle.setImportUrl("icon_cha.png");
							vehicle.setYayunyuandianhua(String.valueOf(a.get("押运员电话")).trim());
							bb++;
						}else{
							vehicle.setImportUrl("icon_gou.png");
							vehicle.setYayunyuandianhua(String.valueOf(a.get("押运员电话")).trim());
						}
					}else{
						vehicle.setYayunyuandianhua(String.valueOf(a.get("押运员电话")).trim());
					}

					String czphone = String.valueOf(a.get("车主电话"));
					if(!StringUtils.isBlank(czphone) && !czphone.equals("null")){
						if(CheckPhoneUtil.isPhoneOrTel(czphone) == false){
							vehicle.setMsg("车主电话格式不正确;");
							errorStr+=czphone+"车主电话格式不正确;";
							vehicle.setImportUrl("icon_cha.png");
							vehicle.setChezhudianhua(String.valueOf(a.get("车主电话")).trim());
							bb++;
						}else{
							vehicle.setImportUrl("icon_gou.png");
							vehicle.setChezhudianhua(String.valueOf(a.get("车主电话")).trim());
						}
					}else{
						vehicle.setChezhudianhua(String.valueOf(a.get("车主电话")).trim());
					}

					vehicles.add(vehicle);
				}else{
					String cph = String.valueOf(a.get("车辆牌照")).trim();
					vehicle.setMsg(deptname+"机构不存在;");
					vehicle.setImportUrl("icon_cha.png");
					errorStr+=cph+"----"+deptname+"机构不存在;";
					bb++;
				}
			}
			if(bb>0){
				rs.setMsg(errorStr);
				rs.setCode(500);
				rs.setSuccess(false);
				rs.setData(vehicles);
				return rs;
			}else{
				rs.setCode(200);
				rs.setMsg("数据验证成功");
				rs.setData(vehicles);
				rs.setSuccess(true);
				return rs;
			}
		}
	}

	/**
	 * 车辆信息--确认导入
	 * @author: elvis
	 * @date: 2020/10/19 10:23
	 * @param
	 * @return: org.springblade.core.tool.api.R
	 */
	@PostMapping("vehicleImportOk")
	@ApiLog("车辆信息-确认导入")
	@ApiOperation(value = "车辆信息-确认导入", notes = "vehicles", position = 10)
	public R vehicleImportOk(@RequestParam(value = "vehicles") String vehicles,BladeUser user,@RequestParam Integer userId,@RequestParam String userName){
		System.out.println("vehicles:"+vehicles);
		JSONArray json = JSONUtil.parseArray(vehicles);
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

		List<Vehicle> vehlist=new ArrayList<Vehicle>();
		for(Map<String,Object> a:lists){
			aa++;
			Vehicle vehicle=new Vehicle();
			Dept dept;
			String id=IdUtil.simpleUUID();
			vehicle.setId(id);
			vehicle.setDeptId(Integer.parseInt(String.valueOf(a.get("deptId")).trim()));
			vehicle.setDeptName(String.valueOf(a.get("deptName")).trim());
			vehicle.setCheliangpaizhao(String.valueOf(a.get("cheliangpaizhao")).trim());
			vehicle.setChepaiyanse(String.valueOf(a.get("chepaiyanse")).trim());
			vehicle.setShiyongxingzhi(String.valueOf(a.get("shiyongxingzhi")).trim());
			if(StringUtils.isNotBlank(String.valueOf(a.get("xinghao")).trim())){
				vehicle.setXinghao(String.valueOf(a.get("xinghao")).trim());
			}
			if(StringUtils.isNotBlank(String.valueOf(a.get("changpai")).trim())){
				vehicle.setChangpai(String.valueOf(a.get("changpai")).trim());
			}
			if(StringUtils.isNotBlank(String.valueOf(a.get("chejiahao")).trim())){
				vehicle.setChejiahao(String.valueOf(a.get("chejiahao")).trim());
			}
			String yys = StringEscapeUtils.unescapeHtml(StringEscapeUtils.unescapeHtml(String.valueOf(a.get("yunyingshang")).trim()));
			if(StringUtils.isNotBlank(yys)){
				vehicle.setYunyingshang(yys);
			}
			if(StringUtils.isNotBlank(String.valueOf(a.get("yunyingshangmingcheng")).trim())){
				vehicle.setYunyingshangmingcheng(String.valueOf(a.get("yunyingshangmingcheng")).trim());
			}
			if(StringUtils.isNotBlank(String.valueOf(a.get("zongduanid")).trim())){
				vehicle.setZongduanid(String.valueOf(a.get("zongduanid")).trim());
			}
			if(StringUtils.isNotBlank(String.valueOf(a.get("jiashiyuanxingming")).trim())){
				vehicle.setJiashiyuanxingming(String.valueOf(a.get("jiashiyuanxingming")).trim());
			}
			if(StringUtils.isNotBlank(String.valueOf(a.get("jiashiyuandianhua")).trim())){
				vehicle.setJiashiyuandianhua(String.valueOf(a.get("jiashiyuandianhua")).trim());
			}
			if(StringUtils.isNotBlank(String.valueOf(a.get("yayunyuanxingming")).trim())){
				vehicle.setYayunyuanxingming(String.valueOf(a.get("yayunyuanxingming")).trim());
			}
			if(StringUtils.isNotBlank(String.valueOf(a.get("yayunyuandianhua")).trim())) {
				vehicle.setYayunyuandianhua(String.valueOf(a.get("yayunyuandianhua")).trim());
			}
			if(StringUtils.isNotBlank(String.valueOf(a.get("chezhu")).trim())) {
				vehicle.setChezhu(String.valueOf(a.get("chezhu")).trim());
			}
			if(StringUtils.isNotBlank(String.valueOf(a.get("chezhudianhua")).trim())) {
				vehicle.setChezhudianhua(String.valueOf(a.get("chezhudianhua")).trim());
			}
			vehicle.setCreatetime(LocalDateTime.now());
			vehicle.setCaozuoshijian(LocalDateTime.now());
			if(user != null){
				vehicle.setCaozuoren(user.getUserName());
				vehicle.setCaozuorenid(user.getUserId());
			}else{
				vehicle.setCaozuoren(userName);
				vehicle.setCaozuorenid(userId);
			}
			vehicle.setCheliangzhuangtai("0");
			vehicle.setIsdel(0);
			vehlist.add(vehicle);
			isDataValidity = vehicleService.insertSelective(vehicle);
			if(StringUtils.isNotBlank(vehicle.getJiashiyuanxingming()) && StringUtils.isNotBlank(vehicle.getJiashiyuandianhua())){
				JiaShiYuan driver=new JiaShiYuan();
				SimpleDateFormat dateFormat2=new SimpleDateFormat("yyyy-MM-dd");
				String ids = IdUtil.simpleUUID();
				driver.setId(ids);
				driver.setDeptId(vehicle.getDeptId());
				driver.setJiashiyuanxingming(vehicle.getJiashiyuanxingming());
				driver.setShoujihaoma(vehicle.getJiashiyuandianhua());
				driver.setJiashiyuanleixing("驾驶员");
				driver.setCongyerenyuanleixing("驾驶员");
				driver.setDenglumima(DigestUtil.encrypt("123456"));
				driver.setIsdelete(0);
				driver.setCreatetime(DateUtil.now());
				driver.setCaozuoshijian(DateUtil.now());
				if(user!=null){
					driver.setCaozuoren(user.getUserName());
					driver.setCaozuorenid(user.getUserId());
				}
				boolean jsy = iJiaShiYuanService.insertSelective(driver);
				//向车辆驾驶员绑定关系表中添加记录
				CheliangJiashiyuan cheliangJiashiyuan = vehicleService.getVehidAndJiashiyuanId(vehicle.getId(),driver.getId());
				if(cheliangJiashiyuan == null){
					CheliangJiashiyuan cheliangJiashiyuan1 = new CheliangJiashiyuan();
					cheliangJiashiyuan1.setDeptId(vehicle.getDeptId());
					cheliangJiashiyuan1.setCreatetime(DateUtil.now());
					cheliangJiashiyuan1.setJiashiyuanid(driver.getId());
					cheliangJiashiyuan1.setVehid(vehicle.getId());
					cheliangJiashiyuan1.setType("1");
					boolean vaj = vehicleService.insertVehidAndJiashiyuan(cheliangJiashiyuan1);
				}
			}
			if(StringUtils.isNotBlank(vehicle.getYayunyuanxingming()) && StringUtils.isNotBlank(vehicle.getYayunyuandianhua())){
				JiaShiYuan driver=new JiaShiYuan();
				SimpleDateFormat dateFormat2=new SimpleDateFormat("yyyy-MM-dd");
				String ids = IdUtil.simpleUUID();
				driver.setId(ids);
				driver.setDeptId(vehicle.getDeptId());
				driver.setJiashiyuanxingming(vehicle.getYayunyuanxingming());
				driver.setShoujihaoma(vehicle.getYayunyuandianhua());
				driver.setJiashiyuanleixing("押运员");
				driver.setCongyerenyuanleixing("押运员");
				driver.setDenglumima(DigestUtil.encrypt("123456"));
				driver.setIsdelete(0);
				driver.setCreatetime(DateUtil.now());
				driver.setCaozuoshijian(DateUtil.now());
				if(user!=null){
					driver.setCaozuoren(user.getUserName());
					driver.setCaozuorenid(user.getUserId());
				}
				boolean yyy = iJiaShiYuanService.insertSelective(driver);
			}
		}
		if(isDataValidity == true){
			rs.setCode(200);
			rs.setMsg("数据导入成功");
			rs.setData(vehicles);
			return rs;
		}else{
			rs.setCode(500);
			rs.setMsg("数据导入失败");
			rs.setData(vehicles);
			return rs;
		}
	}

	@PostMapping("vehiceCount")
	@ApiLog("车辆统计")
	@ApiOperation(value = "车辆统计", notes = "传入deptId", position = 10)
	public  R vehiceCount(@ApiParam(value = "企业id", required = true) @RequestParam Integer deptId,@ApiParam(value = "企业名称", required = true) @RequestParam String company){
		List<VehiclePT> vehiclePT2 = iGpsPointDataClient.getVehiclePT2(company);
		int count = vehicleService.vehicleDayCount(deptId); //查询车辆总数
		VehicleCount vehicleCount=new VehicleCount();
		if(vehiclePT2==null){
					vehicleCount.setVehicleCount(count);
				return R.data(vehicleCount);
		}
		int zaiixan=0;//在线车辆
		int lixian=0;//离线车辆
		int xianzhi=0;//闲置车辆
		for(VehiclePT item:vehiclePT2){
			if("运行".equals(item.getStatus()) || "停车".equals(item.getStatus())){
				zaiixan++;
			}
			if("离线".equals(item.getStatus())){
				lixian++;
			}
			if("闲置".equals(item.getStatus())){
				xianzhi++;
			}

		}
		//List<String> xianzhilist = vehicleService.xianzhiVecleCount(deptId); //查询闲置总数

		vehicleCount.setVehicleCount(count); //设置企业车辆总数
		vehicleCount.setXianzhiVehicleCount(xianzhi);//设置闲置车子总数
		vehicleCount.setYunxingVehicleCount(zaiixan);//设置运行车辆总数
		vehicleCount.setLixianVehicleCount(lixian);//设置离线车辆总数
		vehicleCount.setWeixiuVehicleCount(0);//维修车辆无法判断默认为0
		return R.data(vehicleCount);

	}
	@PostMapping("vehicledetai")
	@ApiLog("车辆详情-车牌-车牌颜色")
	@ApiOperation(value = "车辆详情-车牌-颜色", notes = "传入车牌 车牌颜色", position = 11)
	public  R vehicledetai(@ApiParam(value = "车牌", required = true) @RequestParam String cheliangpaizhao,
						   @ApiParam(value = "车牌颜色", required = true) @RequestParam String chepaiyanse,
						   @ApiParam(value = "deptId", required = true) @RequestParam Integer deptId){
		Vehicle vehicle = vehicleService.vehileOne(cheliangpaizhao, chepaiyanse,deptId);
		return R.data(vehicle);
	}
	@PostMapping("/GPSVehicledetail")
	@ApiLog("获取车辆详情gps信息")
	@ApiOperation(value = "获取gps车辆详情", notes = "获取车辆gps详情", position = 2)
	public R getVehicledetail(@ApiParam(value = "单位名称", required = true) @RequestParam String plateNumber
		,@ApiParam(value = "单位名称", required = true) @RequestParam String company){
		List<VehiclePT> vehiclePT2 = iGpsPointDataClient.getVehiclePT2(company);
		GpsVehicleDetail gps=new GpsVehicleDetail();
		for(VehiclePT index:vehiclePT2){
			if(plateNumber.equals(index.getCph())){
				gps.setVehiclestatus(index.getStatus());
				gps.setLongitude(index.getLongitude());
				gps.setGpstime(index.getGpstime());
				gps.setLatitude(index.getLatitude());
				break;
			}
		}
		return  R.data(gps);
	}

	@GetMapping("/getVehicleAll")
	@ApiLog("GPS获取车辆资料")
	@ApiOperation(value = "GPS获取车辆资料", notes = "传入caozuoshijian", position = 2)
	public R<List<VehicleVO>> getVehicleAll(String caozuoshijian) {
		List<VehicleVO> detail = vehicleService.selectVehicleAll(caozuoshijian);
		return R.data(detail);
	}

	@PostMapping("/updateDeptId")
	@ApiLog("车辆资料管理-车辆异动")
	@ApiOperation(value = "车辆资料管理-车辆异动", notes = "传入车辆ID,企业ID", position = 10)
	public R updateDeptId(@RequestParam String id,@RequestParam String deptId) {
		R r = new R();
		String[] idsss = id.split(",");
		//去除素组中重复的数组
		List<String> listid = new ArrayList<String>();
		for (int i=0; i<idsss.length; i++) {
			if(!listid.contains(idsss[i])) {
				listid.add(idsss[i]);
			}
		}
		//返回一个包含所有对象的指定类型的数组
		String[]  idss= listid.toArray(new String[1]);
		for(int i = 0;i< idss.length;i++){
			boolean ss = vehicleService.updateDeptId(deptId,idsss[i]);
			if (ss){
				r.setMsg("更新成功");
				r.setCode(200);
				r.setData(ss);
			}else{
				r.setMsg("更新失败");
				r.setCode(500);
				r.setData(null);
			}
		}
		return r;
	}

	@PostMapping("/selectGDSVehicleList")
	@ApiLog("分页-各地市车辆变更统计列表")
	@ApiOperation(value = "分页-各地市车辆变更统计列表", notes = "传入vehiclepage", position = 11)
	public R<VehiclePage<VehicleGDSTJ>> selectGDSVehicleList(@RequestBody VehiclePage vehiclepage) {
		VehiclePage<VehicleGDSTJ> pages = vehicleService.selectGDSVehiclePage(vehiclepage);
		return R.data(pages);
	}

	@PostMapping("/selectGDSMXVehicleList")
	@ApiLog("分页-各地市车辆变更明细统计列表")
	@ApiOperation(value = "分页-各地市车辆变更明细统计列表", notes = "传入vehiclepage", position = 12)
	public R<VehiclePage<VehicleGDSTJ>> selectGDSMXVehicleList(@RequestBody VehiclePage vehiclepage) {
		VehiclePage<VehicleGDSTJ> pages = vehicleService.selectGDSMXVehiclePage(vehiclepage);
		return R.data(pages);
	}

	@GetMapping("/getByIdVehicleList")
	@ApiLog("根据企业ID获取相应车辆信息")
	@ApiOperation(value = "根据企业ID获取相应车辆信息", notes = "传入deptId", position = 2)
	public R<List<Vehicle>> getByIdVehicleList(Integer deptId) {
		List<Vehicle> detail = vehicleService.vehileList(deptId);
		return R.data(detail);
	}

	@PostMapping("/updateVehicleZongDuanId")
	@ApiLog("车辆资料管理-编辑车辆终端编号")
	@ApiOperation(value = "车辆资料管理-编辑车辆终端编号", notes = "传入车辆id,新终端ID", position = 19)
	public R updateVehicleZongDuanId(@RequestParam String id,String newzongduanid,String username,String userid) {
		R r = new R();
		VehicleVO vehicleVO1 = vehicleService.selectByZongDuan(newzongduanid);
		if(vehicleVO1 !=null ){
			r.setMsg(vehicleVO1.getZongduanid()+"该终端ID已存在");
			r.setCode(500);
			return r;
		}
		boolean ss = vehicleService.updateVehicleZongDuanId(newzongduanid,username,userid,id);
		if (ss){
			r.setMsg("编辑成功");
			r.setCode(200);
			r.setSuccess(true);
		}else{
			r.setMsg("编辑失败");
			r.setCode(500);
			r.setSuccess(false);
		}
		return r;
	}

	/**
	 * 更新车辆信息--验证
	 * @author: elvis
	 * @date: 2020/06/19 10:23
	 * @update: 黄亚平 更新车辆信息
	 * @param
	 */
	@PostMapping("vehicleUpdateImport")
	@ApiLog("更新车辆信息-验证")
	@ApiOperation(value = "更新车辆信息-验证", notes = "file", position = 20)
	public R vehicleUpdateImport(@RequestParam(value = "file") MultipartFile file){

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

		List<Vehicle> vehicles=new ArrayList<Vehicle>();
		for(Map<String,Object> a:readAll){
			aa++;
			Vehicle vehicle=new Vehicle();
			Dept dept;
			String deptname =  String.valueOf(a.get("所属单位")).trim();
			if(StringUtils.isBlank(deptname)){
				vehicle.setMsg("所属单位不能为空;");
				vehicle.setImportUrl("icon_cha.png");
				bb++;
			}
			dept = iSysClient.getDeptByName(deptname.trim());
			if (dept != null){
				vehicle.setDeptId(Integer.valueOf(dept.getId()));
				vehicle.setDeptName(deptname);
				vehicle.setImportUrl("icon_gou.png");

				String cheliangpaiz=String.valueOf(a.get("车辆牌照")).trim();
				cheliangpaiz = RegexUtils.replaceBlank(cheliangpaiz);
				if(StringUtils.isBlank(cheliangpaiz)){
					vehicle.setMsg("车辆牌照不能为空;");
					vehicle.setImportUrl("icon_cha.png");
					bb++;
				}else{
					if(isCarnumberNO(cheliangpaiz) == false){
						vehicle.setMsg("辆牌照格式不正确;");
						errorStr+=cheliangpaiz+"辆牌照格式不正确;";
						vehicle.setImportUrl("icon_cha.png");
						bb++;
					}else{
						vehicle.setImportUrl("icon_gou.png");
					}
				}
				String chepaiyanse=String.valueOf(a.get("车牌颜色")).trim();
				if(StringUtils.isBlank(chepaiyanse)){
					vehicle.setMsg("车牌颜色不能为空;");
					errorStr+="车牌颜色不能为空;";
					vehicle.setImportUrl("icon_cha.png");
					bb++;
				}else{
					VehicleVO vehicleVO = vehicleService.selectVehicleColor(chepaiyanse);
					if (vehicleVO == null || vehicleVO.getChepaiyanse() == null) {
						vehicle.setMsg("车牌颜色输入错误;");
						errorStr+="车牌颜色输入错误;";
						vehicle.setImportUrl("icon_cha.png");
						bb++;
					}else{
						vehicle.setImportUrl("icon_gou.png");
					}
				}
				vehicle.setCheliangpaizhao(cheliangpaiz);
				vehicle.setChepaiyanse(chepaiyanse);
				VehicleVO vehicleVO = vehicleService.selectCPYS(cheliangpaiz,chepaiyanse);
				if(vehicleVO == null ){
					vehicle.setImportUrl("icon_cha.png");
					errorStr+=cheliangpaiz+"该车不存在;";
					vehicle.setMsg(cheliangpaiz+"该车不存在;");
					bb++;
				}else{
					vehicle.setImportUrl("icon_gou.png");
				}
				for(Vehicle item : vehicles){
					if(item.getCheliangpaizhao().equals(cheliangpaiz) && item.getChepaiyanse().equals(chepaiyanse)){
						vehicle.setImportUrl("icon_cha.png");
						errorStr+=cheliangpaiz+"车牌号重复；";
						vehicle.setMsg(cheliangpaiz+"车牌号重复；");
						bb++;
					}
				}
				vehicle.setShiyongxingzhi(String.valueOf(a.get("使用性质")).trim());
				vehicle.setXinghao(String.valueOf(a.get("车辆类型")).trim());
				vehicle.setChangpai(String.valueOf(a.get("厂牌")).trim());
				vehicle.setChejiahao(String.valueOf(a.get("车架号")).trim());
				vehicle.setYunyingshangmingcheng(String.valueOf(a.get("运营商名称")).trim());
				String yys = StringEscapeUtils.unescapeHtml(StringEscapeUtils.unescapeHtml(String.valueOf(a.get("4G视频地址")).trim()));
				vehicle.setYunyingshang(yys);
				vehicle.setChezhu(String.valueOf(a.get("车主")).trim());
				String zongduanid = String.valueOf(a.get("终端编号")).trim();
				VehicleVO vehicleVO1 = vehicleService.selectByZongDuan(zongduanid);
				if(StringUtils.isNotBlank(vehicleVO1.getZongduanid())){
					vehicle.setImportUrl("icon_cha.png");
					errorStr+=zongduanid+"该终端ID已存在;";
					vehicle.setZongduanid(zongduanid);
					vehicle.setMsg(zongduanid+"该终端ID已存在;");
					bb++;
				}else{
					vehicle.setImportUrl("icon_gou.png");
					vehicle.setZongduanid(zongduanid);
				}

				if(StringUtils.isBlank(String.valueOf(a.get("驾驶员")))){
					vehicle.setJiashiyuanxingming("");
				}else{
					vehicle.setJiashiyuanxingming(String.valueOf(a.get("驾驶员")).trim());
				}

				if(StringUtils.isBlank(String.valueOf(a.get("押运员")))){
					vehicle.setYayunyuanxingming("");
				}else{
					vehicle.setYayunyuanxingming(String.valueOf(a.get("押运员")).trim());
				}

				if(StringUtils.isBlank(String.valueOf(a.get("车主")))){
					vehicle.setChezhu("");
				}else{
					vehicle.setChezhu(String.valueOf(a.get("车主")).trim());
				}

				String phone = String.valueOf(a.get("驾驶员电话"));
				if(!StringUtils.isBlank(phone) && !phone.equals("null")){
					if(CheckPhoneUtil.isPhoneOrTel(phone) == false){
						vehicle.setMsg("驾驶员电话格式不正确;");
						errorStr+=phone+"驾驶员电话格式不正确;";
						vehicle.setImportUrl("icon_cha.png");
						vehicle.setJiashiyuandianhua(String.valueOf(a.get("驾驶员电话")).trim());
						bb++;
					}else{
						vehicle.setImportUrl("icon_gou.png");
						vehicle.setJiashiyuandianhua(String.valueOf(a.get("驾驶员电话")).trim());
					}
				}else{
					vehicle.setJiashiyuandianhua(String.valueOf(a.get("驾驶员电话")).trim());
				}

				String yyphone = String.valueOf(a.get("押运员电话"));
				if(!StringUtils.isBlank(yyphone) && !yyphone.equals("null")){
					if(CheckPhoneUtil.isPhoneOrTel(yyphone) == false){
						vehicle.setMsg("押运员电话格式不正确;");
						errorStr+=yyphone+"押运员电话格式不正确;";
						vehicle.setImportUrl("icon_cha.png");
						vehicle.setYayunyuandianhua(String.valueOf(a.get("押运员电话")).trim());
						bb++;
					}else{
						vehicle.setImportUrl("icon_gou.png");
						vehicle.setYayunyuandianhua(String.valueOf(a.get("押运员电话")).trim());
					}
				}else{
					vehicle.setYayunyuandianhua(String.valueOf(a.get("押运员电话")).trim());
				}

				String czphone = String.valueOf(a.get("车主电话"));
				if(!StringUtils.isBlank(czphone) && !czphone.equals("null")){
					if(CheckPhoneUtil.isPhoneOrTel(czphone) == false){
						vehicle.setMsg("车主电话格式不正确;");
						errorStr+=czphone+"车主电话格式不正确;";
						vehicle.setImportUrl("icon_cha.png");
						vehicle.setChezhudianhua(String.valueOf(a.get("车主电话")).trim());
						bb++;
					}else{
						vehicle.setImportUrl("icon_gou.png");
						vehicle.setChezhudianhua(String.valueOf(a.get("车主电话")).trim());
					}
				}else{
					vehicle.setChezhudianhua(String.valueOf(a.get("车主电话")).trim());
				}
			}else{
				vehicle.setMsg(deptname+"机构不存在;");
				errorStr+=deptname+"机构不存在;";
				vehicle.setImportUrl("icon_cha.png");
				bb++;
			}
			vehicles.add(vehicle);
		}
		if(bb>0){
			rs.setMsg(errorStr);
			rs.setCode(500);
			rs.setSuccess(false);
			rs.setData(vehicles);
			return rs;
		}else{
			rs.setCode(200);
			rs.setMsg("数据验证成功");
			rs.setData(vehicles);
			rs.setSuccess(true);
			return rs;
		}
	}

	/**
	 * 更新车辆信息--确认导入
	 * @author: hyp
	 * @date: 2021/04/09 21:23
	 */
	@PostMapping("vehicleUpdateImportOk")
	@ApiLog("更新车辆信息-确认导入")
	@ApiOperation(value = "更新车辆信息-确认导入", notes = "vehicles", position = 21)
	public R vehicleUpdateImportOk(@RequestParam(value = "vehicles") String vehicles,BladeUser user,@RequestParam Integer userId,@RequestParam String userName){
		System.out.println("vehicles:"+vehicles);
		JSONArray json = JSONUtil.parseArray(vehicles);
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

		List<Vehicle> vehlist=new ArrayList<Vehicle>();
		for(Map<String,Object> a:lists){
			aa++;
			Vehicle vehicle=new Vehicle();
			Dept dept;
			String id=IdUtil.simpleUUID();
			vehicle.setId(id);
			String deptname =  String.valueOf(a.get("所属单位"));
			dept = iSysClient.getDeptByName(deptname);
			vehicle.setDeptId(Integer.valueOf(dept.getId()));
			vehicle.setCheliangpaizhao(String.valueOf(a.get("车辆牌照")));
			vehicle.setChepaiyanse(String.valueOf(a.get("车牌颜色")));
			if(StringUtils.isBlank((String) a.get("使用性质"))){
				vehicle.setShiyongxingzhi("");
			}else{
				vehicle.setShiyongxingzhi(String.valueOf(a.get("使用性质")).trim());
			}

			if(StringUtils.isBlank(String.valueOf(a.get("车辆类型")))){
				vehicle.setXinghao("");
			}else{
				vehicle.setXinghao(String.valueOf(a.get("车辆类型")).trim());
			}

			if(StringUtils.isBlank(String.valueOf(a.get("厂牌")))){
				vehicle.setChangpai("");
			}else{
				vehicle.setChangpai(String.valueOf(a.get("厂牌")).trim());
			}

			if(StringUtils.isBlank(String.valueOf(a.get("车架号")))){
				vehicle.setChejiahao("");
			}else{
				vehicle.setChejiahao(String.valueOf(a.get("车架号")).trim());
			}

			if(StringUtils.isBlank(String.valueOf(a.get("4G视频地址")))){
				vehicle.setYunyingshang("");
			}else{
				String yys = StringEscapeUtils.unescapeHtml(StringEscapeUtils.unescapeHtml(String.valueOf(a.get("4G视频地址")).trim()));
				vehicle.setYunyingshang(yys);
			}

			if(StringUtils.isBlank(String.valueOf(a.get("运营商名称")).trim())){
				vehicle.setYunyingshangmingcheng("");
			}else{
				vehicle.setYunyingshangmingcheng(String.valueOf(a.get("运营商名称")).trim());
			}

			if(StringUtils.isBlank(String.valueOf(a.get("终端编号")))){
				vehicle.setZongduanid("");
			}else{
				vehicle.setZongduanid(String.valueOf(a.get("终端编号")).trim());
			}
			if(StringUtils.isBlank(String.valueOf(a.get("驾驶员")))){
				vehicle.setJiashiyuanxingming("");
			}else{
				vehicle.setJiashiyuanxingming(String.valueOf(a.get("驾驶员")).trim());
			}
			if(StringUtils.isNotBlank(String.valueOf(a.get("驾驶员电话")))){
				vehicle.setJiashiyuandianhua(String.valueOf(a.get("驾驶员电话")).trim());
			}
			if(StringUtils.isBlank(String.valueOf(a.get("押运员")))){
				vehicle.setYayunyuanxingming("");
			}else{
				vehicle.setYayunyuanxingming(String.valueOf(a.get("押运员")).trim());
			}
			if(StringUtils.isNotBlank(String.valueOf(a.get("押运员电话")))){
				vehicle.setYayunyuandianhua(String.valueOf(a.get("押运员电话")).trim());
			}
			if(StringUtils.isBlank(String.valueOf(a.get("车主")))){
				vehicle.setChezhu("");
			}else{
				vehicle.setChezhu(String.valueOf(a.get("车主")).trim());
			}
			if(StringUtils.isNotBlank(String.valueOf(a.get("车主电话")))){
				vehicle.setChezhudianhua(String.valueOf(a.get("车主电话")).trim());
			}
			vehicle.setCreatetime(LocalDateTime.now());
			vehicle.setCaozuoshijian(LocalDateTime.now());
			if(user != null){
				vehicle.setCaozuoren(user.getUserName());
				vehicle.setCaozuorenid(user.getUserId());
			}else{
				vehicle.setCaozuoren(userName);
				vehicle.setCaozuorenid(userId);
			}
			vehicle.setCheliangzhuangtai("0");
			vehlist.add(vehicle);
			isDataValidity = vehicleService.updateSelective(vehicle);
			//判断导入资料是否包含驾驶员姓名和电话
			if(StringUtils.isNotBlank(vehicle.getJiashiyuanxingming()) && StringUtils.isNotBlank(vehicle.getJiashiyuandianhua())){
				//根据企业ID、驾驶员姓名、驾驶员电话查询驾驶员信息是否存在
				JiaShiYuan jiaShiYuan = iJiaShiYuanService.getjiaShiYuan(vehicle.getDeptId().toString(),vehicle.getJiashiyuanxingming(),vehicle.getJiashiyuandianhua());
				if(jiaShiYuan == null){
					//若该驾驶员信息不存在，则新增；
					JiaShiYuan driver=new JiaShiYuan();
					SimpleDateFormat dateFormat2=new SimpleDateFormat("yyyy-MM-dd");
					String ids = IdUtil.simpleUUID();
					driver.setId(ids);
					driver.setDeptId(vehicle.getDeptId());
					driver.setJiashiyuanxingming(vehicle.getJiashiyuanxingming());
					driver.setShoujihaoma(vehicle.getJiashiyuandianhua());
					driver.setJiashiyuanleixing("驾驶员");
					driver.setCongyerenyuanleixing("驾驶员");
					driver.setDenglumima(DigestUtil.encrypt("123456"));
					driver.setIsdelete(0);
					driver.setCreatetime(DateUtil.now());
					driver.setCaozuoshijian(DateUtil.now());
					if(user!=null){
						driver.setCaozuoren(user.getUserName());
						driver.setCaozuorenid(user.getUserId());
					}
					boolean jsy = iJiaShiYuanService.insertSelective(driver);
					//向车辆驾驶员绑定关系表中添加记录
					CheliangJiashiyuan cheliangJiashiyuan = vehicleService.getVehidAndJiashiyuanId(vehicle.getId(),driver.getId());
					if(cheliangJiashiyuan == null){
						CheliangJiashiyuan cheliangJiashiyuan1 = new CheliangJiashiyuan();
						cheliangJiashiyuan1.setDeptId(vehicle.getDeptId());
						cheliangJiashiyuan1.setCreatetime(DateUtil.now());
						cheliangJiashiyuan1.setJiashiyuanid(driver.getId());
						cheliangJiashiyuan1.setVehid(vehicle.getId());
						cheliangJiashiyuan1.setType("1");
						boolean vaj = vehicleService.insertVehidAndJiashiyuan(cheliangJiashiyuan1);
					}
				}else{
					Vehicle v = new Vehicle();
					v.setJiashiyuanid(jiaShiYuan.getId());
					v.setCheliangpaizhao(vehicle.getCheliangpaizhao());
					v.setChepaiyanse(vehicle.getChepaiyanse());
					v.setDeptId(vehicle.getDeptId());
					vehicleService.updateSelective(v);
				}
			}
			//判断导入资料是否包含押运员姓名和电话
			if(StringUtils.isNotBlank(vehicle.getYayunyuanxingming()) && StringUtils.isNotBlank(vehicle.getYayunyuandianhua())){
				//根据企业ID、押运员姓名、押运员电话查询押运员信息是否存在
				JiaShiYuan yaYunYuan = iJiaShiYuanService.getjiaShiYuan(vehicle.getDeptId().toString(),vehicle.getYayunyuanxingming(),vehicle.getYayunyuandianhua());
				if(yaYunYuan == null) {
					//若该押运员信息不存在，则新增；
					JiaShiYuan driver = new JiaShiYuan();
					SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
					String ids = IdUtil.simpleUUID();
					driver.setId(ids);
					driver.setDeptId(vehicle.getDeptId());
					driver.setJiashiyuanxingming(vehicle.getYayunyuanxingming());
					driver.setShoujihaoma(vehicle.getYayunyuandianhua());
					driver.setJiashiyuanleixing("押运员");
					driver.setCongyerenyuanleixing("押运员");
					driver.setDenglumima(DigestUtil.encrypt("123456"));
					driver.setIsdelete(0);
					driver.setCreatetime(DateUtil.now());
					driver.setCaozuoshijian(DateUtil.now());
					if (user != null) {
						driver.setCaozuoren(user.getUserName());
						driver.setCaozuorenid(user.getUserId());
					}
					boolean yyy = iJiaShiYuanService.insertSelective(driver);
					//向车辆驾驶员绑定关系表中添加记录
					CheliangJiashiyuan cheliangJiashiyuan = vehicleService.getVehidAndJiashiyuanId(vehicle.getId(),driver.getId());
					if(cheliangJiashiyuan == null){
						CheliangJiashiyuan cheliangJiashiyuan1 = new CheliangJiashiyuan();
						cheliangJiashiyuan1.setDeptId(vehicle.getDeptId());
						cheliangJiashiyuan1.setCreatetime(DateUtil.now());
						cheliangJiashiyuan1.setJiashiyuanid(driver.getId());
						cheliangJiashiyuan1.setVehid(vehicle.getId());
						cheliangJiashiyuan1.setType("1");
						boolean vaj = vehicleService.insertVehidAndJiashiyuan(cheliangJiashiyuan1);
					}
				}else{
					Vehicle v = new Vehicle();
					v.setYayunyuanid(yaYunYuan.getId());
					v.setCheliangpaizhao(vehicle.getCheliangpaizhao());
					v.setChepaiyanse(vehicle.getChepaiyanse());
					v.setDeptId(vehicle.getDeptId());
					vehicleService.updateSelective(v);
				}
			}
		}
		if(isDataValidity == true){
			rs.setCode(200);
			rs.setMsg("数据更新导入成功");
			rs.setData(vehicles);
			return rs;
		}else{
			rs.setCode(500);
			rs.setMsg("数据更新导入失败");
			rs.setData(vehicles);
			return rs;
		}

	}

}
