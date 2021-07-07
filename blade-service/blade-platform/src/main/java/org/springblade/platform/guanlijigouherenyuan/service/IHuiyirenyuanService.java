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
import org.springblade.platform.guanlijigouherenyuan.entity.Huiyirenyuan;
import org.springblade.platform.guanlijigouherenyuan.page.HuiyirenyuanPage;
import org.springblade.platform.guanlijigouherenyuan.vo.HuiyirenyuanVO;

/**
 *  服务类
 *
 * @author hyp
 * @since 2019-04-28
 */
public interface IHuiyirenyuanService extends IService<Huiyirenyuan> {

	/**
	 * 自定义分页
	 * @param huiyirenyuanPage
	 * @return
	 */
	HuiyirenyuanPage<HuiyirenyuanVO> selectPageQuery(HuiyirenyuanPage huiyirenyuanPage);
	/**
	 * 自定义删除
	 * @param id
	 * @return
	 */
	boolean deleteHuiYi(String id);

}
