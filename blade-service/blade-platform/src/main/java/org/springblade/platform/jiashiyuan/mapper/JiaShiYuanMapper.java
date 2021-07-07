package org.springblade.platform.jiashiyuan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springblade.platform.cheliangguanli.entity.Vehicle;
import org.springblade.platform.jiashiyuan.entity.JiaShiYuan;
import org.springblade.platform.jiashiyuan.page.JiaShiYuanPage;
import org.springblade.platform.jiashiyuan.vo.JiaShiYuanVO;

import java.util.List;

/**
 * Created by you on 2019/4/23.
 */
public interface JiaShiYuanMapper extends BaseMapper<JiaShiYuan> {

	boolean insertJSY(JiaShiYuan jiaShiYuan);

	boolean updateDel(String id);

	/**
	 * 自定义分页
	 * @param
	 * @return
	 */
	List<JiaShiYuanVO> selectPageList(JiaShiYuanPage jiaShiYuanPage);
	/**
	 * 统计
	 * @param
	 * @return
	 */
	int selectTotal(JiaShiYuanPage jiaShiYuanPage);

	JiaShiYuanVO selectByIds(String id);

	/**
	 * @Description: 根据身份证号查询
	 * @Param: cardNo
	 * @Date: 2020-06-23
	 */
	JiaShiYuanVO selectByCardNo(@Param("deptId") String deptId,@Param("cardNo") String cardNo,@Param("driverNo") String driverNo);

	/**
	 * 根据驾驶员ID查询绑定车辆
	 * @param jiashiyuanid
	 * @return
	 */
	List<Vehicle> selectByCar(String jiashiyuanid);

	/**
	 * /初始化登录密码
	 * @param password
	 * @param id
	 * @return
	 */
	boolean updatePassWord(String password,String id);

	/**
	 *查询驾驶员绑定车辆
	 * @param jiaShiYuanPage
	 * @return
	 */
	List<JiaShiYuanVO> selectJVList(JiaShiYuanPage jiaShiYuanPage);
	int selectJVTotal(JiaShiYuanPage jiaShiYuanPage);

	/**
	 * 根据企业ID获取驾驶员列表
	 * @param deptId
	 * @return
	 */
	List<JiaShiYuan> jiaShiYuanList(String deptId);

	/**
	 * 导入驾驶员基本信息
	 * @param jiaShiYuan
	 * @return
	 */
	boolean insertSelective(JiaShiYuan jiaShiYuan);

	/**
	 * 根据驾驶员姓名、联系电话、企业ID查询驾驶员信息
	 * @param deptId
	 * @param jiashiyuanxingming
	 * @param shoujihaoma
	 * @return
	 */
	JiaShiYuan getjiaShiYuan(@Param("deptId") String deptId,
							 @Param("jiashiyuanxingming") String jiashiyuanxingming,
							 @Param("shoujihaoma") String shoujihaoma);

}
