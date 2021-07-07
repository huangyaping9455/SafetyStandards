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
package org.springblade.platform.jinritixing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.platform.jinritixing.entity.Jinritixing;
import org.springblade.platform.jinritixing.page.JinritixingPage;
import org.springblade.platform.jinritixing.vo.JinritixingVO;

import java.util.List;

/**
 *  Mapper 接口
 */
public interface JinritixingMapper extends BaseMapper<Jinritixing> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param jinritixing
	 * @return
	 */
	List<JinritixingVO> selectJinritixingPage(IPage page, JinritixingVO jinritixing);

	boolean updateDel(String id);

	/**
	 * 自定义分页
	 * @param
	 * @return
	 */
	List<JinritixingVO> selectPageList(JinritixingPage Page);
	/**
	 * 统计
	 * @param
	 * @return
	 */
	int selectTotal(JinritixingPage Page);

	JinritixingVO selectByIds(String id);

	List<JinritixingVO> selectLists(JinritixingPage Page);

	int selectListTotal(JinritixingPage Page);

	int selectNum(JinritixingPage Page);
}
