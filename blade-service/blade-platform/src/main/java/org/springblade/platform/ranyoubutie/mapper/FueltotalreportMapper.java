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
package org.springblade.platform.ranyoubutie.mapper;

import org.springblade.platform.ranyoubutie.entity.Fueltotalreport;
import org.springblade.platform.ranyoubutie.page.FueltotalreportPage;
import org.springblade.platform.ranyoubutie.vo.FueltotalreportVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 *  Mapper 接口
 *
 * @author jx
 * @since 2019-10-16
 */
public interface FueltotalreportMapper extends BaseMapper<Fueltotalreport> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param fueltotalreport
	 * @return
	 */
	List<FueltotalreportVO> selectFueltotalreportPage(IPage page, FueltotalreportVO fueltotalreport);

	/**
	 *分页列表
	 * @param page
	 * @return
	 */
	List<FueltotalreportVO> selectAllList(FueltotalreportPage page);

	/**
	 * 总数
	 * @param page
	 * @return
	 */
	int selectTotal(FueltotalreportPage page);

}
