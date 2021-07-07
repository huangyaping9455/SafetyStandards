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
package org.springblade.platform.guanlijigouherenyuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.platform.guanlijigouherenyuan.entity.Organizations;
import org.springblade.platform.guanlijigouherenyuan.page.OrganizationsPage;
import org.springblade.platform.guanlijigouherenyuan.vo.OrganizationsVO;
import org.springblade.platform.zhengfu.entity.Organization;

import java.util.List;


/**
 *  服务类
 *
 * @author hyp
 * @since 2019-04-30
 */
public interface IOrganizationsService extends IService<Organizations> {

	/**
	 * 获取系统所有企业
	 * @return
	 */
	List<Organizations> selectAll();
	/**
	* @Description:
	* @Param: [id]
	* @return: org.springblade.anbiao.guanlijigouherenyuan.vo.OrganizationsVO
	* @Author: hyp
	* @Date: 2019-05-16
	*/
	OrganizationsVO selectByIds(String id);
	/**
	* @Description:
	* @Param: [id]
	*/
	boolean updateDel(String id);

	/**
	 * 自定义分页
	 * @param
	 * @return
	 */
	OrganizationsPage<OrganizationsVO> selectPageList(OrganizationsPage Page);
	/**
	 * 统计
	 * @param
	 * @return
	 */
	int selectTotal(OrganizationsPage Page);
	/**
	* @Description:
	* @Param: [deptId]
	*/
	Organizations selectByDeptId(String deptId);
	/**
	* @Description:
	*/
	boolean delByDeptId(String deptId);

	Organization selectZFRenyuan(String id);

	boolean insertSelective(OrganizationsVO organizationsVO);

	boolean insertOrganizationsSelective(Organizations organizations);


}
