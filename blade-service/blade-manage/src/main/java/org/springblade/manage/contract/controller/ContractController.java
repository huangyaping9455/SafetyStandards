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
package org.springblade.manage.contract.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.manage.contract.entity.Contract;
import org.springblade.manage.contract.page.ContractPage;
import org.springblade.manage.contract.service.IContractService;
import org.springblade.manage.contract.vo.ContractVO;
import org.springblade.upload.upload.feign.IFileUploadClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 *  控制器
 */
@RestController
@AllArgsConstructor
@RequestMapping("/manage/contract")
@Api(value = "经营-合同", tags = "经营-合同")
public class ContractController extends BladeController {

	private IContractService contractService;

	private IFileUploadClient fileUploadClient;


	@PostMapping("/list")
	@ApiLog("合同管理-分页")
	@ApiOperation(value = "合同管理-分页", notes = "传入contractPage", position = 1)
	public R<ContractPage<ContractVO>> list(@RequestBody ContractPage contractPage) {
		ContractPage<ContractVO> pages = contractService.selectAllPage(contractPage);
		return R.data(pages);
	}

	@GetMapping("/detail")
	@ApiLog("合同管理-详情")
	@ApiOperation(value = "合同管理-详情", notes = "传入id", position = 2)
	public R<Contract> detail(String id) {
		Contract detail = contractService.getById(id);
		return R.data(detail);
	}

	/**
	* 新增
	*/
	@PostMapping("/insert")
	@ApiOperation(value = "合同管理-新增", notes = "传入contract", position = 4)
	public R save(@Valid @RequestBody Contract contract, BladeUser user) {
		contract.setCreatetime(LocalDateTime.now().toString());
		contract.setCreateuser(user.getUserName());
		contract.setCaozuoshijian(LocalDateTime.now().toString());
		contract.setCaozuoren(user.getUserName());
		return R.status(contractService.save(contract));
	}

	/**
	* 修改
	*/
	@PostMapping("/update")
	@ApiOperation(value = "合同管理-修改", notes = "传入contract", position = 5)
	public R update(@Valid @RequestBody Contract contract,BladeUser user) {
		contract.setCaozuoshijian(LocalDateTime.now().toString());
		contract.setCaozuoren(user.getUserName());
		return R.status(contractService.updateById(contract));
	}

	/**
	* 删除
	*/
	@PostMapping("/del")
	@ApiOperation(value = "合同管理-删除", notes = "传入ids", position = 7)
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractService.removeByIds(Func.toIntList(ids)));
	}


}
