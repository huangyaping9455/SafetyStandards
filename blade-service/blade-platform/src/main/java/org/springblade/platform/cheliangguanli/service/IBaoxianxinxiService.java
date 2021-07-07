/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 */
package org.springblade.platform.cheliangguanli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.platform.cheliangguanli.entity.Baoxianxinxi;
import org.springblade.platform.cheliangguanli.page.BaoxianxinxiPage;
import org.springblade.platform.cheliangguanli.vo.BaoxianxinxiVO;

/**
 *  服务类
 */
public interface IBaoxianxinxiService extends IService<Baoxianxinxi> {

	BaoxianxinxiVO selectByIds(String id);

	boolean updateDel(String id);

	/**
	 * 自定义分页
	 * @param
	 * @return
	 */
	BaoxianxinxiPage<BaoxianxinxiVO> selectPageList(BaoxianxinxiPage Page);
}
