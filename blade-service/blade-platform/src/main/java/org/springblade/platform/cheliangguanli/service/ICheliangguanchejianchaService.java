/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * limitations under the License.
 */
package org.springblade.platform.cheliangguanli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.platform.cheliangguanli.entity.Cheliangguanchejiancha;
import org.springblade.platform.cheliangguanli.page.CheliangguanchejianchaPage;
import org.springblade.platform.cheliangguanli.vo.CheliangguanchejianchaVO;

/**
 *  服务类
 */
public interface ICheliangguanchejianchaService extends IService<Cheliangguanchejiancha> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param cheliangguanchejiancha
	 * @return
	 */
	IPage<CheliangguanchejianchaVO> selectCheliangguanchejianchaPage(IPage<CheliangguanchejianchaVO> page, CheliangguanchejianchaVO cheliangguanchejiancha);

	boolean updateDel(String id);

	/**
	 * 自定义分页
	 * @param
	 * @return
	 */
	CheliangguanchejianchaPage<CheliangguanchejianchaVO> selectPageList(CheliangguanchejianchaPage Page);

	CheliangguanchejianchaVO selectByIds(String id);
}
