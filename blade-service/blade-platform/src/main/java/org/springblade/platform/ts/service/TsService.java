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
package org.springblade.platform.ts.service;

import org.springblade.platform.cheliangguanli.entity.Vehicle;
import org.springblade.platform.cheliangguanli.vo.*;
import org.springblade.platform.jiashiyuan.vo.JiaShiYuanVO;
import org.springblade.platform.jiashiyuan.vo.ZhengjianshenyanVO;
import org.springblade.system.entity.Dept;

/**
 *  服务类
 *
 * @author hyp
 * @since 2019-05-16
 */
public interface TsService  {


	void insertDept(Dept all);

	void isnertOrg(Dept all);

	void insertVehicle(Vehicle all);

	Integer getDeptIdByDeptName(String deptName);

	void insertCheliangbaoxian(CheliangbaoxianVO all);

	void insertBaoxianmingxi(BaoxianxinxiVO all);

	void insertChelianganquan(ChelianganquanshebeiVO all);

	void insertCheliangdengjipingding(CheliangdengjipingdingVO all);

	void insertCheliangguanchejiancha(CheliangguanchejianchaVO all);

	void insertJiashiyuan(JiaShiYuanVO all);

	void insertCheliangweihu(CheliangweihuVO all);

	void insertCheliangyuejian(CheliangyuejianVO all);

	void insertCheliangjingying(CheliangjingyingVO all);

	void insertCheliangrenyuan(CheliangrenyuanbangdingVO all);

	void insertCheliangbaofei(CheliangbaofeiVO all);

	void insertguanchejianchamingxi(GuanchejianchaxinxiVO all);

	void insertcheliangnianshen(CheliangnianshenVO all);

	void insertZhengjianshenyan(ZhengjianshenyanVO all);
}
