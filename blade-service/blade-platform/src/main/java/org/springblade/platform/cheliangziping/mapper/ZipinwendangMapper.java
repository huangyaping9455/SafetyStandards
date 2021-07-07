package org.springblade.platform.cheliangziping.mapper;


import org.springblade.platform.cheliangziping.entity.Zipingwenjian;
import org.springblade.platform.cheliangziping.page.ZipinwenjianPage;

import java.util.List;

/**
 * @author Lh
 * @description: TODO
 * @projectName SafetyStandards
 * @date 2019/9/316:14
 */
public interface ZipinwendangMapper {

	/**
	 * 统计
	 * @param
	 * @return
	 */
	int selectTotal(ZipinwenjianPage zipinjianchajieguoPage);
	/**
	 * 自定义分页
	 * @param
	 * @return
	 */
	List<Zipingwenjian> selectPageList(ZipinwenjianPage zipinjianchajieguoPage);
	/**
	 * 删除文档
	 */
	int deletawendang(String id);
	/**
	 * 新增文档
	 */
	int insertwendang(Zipingwenjian zIpingwenjian);
	/**
	 * 删除历史删除文件表记录
	 *
	 */
	int deletelishi(String id);

	/***
	 * 获取企业名称
	 * @param id
	 * @return
	 */
	String Deptname(String  id);
}
