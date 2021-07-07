package org.springblade.platform.falvfagui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.platform.falvfagui.entity.FaGui;
import org.springblade.platform.falvfagui.page.FaGuiPage;
import org.springblade.platform.falvfagui.vo.FaGuiVo;

import java.util.List;

/**
* @Description:
* @return:
* @Author: 呵呵哒
*/
public interface FaGuiMapper  extends BaseMapper<FaGui> {

	/**
	 * 自定义分页
	 * @param faGuiPage
	 * @return
	 */
	List<FaGuiVo> selectFaGuiPage(FaGuiPage faGuiPage);
	/**
	 * 统计
	 * @param faGuiPage
	 * @return
	 */
	int selectFaGuiTotal(FaGuiPage faGuiPage);

	/**
	 * 自定义删除
	 * @param id
	 * @return
	 */
	boolean deleteFagui(String id);

}
