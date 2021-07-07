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
package org.springblade.manage.settlement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.manage.settlement.entity.Settlement;
import org.springblade.manage.settlement.mapper.SettlementMapper;
import org.springblade.manage.settlement.page.SettlementPage;
import org.springblade.manage.settlement.service.ISettlementService;
import org.springblade.manage.settlement.vo.SettlementVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 */
@Service
@AllArgsConstructor
public class SettlementServiceImpl extends ServiceImpl<SettlementMapper, Settlement> implements ISettlementService {

	private SettlementMapper settlementMapper;

	@Override
	public SettlementPage<SettlementVO> selectPageList(SettlementPage Page) {
		Integer total = settlementMapper.selectTotal(Page);
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
			List<SettlementVO> org = settlementMapper.selectPageList(Page);
			return (SettlementPage<SettlementVO>) Page.setRecords(org);
		}
	}

	@Override
	public SettlementPage<SettlementVO> selectLirunList(SettlementPage Page) {
		Integer total = settlementMapper.selectLirunTotal(Page);
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
			List<SettlementVO> org = settlementMapper.selectLirunList(Page);
			return (SettlementPage<SettlementVO>) Page.setRecords(org);
		}
	}

	@Override
	public SettlementPage<SettlementVO> selectZhiChuList(SettlementPage Page) {
		Integer total = settlementMapper.selectZhiChuTotal(Page);
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
			List<SettlementVO> org = settlementMapper.selectZhiChuList(Page);
			return (SettlementPage<SettlementVO>) Page.setRecords(org);
		}
	}

	@Override
	public boolean delBywaybillId(String waybillId) {
		return settlementMapper.delBywaybillId(waybillId);
	}
}
