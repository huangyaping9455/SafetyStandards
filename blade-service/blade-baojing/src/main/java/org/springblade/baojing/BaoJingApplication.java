package org.springblade.baojing;

import org.springblade.core.launch.BladeApplication;
import org.springblade.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 报警模块服务启动器
 * @author 呵呵哒
 */
@SpringCloudApplication
@EnableFeignClients(AppConstant.BASE_PACKAGES)
public class BaoJingApplication {

	public static void main(String[] args) {
		BladeApplication.run("blade-baojing", BaoJingApplication.class, args);
	}

}

