/**
 * Copyright (C), 2015-2020,
 * FileName: IXinXiJiaoHuZhuService
 * Description:
 */
package org.springblade.platform.zhengfu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.platform.zhengfu.entity.XinXiJiaoHuHuiFu;
import org.springblade.platform.zhengfu.page.XinXiJiaoHuZhuTiPage;

/**
 * @author 呵呵哒
 * @描述
 */
public interface IXinXiJiaoHuHuiFuiService extends IService<XinXiJiaoHuHuiFu> {

	boolean insertSelective(XinXiJiaoHuHuiFu xinXiJiaoHuHuiFu);

	XinXiJiaoHuZhuTiPage selectHFALLPage(XinXiJiaoHuZhuTiPage xinXiJiaoHuZhuTiPage);

}
