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
package org.springblade.platform.anquanmubiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.platform.anquanmubiao.entity.Zhongchangqijihua;
import org.springblade.platform.anquanmubiao.page.ZhongchangqijihuaPage;
import org.springblade.platform.anquanmubiao.vo.ZhongchangqijihuaVO;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author Blade
 * @since 2019-04-28
 */
public interface ZhongchangqijihuaMapper extends BaseMapper<Zhongchangqijihua> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param zhongchangqijihua
	 * @return
	 */
	List<ZhongchangqijihuaVO> selectZhongchangqijihuaPage(IPage page, ZhongchangqijihuaVO zhongchangqijihua);

	boolean updateDel(String id);

	boolean insertNianDuJiHua(Zhongchangqijihua entity);

	/**
	 * 自定义分页
	 * @param
	 * @return
	 */
	List<ZhongchangqijihuaVO> selectPageList(ZhongchangqijihuaPage Page);
	/**
	 * 统计
	 * @param
	 * @return
	 */
	int selectTotal(ZhongchangqijihuaPage Page);

	ZhongchangqijihuaVO selectByIds(String id);
}
