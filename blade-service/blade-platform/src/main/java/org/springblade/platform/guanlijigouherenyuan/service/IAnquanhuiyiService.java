/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 */
package org.springblade.platform.guanlijigouherenyuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.platform.guanlijigouherenyuan.entity.Anquanhuiyi;
import org.springblade.platform.guanlijigouherenyuan.page.AnquanhuiyiPage;
import org.springblade.platform.guanlijigouherenyuan.vo.AnquanhuiyiVO;

/**
 *  服务类
 */
public interface IAnquanhuiyiService extends IService<Anquanhuiyi> {

	/**
	 * 自定义 分页
	 * @param faGuiPage
	 * @return
	 */
	AnquanhuiyiPage<AnquanhuiyiVO> selectHuiYiPage(AnquanhuiyiPage faGuiPage);
	/**
	 * 自定义删除
	 * @param id
	 * @return
	 */
	boolean deleteHuiYi(String id);



}
