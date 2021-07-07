package org.springblade.manage.managehome.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springblade.manage.managehome.entity.ManageHome;
import org.springblade.manage.managehome.service.ManageHomeService;
import org.springblade.manage.waybill.entity.StandardSetting;
import org.springblade.manage.waybill.page.StandardSettingPage;
import org.springblade.manage.waybill.service.IStandardSettingService;
import org.springblade.manage.waybill.vo.StandardSettingVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: SafetyStandards
 * @description: ManageHome
 * @author: 呵呵哒
 **/
@RestController
@AllArgsConstructor
@RequestMapping("/manage/manageHome")
@Api(value = "首页数据", tags = "首页数据")
public class ManageHomeController {

	private ManageHomeService manageHomeService;

	private IStandardSettingService standardSettingService;
	/**
	 * 首页数据
	 */
	@PostMapping("/managehome")
	@ApiOperation(value = "首页数据", notes = "传入deptId", position = 1)
	public R<Map<String, String>> managehome(String deptId,String yue) {
		HashMap map = new HashMap();
		//默认不传值时加载上月数据
		if(StrUtil.hasEmpty(yue)){
			yue=(DateUtil.month(DateUtil.lastMonth())+1)+"";
		}
		//首页数字数据
		ManageHome home =manageHomeService.selectHome(deptId,yue);
		StandardSettingPage page=new StandardSettingPage();
		page.setDeptId(Integer.parseInt(deptId));
		Date date = DateUtil.date();
		String year=DateUtil.year(date)+"";
		page.setYear(year);
		page.setMonth(yue);
		double nianzaixian=0.0;
		double nianwancheng=0.0;
		//年在线率 -- 折线图
		List<StandardSettingVO> zaixian=standardSettingService.selectByZaiXian(page);
		for (int i = 0; i <zaixian.size() ; i++) {
			if(Integer.parseInt(yue)<10){
				if(zaixian.get(i).getYue().equals("0"+yue)){
					//设置当月在线率
					home.setYuezaixian(zaixian.get(i).getZaixianlv());
				}
			}else{
				if(zaixian.get(i).getYue().equals(yue)){
					//设置当月在线率
					home.setYuezaixian(zaixian.get(i).getZaixianlv());
				}
			}
			nianzaixian=nianzaixian+zaixian.get(i).getZaixianlv();
		}
		//月完成率 --本月完成率
		StandardSettingVO settingVO=standardSettingService.selectByYueDaBiao(page);
		home.setYuewancheng(settingVO.getWanchenglv()*100);
		//年完成率-折线图
		List<StandardSettingVO> wancheng=standardSettingService.selectByWanCheng(page);
		for (int i = 0; i < wancheng.size(); i++) {
			nianwancheng=nianwancheng+wancheng.get(i).getWanchenglv();
		}
		//年完成 年在线
		double a=Double.valueOf(NumberUtil.roundStr(nianwancheng/12,2));
		home.setNianwancheng(Double.valueOf(NumberUtil.roundStr(a,2)));
		double b=Double.valueOf(NumberUtil.roundStr(nianzaixian/12,2));
		home.setNianzaixian(Double.valueOf(NumberUtil.roundStr(b,2)));
		map.put("home",home);
		map.put("zaixian",zaixian);
		map.put("wancheng",wancheng);
		return R.data(map);
	}
	//***************************************左侧界面*************************************************
	/**
	 * 分页-车辆运营情况
	 */
	@PostMapping("/selectZaixian")
	@ApiOperation(value = "运营管理-车辆在线情况分析", notes = "传入page", position = 1)
	public R<List<StandardSettingVO>> selectZaixian(@RequestBody StandardSettingPage page ) {
		List<StandardSettingVO> list = standardSettingService.selectByZaiXian(page);
		return R.data(list);
	}
	/**
	 * 分页
	 */
	@PostMapping("/listBenYueJianKong")
	@ApiOperation(value = "运营管理-本月监控", notes = "传入page", position = 1)
	public R<List<StandardSettingVO>> listBenYueJianKong(@RequestBody StandardSettingPage page ) {
		Date date = DateUtil.date();
		String year=DateUtil.year(date)+"";
		//获得月份，从0开始计数
		String month=(DateUtil.month(date)+1)+"";
		page.setYear(year);
		page.setMonth(month);
		List<StandardSettingVO> list = standardSettingService.selecBenYueJiankong(page);
		return R.data(list);
	}
	/**
	 * 分页
	 */
	@PostMapping("/listdefault")
	@ApiOperation(value = "设置达标--上", notes = "传入page", position = 1)
	public R<StandardSettingPage<StandardSettingVO>> listdefault(@RequestBody StandardSettingPage page ) {
		StandardSettingPage<StandardSettingVO> list = standardSettingService.selectdefaultPageList(page);
		return R.data(list);
	}
	//*******单个车辆设置达标***********
	/**
	 * 分页
	 */
	@PostMapping("/list")
	@ApiOperation(value = "设置达标--下", notes = "传入page", position = 2)
	public R<StandardSettingPage<StandardSettingVO>> list(@RequestBody StandardSettingPage page ) {
		StandardSettingPage<StandardSettingVO> list = standardSettingService.selectPageList(page);
		return R.data(list);
	}
	/**
	 * 新增-设置车辆月份达标
	 */
	@PostMapping("/insert")
	@ApiOperation(value = "新增", notes = "传入standardSetting", position = 3)
	public R<StandardSetting> save(@Valid @RequestBody StandardSetting standardSetting) {
		String[] strArr=new String[11];
		strArr[0]=standardSetting.getYi();strArr[1]=standardSetting.getEr();strArr[2]=standardSetting.getSan();strArr[3]=standardSetting.getSi();
		strArr[4]=standardSetting.getWu();strArr[5]=standardSetting.getLiu();strArr[6]=standardSetting.getQi();strArr[7]=standardSetting.getBa();
		strArr[8]=standardSetting.getJiu();strArr[9]=standardSetting.getShi();strArr[10]=standardSetting.getShiyi();strArr[11]=standardSetting.getShier();
		//判断
		String msg = null;
		if(standardSetting.getVehicleId() != null ){
			for (int i = 0; i <strArr.length ; i++) {
				if(!StrUtil.hasEmpty(strArr[i])){
						StandardSetting obj=new StandardSetting();
						obj.setVehicleId(standardSetting.getVehicleId());
						if(strArr[i].equals("yi")){
							obj.setYue("1");
						}else if(strArr[i].equals("er")){
							obj.setYue("2");
						}else if(strArr[i].equals("san")){
							obj.setYue("3");
						}else if(strArr[i].equals("si")){
							obj.setYue("4");
						}else if(strArr[i].equals("wu")){
							obj.setYue("5");
						}else if(strArr[i].equals("liu")){
							obj.setYue("6");
						}else if(strArr[i].equals("qi")){
							obj.setYue("7");
						}else if(strArr[i].equals("ba")){
							obj.setYue("8");
						}else if(strArr[i].equals("jiu")){
							obj.setYue("9");
						}else if(strArr[i].equals("shi")){
							obj.setYue("10");
						}else if(strArr[i].equals("shiyi")){
							obj.setYue("11");
						}else if(strArr[i].equals("shier")){
							obj.setYue("12");
						}
						obj.setShuju(strArr[i]);
						standardSettingService.save(obj);
					}
			}
		}else{
			msg="保存失败";
		}
		return  R.fail(msg);
	}
	/**
	 * 修改-设置车辆月份达标
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入standardSetting", position = 4)
	public R update(@Valid @RequestBody StandardSetting standardSetting) {
		String[] strArr=new String[11];
		strArr[0]=standardSetting.getYi();strArr[1]=standardSetting.getEr();strArr[2]=standardSetting.getSan();strArr[3]=standardSetting.getSi();
		strArr[4]=standardSetting.getWu();strArr[5]=standardSetting.getLiu();strArr[6]=standardSetting.getQi();strArr[7]=standardSetting.getBa();
		strArr[8]=standardSetting.getJiu();strArr[9]=standardSetting.getShi();strArr[10]=standardSetting.getShiyi();strArr[11]=standardSetting.getShier();
		//判断
		String msg = null;
		if(standardSetting.getVehicleId() != null ){
			for (int i = 0; i <strArr.length ; i++) {
				if(StrUtil.hasEmpty(strArr[i])){
					StandardSettingVO settingVO= standardSettingService.selectByPlate(standardSetting.getVehicleId(),strArr[i]);
					if(settingVO!=null){
						StandardSetting obj=new StandardSetting();
						obj.setId(settingVO.getId());
						obj.setVehicleId(settingVO.getVehicleId());
						if(strArr[i].equals("yi")){
							obj.setYue("1");
						}else if(strArr[i].equals("er")){
							obj.setYue("2");
						}else if(strArr[i].equals("san")){
							obj.setYue("3");
						}else if(strArr[i].equals("si")){
							obj.setYue("4");
						}else if(strArr[i].equals("wu")){
							obj.setYue("5");
						}else if(strArr[i].equals("liu")){
							obj.setYue("6");
						}else if(strArr[i].equals("qi")){
							obj.setYue("7");
						}else if(strArr[i].equals("ba")){
							obj.setYue("8");
						}else if(strArr[i].equals("jiu")){
							obj.setYue("9");
						}else if(strArr[i].equals("shi")){
							obj.setYue("10");
						}else if(strArr[i].equals("shiyi")){
							obj.setYue("11");
						}else if(strArr[i].equals("shier")){
							obj.setYue("12");
						}
						obj.setShuju(strArr[i]);
						standardSettingService.updateById(obj);
					}
				}
			}
		}else{
			msg="保存失败";
		}
		return  R.fail(msg);
	}

	/**
	 * 删除-设置车辆月份达标
	 */
	@PostMapping("/del")
	@ApiOperation(value = "删除", notes = "传入id", position = 5)
	public R remove(@ApiParam(value = "主键id", required = true) @RequestParam String id) {
		boolean flag=standardSettingService.removeById(id);
		return R.status(flag);
	}

	//***************************************右侧达标界面数据*************************************************
	/**
	 * 分页
	 */
	@PostMapping("/listJianKong")
	@ApiOperation(value = "运营管理-监控达标", notes = "传入page", position = 6)
	public R<StandardSettingPage<StandardSettingVO>> listJianKong(@RequestBody StandardSettingPage page ) {
		Date date = DateUtil.date();
		//获得月份，从0开始计数
		String month=(DateUtil.month(date)+1)+"";
		String yue="";
		if(StrUtil.hasEmpty(page.getMonth())){
			//默认查询上月数据
			page.setMonth(DateUtil.month(date)+"");
			if(month.equals("1")){
				//设置为去年
				yue="shier";
				page.setYear((DateUtil.year(date)-1)+"");
				page.setMonth("12");
			}else if(month.equals("2")){
				yue="yi";
			}else if(month.equals("3")){
				yue="er";
			}else if(month.equals("4")){
				yue="san";
			}else if(month.equals("5")){
				yue="si";
			}else if(month.equals("6")){
				yue="wu";
			}else if(month.equals("7")){
				yue="liu";
			}else if(month.equals("8")){
				yue="qi";
			}else if(month.equals("9")){
				yue="ba";
			}else if(month.equals("10")){
				yue="jiu";
			}else if(month.equals("11")){
				yue="shi";
			}else if(month.equals("12")){
				yue="shiyi";
			}
		}else{
			if(month.equals("1")){
				yue="yi";
			}else if(month.equals("2")){
				yue="er";
			}else if(month.equals("3")){
				yue="san";
			}else if(month.equals("4")){
				yue="si";
			}else if(month.equals("5")){
				yue="wu";
			}else if(month.equals("6")){
				yue="liu";
			}else if(month.equals("7")){
				yue="qi";
			}else if(month.equals("8")){
				yue="ba";
			}else if(month.equals("9")){
				yue="jiu";
			}else if(month.equals("10")){
				yue="shi";
			}else if(month.equals("11")){
				yue="shiyi";
			}else if(month.equals("12")){
				yue="shier";
			}
		}
		page.setYue(yue);
		StandardSettingPage<StandardSettingVO> list = standardSettingService.selecJiankong(page);
		return R.data(list);
	}
	/**
	 * 分页-车辆运营情况
	 */
	@PostMapping("/selectByYunYing")
	@ApiOperation(value = "运营管理-车辆运营情况", notes = "传入page", position = 7)
	public R<List<StandardSettingVO>> selectByYunYing(@RequestBody StandardSettingPage page ) {
		List<StandardSettingVO> list = standardSettingService.selectByYunYing(page);
		return R.data(list);
	}
}
