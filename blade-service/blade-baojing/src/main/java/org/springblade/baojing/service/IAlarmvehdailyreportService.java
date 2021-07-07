package org.springblade.baojing.service;

import com.baomidou.mybatisplus.extension.service.IService;

import org.springblade.baojing.entity.Alarmvehdailyreport;
import org.springblade.baojing.page.AlarmvehPage;

/**
 * 超速报警 及处理 （日）
 */

public interface IAlarmvehdailyreportService extends IService<Alarmvehdailyreport> {



	AlarmvehPage chaosu(AlarmvehPage alarmvehPage);
	AlarmvehPage pilao(AlarmvehPage alarmvehPage);
	AlarmvehPage zhudonganquan(AlarmvehPage alarmvehPage);
}
