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
package org.springblade.platform.guanlijigouherenyuan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.platform.guanlijigouherenyuan.entity.Anquanhuiyi;
import org.springblade.platform.guanlijigouherenyuan.page.AnquanhuiyiPage;
import org.springblade.platform.guanlijigouherenyuan.vo.AnquanhuiyiVO;

import java.util.List;

/**
 *  Mapper 接口
 */
public interface AnquanhuiyiMapper extends BaseMapper<Anquanhuiyi> {

	/**
	 * 自定义分页
	 * @param AnquanhuiyiPage
	 * @return
	 */
	List<AnquanhuiyiVO> selectHuiYiPage(AnquanhuiyiPage AnquanhuiyiPage);
	/**
	 * 统计
	 * @param AnquanhuiyiPage
	 * @return
	 */
	int selectHuiYiTotal(AnquanhuiyiPage  AnquanhuiyiPage);

	/**
	 * 自定义删除
	 * @param id
	 * @return
	 */
	boolean deleteHuiYi(String id);


}
