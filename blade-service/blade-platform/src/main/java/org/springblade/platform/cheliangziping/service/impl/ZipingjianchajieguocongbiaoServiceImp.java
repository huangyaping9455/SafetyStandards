package org.springblade.platform.cheliangziping.service.impl;

import lombok.AllArgsConstructor;

import org.springblade.platform.cheliangziping.entity.ZipinZhouQI;
import org.springblade.platform.cheliangziping.entity.Zipingjieguocongbiao;
import org.springblade.platform.cheliangziping.mapper.Zipingjianchajieguocongbiao;

import org.springblade.platform.cheliangziping.service.IZipingjianchajieguocongbiaoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lh
 * @description: TODO
 * @projectName SafetyStandards
 * @date 2019/9/223:02
 */
@Service
@AllArgsConstructor
public class ZipingjianchajieguocongbiaoServiceImp implements IZipingjianchajieguocongbiaoService {
		private Zipingjianchajieguocongbiao zipingjianchajieguocongbiao;


	@Override
	public int insertAll(List<Zipingjieguocongbiao>  list) {
		return zipingjianchajieguocongbiao.InsertAllcongbiao(list);
	}

	@Override
	public Integer delete(String id) {
		return zipingjianchajieguocongbiao.delete(id);
	}

	@Override
	public Integer updatetable(List<Zipingjieguocongbiao> list) {
		return zipingjianchajieguocongbiao.updatetable(list);
	}

	@Override
	public List<String> selectallByID(String id) {
		return zipingjianchajieguocongbiao.selectallByID(id);
	}

	@Override
	public List<ZipinZhouQI > zhouqi() {
		return zipingjianchajieguocongbiao.zhouqi();
	}

}
