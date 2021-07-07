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
package org.springblade.baojing.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.baojing.entity.AlarmBaojingTongji;
import org.springblade.baojing.entity.AlarmWeichuliType;
import org.springblade.baojing.entity.Alarmhandleresult;
import org.springblade.baojing.mapper.AlarmhandleresultMapper;
import org.springblade.baojing.service.IAlarmhandleresultService;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.*;

/**
 * 报警处理 服务实现类
 *
 * @author hyp
 * @since 2019-05-12
 */
@Service
@AllArgsConstructor
public class AlarmhandleresultServiceImpl extends ServiceImpl<AlarmhandleresultMapper, Alarmhandleresult> implements IAlarmhandleresultService {

    private AlarmhandleresultMapper alarmhandleresultMapper;


    @Override
    public List<Alarmhandleresult> selectIdList(Alarmhandleresult result) {
        return alarmhandleresultMapper.selectIdList(result);
    }

	@Override
	public boolean removeByAlmIds(Alarmhandleresult result) {
		return alarmhandleresultMapper.removeByAlmIds(result);
	}

	@Override
    public Alarmhandleresult selectChuliDetail(Integer baojingid) {
        return alarmhandleresultMapper.selectChuliDetail(baojingid);
    }

	@Override
	public List<Alarmhandleresult> selectBybaojin(String id) {
		return alarmhandleresultMapper.selectBybaojin(id);
	}

    @Override
    public int selectAlarmCountByIdsAndDetpId(String[] idss, String deptName) {
        return alarmhandleresultMapper.selectAlarmCountByIdsAndDetpId(idss,deptName);
    }

	@Override
	public List<AlarmWeichuliType> weichulitongji(Integer deptId) {
		String date = DateUtil.format(new Date(), "yyyy-MM-dd");

		List<AlarmWeichuliType> gpsweichuli = alarmhandleresultMapper.gpsweichuli(deptId, date,"true");
		//判断类型
		int index=0; //记录 不在线 无数据报警总数
		Iterator<AlarmWeichuliType> it = gpsweichuli.iterator();
		while(it.hasNext()){
			AlarmWeichuliType v = it.next();
			String alarmType=v.getAlarmType();
			int  number=v.getNumber();
			if("不定位报警".equals(alarmType) || "无数据报警".equals(alarmType)){
				index+=number;
				it.remove();
			}
			if("停运异常报警".equals(alarmType)){
				it.remove();

			}
			if("出区域报警(平台)".equals(alarmType)){
				it.remove();
			}
			if("区域内停车报警".equals(alarmType)){
				it.remove();
			}
			if("进区域报警(平台)".equals(alarmType)){
				it.remove();
			}
			if("速度异常报警".equals(alarmType)){
				it.remove();
			}
		}
		AlarmWeichuliType last=new AlarmWeichuliType();
		last.setNumber(index);
		last.setAlarmType("异常车辆报警");
		if(index!=0){
			gpsweichuli.add(last);
		}


		List<AlarmWeichuliType> zhudonganquanweichuli = alarmhandleresultMapper.zhudonganquanweichuli(deptId, date,"true");
//
//		for(Map<String, Object> item:gpsweichuli){
//			 String alarmtype=String.valueOf(item.get("AlarmType"));
//			 String index=String.valueOf(item.get("number"));
//			if("无数据报警".equals(alarmtype) || "不定位报警".equals()){
//
//			}
//
//		}
		gpsweichuli.addAll(zhudonganquanweichuli);
//		if(gpsweichuli==null || gpsweichuli.size()==0){
//			a.put("number",0);
//			Map<String,Object> a=new HashMap<>();
//			a.put("AlarmType","现在没有需要进行处理的报警");
//
//				  gpsweichuli.add(a);
//		}
		return gpsweichuli;
	}

	@Override
	public List<AlarmWeichuliType> cqweichulitongji(Integer deptId, String date) {
		//gps报警
		List<AlarmWeichuliType> gpsweichuli = alarmhandleresultMapper.gpsweichuli(deptId, date,"false");
		//判断类型
		int index=0;
		//记录 不在线 无数据报警总数
		Iterator<AlarmWeichuliType> it = gpsweichuli.iterator();
		while(it.hasNext()){
			AlarmWeichuliType v = it.next();
			String alarmType=v.getAlarmType();
			int  number=v.getNumber();
			if("不定位报警".equals(alarmType) || "无数据报警".equals(alarmType)){
				index+=number;
				it.remove();
			}
			if("停运异常报警".equals(alarmType)){
				it.remove();
			}
			if("出区域报警(平台)".equals(alarmType)){
				it.remove();
			}
			if("区域内停车报警".equals(alarmType)){
				it.remove();
			}
			if("进区域报警(平台)".equals(alarmType)){
				it.remove();
			}
			if("速度异常报警".equals(alarmType)){
				it.remove();
			}
		}
		AlarmWeichuliType last=new AlarmWeichuliType();
		last.setNumber(index);
		last.setAlarmType("异常车辆报警");
		if(index!=0){
			gpsweichuli.add(last);
		}
		//主动安全设备报警
		List<AlarmWeichuliType> zhudonganquanweichuli = alarmhandleresultMapper.zhudonganquanweichuli(deptId, date,"false");
		gpsweichuli.addAll(zhudonganquanweichuli);
		return gpsweichuli;
	}

	@Override
	public Integer updateribao(String cutoffTime, String company,String deptId) {
		DateTime dateTime = new DateTime(cutoffTime, DatePattern.NORM_DATETIME_FORMAT);

		String date = dateTime.toString("yyyy-MM-dd");
		int alarmcount=0; //报警总条数
		int chuli=0; //处理总条数
		//查询gps报警数 处理数
		AlarmBaojingTongji count = alarmhandleresultMapper.zhudongCount(company, date);
		alarmcount+=count.getAlarmCount();
		chuli+=count.getHandledCount();
		//查询主动安全报警数 处理数
		AlarmBaojingTongji count1 = alarmhandleresultMapper.alarmCount(company, date);
		alarmcount+=count1.getAlarmCount();
		chuli+=count1.getHandledCount();

		//查询日报id
		Integer ribao = alarmhandleresultMapper.selectOneribao(deptId, date);
		String id=String.valueOf(ribao);
		//处理率;
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(0);
		//处理率
		String chulilv=null;
		if(chuli>=alarmcount){
			chulilv="100%";
		}else {
			chulilv = numberFormat.format((float) chuli / (float) alarmcount * 100) + "%";
		}

		Integer integer = alarmhandleresultMapper.updateRibao(id, chulilv);

		return integer;
	}

	@Override
	public boolean updateAftertreatment(String twicechulixingshi, String twicechulimiaoshu, String twicechuliren, String twicechulishijian, Integer twicechulirenid, String twicefujian, String id) {
		return alarmhandleresultMapper.updateAftertreatment(twicechulixingshi, twicechulimiaoshu, twicechuliren, twicechulishijian, twicechulirenid, twicefujian, id);
	}


}
