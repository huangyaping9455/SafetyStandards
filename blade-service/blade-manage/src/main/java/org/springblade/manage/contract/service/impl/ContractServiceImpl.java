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
package org.springblade.manage.contract.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.manage.contract.entity.Contract;
import org.springblade.manage.contract.mapper.ContractMapper;
import org.springblade.manage.contract.page.ContractPage;
import org.springblade.manage.contract.service.IContractService;
import org.springblade.manage.contract.vo.ContractVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 *
 * @author hyp
 * @since 2019-08-06
 */
@Service
@AllArgsConstructor
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements IContractService {

	private ContractMapper contractMapper;

	@Override
	public ContractPage<ContractVO> selectAllPage(ContractPage contractPage) {
		Integer total = contractMapper.selectAllTotal(contractPage);
		Integer pagetotal = 0;
		if (total > 0) {
			if(total%contractPage.getSize()==0){
				pagetotal = total / contractPage.getSize();
			}else {
				pagetotal = total / contractPage.getSize() + 1;
			}
		}
		if (pagetotal >= contractPage.getCurrent()) {
			contractPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (contractPage.getCurrent() > 1) {
				offsetNo = contractPage.getSize() * (contractPage.getCurrent() - 1);
			}
			contractPage.setTotal(total);
			contractPage.setOffsetNo(offsetNo);
			List<ContractVO> vehlist = contractMapper.selectAllPage(contractPage);
			contractPage.setRecords(vehlist);
		}
		return contractPage;
	}

}
