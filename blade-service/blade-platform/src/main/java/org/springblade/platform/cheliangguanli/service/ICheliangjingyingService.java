/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * limitations under the License.
 */
package org.springblade.platform.cheliangguanli.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.platform.cheliangguanli.entity.Cheliangjingying;
import org.springblade.platform.cheliangguanli.page.CheliangjingyingPage;
import org.springblade.platform.cheliangguanli.vo.CheliangjingyingVO;

/**
 *  服务类
 */
public interface ICheliangjingyingService extends IService<Cheliangjingying> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param cheliangjingying
	 * @return
	 */
	IPage<CheliangjingyingVO> selectCheliangjingyingPage(IPage<CheliangjingyingVO> page, CheliangjingyingVO cheliangjingying);

	CheliangjingyingVO selectByIds(String id);

	boolean updateDel(String id);

	/**
	 * 自定义分页
	 * @param
	 * @return
	 */
	CheliangjingyingPage<CheliangjingyingVO> selectPageList(CheliangjingyingPage Page);
}
