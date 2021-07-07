/**
 * Copyright (C), 2015-2020,
 * FileName: XinXiJiaoHuZhuTiController
 * Author:   呵呵哒
 * Date:     2020/6/20 17:24
 * Description:
 */
package org.springblade.platform.zhengfu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.platform.zhengfu.entity.XinXiJiaoHuHuiFu;
import org.springblade.platform.zhengfu.entity.XinXiJiaoHuZhuTi;
import org.springblade.platform.zhengfu.page.XinXiJiaoHuZhuTiPage;
import org.springblade.platform.zhengfu.service.IXinXiJiaoHuHuiFuiService;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 呵呵哒
 * @创建人 hyp
 * @创建时间 2020/7/13
 * @描述
 */
@RestController
@AllArgsConstructor
@RequestMapping("/platform/xinXiJiaoHuHuiFu")
@Api(value = "政企互通-企业回复", tags = "政企互通-企业回复")
public class XinXiJiaoHuHuiFuController {

	private IXinXiJiaoHuHuiFuiService iXinXiJiaoHuHuiFuiService;

	@PostMapping("/insert")
	@ApiLog("新增-回复政府信息")
	@ApiOperation(value = "新增-回复政府信息", notes = "传入xinXiJiaoHuHuiFu", position = 1)
	public R insert(@RequestBody XinXiJiaoHuHuiFu xinXiJiaoHuHuiFu) {
		UUID uuid = UUID.randomUUID();
		xinXiJiaoHuHuiFu.setId(uuid.toString());
		String formatStr2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		xinXiJiaoHuHuiFu.setHuifushijian(formatStr2);
		boolean i = iXinXiJiaoHuHuiFuiService.insertSelective(xinXiJiaoHuHuiFu);
		R rs = new R();
		if(i == true){
			rs.setCode(200);
			rs.setSuccess(true);
			rs.setMsg("新增-回复政府信息成功");
		}else{
			rs.setCode(500);
			rs.setSuccess(false);
			rs.setMsg("新增-回复政府信息失败");
		}
		return rs;
	}

	@PostMapping("/list")
	@ApiLog("分页-安全查岗回复企业信息")
	@ApiOperation(value = "分页-安全查岗回复企业信息", notes = "传入zhutiId", position = 4)
	public R<XinXiJiaoHuZhuTiPage<XinXiJiaoHuZhuTi>> list(@RequestBody XinXiJiaoHuZhuTiPage xinXiJiaoHuZhuTiPage) {
		XinXiJiaoHuZhuTiPage<XinXiJiaoHuZhuTi> pages = iXinXiJiaoHuHuiFuiService.selectHFALLPage(xinXiJiaoHuZhuTiPage);
		return R.data(pages);
	}
}
