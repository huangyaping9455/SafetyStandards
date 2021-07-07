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
package org.springblade.platform.shigu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.platform.shigu.entity.Shiguchuli;
import org.springblade.platform.shigu.page.ShiguchuliPage;
import org.springblade.platform.shigu.vo.ShiguchuliVO;

import java.util.List;

/**
 * 事故报告调查处理-事故处理 Mapper 接口
 *
 * @author hyp
 * @since 2019-04-29
 */
public interface ShiguchuliMapper extends BaseMapper<Shiguchuli> {

    /**
     * 自定义分页
     *
     * @param shiguchuliPage
     * @return
     */
    List<ShiguchuliVO> selectShiguchuliPage(ShiguchuliPage shiguchuliPage);

    /**
     * 统计
     *
     * @param shiguchuliPage
     * @return
     */
    int selectShiguchuliTotal(ShiguchuliPage shiguchuliPage);

    /**
     * 根据id获取数据
     * *@param id
     *
     * @return
     */
    ShiguchuliVO selectByKey(String id);

    /**
     * 自定义删除
     *
     * @param id
     * @return
     */
    boolean deleteShiguchuli(String id);


}
