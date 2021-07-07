package org.springblade.baojing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.baojing.entity.Alarmvehdailyreport;
import org.springblade.baojing.mapper.AlarmvehdailyreportMapper;
import org.springblade.baojing.page.AlarmvehPage;
import org.springblade.baojing.service.IAlarmvehdailyreportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlarmvehdailyreportServiceImpl extends ServiceImpl<AlarmvehdailyreportMapper,Alarmvehdailyreport> implements
	IAlarmvehdailyreportService {
	AlarmvehdailyreportMapper  alarmvehdailyreportMapper;




	@Override
	public AlarmvehPage<AlarmvehPage> chaosu(AlarmvehPage alarmPage) {
		Integer total =alarmvehdailyreportMapper.findpageList(alarmPage);
		System.out.println(total);
		if(alarmPage.getSize()==0){
			if(alarmPage.getTotal()==0){
				alarmPage.setTotal(total);
			}

			List<Alarmvehdailyreport> list=alarmvehdailyreportMapper.chaosu(alarmPage);
			alarmPage.setRecords(list);
			return alarmPage;

		}
		Integer pagetotal = 0;
		if (total > 0) {
			pagetotal = total / alarmPage.getSize() + 1;
		}
		if (pagetotal >= alarmPage.getCurrent() && pagetotal > 0) {
			alarmPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (alarmPage.getCurrent() > 1) {
				offsetNo = alarmPage.getSize() * (alarmPage.getCurrent() - 1);
			}

			alarmPage.setTotal(total);
			alarmPage.setOffsetNo(offsetNo);
			List<Alarmvehdailyreport> list=alarmvehdailyreportMapper.chaosu(alarmPage);
			alarmPage.setRecords(list);
		}

		return alarmPage;
	}

	@Override
	public AlarmvehPage<AlarmvehPage> pilao(AlarmvehPage alarmPage) {
		Integer total =alarmvehdailyreportMapper.findpageList(alarmPage);
		System.out.println(total);
		if(alarmPage.getSize()==0){
			if(alarmPage.getTotal()==0){
				alarmPage.setTotal(total);
			}

			List<Alarmvehdailyreport> list=alarmvehdailyreportMapper.chaosu(alarmPage);
			alarmPage.setRecords(list);
			return alarmPage;

		}
		Integer pagetotal = 0;
		if (total > 0) {
			pagetotal = total / alarmPage.getSize() + 1;
		}
		if (pagetotal >= alarmPage.getCurrent() && pagetotal > 0) {
			alarmPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (alarmPage.getCurrent() > 1) {
				offsetNo = alarmPage.getSize() * (alarmPage.getCurrent() - 1);
			}

			alarmPage.setTotal(total);
			alarmPage.setOffsetNo(offsetNo);
			List<Alarmvehdailyreport> list=alarmvehdailyreportMapper.pilao(alarmPage);
			alarmPage.setRecords(list);
		}

		return alarmPage;
	}

	@Override
	public AlarmvehPage<AlarmvehPage> zhudonganquan(AlarmvehPage alarmPage) {
		Integer total =alarmvehdailyreportMapper.findpageList(alarmPage);
		System.out.println(total);
		if(alarmPage.getSize()==0){
			if(alarmPage.getTotal()==0){
				alarmPage.setTotal(total);
			}

			List<Alarmvehdailyreport> list=alarmvehdailyreportMapper.chaosu(alarmPage);
			alarmPage.setRecords(list);
			return alarmPage;

		}
		Integer pagetotal = 0;
		if (total > 0) {
			pagetotal = total / alarmPage.getSize() + 1;
		}
		if (pagetotal >= alarmPage.getCurrent() && pagetotal > 0) {
			alarmPage.setPageTotal(pagetotal);
			Integer offsetNo = 0;
			if (alarmPage.getCurrent() > 1) {
				offsetNo = alarmPage.getSize() * (alarmPage.getCurrent() - 1);
			}

			alarmPage.setTotal(total);
			alarmPage.setOffsetNo(offsetNo);
			List<Alarmvehdailyreport> list=alarmvehdailyreportMapper.zhudonganquan(alarmPage);

			alarmPage.setRecords(list);
		}

		return alarmPage;
	}
}
