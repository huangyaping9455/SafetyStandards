package org.springblade.platform.cheliangziping.service;

import org.springblade.platform.cheliangziping.entity.ZipinZhouQI;
import org.springblade.platform.cheliangziping.entity.Zipingjieguocongbiao;


import java.util.List;

/**
 * @author Lh
 * @description: TODO
 * @projectName SafetyStandards
 * @date 2019/9/223:02
 */
public interface IZipingjianchajieguocongbiaoService {
		int insertAll (List<Zipingjieguocongbiao>  list);
		Integer delete(String id);
		Integer updatetable(List<Zipingjieguocongbiao> list);
		List<String>  selectallByID(String  id);
		List<ZipinZhouQI> zhouqi();


}
