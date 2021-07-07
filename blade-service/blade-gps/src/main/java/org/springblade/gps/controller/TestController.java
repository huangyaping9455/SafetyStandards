/**
 * Copyright (C), 2015-2021
 * FileName: TestController
 * Author:   呵呵哒
 * Date:     2021/3/27 17:37
 * Description:
 */
package org.springblade.gps.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.tool.api.R;
import org.springblade.gps.entity.BaseTestBaseCode;
import org.springblade.gps.entity.FileUploadShow;
import org.springblade.gps.service.IGpsPointDataService;
import org.springblade.gps.util.testUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @创建人 hyp
 * @创建时间 2021/3/27
 * @描述
 */
@RestController
@AllArgsConstructor
@RequestMapping("/gps/test")
@Api(value = "获取gps数据接口", tags = "获取gps数据接口")
public class TestController {

	private IGpsPointDataService gpsPointDataService;

		@GetMapping("/getASCListJson")
		@ApiOperation("AAAAAAAAAAAAAAAAAAAAA")
		public void getASCListJson() throws Exception {
			List<FileUploadShow> fileUploads = gpsPointDataService.selectFLODERASCListAll();
			//封装目录
			if(fileUploads != null){
				for(int j = 0;j<fileUploads.size();j++){
//					String oldurlss ="e:\\a\\b\\"+fileUploads.get(j).getFloder();
//					String newUrlss = "E:\\safesystemImg";
//					List<List<FileUploadShow>> averageAssignlists = testUtil.averageAssign(fileUploads, 10);
					//根据Floder查询对应的文件
					List<FileUploadShow> list = gpsPointDataService.selectListAll(fileUploads.get(j).getFloder());
					//服务器
					String oldurlss = "D:\\safesystem\\PlatServer\\FileSystem\\AttachFiles\\"+fileUploads.get(j).getFloder();
					String newUrlss = "D:\\safesystem\\safesystemImg";
					System.out.println(oldurlss);
					File f1=new File(oldurlss);
					File f2 = new File(newUrlss);
					File file = new File(oldurlss);
					File[] files = file.listFiles();
					if(files == null || files.length < 1){
						j=j+1;
					}else{
						for(int i=0;i<list.size();i++){
		//                	正式
							String fileSaveName = list.get(i).getFileSaveName();
							String fileName = list.get(i).getFileName();
							String newfileName = list.get(i).getFileSaveName()+"_"+list.get(i).getFileName();
	//					测试
//							fileSaveName = "1523345490409.at";
//							String fileName = "hhhh.doc";
//							String newfileName = "hhhh.doc";
							System.out.println(newfileName);
							System.out.println("hyphyp2222222222222222222222222222222222222222222222222");
						for (File img:files) {
							System.out.println(img);
							String wjname = img.getName();
							if(fileName !=null){
								if(fileSaveName.equals(wjname)){
									fileName = fileName.substring(fileName.lastIndexOf('.'));
									if(fileName.indexOf("doc") != -1 || fileName.indexOf("docx") != -1 ){
											testUtil.createdocNewFile(f1,f2,newfileName);
											i=i+1;
									}
									if(fileName.indexOf("jpg") != -1 || fileName.indexOf("png") != -1 || fileName.indexOf("JPG") != -1 || fileName.indexOf("jpeg") != -1 ){
										testUtil.createdjpgNewFile(f1,f2,newfileName);
										i=i+1;
									}
									if(fileName.indexOf("pdf") != -1){
										testUtil.createdpdfNewFile(f1,f2,newfileName);
										i=i+1;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@GetMapping("/getDESCListJson")
	@ApiOperation("BBBBBBBBBBBBBBBBBBBBBB")
	public void getDESCListJson() throws Exception {
		List<FileUploadShow> fileUploads = gpsPointDataService.selectFLODERDESCListAll();
		//封装目录
		if(fileUploads != null){
			for(int j = 0;j<fileUploads.size();j++){
//				String oldurlss ="e:\\a\\b\\"+fileUploads.get(j).getFloder();
//				String newUrlss = "E:\\safesystemImg";
//			    List<List<FileUploadShow>> averageAssignlists = testUtil.averageAssign(fileUploads, 10);
				//根据Floder查询对应的文件
				List<FileUploadShow> list = gpsPointDataService.selectListAll(fileUploads.get(j).getFloder());
				//服务器
				String oldurlss = "D:\\safesystem\\PlatServer\\FileSystem\\AttachFiles\\"+fileUploads.get(j).getFloder();
				String newUrlss = "D:\\safesystem\\safesystemImg";
				System.out.println(oldurlss);
				File f1=new File(oldurlss);
				File f2 = new File(newUrlss);
				File file = new File(oldurlss);
				File[] files = file.listFiles();
				if(files == null || files.length < 1){
					j=j+1;
				}else{
					for(int i=0;i<list.size();i++){
						//正式
						String fileSaveName = list.get(i).getFileSaveName();
						String fileName = list.get(i).getFileName();
						String newfileName = list.get(i).getFileSaveName()+"_"+list.get(i).getFileName();
						//测试
//						String fileSaveName = "1523345490409.at";
//						String fileName = "hhhh.doc";
//						String newfileName = "hhhh.doc";
						System.out.println(newfileName);
						System.out.println("hyphyp3333333333333333333333333333333333333333333333");
						for (File img:files) {
							System.out.println(img);
							String wjname = img.getName();
							if(fileName !=null){
								if(fileSaveName.equals(wjname)){
									fileName = fileName.substring(fileName.lastIndexOf('.'));
									if(fileName.indexOf("doc") != -1 || fileName.indexOf("docx") != -1 ){
										testUtil.createdocNewFile(f1,f2,newfileName);
										i=i+1;
									}
									if(fileName.indexOf("jpg") != -1 || fileName.indexOf("png") != -1 || fileName.indexOf("JPG") != -1 || fileName.indexOf("jpeg") != -1 ){
										testUtil.createdjpgNewFile(f1,f2,newfileName);
										i=i+1;
									}
									if(fileName.indexOf("pdf") != -1){
										testUtil.createdpdfNewFile(f1,f2,newfileName);
										i=i+1;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@GetMapping(value = "/getTestBaseCode")
	@ApiLog("测试SqlServer安全连接")
	@ApiOperation(value = "测试SqlServer安全连接", notes = "测试SqlServer安全连接", position = 2)
	public R getTestBaseCode() throws IOException {
		List<BaseTestBaseCode> baseTestBaseCodes = gpsPointDataService.selectTestBaseCode();
		return R.data(baseTestBaseCodes);
	}


}
