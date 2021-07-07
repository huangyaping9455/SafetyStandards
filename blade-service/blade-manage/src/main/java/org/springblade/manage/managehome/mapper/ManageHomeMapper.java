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
package org.springblade.manage.managehome.mapper;

import org.springblade.manage.managehome.entity.ManageHome;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author 呵呵哒
 */
public interface ManageHomeMapper {

	/**
	* @Description: 根据单位id获取数据 首页数字数据
	* @Param: [deptId]
	* @return: org.springblade.manage.managehome.entity.ManageHome
	* @Author: 呵呵哒
	*/
	ManageHome selectHome(String deptId,String month);
	/**
	* @Description: 根据单位id获取当月数据
	* @return: org.springblade.manage.managehome.entity.ManageHome
	* @Author: 呵呵哒
	*/
	List<ManageHome> selectyue(String deptId);
	/**
	* @Description: 根据单位id获取本年数据
	* @Param: [deptId]
	* @return: org.springblade.manage.managehome.entity.ManageHome
	* @Author: 呵呵哒
	* @Date: 2019-08-14
	*/
	List<ManageHome> selectnian(String deptId);
	/**
	* @Description: 根据单位id获取在运车辆信息
	* @Param: [deptId]
	* @return: java.util.List<org.springblade.manage.managehome.entity.ManageHome>
	* @Author: 呵呵哒
	*/
	List<ManageHome> selectzaiyun(String deptId);

	/**
	 * @Description: 根据单位id获取闲置车辆信息
	 * @Param: [deptId]
	 * @return: java.util.List<org.springblade.manage.managehome.entity.ManageHome>
	 * @Author: 呵呵哒
	 */
	List<ManageHome> selectxianzhi(String deptId);

}
