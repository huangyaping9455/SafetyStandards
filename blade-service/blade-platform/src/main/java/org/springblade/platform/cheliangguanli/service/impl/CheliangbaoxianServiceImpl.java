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
package org.springblade.platform.cheliangguanli.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.platform.cheliangguanli.entity.Cheliangbaoxian;
import org.springblade.platform.cheliangguanli.mapper.CheliangbaoxianMapper;
import org.springblade.platform.cheliangguanli.page.CheliangbaoxianPage;
import org.springblade.platform.cheliangguanli.service.ICheliangbaoxianService;
import org.springblade.platform.cheliangguanli.vo.CheliangbaoxianVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 */
@Service
@AllArgsConstructor
public class CheliangbaoxianServiceImpl extends ServiceImpl<CheliangbaoxianMapper, Cheliangbaoxian> implements ICheliangbaoxianService {

	private CheliangbaoxianMapper mapper;

	@Override
	public IPage<CheliangbaoxianVO> selectCheliangbaoxianPage(IPage<CheliangbaoxianVO> page, CheliangbaoxianVO cheliangbaoxian) {
		return page.setRecords(baseMapper.selectCheliangbaoxianPage(page, cheliangbaoxian));
	}

	@Override
	public CheliangbaoxianVO selectByIds(String id) {
		return mapper.selectByIds(id);
	}

	@Override
	public boolean updateDel(String id) {
		return mapper.updateDel(id);
	}

	@Override
	public CheliangbaoxianPage<CheliangbaoxianVO> selectPageList(CheliangbaoxianPage Page) {
		Integer total = mapper.selectTotal(Page);
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%Page.getSize()==0){
				pagetotal = total / Page.getSize();
			}else {
				pagetotal = total / Page.getSize() + 1;
			}
		}
		if (pagetotal < Page.getCurrent()) {
			return Page;
		} else {
			Page.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (Page.getCurrent() > 1) {
				offsetNo = Page.getSize() * (Page.getCurrent() - 1);
			}
			Page.setTotal(total);
			Page.setOffsetNo(offsetNo);
			List<CheliangbaoxianVO> vehlist = mapper.selectPageList(Page);
			return (CheliangbaoxianPage<CheliangbaoxianVO>) Page.setRecords(vehlist);
		}
	}

	@Override
	public boolean insertSelective(Cheliangbaoxian cheliangbaoxian) {
		return mapper.insertSelective(cheliangbaoxian);
	}

}
