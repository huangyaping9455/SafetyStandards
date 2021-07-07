/**
 * Copyright (C), 2015-2020,
 * FileName: IXinXiJiaoHuZhuService
 * Author:   呵呵哒
 * Date:     2020/6/20 17:18
 * Description:
 */
package org.springblade.platform.zhengfu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.platform.zhengfu.entity.ZhengFuBaoGao;
import org.springblade.platform.zhengfu.page.ZhengFuBaoGaoPage;

/**
 * @author 呵呵哒
 * @创建人 hyp
 * @创建时间 2020/6/20
 * @描述
 */
public interface IZhengFuBaoGaoService extends IService<ZhengFuBaoGao> {

	/**
	 * 查询政府报告
	 * @param zhengFuBaoGaoPage
	 * @return
	 */
	ZhengFuBaoGaoPage selectALLPage(ZhengFuBaoGaoPage zhengFuBaoGaoPage);

}
