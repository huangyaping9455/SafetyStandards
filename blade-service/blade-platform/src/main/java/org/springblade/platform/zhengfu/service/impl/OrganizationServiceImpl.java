/**
 * Copyright (C), 2015-2020,
 * FileName: XinXiJiaoHuZhuServiceImpl
 * Author:   呵呵哒
 */
package org.springblade.platform.zhengfu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.platform.zhengfu.entity.Organization;
import org.springblade.platform.zhengfu.entity.ZhengFuOrganization;
import org.springblade.platform.zhengfu.mapper.OrganizationMapper;
import org.springblade.platform.zhengfu.mapper.ZhengFuBaoJingTongJiMapper;
import org.springblade.platform.zhengfu.page.OrganizationPage;
import org.springblade.platform.zhengfu.service.IOrganizationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 呵呵哒
 * @描述
 */
@Service
@AllArgsConstructor
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements IOrganizationService {

	private OrganizationMapper organizationMapper;
	private ZhengFuBaoJingTongJiMapper zhengFuBaoJingTongJiMapper;

	@Override
	public Organization selectGetSheng(String deptId) {
		return organizationMapper.selectGetSheng(deptId);
	}

	@Override
	public List<Organization> selectGetShi(String Id) {
		return organizationMapper.selectGetShi(Id);
	}

	@Override
	public List<Organization> selectGetXian(String Id) {
		return organizationMapper.selectGetXian(Id);
	}


	@Override
	public Organization selectGetRenyuan(String Id) {
		return organizationMapper.selectGetRenyuan(Id);
	}

	@Override
	public Organization selectGetGangWei(String Id) {
		return organizationMapper.selectGetGangWei(Id);
	}

	@Override
	public List<ZhengFuOrganization> selectGetZF(String province, String city, String country,String deptId) {
		return organizationMapper.selectGetZF(province, city, country,deptId);
	}

	@Override
	public Organization selectGetZFJB(String deptId) {
		return organizationMapper.selectGetZFJB(deptId);
	}

	@Override
	public OrganizationPage selectALLPage(OrganizationPage organizationPage) {
		Integer total = organizationMapper.selectAllTotal(organizationPage);
		if(organizationPage.getSize()==0){
			if(organizationPage.getTotal()==0){
				organizationPage.setTotal(total);
			}

			if(organizationPage.getTotal()==0){
				return organizationPage;
			}else{
				List<Organization> organizationList = organizationMapper.selectALLPage(organizationPage);
				organizationPage.setRecords(organizationList);
				return organizationPage;
			}
		}
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%organizationPage.getSize()==0){
				pagetotal = total / organizationPage.getSize();
			}else {
				pagetotal = total / organizationPage.getSize() + 1;
			}
		}
		if (pagetotal >= organizationPage.getCurrent()) {
			organizationPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (organizationPage.getCurrent() > 1) {
				offsetNo = organizationPage.getSize() * (organizationPage.getCurrent() - 1);
			}
			organizationPage.setTotal(total);
			organizationPage.setOffsetNo(offsetNo);
			List<Organization> organizationList = organizationMapper.selectALLPage(organizationPage);
			organizationPage.setRecords(organizationList);
		}
		return organizationPage;
	}


}
