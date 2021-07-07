package org.springblade.platform.jiashiyuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.platform.cheliangguanli.entity.Vehicle;
import org.springblade.platform.jiashiyuan.entity.JiaShiYuan;
import org.springblade.platform.jiashiyuan.mapper.JiaShiYuanMapper;
import org.springblade.platform.jiashiyuan.page.JiaShiYuanPage;
import org.springblade.platform.jiashiyuan.service.IJiaShiYuanService;
import org.springblade.platform.jiashiyuan.vo.JiaShiYuanVO;
import org.springblade.common.constant.CommonConstant;
import org.springblade.core.tool.utils.DigestUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by you on 2019/4/23.
 */
@Service
@AllArgsConstructor
public class JiaShiYuanServiceImpl extends ServiceImpl<JiaShiYuanMapper, JiaShiYuan> implements IJiaShiYuanService {

	private JiaShiYuanMapper jiaShiYuanMapper;

	@Override
	public boolean insertJSY(JiaShiYuan jiaShiYuan) {
		return jiaShiYuanMapper.insertJSY(jiaShiYuan);
	}

	@Override
	public boolean updateDel(String id) {
		return jiaShiYuanMapper.updateDel(id);
	}

	@Override
	public JiaShiYuanPage<JiaShiYuanVO> selectPageList(JiaShiYuanPage jiaShiYuanPage) {
		Integer total = jiaShiYuanMapper.selectTotal(jiaShiYuanPage);
		if(jiaShiYuanPage.getSize()==0){
			if(jiaShiYuanPage.getTotal()==0){
				jiaShiYuanPage.setTotal(total);
			}
			List<JiaShiYuanVO> jiaShiYuanVOList = jiaShiYuanMapper.selectPageList(jiaShiYuanPage);
			jiaShiYuanPage.setRecords(jiaShiYuanVOList);
			return jiaShiYuanPage;
		}
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%jiaShiYuanPage.getSize()==0){
				pagetotal = total / jiaShiYuanPage.getSize();
			}else {
				pagetotal = total / jiaShiYuanPage.getSize() + 1;
			}
		}
		if (pagetotal < jiaShiYuanPage.getCurrent()) {
			return jiaShiYuanPage;
		} else {
			jiaShiYuanPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (jiaShiYuanPage.getCurrent() > 1) {
				offsetNo = jiaShiYuanPage.getSize() * (jiaShiYuanPage.getCurrent() - 1);
			}
			jiaShiYuanPage.setTotal(total);
			jiaShiYuanPage.setOffsetNo(offsetNo);
			List<JiaShiYuanVO> jiaShiYuanVOList = jiaShiYuanMapper.selectPageList(jiaShiYuanPage);
			return (JiaShiYuanPage<JiaShiYuanVO>) jiaShiYuanPage.setRecords(jiaShiYuanVOList);
		}
	}

	@Override
	public JiaShiYuanVO selectByIds(String id) {
		return jiaShiYuanMapper.selectByIds(id);
	}

	@Override
	public JiaShiYuanVO selectByCardNo(String deptId,String cardNo,String driverNo){
		return jiaShiYuanMapper.selectByCardNo(deptId,cardNo,driverNo);
	}

	@Override
	public List<Vehicle> selectByCar(String jiashiyuanid) {
		return jiaShiYuanMapper.selectByCar(jiashiyuanid);
	}

	@Override
	public boolean updatePassWord(String password,String id) {
		return jiaShiYuanMapper.updatePassWord(password, id);
	}

	@Override
	public JiaShiYuanPage<JiaShiYuanVO> selectJVList(JiaShiYuanPage jiaShiYuanPage) {
		Integer total = jiaShiYuanMapper.selectJVTotal(jiaShiYuanPage);
		if(jiaShiYuanPage.getSize()==0){
			if(jiaShiYuanPage.getTotal()==0){
				jiaShiYuanPage.setTotal(total);
			}
			List<JiaShiYuanVO> vehlist = jiaShiYuanMapper.selectJVList(jiaShiYuanPage);
			jiaShiYuanPage.setRecords(vehlist);
			return jiaShiYuanPage;
		}
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%jiaShiYuanPage.getSize()==0){
				pagetotal = total / jiaShiYuanPage.getSize();
			}else {
				pagetotal = total / jiaShiYuanPage.getSize() + 1;
			}
		}
		if (pagetotal >= jiaShiYuanPage.getCurrent()) {
			jiaShiYuanPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (jiaShiYuanPage.getCurrent() > 1) {
				offsetNo = jiaShiYuanPage.getSize() * (jiaShiYuanPage.getCurrent() - 1);
			}
			jiaShiYuanPage.setTotal(total);
			jiaShiYuanPage.setOffsetNo(offsetNo);
			List<JiaShiYuanVO> vehlist = jiaShiYuanMapper.selectJVList(jiaShiYuanPage);
			jiaShiYuanPage.setRecords(vehlist);
		}
		return jiaShiYuanPage;
	}

	@Override
	public List<JiaShiYuan> jiaShiYuanList(String deptId) {
		return jiaShiYuanMapper.jiaShiYuanList(deptId);
	}

	@Override
	public boolean insertSelective(JiaShiYuan jiaShiYuan) {
		return jiaShiYuanMapper.insertSelective(jiaShiYuan);
	}

	@Override
	public JiaShiYuan getjiaShiYuan(String deptId, String jiashiyuanxingming, String shoujihaoma) {
		return jiaShiYuanMapper.getjiaShiYuan(deptId, jiashiyuanxingming, shoujihaoma);
	}

}
