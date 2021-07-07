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
package org.springblade.platform.jinritixing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.platform.jinritixing.entity.Yujingquanxian;
import org.springblade.platform.jinritixing.mapper.YujingquanxianMapper;
import org.springblade.platform.jinritixing.page.YujingquanxianPage;
import org.springblade.platform.jinritixing.service.IYujingquanxianService;
import org.springblade.platform.jinritixing.vo.YujingquanxianVO;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 *  服务实现类
 */
@Service
@AllArgsConstructor
public class YujingquanxianServiceImpl extends ServiceImpl<YujingquanxianMapper, Yujingquanxian> implements IYujingquanxianService {

	private YujingquanxianMapper mapper;

	@Override
	public IPage<YujingquanxianVO> selectYujingquanxianPage(IPage<YujingquanxianVO> page, YujingquanxianVO yujingquanxian) {
		return page.setRecords(baseMapper.selectYujingquanxianPage(page, yujingquanxian));
	}

	@Override
	public List<YujingquanxianVO> selectAllYuJing() {
		return mapper.selectAllYuJing();
	}

	@Override
	public List<YujingquanxianVO> selectYuJingList(YujingquanxianPage page) {
		return mapper.selectYuJingList(page);
	}

	@Override
	public boolean delYuJing(YujingquanxianPage page) {
		return mapper.delYuJing(page);
	}

	@Override
	public void yujingjiesuan(YujingquanxianPage page) {
		mapper.yujingjiesuan(page);
	}

}
