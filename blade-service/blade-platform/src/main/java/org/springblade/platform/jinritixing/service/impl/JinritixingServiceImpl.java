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
import org.springblade.platform.jinritixing.entity.Jinritixing;
import org.springblade.platform.jinritixing.mapper.JinritixingMapper;
import org.springblade.platform.jinritixing.page.JinritixingPage;
import org.springblade.platform.jinritixing.service.IJinritixingService;
import org.springblade.platform.jinritixing.vo.JinritixingVO;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Iterator;
import java.util.List;

/**
 *  服务实现类
 */
@Service
@AllArgsConstructor
public class JinritixingServiceImpl extends ServiceImpl<JinritixingMapper, Jinritixing> implements IJinritixingService {

	private JinritixingMapper mapper;

	@Override
	public IPage<JinritixingVO> selectJinritixingPage(IPage<JinritixingVO> page, JinritixingVO jinritixing) {
		return page.setRecords(baseMapper.selectJinritixingPage(page, jinritixing));
	}

	@Override
	public boolean updateDel(String id) {
		return mapper.updateDel(id);
	}

	@Override
	public JinritixingPage<JinritixingVO> selectPageList(JinritixingPage Page) {
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
			List<JinritixingVO> vehlist = mapper.selectPageList(Page);
			//赛选 预警全部不提醒
			Iterator<JinritixingVO> it = vehlist.iterator();
			while (it.hasNext()){
				JinritixingVO next = it.next();
				if("预警".equals(next.getTixingleixing())){
					it.remove();
				}
			}


			return (JinritixingPage<JinritixingVO>) Page.setRecords(vehlist);
		}
	}

	@Override
	public JinritixingVO selectByIds(String id) {
		return mapper.selectByIds(id);
	}

	@Override
	public JinritixingPage<JinritixingVO> selectLists(JinritixingPage Page) {
		Integer total = mapper.selectListTotal(Page);
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
			List<JinritixingVO> vehlist = mapper.selectLists(Page);
			return (JinritixingPage<JinritixingVO>) Page.setRecords(vehlist);
		}
	}

	@Override
	public int selectNum(JinritixingPage Page) {
		return mapper.selectNum(Page);
	}

}
