package org.springblade.gps.feign;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import org.springblade.common.tool.GpsGuJiUtil;
import org.springblade.gps.entity.*;
import org.springblade.gps.service.IGpsPointDataService;
import org.springblade.gps.util.RedisOps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: SpringBlade
 * @description: BlogClientImpl
 **/
@RestController
@AllArgsConstructor
public class GpsPointDataClientImpl implements IGpsPointDataClient {

	private IGpsPointDataService gpsPointDataService;

	@Override
	@GetMapping(API_PREFIX + "/getVehiclePT2")
	public List<VehiclePT> getVehiclePT2(String company) {
		String s = RedisOps.get(company);
		String substring = s.substring(s.indexOf('['), s.lastIndexOf(']')+1);
		JSONArray objects = JSONUtil.parseArray(substring);
		List<VehiclePT> vehiclePTS = JSONUtil.<VehiclePT>toList(objects, VehiclePT.class);

		System.out.println(vehiclePTS);
		return vehiclePTS;
	}

	@Override
	@GetMapping(API_PREFIX+"/getvehiclestop")
	public List<VehicleStop> getvehiclestop(String company) {
		String time = DateUtil.yesterday().toString("yyyy-MM-dd");

		//获取企业下的车辆
		List<VehiclePTCompany> vehiclePTCompanies = gpsPointDataService.selectcompanyAll(company);
		List<VehicleStop> stops=new ArrayList<>();

		for(VehiclePTCompany data:vehiclePTCompanies){

			int times=0;
			List<LocalDateTime>  gpstime=new ArrayList<>();
			List<VehilePTData> list = gpsPointDataService.selectPointData(time + " 0:0:0", time + " 23:59:59", data.getId());
			if( list==null || list.size()==0){
				continue;
			}
			for(int i=0;i<list.size();i++){
				if(i+1==list.size()){
					break;
				}
				VehilePTData pt1 = list.get(i);
				VehilePTData pt2 = list.get(i + 1);
				if(pt1.getSpeed()==0 && pt2.getSpeed()==0){
					Long a1 = GpsGuJiUtil.DateForLong(pt1.getGpsTime());
					Long a2 = GpsGuJiUtil.DateForLong(pt2.getGpsTime());
					times+=(int) (a2-a1);
					gpstime.add(pt1.getGpsTime());
				}
			}
			if(gpstime!=null || gpstime.size()==0){
				VehicleStop vehicleStop=new VehicleStop();
				vehicleStop.setTimes(times);
				vehicleStop.setId(IdUtil.simpleUUID());
				vehicleStop.setVehid(data.getId());
				vehicleStop.setCompany(data.getCompany());
				vehicleStop.setPlate(data.getCph());
				vehicleStop.setColor(data.getPlatecolor());
				vehicleStop.setBegintime(gpstime.get(0));
				vehicleStop.setEndtime(gpstime.get(gpstime.size()-1));

				stops.add(vehicleStop);
			}
		}
		return  stops;

	}

	@Override
	@GetMapping(API_PREFIX+"/getgpsvehicle")
	public GpsVehicle getgpsvehicle(String cph, String color) {
		return gpsPointDataService.selectOneGpsVehicle(cph,color);
	}

	@Override
	@GetMapping(API_PREFIX+"/getvehicle")
	public List<VehiclePTCompany> getvehicle(String company) {
		List<VehiclePTCompany> vehiclePTCompanies = gpsPointDataService.selectcompanyAll(company);
		return vehiclePTCompanies;
	}
}
