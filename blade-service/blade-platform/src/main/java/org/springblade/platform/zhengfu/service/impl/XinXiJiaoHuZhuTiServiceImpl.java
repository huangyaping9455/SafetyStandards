/**
 * Copyright (C), 2015-2020,
 * FileName: XinXiJiaoHuZhuServiceImpl
 * Author:   呵呵哒
 * Date:     2020/6/20 17:18
 * Description:
 */
package org.springblade.platform.zhengfu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.platform.zhengfu.entity.Organization;
import org.springblade.platform.zhengfu.entity.XinXiJiaoHuZhuTi;
import org.springblade.platform.zhengfu.entity.XinXiJiaoHuZhuTiVo;
import org.springblade.platform.zhengfu.mapper.XinXiJiaoHuZhuTiMapper;
import org.springblade.platform.zhengfu.page.XinXiJiaoHuZhuTiPage;
import org.springblade.platform.zhengfu.service.IXinXiJiaoHuZhuTiService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 呵呵哒
 * @创建人 hyp
 * @创建时间 2020/6/20
 * @描述
 */
@Service
@AllArgsConstructor
public class XinXiJiaoHuZhuTiServiceImpl extends ServiceImpl<XinXiJiaoHuZhuTiMapper, XinXiJiaoHuZhuTi> implements IXinXiJiaoHuZhuTiService {

	private XinXiJiaoHuZhuTiMapper xinXiJiaoHuZhuTiMapper;

	@Override
	public boolean insertTongZhiGongGao(XinXiJiaoHuZhuTi xinXiJiaoHuZhuTi) {
		return xinXiJiaoHuZhuTiMapper.insertTongZhiGongGao(xinXiJiaoHuZhuTi);
	}

	@Override
	public boolean insertSelective(XinXiJiaoHuZhuTi xinXiJiaoHuZhuTi) {
		return xinXiJiaoHuZhuTiMapper.insertSelective(xinXiJiaoHuZhuTi);
	}

	@Override
	public boolean updateByPrimaryKeySelective(XinXiJiaoHuZhuTi xinXiJiaoHuZhuTi) {
		return xinXiJiaoHuZhuTiMapper.updateByPrimaryKeySelective(xinXiJiaoHuZhuTi);
	}

	@Override
	public boolean updateByPrimaryKey(XinXiJiaoHuZhuTi xinXiJiaoHuZhuTi) {
		return xinXiJiaoHuZhuTiMapper.updateByPrimaryKey(xinXiJiaoHuZhuTi);
	}

	@Override
	public XinXiJiaoHuZhuTiPage selectALLPage(XinXiJiaoHuZhuTiPage xinXiJiaoHuZhuTiPage) {
		Integer total = xinXiJiaoHuZhuTiMapper.selectAllTotal(xinXiJiaoHuZhuTiPage);
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%xinXiJiaoHuZhuTiPage.getSize()==0){
				pagetotal = total / xinXiJiaoHuZhuTiPage.getSize();
			}else {
				pagetotal = total / xinXiJiaoHuZhuTiPage.getSize() + 1;
			}
		}
		if (pagetotal >= xinXiJiaoHuZhuTiPage.getCurrent()) {
			xinXiJiaoHuZhuTiPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (xinXiJiaoHuZhuTiPage.getCurrent() > 1) {
				offsetNo = xinXiJiaoHuZhuTiPage.getSize() * (xinXiJiaoHuZhuTiPage.getCurrent() - 1);
			}
			xinXiJiaoHuZhuTiPage.setTotal(total);
			xinXiJiaoHuZhuTiPage.setOffsetNo(offsetNo);
			List<XinXiJiaoHuZhuTi> vehlist = xinXiJiaoHuZhuTiMapper.selectALLPage(xinXiJiaoHuZhuTiPage);
			xinXiJiaoHuZhuTiPage.setRecords(vehlist);
		}
		return xinXiJiaoHuZhuTiPage;
	}

	@Override
	public XinXiJiaoHuZhuTi selectById(XinXiJiaoHuZhuTi xinXiJiaoHuZhuTi) {
		return xinXiJiaoHuZhuTiMapper.selectById(xinXiJiaoHuZhuTi);
	}

	@Override
	public List<XinXiJiaoHuZhuTiVo> selectGetQiYe(String deptId,String leixing) {
		return xinXiJiaoHuZhuTiMapper.selectGetQiYe(deptId,leixing);
	}

	@Override
	public Organization selectGetLeiXing(String deptId) {
		return xinXiJiaoHuZhuTiMapper.selectGetLeiXing(deptId);
	}

	@Override
	public XinXiJiaoHuZhuTi selectGetQYByOne(String deptId) {
		return xinXiJiaoHuZhuTiMapper.selectGetQYByOne(deptId);
	}


}
