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
package org.springblade.platform.cheliangguanli.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.platform.cheliangguanli.entity.Chelianganquanshebei;
import org.springblade.platform.cheliangguanli.page.ChelianganquanshebeiPage;
import org.springblade.platform.cheliangguanli.vo.ChelianganquanshebeiVO;

import java.util.List;


/**
 *  Mapper 接口
 */
public interface ChelianganquanshebeiMapper extends BaseMapper<Chelianganquanshebei> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param chelianganquanshebei
	 * @return
	 */
	List<ChelianganquanshebeiVO> selectChelianganquanshebeiPage(IPage page, ChelianganquanshebeiVO chelianganquanshebei);

	boolean updateDel(String id);

	/**
	 * 自定义分页
	 * @param
	 * @return
	 */
	List<ChelianganquanshebeiVO> selectPageList(ChelianganquanshebeiPage Page);
	/**
	 * 统计
	 * @param
	 * @return
	 */
	int selectTotal(ChelianganquanshebeiPage Page);

	ChelianganquanshebeiVO selectByIds(String id);
}
