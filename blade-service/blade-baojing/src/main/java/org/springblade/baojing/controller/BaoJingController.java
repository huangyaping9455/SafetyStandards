
package org.springblade.baojing.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.baojing.entity.*;
import org.springblade.baojing.page.*;
import org.springblade.baojing.service.*;
import org.springblade.baojing.vo.AlarmsummaryCutofftimeVO;
import org.springblade.baojing.vo.DriverbehaviorVO;
import org.springblade.baojing.vo.NotlocatedetailVO;
import org.springblade.baojing.vo.OfflinedetailVO;
import org.springblade.common.configurationBean.AlarmServer;
import org.springblade.common.tool.CommonTool;
import org.springblade.common.tool.DateUtils;
import org.springblade.common.tool.GpsToBaiduUtil;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.system.entity.Dept;
import org.springblade.system.feign.ISysClient;
import org.springblade.upload.upload.feign.IFileUploadClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报警信息 控制器
 * @author 呵呵哒
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baojing")
@Api(value = "报警信息接口", tags = "报警信息接口")
public class BaoJingController {

    private IAlarmsummaryCutofftimeService cutofftimeService;
    private IOfflinedetailService offlinedetailService;
    private INotlocatedetailService notlocatedetailService;
    private IAlarmhandleresultService alarmhandleresultService;
    private ISysClient sysClient;
    private IDriverbehaviorService driverbehaviorService;
    private IAlarmvehdailyreportService iAlarmvehdailyreportService;
    private IVehdailyreportService iVehdailyreportService;
    private IFileUploadClient fileUploadClient;
	private AlarmServer alarmServer;

	@PostMapping("/OperatType")
	@ApiLog("类型-获取企业车辆类型")
	@ApiOperation(value = "类型-获取企业车辆类型", notes = "deptId", position = 1)
	public R OperatType(String deptId){
			return  R.data(cutofftimeService.findoperattype(deptId));
	}

    @PostMapping("/getBDSBAlarmList")
	@ApiLog("企业统计-北斗设备报警")
    @ApiOperation(value = "企业统计-北斗设备报警", notes = "传入AlarmPage", position = 2)
    public R<AlarmPage<AlarmsummaryCutofftimeVO>> getBDSBAlarmList(@RequestBody AlarmPage alarmPage) {
		if("chaoSuBiShow".equals(alarmPage.getOrderColumn())){
			alarmPage.setOrderColumn("chaoSuBi");
		}
		if("keeptimeShow".equals(alarmPage.getOrderColumn())){
			alarmPage.setOrderColumn("keepTime");
		}
        AlarmPage pages = cutofftimeService.selectAlarmPage(alarmPage);
        return R.data(pages);
    }

    @PostMapping("/getBDSBAlarmDetail")
	@ApiLog("详情-北斗设备报警")
    @ApiOperation(value = "详情-北斗设备报警", notes = "传入报警id", position = 3)
    public R<AlarmsummaryCutofftimeVO> getBDSBAlarmDetail(@RequestParam String id) {
        AlarmsummaryCutofftimeVO vo = cutofftimeService.selectAlarmDetail(id);
        if("".equals(vo.getFujian()) && vo.getFujian()!=null){
            vo.setFujian(fileUploadClient.getUrl(vo.getFujian()));
        }
        return R.data(vo);
    }

    @PostMapping("/getZDAQSBAlarmList")
	@ApiLog("企业统计-主动安全设备报警")
    @ApiOperation(value = "企业统计-主动安全设备报警", notes = "传入DriverAlarmPage", position = 4)
    public R<DriverAlarmPage<DriverbehaviorVO>> getZDAQSBAlarmList(@RequestBody DriverAlarmPage driverAlarmPage) {
		if("chaoSuBiShow".equals(driverAlarmPage.getOrderColumn())){
			driverAlarmPage.setOrderColumn("");
		}
        if (driverAlarmPage.getHedingzhuangtai() == null) {
            driverAlarmPage.setHedingzhuangtai("核定报警");
        }
        DriverAlarmPage pages = driverbehaviorService.selectAlarmPage(driverAlarmPage);
        return R.data(pages);
    }

    @PostMapping("/getZDAQSBAlarmDetail")
	@ApiLog("详情-主动安全设备报警")
    @ApiOperation(value = "详情-主动安全设备报警", notes = "传入报警id", position = 5)
    public R<DriverbehaviorVO> getZDAQSBAlarmDetail(@RequestParam String id) {
        DriverbehaviorVO vo = driverbehaviorService.selectAlarmDetail(id);
        if("".equals(vo.getFujian()) && vo.getFujian()!=null){
            vo.setFujian(fileUploadClient.getUrl(vo.getFujian()));
        }
        return R.data(vo);
    }

    @PostMapping("/buzaixianlist")
	@ApiLog("分页查询-24小时不在线")
    @ApiOperation(value = "分页查询-24小时不在线", notes = "传入offlinePage", position = 6)
    public R<OfflinePage<OfflinedetailVO>> buzaixianlist(@RequestBody OfflinePage offlinePage) {
        OfflinePage pages = offlinedetailService.selectAlarmPage(offlinePage);
        return R.data(pages);
    }

    @PostMapping("/budingweilist")
	@ApiLog("分页查询-24小时不定位")
    @ApiOperation(value = "分页查询-24小时不定位", notes = "传入notlocatePage", position = 7)
    public R<NotlocatePage<NotlocatedetailVO>> budingweilist(@RequestBody NotlocatePage notlocatePage) {
        NotlocatePage pages = notlocatedetailService.selectAlarmPage(notlocatePage);
        return R.data(pages);
    }

	/**
	 * 添加处理记录
	 */
	@PostMapping("/insertChuli")
	@ApiLog("新增-报警处理记录")
	@ApiOperation(value = "新增-报警处理记录", notes = "传入alarmResult", position = 7)
	public R insertChuli(
		@ApiParam(value = "报警id（传入多个报警ID需以英文逗号隔开）", required = true) String ids,
		@ApiParam(value = "报警类型", required = true) String alarmType,
		@ApiParam(value = "处理形式", required = true) String chulixingshi,
		@ApiParam(value = "处理描述") String chulimiaoshu,
		@ApiParam(value = "附件") String fujian,BladeUser bladeUser,String deptId) {

		Dept dept = sysClient.selectById(deptId);
		String[] idsss = ids.split(",");
		//去除素组中重复的数组
		List<String> listid = new ArrayList<String>();
		for (int i=0; i<idsss.length; i++) {
			if(!listid.contains(idsss[i])) {
				listid.add(idsss[i]);
			}
		}
		//返回一个包含所有对象的指定类型的数组
		String[]  idss= listid.toArray(new String[1]);

		int count = alarmhandleresultService.selectAlarmCountByIdsAndDetpId(idss,dept.getDeptName());
		System.out.println(count);
		if(count<idss.length){
			return R.fail("当前所属单位与报警单位不符，不能进行报警处理");
		}
		Alarmhandleresult result = new Alarmhandleresult();
		result.setIdss(idss);
		result.setBaojingleixing(alarmType);
		result.setRemark(null);
		//根据报警id 报警类型

		List<Alarmhandleresult> resultids = alarmhandleresultService.selectIdList(result);
		int code = 200;
		String msg = "";
		Integer delnum = 0;
		boolean addnum = false;
		Integer countnum = 0;
		for (int i = 0; i < idss.length; i++) {
			List<Alarmhandleresult> list = new ArrayList<>();
			System.out.println(resultids.size()+"+++++++++++++++++++++++++++++++"+countnum+"+++++++++++++"+idss.length);
			if (resultids.size() > 0 && resultids.size() > countnum) {
				for(int j=0;j<= i;j++){
					System.out.println(countnum+"+++++++++"+resultids.size());
					// || resultids.get(j).getShensushenhebiaoshi()  == null
					if(countnum >= resultids.size()){
						return R.fail(code, msg);
					}
					if(resultids.get(j).getShensushenhebiaoshi() == null){
						Alarmhandleresult alarmResult = new Alarmhandleresult();
						alarmResult.setBaojingid(idss[i]);
						alarmResult.setChulizhuangtai(1);
						if (bladeUser == null){
							alarmResult.setChuliren("hyp");
							alarmResult.setChulirenid(1);
						}else{
							alarmResult.setChuliren(bladeUser.getUserName());
							alarmResult.setChulirenid(bladeUser.getUserId());
						}
						alarmResult.setChulishijian(DateUtil.now());
						alarmResult.setQiyemingcheng(dept.getDeptName());
						alarmResult.setQiyeid(Integer.parseInt(deptId));
						alarmResult.setChulixingshi(chulixingshi);
						alarmResult.setBaojingleixing(alarmType);
						//登录页
						if (StringUtil.isNotBlank(fujian)) {
							fileUploadClient.updateCorrelation(fujian, "1");
						}
						alarmResult.setFujian(fujian);
						String str = CommonTool.HtmlToText(chulimiaoshu);
						alarmResult.setChulimiaoshu(str);
						alarmResult.setRemark(true);
						alarmResult.setEndresult(1);
						//根据报警id查询是否出处理过 处理过就不添加
						List<Alarmhandleresult> alarmhandleresult = alarmhandleresultService.selectBybaojin(idss[i]);
						if(alarmhandleresult.size()==0){
							list.add(alarmResult);
						}
						//添加记录
						addnum = alarmhandleresultService.saveBatch(list);

						if (delnum>0 && addnum) {
							msg = "更新成功";
						} else if (addnum) {
							msg = "保存成功";
						} else {
							code = 400;
							msg = "保存失败";
						}
					}else{
						System.out.println(resultids.get(j).getShensushenhebiaoshi()+"++++++++++++++"+resultids.get(j).getBaojingid()+"++++++++++"+idss[i]);
						if(resultids.get(j).getShensushenhebiaoshi() == 2 && resultids.get(j).getBaojingid().equals(idss[i])){
							if (StringUtil.isNotBlank(fujian)) {
								fileUploadClient.updateCorrelation(fujian, "1");
							}
							String str = CommonTool.HtmlToText(chulimiaoshu);
							Integer userId ;
							String userName;
							if (bladeUser == null){
								userId = 1;
								userName = "hyp";
							}else{
								userId = bladeUser.getUserId();
								userName = bladeUser.getUserName();
							}
							String baojingid = resultids.get(j).getBaojingid();
							addnum = alarmhandleresultService.updateAftertreatment(chulixingshi,str,userName,DateUtil.now(),userId,fujian,baojingid);
							if (delnum>0 && addnum) {
								msg = "更新成功";
							} else if (addnum) {
								msg = "保存成功";
							} else {
								code = 400;
								msg = "保存失败";
							}
							countnum +=1;
						}else{
							Alarmhandleresult alarmResult = new Alarmhandleresult();
							alarmResult.setBaojingid(idss[i]);
							alarmResult.setChulizhuangtai(1);
							if (bladeUser == null){
								alarmResult.setChuliren("hyp");
								alarmResult.setChulirenid(1);
							}else{
								alarmResult.setChuliren(bladeUser.getUserName());
								alarmResult.setChulirenid(bladeUser.getUserId());
							}
							alarmResult.setChulishijian(DateUtil.now());
							alarmResult.setQiyemingcheng(dept.getDeptName());
							alarmResult.setQiyeid(Integer.parseInt(deptId));
							alarmResult.setChulixingshi(chulixingshi);
							alarmResult.setBaojingleixing(alarmType);
							//登录页
							if (StringUtil.isNotBlank(fujian)) {
								fileUploadClient.updateCorrelation(fujian, "1");
							}
							alarmResult.setFujian(fujian);
							String str = CommonTool.HtmlToText(chulimiaoshu);
							alarmResult.setChulimiaoshu(str);
							alarmResult.setRemark(true);
							alarmResult.setEndresult(1);
							//根据报警id查询是否出处理过 处理过就不添加
							List<Alarmhandleresult> alarmhandleresult = alarmhandleresultService.selectBybaojin(idss[i]);
							if(alarmhandleresult.size()==0){
								list.add(alarmResult);
							}
							//添加记录
							addnum = alarmhandleresultService.saveBatch(list);

							if (delnum>0 && addnum) {
								msg = "更新成功";
							} else if (addnum) {
								msg = "保存成功";
							} else {
								code = 400;
								msg = "保存失败";
							}
							countnum = j;
						}
					}
				}
			} else{
				Alarmhandleresult alarmResult = new Alarmhandleresult();
				alarmResult.setBaojingid(idss[i]);
				alarmResult.setChulizhuangtai(1);
				if (bladeUser == null){
					alarmResult.setChuliren("hyp");
					alarmResult.setChulirenid(1);
				}else{
					alarmResult.setChuliren(bladeUser.getUserName());
					alarmResult.setChulirenid(bladeUser.getUserId());
				}
				alarmResult.setChulishijian(DateUtil.now());
				alarmResult.setQiyemingcheng(dept.getDeptName());
				alarmResult.setQiyeid(Integer.parseInt(deptId));
				alarmResult.setChulixingshi(chulixingshi);
				alarmResult.setBaojingleixing(alarmType);
				//登录页
				if (StringUtil.isNotBlank(fujian)) {
					fileUploadClient.updateCorrelation(fujian, "1");
				}
				alarmResult.setFujian(fujian);
				String str = CommonTool.HtmlToText(chulimiaoshu);
				alarmResult.setChulimiaoshu(str);
				alarmResult.setRemark(true);
				alarmResult.setEndresult(1);
				//根据报警id查询是否出处理过 处理过就不添加
				List<Alarmhandleresult> alarmhandleresult = alarmhandleresultService.selectBybaojin(idss[i]);
				if(alarmhandleresult.size()==0){
					list.add(alarmResult);
				}
				//添加记录
				addnum = alarmhandleresultService.saveBatch(list);

				if (delnum>0 && addnum) {
					msg = "更新成功";
				} else if (addnum) {
					msg = "保存成功";
				} else {
					code = 400;
					msg = "保存失败";
				}
			}
		}
		return R.fail(code, msg);
	}

    @PostMapping("/selectBJDetail")
    @ApiLog("报警详情")
    @ApiOperation(value="报警详情",notes="传入报警id",position=9)
    public R selectBJDetail(String baojingid,String type){
        if("1".equals(type)){
            AlarmsummaryCutofftimeVO page=cutofftimeService.selectAlarmDetail(baojingid);
            return R.data(page);
        }
        DriverbehaviorVO vo = driverbehaviorService.selectAlarmDetail(baojingid);
        return R.data(vo);
    }

    @PostMapping("/realTimeBaojingTongji")
	@ApiLog("统计-实时报警")
    @ApiOperation(value = "统计-实时报警", notes = "传入shishiBaojingTongjiPage", position = 10)
    public R realTimeBaojingTongji(@RequestBody ShishiBaojingTongjiPage shishiBaojingTongjiPage) {
        Map<String, Object> baojingtongji = new HashMap<>();

//        Map<String, Object> tongji = cutofftimeService.selectShishiBaojingTongji(shishiBaojingTongjiPage);
//        baojingtongji.put("gps", tongji);
//        Map<String, Object> tongjidr = driverbehaviorService.selectShishiBaojingTongji(shishiBaojingTongjiPage);
//        baojingtongji.put("driver", tongjidr);
        Map<String, Object> gps = cutofftimeService.selectShifouBaojing(shishiBaojingTongjiPage);
        baojingtongji.put("gps", gps);
        Map<String, Object> driver = driverbehaviorService.selectShifouBaojing(shishiBaojingTongjiPage);
        baojingtongji.put("driver", driver);
        return R.data(baojingtongji);
    }

    @PostMapping("/chaosu")
	@ApiLog("超速报警-处理统计")
    @ApiOperation(value = "超速报警-处理统计", notes = "传入AlarmvehPage ", position = 11)
    public R chaosu(@RequestBody AlarmvehPage alarmvehPage) {
        AlarmvehPage alarmvehPage1 = iAlarmvehdailyreportService.chaosu(alarmvehPage);
        return R.data(alarmvehPage1);
    }

    @PostMapping("/pilao")
	@ApiLog("疲劳驾驶报警-处理统计")
    @ApiOperation(value = "疲劳驾驶报警-处理统计", notes = "传入AlarmvehPage", position = 12)
    public R pilao(@RequestBody AlarmvehPage alarmvehPage) {
        return R.data(iAlarmvehdailyreportService.pilao(alarmvehPage));
    }

    @PostMapping("/zhudonganquan")
	@ApiLog("主动安全设备报警-处理统计")
    @ApiOperation(value = "主动安全设备报警-处理统计", notes = "传入AlarmvehPage ", position = 13)
    public R zhudonganquan(@RequestBody AlarmvehPage alarmvehPage) {
        return R.data(iAlarmvehdailyreportService.zhudonganquan(alarmvehPage));
    }

    @PostMapping("/licheng")
	@ApiLog("里程-日行驶")
    @ApiOperation(value = "里程-日行驶", notes = "传入AlarmvehPage ", position = 14)
    public R licheng(@RequestBody VehdailyreportPage vehdailyreportPage) {
        return R.data(iVehdailyreportService.selectall(vehdailyreportPage));
    }

	/**
	 * 添加申述记录
	 * @param ids
	 * @param alarmType
	 * @param chulixingshi
	 * @param chulimiaoshu
	 * @param fujian
	 */
	@PostMapping("/insertShenshu")
	@ApiLog("新增-报警申述记录")
	@ApiOperation(value = "新增-报警申述记录", notes = "传入alarmResult", position = 15)
	public R insertShenshu(
		@ApiParam(value = "ids串", required = true) String ids,
		@ApiParam(value = "报警类型", required = true) String alarmType,
		@ApiParam(value = "处理形式", required = true) String chulixingshi,
		@ApiParam(value = "处理描述") String chulimiaoshu,
		@ApiParam(value = "附件") String fujian,BladeUser bladeUser,String deptId) {
        Dept dept = sysClient.selectById(deptId);
		String[] idss = ids.split(",");
        int count = alarmhandleresultService.selectAlarmCountByIdsAndDetpId(idss,dept.getDeptName());
        if(count<idss.length){
            return R.fail("当前所属单位与报警单位不符，不能进行报警申述");
        }
		Alarmhandleresult result = new Alarmhandleresult();
		result.setIdss(idss);
		result.setBaojingleixing(alarmType);
		result.setRemark(false);
		//根据报警id 报警类型
		List<Alarmhandleresult> resultids = alarmhandleresultService.selectIdList(result);
		List<Alarmhandleresult> list = new ArrayList<>();
		int code = 200;
		String msg = "";
		boolean delnum = false;
		boolean addnum = false;
		for (int i = 0; i < idss.length; i++) {
			Alarmhandleresult alarmResult = new Alarmhandleresult();
			alarmResult.setBaojingid(idss[i]);
			alarmResult.setChulizhuangtai(1);
			alarmResult.setChulirenid(bladeUser.getUserId());
			alarmResult.setChuliren(bladeUser.getUserName());
			alarmResult.setChulishijian(DateUtil.now());
			alarmResult.setQiyemingcheng(dept.getDeptName());
			alarmResult.setQiyeid(Integer.parseInt(deptId));
			alarmResult.setChulixingshi(chulixingshi);
			alarmResult.setBaojingleixing(alarmType);
			alarmResult.setShensushenhebiaoshi(0);
			//登录页
			if (StringUtil.isNotBlank(fujian)) {
				fileUploadClient.updateCorrelation(fujian, "1");
			}
			alarmResult.setFujian(fujian);
			String str = CommonTool.HtmlToText(chulimiaoshu);
			alarmResult.setChulimiaoshu(str);
			alarmResult.setRemark(false);
			//根据报警id查询是否出处理过 处理过就不添加
			List<Alarmhandleresult> alarmhandleresults = alarmhandleresultService.selectBybaojin(idss[i]);
			if(alarmhandleresults.size()==0){
				list.add(alarmResult);
			}
		}
		//保存之前，先删除要保存的报警已处理记录
		if (resultids.size() > 0) {
			delnum = alarmhandleresultService.removeByIds(resultids);
		}
		//添加记录
		addnum = alarmhandleresultService.saveBatch(list);

		//更新日报百分比
//		String company=dept.getDeptName();
//		DateTime yesterday = DateUtil.yesterday();
//		String cutoffTime=yesterday.toString();
//		Integer updateribao = alarmhandleresultService.updateribao(cutoffTime, company, deptId);

		if (delnum && addnum) {
			msg = "更新成功";
		} else if (addnum) {
			msg = "保存成功";
		} else {
			code = 400;
			msg = "保存失败";
		}
		return R.fail(code, msg);
	}

	@GetMapping("/alarmDay")
	@ApiLog("北斗设备-今日报警")
	@ApiOperation(value = "北斗设备-今日报警", notes = "传入company:企业名称,AlarmType:报警类型", position = 16)
	public R licheng(@ApiParam(value = "企业名称", required = true)  @RequestParam String company,@ApiParam(value = "报警类型", required = true)  @RequestParam String AlarmType) {
		List<AlarmsummaryCutofftimeVO> alarmDays = cutofftimeService.alarmDay(company, AlarmType);
		return R.data(alarmDays);
	}

	@GetMapping("/zhudongDay")
	@ApiLog("主动设备报警-今日报警")
	@ApiOperation(value = "主动设备报警-今日报警", notes = "传入company:企业名称,AlarmType:报警类型", position = 17)
	public R zhudongDay(@ApiParam(value = "企业名称", required = true)  @RequestParam String company,@ApiParam(value = "报警类型", required = true)  @RequestParam String AlarmType){
		List<DriverbehaviorVO> zhudongDay = driverbehaviorService.zhudongDay(company, AlarmType);
		return  R.data(zhudongDay);
	}

	@GetMapping("/alarmweichuli")
	@ApiLog("未处理报警统计")
	@ApiOperation(value = "未处理报警统计", notes = "deptId:企业ID", position = 18)
	public R alarmweichuli(@ApiParam(value = "企业ID", required = true)  @RequestParam Integer deptId){
		List<AlarmWeichuliType> weichulitongji = alarmhandleresultService.weichulitongji(deptId);
		if(weichulitongji==null || weichulitongji.size()==0){
			return  R.data("");
		}
		return  R.data(weichulitongji);
	}

	@GetMapping("/theExtendedAlarmDay")
	@ApiLog("即将超期未处理报警")
	@ApiOperation(value = "即将超期未处理报警", notes = "传入 deptId 企业ID", position = 23)
	public R theExtendedAlarmDay(@ApiParam(value = "企业ID", required = true)  @RequestParam Integer deptId){
		String date = DateUtils.getPastDate(alarmServer.getRunoutthetime());
		List<AlarmWeichuliType> weichulitongji = alarmhandleresultService.cqweichulitongji(deptId,date);
		if(weichulitongji==null || weichulitongji.size()==0){
			return  R.data("");
		}
		return  R.data(weichulitongji);
	}

	@GetMapping("/overdueAlarmDay")
	@ApiLog("超期未处理报警")
	@ApiOperation(value = "超期未处理报警", notes = "传入 deptId 企业ID", position = 24)
	public R overdueAlarmDay(@ApiParam(value = "企业ID", required = true)  @RequestParam Integer deptId){
		String date = DateUtils.getPastDate(alarmServer.getBeyondthetime());
		List<AlarmWeichuliType> weichulitongji = alarmhandleresultService.cqweichulitongji(deptId,date);
		if(weichulitongji==null || weichulitongji.size()==0){
			return  R.data("");
		}
		return  R.data(weichulitongji);
	}

    @PostMapping("/listMG")
    @ApiLog("分页查询-北斗设备报警倒查MG")
    @ApiOperation(value = "分页查询-GPS报警MG", notes = "传入AlarmPage", position = 19)
    public R<AlarmPage<AlarmsummaryCutofftimeMG>> listMG(@RequestBody AlarmPage alarmPage) {
        AlarmPage pages = cutofftimeService.selectAlarmMGPage(alarmPage);
        return R.data(pages);
    }

	@PostMapping("/driverAlarmListMG")
	@ApiLog("分页查询-驾驶员行为报警倒查MG")
	@ApiOperation(value = "分页查询-驾驶员行为报警MG", notes = "传入DriverAlarmPage", position = 20)
	public R<DriverAlarmPage<DriverbehaviorMG>> driverAlarmLisMG(@RequestBody DriverAlarmPage driverAlarmPage) {
		DriverAlarmPage pages = driverbehaviorService.selectdriverbehaviorPage(driverAlarmPage);
		return R.data(pages);
	}

	@PostMapping("/AlarmTimeData")
	@ApiLog("报警信息接口-时间段报警信息")
	@ApiOperation(value = "报警信息-时间段报警信息", notes = "传入DriverAlarmPage", position = 21)
	public R alarmTimeData(@RequestBody AlarmTimePage alarmTimePage) {
		Map<String,Object> map=new HashMap<>();
		//车辆报警
		List<AlarmsummaryCutofftime> alarm = cutofftimeService.timeAlarm(alarmTimePage);
		//主动防御报警
		List<Driverbehavior> zhudong = driverbehaviorService.timeZhudong(alarmTimePage);
		for(AlarmsummaryCutofftime data:alarm){
			double lat=Double.valueOf(data.getLatitude().toString());
			double lon=Double.valueOf(data.getLongitude().toString());
			double[] zuobiao = GpsToBaiduUtil.wgs2bd(lat,lon);
			data.setLatitude(new BigDecimal(zuobiao[0]).setScale(6,BigDecimal.ROUND_HALF_UP));
			data.setLongitude(new BigDecimal(zuobiao[1]).setScale(6,BigDecimal.ROUND_HALF_UP));
		}
		for(Driverbehavior data:zhudong){
			double lat=Double.valueOf(data.getLatitude().toString());
			double lon=Double.valueOf(data.getLongitude().toString());
			double[] zuobiao = GpsToBaiduUtil.wgs2bd(lat,lon);
			data.setLatitude(new BigDecimal(zuobiao[0]).setScale(6,BigDecimal.ROUND_HALF_UP));
			data.setLongitude(new BigDecimal(zuobiao[1]).setScale(6,BigDecimal.ROUND_HALF_UP));
		}
		map.put("gps",alarm);
		map.put("driver",zhudong);
		return  R.data(map);
	}

	@PostMapping(value = "/UpdateStatementProcessRate")
	@ApiLog("企业报告-更新报告处理率")
	@ApiOperation(value = "企业报告-更新报告处理率", notes = "传入相关参数（beginTime,endTime,company,property）",position = 22)
	public R UpdateStatementProcessRate(String beginTime,String endTime,String company,String property) {
		R rs = new R();
		List<AlarmsummaryCutofftimeVO> alarmsummaryVO = cutofftimeService.selectAlarmLv(beginTime, endTime, company);
		if(alarmsummaryVO.size() > 0) {
			for (int i = 0;i < alarmsummaryVO.size();i++){
				String processRate = alarmsummaryVO.get(i).getChulilv();
				if(alarmsummaryVO.get(i).getBaojingcishu() == 0){
					processRate = "100%";
				}
				String deptId = alarmsummaryVO.get(i).getDeptId();
//				String countdate = alarmsummaryVO.get(i).getDateShow();
				boolean is = cutofftimeService.updateBaoBiaoMuLu(processRate,deptId,property,beginTime,endTime);
				if (is == true){
					rs.setSuccess(true);
					rs.setCode(200);
					rs.setMsg("更新"+beginTime+"至"+endTime+"处理率成功");
				}
			}
		}else{
			rs.setMsg("无该企业数据");
			return rs;
		}
		return rs;
	}

}
