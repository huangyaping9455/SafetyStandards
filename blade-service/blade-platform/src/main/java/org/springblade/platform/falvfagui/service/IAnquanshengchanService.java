/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.platform.falvfagui.service;

import org.springblade.platform.falvfagui.entity.Anquanshengchan;
import org.springblade.platform.falvfagui.page.AnquanshengchanPage;
import org.springblade.platform.falvfagui.vo.AnquanshengchanVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author Blade
 * @since 2019-04-24
 */
public interface IAnquanshengchanService extends IService<Anquanshengchan> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param anquanshengchan
	 * @return
	 */
	IPage<AnquanshengchanVO> selectAnquanshengchanPage(IPage<AnquanshengchanVO> page, AnquanshengchanVO anquanshengchan);

	boolean insertAnQuanShengChan(Anquanshengchan anquanshengchan);

	boolean updateDel(String id);

	/**
	 * 自定义 分页
	 * @param
	 * @return
	 */
	AnquanshengchanPage<AnquanshengchanVO> selectPageList(AnquanshengchanPage anquanshengchanPage);

	AnquanshengchanVO selectByIds(String id);
}
