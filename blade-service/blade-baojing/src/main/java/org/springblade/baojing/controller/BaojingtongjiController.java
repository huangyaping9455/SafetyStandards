
package org.springblade.baojing.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.baojing.page.BaojingTJPage;
import org.springblade.baojing.service.IBaojingtongjiService;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/baojing/alarmTongji")
@Api(value = "报警统计接口", tags = "报警统计接口")
public class BaojingtongjiController extends BladeController {

	private IBaojingtongjiService iBaojingtongjiService;

	@PostMapping("ChosuList")
	@ApiLog("超速-统计")
	@ApiOperation(value = "超速-统计-分页", notes = "传入baojingTJPage", position = 1)
	public  R findAllChaosuPage(@RequestBody BaojingTJPage baojingTJPage){
		BaojingTJPage list=iBaojingtongjiService.selectAll(baojingTJPage);
		return  R.data(list);
	}

	@PostMapping("PilaoList")
	@ApiLog("疲劳-统计-分页")
	@ApiOperation(value = "疲劳-统计-分页", notes = "传入baojingTJPage", position = 2)
	public R findAllPilaoPage(@RequestBody BaojingTJPage baojingTJPage){
		BaojingTJPage list=iBaojingtongjiService.PilaoAll(baojingTJPage);
		return  R.data(list);
	}

	@PostMapping("alarmCountDay")
	@ApiLog("当日报警统计")
	@ApiOperation(value = "当日报警统计", notes = "传入company 企业名称", position = 3)
   public R alarmCountDay(@ApiParam(value = "企业名称", required = true)  @RequestParam String company){
		return R.data(iBaojingtongjiService.alarmCount(company));
	}

	@PostMapping("BudingweiList")
	@ApiLog("不定位-统计")
	@ApiOperation(value = "不定位-统计-分页", notes = "BaojingTJPage", position = 4)
	public R budingwei(@RequestBody BaojingTJPage baojingTJPage){
		BaojingTJPage selectbudingwei = iBaojingtongjiService.selectbudingwei(baojingTJPage);
		return R.data(selectbudingwei);
	}
	@PostMapping("BuzaixianList")
	@ApiLog("不在线-统计")
	@ApiOperation(value = "不在线-统计-分页", notes = "BaojingTJPage", position = 5)
	public R buzaixian(@RequestBody BaojingTJPage baojingTJPage){
		BaojingTJPage selectbudingwei = iBaojingtongjiService.selectbuzaixian(baojingTJPage);
		return R.data(selectbudingwei);
	}

}
