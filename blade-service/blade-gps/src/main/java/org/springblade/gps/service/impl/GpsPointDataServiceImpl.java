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
package org.springblade.gps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springblade.common.tool.GpsGuJiUtil;
import org.springblade.gps.entity.*;
import org.springblade.gps.mapper.GpsPointDataMapper;
import org.springblade.gps.page.VehicleStopPage;
import org.springblade.gps.page.VehicleStopSumPage;
import org.springblade.gps.service.IGpsPointDataService;
import org.springblade.gps2.mapper.Gps2PointDataMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * gps点位数据 服务实现类
 *
 * @author hyp
 * @since 2019-05-17
 */
@Service
@Primary
@AllArgsConstructor
public class GpsPointDataServiceImpl extends ServiceImpl<GpsPointDataMapper, T> implements IGpsPointDataService {
    private GpsPointDataMapper gpsPointDataMapper;
    private Gps2PointDataMapper gps2PointDataMapper;

    @Override
    public List<VehilePTData> selectPointData(String beginTime, String endTime, String vehId) {
		List<VehilePTData> maps = gps2PointDataMapper.selectPointData(beginTime, endTime, vehId);
		if(maps==null || maps.size()==0){
            maps= gpsPointDataMapper.selectPointData(beginTime, endTime, vehId);
        }
		List<VehilePTData> mapss=new ArrayList<>();
        for(int i=0;i<maps.size();i++){
        	if(i+1==maps.size()){
        		break;
			}
			VehilePTData data = maps.get(i);
			VehilePTData data1 = maps.get(i + 1);
			if(GpsGuJiUtil.isloglat(data.getLongitude(),data.getLatitude(),
				data1.getLongitude(),data1.getLatitude(),data.getGpsTime(),data1.getGpsTime())){
				mapss.add(data);
			}
		}
		return mapss;
    }

	@Override
	public List<VehiclePTCompany> selectcompanyAll(String company) {
		List<VehiclePTCompany> list = gpsPointDataMapper.selectcompanyAll(company);
		if( list==null || list.size()==0 ){
			list=gps2PointDataMapper.selectcompanyAll(company);
		}
		return list;
	}

	@Override
	public GpsVehicle selectOneGpsVehicle(String cph, String color) {
		GpsVehicle gpsVehicle = gpsPointDataMapper.selectOneGpsVehicle(cph, color);
		if(gpsVehicle==null){
			gpsVehicle=gps2PointDataMapper.selectOneGpsVehicle(cph,color);
		}

		return gpsVehicle;
	}

	@Override
	public VehicleStopPage selectallofVehid(VehicleStopPage vehicleStopPage) {
		List<VehicleStopData> vehicleStopData = gpsPointDataMapper.selectallofVehid(vehicleStopPage);
		if(vehicleStopData==null || vehicleStopData.size()==0){
			vehicleStopData=gps2PointDataMapper.selectallofVehid(vehicleStopPage);
		}
		if(vehicleStopData==null || vehicleStopData.size()==0){
				return  vehicleStopPage;
		}else{

			List<VehicleStopData> list=new ArrayList<>();
			for (int i = 0; i <vehicleStopData.size() ; i++) {
				if(vehicleStopData.size()==1){
					list.add(vehicleStopData.get(0));

				}
				if(i+1==vehicleStopData.size()){
					continue;
				}
				VehicleStopData data = vehicleStopData.get(i);
				VehicleStopData data1 = vehicleStopData.get(i + 1);
				Long a = GpsGuJiUtil.DateForLong(data.getEndtime());
				Long b = GpsGuJiUtil.DateForLong(data1.getBegintime());
				if(b>a || vehicleStopData.size()==1) {
					list.add(data);
				}
			}
			if(vehicleStopPage.getSize()!=0 && vehicleStopPage.getCurrent()!=0){

				int page = vehicleStopPage.getCurrent();//相当于pageNo
				int count = vehicleStopPage.getSize();//相当于pageSize
				int size = list.size();
				//总条数
				vehicleStopPage.setTotal(size);
				int pageCount=(size+vehicleStopPage.getSize()-1)/vehicleStopPage.getSize();
				vehicleStopPage.setPageTotal(pageCount);
				int fromIndex = count * (page - 1);
				int toIndex = fromIndex + count;
				if (toIndex >= size) {
					toIndex = size;
				}
				if(page>pageCount+1){
					fromIndex=0;
					toIndex=0;
				}
				if(vehicleStopPage.getCurrent()>pageCount){
						return  vehicleStopPage;
				}

				vehicleStopPage.setRecords(list.subList(fromIndex, toIndex));

			}else {
				vehicleStopPage.setTotal(list.size());
				vehicleStopPage.setRecords(list);

			}
		}
		return vehicleStopPage;
	}

	@Override
	public VehicleStopSumPage selectallofCompany(VehicleStopSumPage vehicleStopSumPage) {
    	//获取企业下的所有车停车点
		List<VehicleStopData> list = gpsPointDataMapper.selectallofCompany(vehicleStopSumPage);
		if(list==null || list.size()==0){
			list=gps2PointDataMapper.selectallofCompany(vehicleStopSumPage);
		}
		if(list==null || list.size()==0){
				return  vehicleStopSumPage;
		}else {
			//获取企业下的所有车辆
			List<List<VehicleStopData>>  compaystopdata=new ArrayList<>();

			List<GpsPlateVehid> companyList = gpsPointDataMapper.selectPlateVehid(vehicleStopSumPage);
			if(companyList==null || companyList.size()==0){
				companyList=gps2PointDataMapper.selectPlateVehid(vehicleStopSumPage);
			}
			for(GpsPlateVehid plate:companyList){
				List<VehicleStopData> data=new ArrayList<>();
					for(VehicleStopData dataforstop:list){

							if(plate.getCph().equals(dataforstop.getCph())){
								data.add(dataforstop);
							}

					}
					compaystopdata.add(data);
			}
			//过滤掉没有车辆信息的车牌


			//存放过滤后的停车点位信息
			List<List<VehicleStopData>>  listcompany=new ArrayList<>();
			for(List<VehicleStopData> listdata:compaystopdata){
				List<VehicleStopData> cop=new ArrayList<>();
				for (int i = 0; i <listdata.size() ; i++) {
					if(listdata.size()==1){
							cop.add(list.get(0));

					}
					if(i+1==listdata.size()){
						continue;
					}
					VehicleStopData data = listdata.get(i);
					VehicleStopData data1 = listdata.get(i + 1);
					Long a = GpsGuJiUtil.DateForLong(data.getEndtime());
					Long b = GpsGuJiUtil.DateForLong(data1.getBegintime());
					if(b>a  || listdata.size()==1) {
							cop.add(data);
					}

				}
				listcompany.add(cop);

			}

			List<VehicleStopData> jieguo=new ArrayList<>();
			for(List<VehicleStopData> data:listcompany){
				int sum=0;
				VehicleStopData stopData=new VehicleStopData();
				for (VehicleStopData a:data) {
					sum+=Integer.valueOf(a.getTimes());
				}
					if(data==null || data.size()==0){
						break;
					}
				stopData.setTimes(String.valueOf(sum));
				stopData.setCph(data.get(0).getCph());
				stopData.setVehid(data.get(0).getVehid());
				stopData.setPlatecolor(data.get(0).getPlatecolor());
				stopData.setBegintime(data.get(0).getBegintime());
				stopData.setEndtime(data.get(data.size()-1).getEndtime());
				stopData.setStopcount(data.size());

				jieguo.add(stopData);
			}
			if(vehicleStopSumPage.getSize()!=0 && vehicleStopSumPage.getCurrent()!=0){

				int page = vehicleStopSumPage.getCurrent();//相当于pageNo
				int count = vehicleStopSumPage.getSize();//相当于pageSize
				int size = jieguo.size();
				//总条数
				vehicleStopSumPage.setTotal(size);
				int pageCount=(size+vehicleStopSumPage.getSize()-1)/vehicleStopSumPage.getSize();
				vehicleStopSumPage.setPageTotal(pageCount);
				int fromIndex = count * (page - 1);
				int toIndex = fromIndex + count;
				if (toIndex >= size) {
					toIndex = size;
				}
				if(page>pageCount+1){
					fromIndex=0;
					toIndex=0;
				}
				if(vehicleStopSumPage.getCurrent()>pageCount){
					return  vehicleStopSumPage;
				}

				vehicleStopSumPage.setRecords(jieguo.subList(fromIndex, toIndex));

			}else {
				vehicleStopSumPage.setTotal(jieguo.size());
				vehicleStopSumPage.setRecords(jieguo);

			}

		}
		return vehicleStopSumPage;
	}

//	//去除重复数据的方法
//	private static ArrayList<VehilePTData> removeDuplicateUser(List<VehilePTData> users) {
//		Set<VehilePTData> set = new TreeSet<VehilePTData>(new Comparator<VehilePTData>() {
//			@Override
//			public int compare(VehilePTData o1, VehilePTData o2) {
//				//字符串,则按照asicc码升序排列
//				return o1.getGpsmileage().compareTo(o2.getGpsmileage());
//			}
//		});
//		set.addAll(users);
//		return new ArrayList<VehilePTData>(set);
//	}

	@Override
	public List<VehicleNode> tree(String company){
		return gpsPointDataMapper.getVehiclesByCom(company);
	}

	@Override
	public List<FileUploadShow> selectListAll(String FLODER) {
		return gpsPointDataMapper.selectListAll(FLODER);
	}

	@Override
	public List<FileUploadShow> selectFLODERASCListAll() {
		return gpsPointDataMapper.selectFLODERASCListAll();
	}

	@Override
	public List<FileUploadShow> selectFLODERDESCListAll() {
		return gpsPointDataMapper.selectFLODERDESCListAll();
	}

	@Override
	public List<BaseTestBaseCode> selectTestBaseCode() {
		return gpsPointDataMapper.selectTestBaseCode();
	}

}
