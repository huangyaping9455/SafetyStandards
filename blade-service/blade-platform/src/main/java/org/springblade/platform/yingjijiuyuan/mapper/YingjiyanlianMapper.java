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
package org.springblade.platform.yingjijiuyuan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.platform.yingjijiuyuan.entity.Yingjiyanlian;
import org.springblade.platform.yingjijiuyuan.page.YingjiyanlianPage;
import org.springblade.platform.yingjijiuyuan.vo.YingjiyanlianVO;

import java.util.List;

/**
 * Mapper 接口
 * @author 呵呵哒
 */
public interface YingjiyanlianMapper extends BaseMapper<Yingjiyanlian> {

    /**
     * 自定义分页
     *
     * @param yingjiyanlianPage
     * @return
     */
    List<YingjiyanlianVO> selectYingjiyanlianPage(YingjiyanlianPage yingjiyanlianPage);

    /**
     * 统计
     *
     * @param yingjiyanlianPage
     * @return
     */
    int selectYingjiyanlianTotal(YingjiyanlianPage yingjiyanlianPage);

    /**
     * 根据id获取数据
     * *@param id
     *
     * @return
     */
    YingjiyanlianVO selectByKey(String id);

    /**
     * 自定义删除
     *
     * @param id
     * @return
     */
    boolean deleteYingjiyanlian(String id);

}
